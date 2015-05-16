/**
 * AccountManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws;

public interface AccountManagementService extends javax.xml.rpc.Service {

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
    public java.lang.String getAccountManagementAddress();

    public net.cinecable.ws.AccountManagement getAccountManagement() throws javax.xml.rpc.ServiceException;

    public net.cinecable.ws.AccountManagement getAccountManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
