package com.itmo.egalkin.throttling;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author egalkin
 * @since 06.06.2021
 */
public class ThrottlingController {

    private static int MAX_ACTIVE_REQUEST_NUMBER = 500;

    private static AtomicLong requestCounter = new AtomicLong(0L);

    private ThrottlingController() {

    }

    public static Long getCurrentRequestNumber() {
        return requestCounter.get();
    }

    public static void incrementRequestNumber() {
        requestCounter.incrementAndGet();
    }

    public static void decrementRequestNumber() {
        requestCounter.decrementAndGet();
    }

    public static void restoreRequestCounter() {
        requestCounter.set(0L);
    }

    public static boolean canPerformNewRequest() {
        return requestCounter.get() < MAX_ACTIVE_REQUEST_NUMBER;
    }

}
