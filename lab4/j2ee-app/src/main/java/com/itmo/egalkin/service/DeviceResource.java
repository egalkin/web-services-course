package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class DeviceResource {

    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;


    @GET
    public List<Device> getDevicesWith(@QueryParam("name") String name,
                                       @QueryParam("price") String price,
                                       @QueryParam("type") String type,
                                       @QueryParam("available") String available,
                                       @QueryParam("releaseYear") String releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        return dao.getDevices(name, price, type, available, releaseYear);
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DeviceResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
