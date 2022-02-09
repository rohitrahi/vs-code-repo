package com.vs.customer.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vs.customer.dto.CustomerDTO;
import com.vs.customer.dto.CustomersDTO;
import com.vs.customer.exception.CustomerNotFoundException;
import oracle.soda.*;
import oracle.soda.rdbms.OracleRDBMSClient;
import oracle.soda.rdbms.OracleRDBMSMetadataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.vs.customer.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Repository
public class CustomerSodaRepository {
    @Autowired
    OracleRDBMSClient client;

    @Autowired
    OracleDatabase database;

    @Autowired
    DatabaseConfig dbConfig;

    private Connection connection;

    private static String collectionName = "CUSTOMERS";

    private OracleCollection getCollection() throws OracleException, SQLException {
        connection = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getUserName(), dbConfig.getPassword());
        Properties props = new Properties();
        props.put("oracle.soda.sharedMetadataCache", "true");
        database = new OracleRDBMSClient(props).getDatabase(connection);

        OracleCollection col = database.openCollection(collectionName);
        if (col == null) {
            OracleRDBMSMetadataBuilder builder = client.createMetadataBuilder();
            OracleDocument collectionMetadata = builder.keyColumnAssignmentMethod("client").build();
            database.admin().createCollection(collectionName, collectionMetadata);
            col = database.openCollection(collectionName);
        }
        return col;
    }

    private void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    public CustomersDTO findAll() throws Throwable {
        CustomersDTO retVal = new CustomersDTO();
        OracleCollection col = getCollection();
        OracleCursor c = null;
        c = col.find().getCursor();
        while (c.hasNext()) {
            OracleDocument resultDoc = c.next();
            CustomerDTO customer = new ObjectMapper().readValue(resultDoc.getContentAsByteArray(), CustomerDTO.class);
            retVal.addCustomer(customer);
        }
        c.close();
        closeConnection();
        return retVal;
    }

    public CustomerDTO find(String id) throws Throwable {
        OracleCollection col = getCollection();
        OracleDocument resultDoc = col.findOne(id);
        CustomerDTO customer;
        if (resultDoc != null) {
            customer = new ObjectMapper().readValue(resultDoc.getContentAsByteArray(), CustomerDTO.class);
        } else {
            throw new CustomerNotFoundException("Customer with Id '"+ id +"' Not Found");
        }
        closeConnection();
        return customer;
    }

    public CustomerDTO create(CustomerDTO customer) throws Throwable {
        OracleCollection col = getCollection();
        OracleDocument document = database.createDocumentFrom(customer.getId(), new ObjectMapper().writeValueAsString(customer));
        col.insert(document);
        closeConnection();
        return customer;
    }

    public CustomerDTO update(CustomerDTO customer) throws Throwable {
        OracleCollection col = getCollection();
        OracleDocument document = database.createDocumentFrom(customer.getId(), new ObjectMapper().writeValueAsString(customer));
        col.save(document);
        closeConnection();
        return customer;
    }

    public int delete(String id) throws Throwable {
        OracleCollection col = getCollection();
        int retVal = col.find().key(id).remove();
        closeConnection();
        return retVal;
    }

}
