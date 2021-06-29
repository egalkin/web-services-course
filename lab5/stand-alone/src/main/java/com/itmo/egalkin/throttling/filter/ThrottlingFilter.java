package com.itmo.egalkin.throttling.filter;

import com.itmo.egalkin.exception.ThrottlingException;
import com.itmo.egalkin.throttling.ThrottlingController;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.ext.Provider;

/**
 * @author egalkin
 * @since 06.06.2021
 */
@Provider
public class ThrottlingFilter implements ContainerRequestFilter {
    @Override
    public ContainerRequest filter(ContainerRequest request) throws ThrottlingException {
        if (ThrottlingController.canPerformNewRequest()) {
            ThrottlingController.incrementRequestNumber();
            return request;
        } else {
            throw new ThrottlingException("Max request number exceeds");
        }
    }
}
