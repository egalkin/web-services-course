package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/devices")
@Produces({MediaType.APPLICATION_JSON})
public class DeviceResource {

    @GET
    public List<Device> getDevicesWith(@QueryParam("name") String name,
                                       @QueryParam("price") String price,
                                       @QueryParam("type") String type,
                                       @QueryParam("available") String available,
                                       @QueryParam("releaseYear") String releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getDevices(name, price, type, available, releaseYear);
    }

}
