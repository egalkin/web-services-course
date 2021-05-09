package com.itmo.egalkin.exception;

import com.itmo.egalkin.exception.fault.DefaultServiceFault;

import javax.xml.ws.WebFault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
@WebFault(faultBean = "com.itmo.egalkin.exception.fault.InvalidReleaseYearFault")
public class InvalidReleaseYearException extends ServiceException{

    public InvalidReleaseYearException(String message, DefaultServiceFault fault) {
        super(message, fault);
    }

    public InvalidReleaseYearException(String message, DefaultServiceFault fault, Throwable cause) {
        super(message, fault, cause);
    }
}
