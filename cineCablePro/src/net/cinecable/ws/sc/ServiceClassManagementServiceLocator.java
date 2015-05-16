/**
 * ServiceClassManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sc;

public class ServiceClassManagementServiceLocator extends org.apache.axis.client.Service implements net.cinecable.ws.sc.ServiceClassManagementService {

/**
 * Web service that allows to work with Service Classes in IPS.
 *         <p>To save WSDL file you can view this page as a source code
 * in your browser and save it, or click "Save Link As…" from the Documentation
 * page.</p>
 *         <p>Note: Each API call must contain information about initiator
 * of the call.<br />
 *         Initiator is represented by User containing user name and
 * encoded password.<br />
 *         In order to obtain User object call authenticate method of
 * SecurityManagement web service with IPS user name and not encoded
 * password.</p>
 */

    public ServiceClassManagementServiceLocator() {
    }


    public ServiceClassManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceClassManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceClassManagement
    private java.lang.String ServiceClassManagement_address = "https://190.90.83.4:8443/ws/ServiceClassManagement";

    public java.lang.String getServiceClassManagementAddress() {
        return ServiceClassManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceClassManagementWSDDServiceName = "ServiceClassManagement";

    public java.lang.String getServiceClassManagementWSDDServiceName() {
        return ServiceClassManagementWSDDServiceName;
    }

    public void setServiceClassManagementWSDDServiceName(java.lang.String name) {
        ServiceClassManagementWSDDServiceName = name;
    }

    public net.cinecable.ws.sc.ServiceClassManagement getServiceClassManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceClassManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceClassManagement(endpoint);
    }

    public net.cinecable.ws.sc.ServiceClassManagement getServiceClassManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.cinecable.ws.sc.ServiceClassManagementSoapBindingStub _stub = new net.cinecable.ws.sc.ServiceClassManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getServiceClassManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceClassManagementEndpointAddress(java.lang.String address) {
        ServiceClassManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.cinecable.ws.sc.ServiceClassManagement.class.isAssignableFrom(serviceEndpointInterface)) {
                net.cinecable.ws.sc.ServiceClassManagementSoapBindingStub _stub = new net.cinecable.ws.sc.ServiceClassManagementSoapBindingStub(new java.net.URL(ServiceClassManagement_address), this);
                _stub.setPortName(getServiceClassManagementWSDDServiceName());
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
        if ("ServiceClassManagement".equals(inputPortName)) {
            return getServiceClassManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceClassManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceClassManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceClassManagement".equals(portName)) {
            setServiceClassManagementEndpointAddress(address);
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
