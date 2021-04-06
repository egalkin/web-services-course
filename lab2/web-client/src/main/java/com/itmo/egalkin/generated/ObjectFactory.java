
package com.itmo.egalkin.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.itmo.egalkin.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDevices_QNAME = new QName("http://service.egalkin.itmo.com/", "getDevices");
    private final static QName _UpdateDevice_QNAME = new QName("http://service.egalkin.itmo.com/", "updateDevice");
    private final static QName _UpdateDeviceResponse_QNAME = new QName("http://service.egalkin.itmo.com/", "updateDeviceResponse");
    private final static QName _DeleteDevice_QNAME = new QName("http://service.egalkin.itmo.com/", "deleteDevice");
    private final static QName _GetDevicesResponse_QNAME = new QName("http://service.egalkin.itmo.com/", "getDevicesResponse");
    private final static QName _CreateDevice_QNAME = new QName("http://service.egalkin.itmo.com/", "createDevice");
    private final static QName _DeleteDeviceResponse_QNAME = new QName("http://service.egalkin.itmo.com/", "deleteDeviceResponse");
    private final static QName _CreateDeviceResponse_QNAME = new QName("http://service.egalkin.itmo.com/", "createDeviceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.itmo.egalkin.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDevices }
     * 
     */
    public GetDevices createGetDevices() {
        return new GetDevices();
    }

    /**
     * Create an instance of {@link UpdateDevice }
     * 
     */
    public UpdateDevice createUpdateDevice() {
        return new UpdateDevice();
    }

    /**
     * Create an instance of {@link UpdateDeviceResponse }
     * 
     */
    public UpdateDeviceResponse createUpdateDeviceResponse() {
        return new UpdateDeviceResponse();
    }

    /**
     * Create an instance of {@link DeleteDevice }
     * 
     */
    public DeleteDevice createDeleteDevice() {
        return new DeleteDevice();
    }

    /**
     * Create an instance of {@link GetDevicesResponse }
     * 
     */
    public GetDevicesResponse createGetDevicesResponse() {
        return new GetDevicesResponse();
    }

    /**
     * Create an instance of {@link CreateDevice }
     * 
     */
    public CreateDevice createCreateDevice() {
        return new CreateDevice();
    }

    /**
     * Create an instance of {@link DeleteDeviceResponse }
     * 
     */
    public DeleteDeviceResponse createDeleteDeviceResponse() {
        return new DeleteDeviceResponse();
    }

    /**
     * Create an instance of {@link CreateDeviceResponse }
     * 
     */
    public CreateDeviceResponse createCreateDeviceResponse() {
        return new CreateDeviceResponse();
    }

    /**
     * Create an instance of {@link Device }
     * 
     */
    public Device createDevice() {
        return new Device();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "getDevices")
    public JAXBElement<GetDevices> createGetDevices(GetDevices value) {
        return new JAXBElement<GetDevices>(_GetDevices_QNAME, GetDevices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDevice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "updateDevice")
    public JAXBElement<UpdateDevice> createUpdateDevice(UpdateDevice value) {
        return new JAXBElement<UpdateDevice>(_UpdateDevice_QNAME, UpdateDevice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateDeviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "updateDeviceResponse")
    public JAXBElement<UpdateDeviceResponse> createUpdateDeviceResponse(UpdateDeviceResponse value) {
        return new JAXBElement<UpdateDeviceResponse>(_UpdateDeviceResponse_QNAME, UpdateDeviceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDevice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "deleteDevice")
    public JAXBElement<DeleteDevice> createDeleteDevice(DeleteDevice value) {
        return new JAXBElement<DeleteDevice>(_DeleteDevice_QNAME, DeleteDevice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "getDevicesResponse")
    public JAXBElement<GetDevicesResponse> createGetDevicesResponse(GetDevicesResponse value) {
        return new JAXBElement<GetDevicesResponse>(_GetDevicesResponse_QNAME, GetDevicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDevice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "createDevice")
    public JAXBElement<CreateDevice> createCreateDevice(CreateDevice value) {
        return new JAXBElement<CreateDevice>(_CreateDevice_QNAME, CreateDevice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDeviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "deleteDeviceResponse")
    public JAXBElement<DeleteDeviceResponse> createDeleteDeviceResponse(DeleteDeviceResponse value) {
        return new JAXBElement<DeleteDeviceResponse>(_DeleteDeviceResponse_QNAME, DeleteDeviceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDeviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "createDeviceResponse")
    public JAXBElement<CreateDeviceResponse> createCreateDeviceResponse(CreateDeviceResponse value) {
        return new JAXBElement<CreateDeviceResponse>(_CreateDeviceResponse_QNAME, CreateDeviceResponse.class, null, value);
    }

}
