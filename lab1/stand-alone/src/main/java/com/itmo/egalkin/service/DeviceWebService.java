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
        List<Device> devices = dao.getDevices(name, price, type, available, releaseYear);
        return devices;
    }

}
