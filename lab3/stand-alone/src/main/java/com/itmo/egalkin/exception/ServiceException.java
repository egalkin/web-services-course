package com.itmo.egalkin.exception;

import com.itmo.egalkin.exception.fault.DefaultServiceFault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
public class ServiceException extends Exception {

    private final DefaultServiceFault fault;

    public ServiceException(String message, DefaultServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ServiceException(String message, DefaultServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public DefaultServiceFault getFaultInfo() {
        return this.fault;
    }

}
