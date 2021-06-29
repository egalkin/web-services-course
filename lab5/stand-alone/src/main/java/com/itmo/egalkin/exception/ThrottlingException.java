package com.itmo.egalkin.exception;

/**
 * @author egalkin
 * @since 06.06.2021
 */
public class ThrottlingException extends RuntimeException {

    public ThrottlingException(String message) {
        super(message);
    }
}
