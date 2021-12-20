package com.vs.customer.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vs.customer.oci.OciHelperBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
public class DatabaseConfig {

    @Autowired
    OciHelperBean ociHelperBean;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    OracleRDBMSClient oracleRDBMSClient() {
        OracleRDBMSClient oracleRDBMSClient = new OracleRDBMSClient();
        return oracleRDBMSClient;
    }

    @Bean
    OracleDatabase oracleDatabase() throws OracleException, SQLException, Exception {

        String adbUsernameOcid = ociHelperBean.getAdbUsernameOcid();
        String adbPasswordOcid = ociHelperBean.getAdbPasswordOcid();

        if(adbUsernameOcid != null && adbUsernameOcid.startsWith("ocid1.vaultsecret") && adbPasswordOcid != null && adbPasswordOcid.startsWith("ocid1.vaultsecret")) {
            try {
                String adbUserName = ociHelperBean.getSecretValue(adbUsernameOcid);
                String adbPassword = ociHelperBean.getSecretValue(adbPasswordOcid);
                if(adbUserName != null && adbPassword != null) {
                    this.userName = adbUserName;
                    this.password = adbPassword;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Unable to get  OCI Secrets");
            }
        }


        Connection connection = DriverManager.getConnection(dbUrl, userName, password);
        Properties props = new Properties();
        props.put("oracle.soda.sharedMetadataCache", "true");
        OracleDatabase oracleDatabase = new OracleRDBMSClient(props).getDatabase(connection);
        return oracleDatabase;
    }

}

