/**
 * SecurityManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sec;

public class SecurityManagementServiceLocator extends org.apache.axis.client.Service implements net.cinecable.ws.sec.SecurityManagementService {

/**
 * Web service for authenticating users within IPS based on plain
 * text login and password.
 *         <p>To save WSDL file you can view this page as a source code
 * in your browser and save it, or click "Save Link As…" from the IPS
 * Documentation page.</p>
 */

    public SecurityManagementServiceLocator() {
    }


    public SecurityManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SecurityManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SecurityManagement
    private java.lang.String SecurityManagement_address = "https://190.90.83.4:8443/ws/SecurityManagement";

    public java.lang.String getSecurityManagementAddress() {
        return SecurityManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SecurityManagementWSDDServiceName = "SecurityManagement";

    public java.lang.String getSecurityManagementWSDDServiceName() {
        return SecurityManagementWSDDServiceName;
    }

    public void setSecurityManagementWSDDServiceName(java.lang.String name) {
        SecurityManagementWSDDServiceName = name;
    }

    public net.cinecable.ws.sec.SecurityManagement getSecurityManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SecurityManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSecurityManagement(endpoint);
    }

    public net.cinecable.ws.sec.SecurityManagement getSecurityManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.cinecable.ws.sec.SecurityManagementSoapBindingStub _stub = new net.cinecable.ws.sec.SecurityManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getSecurityManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSecurityManagementEndpointAddress(java.lang.String address) {
        SecurityManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.cinecable.ws.sec.SecurityManagement.class.isAssignableFrom(serviceEndpointInterface)) {
                net.cinecable.ws.sec.SecurityManagementSoapBindingStub _stub = new net.cinecable.ws.sec.SecurityManagementSoapBindingStub(new java.net.URL(SecurityManagement_address), this);
                _stub.setPortName(getSecurityManagementWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SecurityManagement".equals(inputPortName)) {
            return getSecurityManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "SecurityManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "SecurityManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SecurityManagement".equals(portName)) {
            setSecurityManagementEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
