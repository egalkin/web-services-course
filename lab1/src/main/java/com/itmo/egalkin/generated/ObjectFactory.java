
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
    private final static QName _GetDevicesResponse_QNAME = new QName("http://service.egalkin.itmo.com/", "getDevicesResponse");

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
     * Create an instance of {@link GetDevicesResponse }
     * 
     */
    public GetDevicesResponse createGetDevicesResponse() {
        return new GetDevicesResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDevicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.egalkin.itmo.com/", name = "getDevicesResponse")
    public JAXBElement<GetDevicesResponse> createGetDevicesResponse(GetDevicesResponse value) {
        return new JAXBElement<GetDevicesResponse>(_GetDevicesResponse_QNAME, GetDevicesResponse.class, null, value);
    }

}
