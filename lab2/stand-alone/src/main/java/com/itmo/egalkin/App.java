package com.itmo.egalkin;

import com.itmo.egalkin.service.DeviceWebService;

import javax.xml.ws.Endpoint;

/**
 * @author egalkin
 * @since 22.03.2021
 */
public class App {

    public static void main(String[] args) {
        String url = "http://0.0.0.0:8081/DeviceService";
        Endpoint.publish(url, new DeviceWebService());
    }

}
