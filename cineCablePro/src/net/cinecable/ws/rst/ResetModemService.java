/**
 * ResetModemService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.rst;

public interface ResetModemService extends javax.xml.rpc.Service {

/**
 * Web service for resetting devices.
 *         <p>To save WSDL file without HTML layouting, click "Save Link
 * As…" from the Documentation page.</p>
 *         <p>Note: Each API call should contain information about initiator
 * of the call.<br />
 *            Initiator is represented by User containing user name and
 * encoded password.<br />
 *            In order to obtain User object call authenticate method
 * of SecurityManagement web service with IPS user name and not encoded
 * password.
 *         </p>
 */
    public java.lang.String getResetModemAddress();

    public net.cinecable.ws.rst.ResetModem getResetModem() throws javax.xml.rpc.ServiceException;

    public net.cinecable.ws.rst.ResetModem getResetModem(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
