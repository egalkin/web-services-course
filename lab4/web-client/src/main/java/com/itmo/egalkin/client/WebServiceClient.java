package com.itmo.egalkin.client;

import com.itmo.egalkin.model.Device;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author egalkin
 * @since 23.03.2021
 */
public class WebServiceClient {


    private static final String URL = "http://localhost:8081/rest/devices";

    public static void main(String[] args) {
        Client client = Client.create();
        printList(getDevices(client, null, null, null, null, null));
        System.out.println();
        printList(getDevices(client, null, "299", null, null, "2013"));

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
