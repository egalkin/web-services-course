package com.itmo.egalkin.exception.fault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
public class IllegalNameFault extends DefaultServiceFault {

    private static final String DEFAULT_MESSAGE = "Device name cannot be null or empty";

    public static IllegalNameFault buildInstance() {
        IllegalNameFault fault = new IllegalNameFault();
        fault.setMessage(DEFAULT_MESSAGE);
        return fault;
    }

}
