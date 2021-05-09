
package com.itmo.egalkin.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "IllegalNameException", targetNamespace = "http://service.egalkin.itmo.com/")
public class IllegalNameException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private DefaultServiceFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IllegalNameException(String message, DefaultServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IllegalNameException(String message, DefaultServiceFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.itmo.egalkin.generated.DefaultServiceFault
     */
    public DefaultServiceFault getFaultInfo() {
        return faultInfo;
    }

}
