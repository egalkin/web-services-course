package com.itmo.egalkin.client;

import com.itmo.egalkin.model.Device;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author egalkin
 * @since 23.03.2021
 */
public class WebServiceClient {


    private static final String URL = "http://localhost:8081/rest/devices";

    public static void main(String[] args) {
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);
        System.out.println(createDevice(client, "Xbox four", 300.55, "Gaming console", true, 2020));
        printList(getDevices(client, null, "299", null, null, "2013"));
    }

    private static String createDevice(Client client,
                                       String name,
                                       double price,
                                       String type,
                                       boolean available,
                                       int releaseYear) {
        WebResource webResource = client.resource(URL);
        Map<String,Object> postBody = new HashMap<>();
        postBody.put("name", name);
        postBody.put("price", -price);
        postBody.put("type", type);
        postBody.put("available", available);
        postBody.put("releaseYear", releaseYear);
        ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN)
            .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, postBody);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException(response.getEntity(String.class));
        }
        GenericType<String> genericType = new GenericType<String>() {
        };
        return response.getEntity(genericType);
    }

    private static List<Device> getDevices(Client client,
                                           String name,
                                           String price,
                                           String type,
                                           String available,
                                           String releaseYear) {
        WebResource webResource = client.resource(URL);
        if (name != null)
            webResource = webResource.queryParam("name", name);
        if (price != null)
            webResource = webResource.queryParam("price", price);
        if (type != null)
            webResource = webResource.queryParam("type", type);
        if (available != null)
            webResource = webResource.queryParam("available", available);
        if (releaseYear != null)
            webResource = webResource.queryParam("releaseYear", releaseYear);

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        GenericType<List<Device>> genericType = new GenericType<List<Device>>() {
        };
        return response.getEntity(genericType);
    }

    private static void printList(List<Device> persons) {
        for (Device person : persons) {
            System.out.println(person);
        }
    }

    public static String deviceToString(Device device) {
        return "Device{" + "name=" + device.getName() +
            ", price=" + device.getPrice() +
            ", type=" + device.getType() +
            ", available=" + device.isAvailable() +
            ", release year=" + device.getReleaseYear() +
            '}';
    }

}
