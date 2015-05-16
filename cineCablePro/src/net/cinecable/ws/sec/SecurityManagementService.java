/**
 * SecurityManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sec;

public interface SecurityManagementService extends javax.xml.rpc.Service {

/**
 * Web service for authenticating users within IPS based on plain
 * text login and password.
 *         <p>To save WSDL file you can view this page as a source code
 * in your browser and save it, or click "Save Link As…" from the IPS
 * Documentation page.</p>
 */
    public java.lang.String getSecurityManagementAddress();

    public net.cinecable.ws.sec.SecurityManagement getSecurityManagement() throws javax.xml.rpc.ServiceException;

    public net.cinecable.ws.sec.SecurityManagement getSecurityManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
