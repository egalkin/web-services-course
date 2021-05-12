package com.itmo.egalkin.exception;

/**
 * @author egalkin
 * @since 12.05.2021
 */
public class NegativePriceException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "price can't be negative. Value %,.02f. is invalid";

    public NegativePriceException(double price) {
        super(String.format(DEFAULT_MESSAGE, price));
    }
}
