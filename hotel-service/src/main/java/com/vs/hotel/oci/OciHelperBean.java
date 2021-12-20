package com.vs.hotel.oci;

import com.oracle.bmc.auth.BasicAuthenticationDetailsProvider;
import com.oracle.bmc.auth.InstancePrincipalsAuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.secrets.SecretsClient;
import com.oracle.bmc.secrets.model.Base64SecretBundleContentDetails;
import com.oracle.bmc.secrets.requests.GetSecretBundleRequest;
import com.oracle.bmc.secrets.responses.GetSecretBundleResponse;
import com.oracle.bmc.ons.NotificationDataPlaneClient;
import com.oracle.bmc.ons.model.MessageDetails;
import com.oracle.bmc.ons.requests.PublishMessageRequest;
import com.oracle.bmc.streaming.StreamClient;
import com.oracle.bmc.streaming.model.PutMessagesDetails;
import com.oracle.bmc.streaming.model.PutMessagesDetailsEntry;
import com.oracle.bmc.streaming.model.PutMessagesResultEntry;
import com.oracle.bmc.streaming.requests.PutMessagesRequest;
import com.oracle.bmc.streaming.responses.PutMessagesResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Base64;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class OciHelperBean {

    @Value("${oci.tenant.region}")
    private String ociRegion;

    @Value("${oci.tenant.config}")
    private String config;

    @Value("${oci.tenant.adb-username-ocid}")
    private String adbUsernameOcid;

    @Value("${oci.tenant.adb-password-ocid}")
    private String adbPasswordOcid;

    @Value("${oci.tenant.stream-ocid}")
    private String streamOcid;

    @Value("${oci.tenant.message-endpoint}")
    private String messageEndpoint;

    @Value("${oci.tenant.topic-ocid}")
    private String topicOcid;


    private BasicAuthenticationDetailsProvider provider = null;

    private IdentityClient identityClient = null;

    public OciHelperBean() {
    }

    public String getAdbUsernameOcid() {
        return adbUsernameOcid;
    }

    public String getAdbPasswordOcid() {
        return adbPasswordOcid;
    }


    private void initProvider()  {
        try {
            if("local".equals(config)) {
                provider = new ConfigFileAuthenticationDetailsProvider("~/.oci/config", "DEFAULT");
            } else {
                provider  = InstancePrincipalsAuthenticationDetailsProvider.builder().build();
                identityClient = new IdentityClient(provider);
           }
        } catch (Exception e) {
             System.out.println("Unable to connect to OCI");
        }
    }


    public String getSecretValue(String secretOcid) {
        initProvider();
        if(provider == null) return null;

        SecretsClient secretsClient = new SecretsClient(provider);
        secretsClient.setRegion(ociRegion);

        // create get secret bundle request
        GetSecretBundleRequest getSecretBundleRequest = GetSecretBundleRequest
                .builder()
                .secretId(secretOcid)
                .stage(GetSecretBundleRequest.Stage.Current)
                .build();

        // get the secret
        GetSecretBundleResponse getSecretBundleResponse = secretsClient.
                getSecretBundle(getSecretBundleRequest);

        // get the bundle content details
        Base64SecretBundleContentDetails base64SecretBundleContentDetails =
                (Base64SecretBundleContentDetails) getSecretBundleResponse.
                        getSecretBundle().getSecretBundleContent();

        // decode the encoded secret
        byte[] secretValueDecoded = Base64.decodeBase64(base64SecretBundleContentDetails.getContent());

        if (identityClient != null) {
            identityClient.close();
        }

        return new String(secretValueDecoded);
    }

    public void produceMessage(String key, String value) {
        initProvider();
        if(provider == null) return;
        StreamClient streamClient = StreamClient.builder().endpoint(messageEndpoint).build(provider);

        List<PutMessagesDetailsEntry> messages = new ArrayList<>();
        messages.add(
                PutMessagesDetailsEntry.builder()
                    .key(String.format(key).getBytes(UTF_8))
                    .value(String.format(value).getBytes(UTF_8))
                    .build());

        PutMessagesDetails messagesDetails =
                PutMessagesDetails.builder().messages(messages).build();

        PutMessagesRequest putRequest =
                PutMessagesRequest.builder()
                        .streamId(streamOcid)
                        .putMessagesDetails(messagesDetails)
                        .build();
        PutMessagesResponse putResponse = streamClient.putMessages(putRequest);
        for (PutMessagesResultEntry entry : putResponse.getPutMessagesResult().getEntries()) {
            if (StringUtils.isNotBlank(entry.getError())) {
                System.out.println(
                        String.format("Error(%s): %s", entry.getError(), entry.getErrorMessage()));
            } else {
                System.out.println(
                        String.format(
                                "Published message to partition %s, offset %s.",
                                entry.getPartition(),
                                entry.getOffset()));
            }
        }
        if (identityClient != null) {
            identityClient.close();
        }

    }

    public void sendNotification(String title, String body) {
        initProvider();
        if(provider == null) return;

        NotificationDataPlaneClient client = NotificationDataPlaneClient.builder().region(ociRegion)
                .build(provider);

        MessageDetails messageDetails = MessageDetails.builder().title(title).body(body).build();

        PublishMessageRequest publishMessageRequest = PublishMessageRequest.builder()
                .messageDetails( messageDetails )
                .topicId(topicOcid)
                .build();

        client.publishMessage( publishMessageRequest );

        if (identityClient != null) {
            identityClient.close();
        }
    }
}

