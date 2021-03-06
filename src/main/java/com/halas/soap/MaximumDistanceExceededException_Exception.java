package com.halas.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebFault(name = "MaximumDistanceExceededException", targetNamespace = "http://soap.service.halas.com/")
public class MaximumDistanceExceededException_Exception
        extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     */
    private MaximumDistanceExceededException faultInfo;

    public MaximumDistanceExceededException_Exception() {
        super();
    }

    /**
     * @param faultInfo
     * @param message
     */
    public MaximumDistanceExceededException_Exception(String message, MaximumDistanceExceededException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MaximumDistanceExceededException_Exception(String message, MaximumDistanceExceededException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * @return returns fault bean: com.halas.service.soap.MaximumDistanceExceededException
     */
    public MaximumDistanceExceededException getFaultInfo() {
        return faultInfo;
    }

}
