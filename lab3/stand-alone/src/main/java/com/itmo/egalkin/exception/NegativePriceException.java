package com.itmo.egalkin.exception;

import com.itmo.egalkin.exception.fault.DefaultServiceFault;

import javax.xml.ws.WebFault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
@WebFault(faultBean = "com.itmo.egalkin.exception.fault.NegativePriceFault")
public class NegativePriceException extends ServiceException {

    public NegativePriceException(String message, DefaultServiceFault fault) {
        super(message, fault);
    }

    public NegativePriceException(String message, DefaultServiceFault fault, Throwable cause) {
        super(message, fault, cause);
    }

}
