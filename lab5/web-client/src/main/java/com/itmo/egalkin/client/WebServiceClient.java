package com.itmo.egalkin.client;

import com.itmo.egalkin.model.Device;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * @author egalkin
 * @since 23.03.2021
 */
public class WebServiceClient {


    private static final String URL = "http://localhost:8081/rest/devices";

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder().build();
        getDevices(client, null, "1555.30", null, null, "2013");
    }


    private static void getDevices(Client client,
                                           String name,
                                           String price,
                                           String type,
                                           String available,
                                           String releaseYear) {
        WebTarget target = client.target(URL);
        if (name != null)
            target = target.queryParam("name", name);
        if (price != null)
            target = target.queryParam("price", price);
        if (type != null)
            target = target.queryParam("type", type);
        if (available != null)
            target = target.queryParam("available", available);
        if (releaseYear != null)
            target = target.queryParam("releaseYear", releaseYear);

        target
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .async()
            .get(new InvocationCallback<Response>() {
                @Override
                public void completed(Response response) {
                    String devices = response.readEntity(String.class);
                    System.out.println(devices);
                }

                @Override
                public void failed(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
    }


}
