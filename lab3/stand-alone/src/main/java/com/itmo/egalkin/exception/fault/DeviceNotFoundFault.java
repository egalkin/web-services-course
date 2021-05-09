package com.itmo.egalkin.exception.fault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
public class DeviceNotFoundFault extends DefaultServiceFault {

    private static final String DEFAULT_MESSAGE = "There is no device with such id: %d";

    public static DeviceNotFoundFault buildInstance(long id) {
        DeviceNotFoundFault fault = new DeviceNotFoundFault();
        fault.setMessage(String.format(DEFAULT_MESSAGE, id));
        return fault;
    }

}
