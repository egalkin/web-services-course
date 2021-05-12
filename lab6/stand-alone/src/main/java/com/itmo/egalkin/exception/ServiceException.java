package com.itmo.egalkin.exception;

/**
 * @author egalkin
 * @since 12.05.2021
 *
 * Marker class for ExceptionMapper implementation
 *
 */
public class ServiceException extends Exception {

    public ServiceException(String defaultMessage) {
        super(defaultMessage);
    }

}
