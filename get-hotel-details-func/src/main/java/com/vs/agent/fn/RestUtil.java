package com.vs.agent.fn;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestUtil {
    public static String getJsonRequest(String endPoint) {
        Client client = Client.create();

        WebResource webResource = client.resource(endPoint);

        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP error code : " + response.getStatus());
        }

        return response.getEntity(String.class);
    }

    public static String postJsonRequest(String endPoint, String jsonBody) {
        Client client = Client.create();

        WebResource webResource = client.resource(endPoint);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonBody);

        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP error code : " + response.getStatus());
        }

        return response.getEntity(String.class);
    }
}

