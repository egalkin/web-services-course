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
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(serviceName = "DeviceService")
public class DeviceWebService {

    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;

    @WebMethod(operationName = "getDevices")
    public List<Device> getDevicesWith(String name,
                                       String price,
                                       String type,
                                       String available,
                                       String releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<Device> devices = dao.getDevices(name, price, type, available, releaseYear);
        return devices;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DeviceWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
