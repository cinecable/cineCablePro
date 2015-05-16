/**
 * AccountManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws;

public class AccountManagementServiceLocator extends org.apache.axis.client.Service implements net.cinecable.ws.AccountManagementService  {

/**
 * Web service that provides ability to read and manipulate Account/Device
 * objects as well as to read ISP objects.
 *          <p>To save WSDL file you can view this page as a source code
 * in your browser and save it, or click "Save Link As…" from the Documentation
 * page.</p>
 *          <p>Note: Each API call should contain information about initiator
 * of the call. Initiator is represented by User containing user name
 * and encoded password.<br />
 *          In order to obtain User object call authenticate method of
 * SecurityManagement web service with IPS user name and not encoded
 * password.</p>
 */

    public AccountManagementServiceLocator() {
    }


    public AccountManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccountManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccountManagement
    private java.lang.String AccountManagement_address = "https://190.90.83.4:8443/ws/AccountManagement";

    public java.lang.String getAccountManagementAddress() {
        return AccountManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccountManagementWSDDServiceName = "AccountManagement";

    public java.lang.String getAccountManagementWSDDServiceName() {
        return AccountManagementWSDDServiceName;
    }

    public void setAccountManagementWSDDServiceName(java.lang.String name) {
        AccountManagementWSDDServiceName = name;
    }

    public net.cinecable.ws.AccountManagement getAccountManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccountManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccountManagement(endpoint);
    }

    public net.cinecable.ws.AccountManagement getAccountManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.cinecable.ws.AccountManagementSoapBindingStub _stub = new net.cinecable.ws.AccountManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getAccountManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccountManagementEndpointAddress(java.lang.String address) {
        AccountManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.cinecable.ws.AccountManagement.class.isAssignableFrom(serviceEndpointInterface)) {
                net.cinecable.ws.AccountManagementSoapBindingStub _stub = new net.cinecable.ws.AccountManagementSoapBindingStub(new java.net.URL(AccountManagement_address), this);
                _stub.setPortName(getAccountManagementWSDDServiceName());
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
        if ("AccountManagement".equals(inputPortName)) {
            return getAccountManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "AccountManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "AccountManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccountManagement".equals(portName)) {
            setAccountManagementEndpointAddress(address);
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
