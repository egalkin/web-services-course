package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "DeviceService")
public class DeviceWebService {

    @WebMethod(operationName = "getDevices")
    public List<Device> getDevicesWith(String name,
                                       String price,
                                       String type,
                                       String available,
                                       String releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getDevices(name, price, type, available, releaseYear);
    }

    @WebMethod(operationName = "createDevice")
    public long createDevice(String name,
                             double price,
                             String type,
                             boolean available,
                             int releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createDevice(name, price, type, available, releaseYear);
    }

    @WebMethod(operationName = "updateDevice")
    public String updateDevice(long id,
                               String name,
                               double price,
                               String type,
                               boolean available,
                               int releaseYear) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updateDevice(id, name, price, type, available, releaseYear);
    }

    @WebMethod(operationName = "deleteDevice")
    public String deleteDevice(long id) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deleteDevice(id);
    }

}
