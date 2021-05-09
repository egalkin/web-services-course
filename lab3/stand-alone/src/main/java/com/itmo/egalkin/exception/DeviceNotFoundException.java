package com.itmo.egalkin.exception;

import com.itmo.egalkin.exception.fault.DefaultServiceFault;

import javax.xml.ws.WebFault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
@WebFault(faultBean = "")
public class DeviceNotFoundException extends ServiceException {

    public DeviceNotFoundException(String message, DefaultServiceFault fault) {
        super(message, fault);
    }

    public DeviceNotFoundException(String message, DefaultServiceFault fault, Throwable cause) {
        super(message, fault, cause);
    }
}
