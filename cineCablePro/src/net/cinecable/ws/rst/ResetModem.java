/**
 * ResetModem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.rst;

public interface ResetModem extends java.rmi.Remote {

    /**
     * Resets a Device. <b>Reset Device</b> permission is required
     * for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>mac</td><td>MAC address of the Device to be reset</td></tr>
     * 			</table>
     * 			</p>
     */
    public void resetModem(java.lang.String mac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.rst.WebServiceException;
}
