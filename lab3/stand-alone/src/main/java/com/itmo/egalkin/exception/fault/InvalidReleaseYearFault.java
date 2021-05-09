package com.itmo.egalkin.exception.fault;

import java.util.Calendar;

/**
 * @author egalkin
 * @since 09.05.2021
 */
public class InvalidReleaseYearFault extends DefaultServiceFault {

    private static final String DEFAULT_MESSAGE = "Provided year is invalid. Value %d is out of range: [1950, %d]";
    private static final Calendar calendar = Calendar.getInstance();

    public static InvalidReleaseYearFault buildInstance(int year) {
        InvalidReleaseYearFault fault = new InvalidReleaseYearFault();
        fault.setMessage(String.format(DEFAULT_MESSAGE, year, calendar.get(Calendar.YEAR)));
        return fault;
    }

}
