package com.itmo.egalkin.exception;

/**
 * @author egalkin
 * @since 12.05.2021
 */
public class DeviceNotFoundException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "There is no device with such id: %d";

    public DeviceNotFoundException(long id) {
        super(String.format(DEFAULT_MESSAGE, id));
    }
}
