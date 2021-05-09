package com.itmo.egalkin.client;

import com.itmo.egalkin.generated.Device;
import com.itmo.egalkin.generated.DeviceService;
import com.itmo.egalkin.generated.IllegalNameException;
import com.itmo.egalkin.generated.InvalidReleaseYearException;
import com.itmo.egalkin.generated.NegativePriceException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author egalkin
 * @since 23.03.2021
 */
public class WebServiceClient {

    private static final String[] PARAM_NAMES = new String[] {"name", "price", "type", "available", "releaseYear"};

    // expect params in string form: param:value
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String name = args[0];
        double price = Double.parseDouble(args[1]);
        String type = args[2];
        boolean available = Boolean.parseBoolean(args[3]);
        int releaseYear = Integer.parseInt(args[4]);
        DeviceService deviceService = new DeviceService();

        try {
            deviceService.getDeviceWebServicePort().createDevice(name, price,type, available,releaseYear);
        } catch (IllegalNameException ex) {
            System.out.println(ex.getFaultInfo().getMessage());
        } catch (NegativePriceException ex) {
            System.out.println(ex.getFaultInfo().getMessage());
        } catch (InvalidReleaseYearException ex) {
            System.out.println(ex.getFaultInfo().getMessage());
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
