package com.itmo.egalkin.service;

/**
 * @author egalkin
 * @since 22.03.2021
 */

import com.itmo.egalkin.exception.DeviceNotFoundException;
import com.itmo.egalkin.exception.IllegalNameException;
import com.itmo.egalkin.exception.InvalidReleaseYearException;
import com.itmo.egalkin.exception.NegativePriceException;
import com.itmo.egalkin.model.Device;
import com.itmo.egalkin.model.PostgreSQLDAO;

import java.awt.Image;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

@MTOM
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

    @WebMethod(operationName = "getDevice")
    public Device getDevice(long id) throws DeviceNotFoundException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getDevice(id);
    }

    @WebMethod(operationName = "createDevice")
    public long createDevice(String name,
                             double price,
                             String type,
                             boolean available,
                             int releaseYear) throws NegativePriceException, InvalidReleaseYearException, IllegalNameException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.createDevice(name, price, type, available, releaseYear);
    }

    @WebMethod(operationName = "updateDevice")
    public String updateDevice(long id,
                               String name,
                               double price,
                               String type,
                               boolean available,
                               int releaseYear) throws NegativePriceException, InvalidReleaseYearException, IllegalNameException, DeviceNotFoundException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updateDevice(id, name, price, type, available, releaseYear);
    }

    @WebMethod(operationName = "deleteDevice")
    public String deleteDevice(long id) throws DeviceNotFoundException {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.deleteDevice(id);
    }

    @WebMethod(operationName = "uploadDeviceImageMock")
    public String uploadDeviceImageMock(@XmlMimeType("image/*")Image data) {
        if (data != null) {
            return "Device image uploaded successfully";
        }
        throw new WebServiceException("Upload failed");
    }

}
