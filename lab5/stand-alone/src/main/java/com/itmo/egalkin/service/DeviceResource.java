package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;
import com.itmo.egalkin.throttling.ThrottlingController;

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
        List<Device> result = dao.getDevices(name, price, type, available, releaseYear);
        ThrottlingController.decrementRequestNumber();
        return result;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createDevice(Device device) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String result = dao.createDevice(device);
        ThrottlingController.decrementRequestNumber();
        return result;
    }

    @Path("/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String updateDevice(@PathParam("id") Long id, Device device) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String result = dao.updateDevice(id, device);
        ThrottlingController.decrementRequestNumber();
        return result;
    }

    @Path("/{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDevice(@PathParam("id") Long id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String result = dao.deleteDevice(id);
        ThrottlingController.decrementRequestNumber();
        return result;
    }

}
