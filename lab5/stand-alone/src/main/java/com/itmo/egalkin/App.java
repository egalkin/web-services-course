package com.itmo.egalkin;

import com.itmo.egalkin.service.DeviceResource;
import com.itmo.egalkin.throttling.ThrottlingController;
import com.itmo.egalkin.throttling.filter.ThrottlingFilter;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;


import java.io.IOException;
import java.net.URI;

/**
 * @author egalkin
 * @since 22.03.2021
 */
public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8081/rest/");

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            ResourceConfig resourceConfig = new PackagesResourceConfig(DeviceResource.class.getPackage().getName(),
                ThrottlingFilter.class.getPackage().getName());
            resourceConfig.getProperties().put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS,
                new String[] {"com.itmo.egalkin.throttling.filter.ThrottlingFilter"});
            server = GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
            ThrottlingController.restoreRequestCounter();
            server.start();
            System.in.read();
            stopServer(server);
        } catch (IOException e) {
            e.printStackTrace();
            stopServer(server);
        }
    }
    private static void stopServer(HttpServer server) {
        if (server != null)
            server.stop();
    }

}
