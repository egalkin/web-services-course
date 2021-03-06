package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.exception.DeviceNotFoundException;
import com.itmo.egalkin.exception.IllegalStringFieldValueException;
import com.itmo.egalkin.exception.InvalidReleaseYearException;
import com.itmo.egalkin.exception.NegativePriceException;
import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/devices")
public class DeviceResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Device> getDevicesWith(@QueryParam("name") String name,
                                       @QueryParam("price") String price,
                                       @QueryParam("type") String type,
                                       @QueryParam("available") String available,
                                       @QueryParam("releaseYear") String releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getDevices(name, price, type, available, releaseYear);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Device getDevice(@PathParam("id") Long id) throws DeviceNotFoundException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getDevice(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDevice(Device device) throws InvalidReleaseYearException, IllegalStringFieldValueException, NegativePriceException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createDevice(device);
    }

    @Path("/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String updateDevice(@PathParam("id") Long id, Device device) throws NegativePriceException, IllegalStringFieldValueException, DeviceNotFoundException, InvalidReleaseYearException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updateDevice(id, device);
    }

    @Path("/{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDevice(@PathParam("id") Long id) throws DeviceNotFoundException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deleteDevice(id);
    }

}
