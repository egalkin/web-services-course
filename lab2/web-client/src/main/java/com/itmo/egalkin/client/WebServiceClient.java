package com.itmo.egalkin.client;

import com.itmo.egalkin.generated.Device;
import com.itmo.egalkin.generated.DeviceService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author egalkin
 * @since 23.03.2021
 */
public class WebServiceClient {

    private static final String[] PARAM_NAMES = new String[] {"name", "price", "type", "available", "releaseYear"};

    // expect params in string form: param:value
    public static void main(String[] args) throws MalformedURLException {
        List<String> parsedParams = parseArgs(args);
        URL url = new URL("http://localhost:8081/DeviceService?wsdl");

        DeviceService deviceService = new DeviceService(url);

        List<Device> devices = deviceService.getDeviceWebServicePort().getDevices(
            parsedParams.get(0),
            parsedParams.get(1),
            parsedParams.get(2),
            parsedParams.get(3),
            parsedParams.get(4)
        );
        for (Device device : devices) {
            System.out.println(deviceToString(device));
        }
        System.out.println("Total devices: " + devices.size());
    }

    private static List<String> parseArgs(String... args) {
        HashMap<String, String> namesToValues = new HashMap<>();
        List<String> parsedArgs = new ArrayList<>();
        for (String arg : args) {
            String[] splitedArgs = arg.split(":");
            if (splitedArgs.length == 2) {
                namesToValues.put(splitedArgs[0], splitedArgs[1]);
            }
        }
        for (String paramName : PARAM_NAMES) {
            parsedArgs.add(namesToValues.getOrDefault(paramName, null));
        }
        return parsedArgs;
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
