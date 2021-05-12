package com.itmo.egalkin.exception;

import java.util.Calendar;

/**
 * @author egalkin
 * @since 12.05.2021
 */
public class InvalidReleaseYearException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "Provided year is invalid. Value %d is out of range: [1950, %d]";
    private static final Calendar calendar = Calendar.getInstance();

    public InvalidReleaseYearException(int year) {
        super(String.format(DEFAULT_MESSAGE, year, calendar.get(Calendar.YEAR)));
    }
}
