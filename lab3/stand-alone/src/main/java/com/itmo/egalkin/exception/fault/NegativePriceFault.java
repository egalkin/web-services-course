package com.itmo.egalkin.exception.fault;

/**
 * @author egalkin
 * @since 09.05.2021
 */
public class NegativePriceFault extends DefaultServiceFault {

    private static final String DEFAULT_MESSAGE = "price can't be negative. Value %,.02f. is invalid";

    public static NegativePriceFault buildInstance(double price) {
        NegativePriceFault fault = new NegativePriceFault();
        fault.setMessage(String.format(DEFAULT_MESSAGE, price));
        return fault;
    }

}
