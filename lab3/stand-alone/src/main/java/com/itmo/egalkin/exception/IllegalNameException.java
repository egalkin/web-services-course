package com.itmo.egalkin.exception;

import com.itmo.egalkin.exception.fault.DefaultServiceFault;

import javax.xml.ws.WebFault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
@WebFault(faultBean = "com.itmo.egalkin.exception.fault.IllegalNameFault")
public class IllegalNameException extends ServiceException {

    public IllegalNameException(String message, DefaultServiceFault fault) {
        super(message, fault);
    }

    public IllegalNameException(String message, DefaultServiceFault fault, Throwable cause) {
        super(message, fault, cause);
    }
}
