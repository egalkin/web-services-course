package com.itmo.egalkin.exception;

/**
 * @author egalkin
 * @since 12.05.2021
 */
public class IllegalStringFieldValueException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Device %s cannot be null or empty";

    public IllegalStringFieldValueException(String fieldName) {
        super(String.format(DEFAULT_MESSAGE, fieldName));
    }
}
