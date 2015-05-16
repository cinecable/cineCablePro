/**
 * SecurityManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sec;

public interface SecurityManagement extends java.rmi.Remote {

    /**
     * Returns an object that identifies IPS Web Services user. User
     * must exist in IPS.
     *             <p>Operation takes the following parameters:<br />
     *             <table border="1" cellpadding="5" style="border: 1px solid
     * rgb(255, 255, 255);">
     * 	            <tr><th>Parameter</th><th>Description</th></tr>
     * 	            <tr><td>login</td><td>Plain text User Login</td></tr>
     * 	            <tr><td>password</td><td>Plain text User Password</td></tr>
     * 	        </table>
     * 	        </p>
     */
    public net.cinecable.ws.User authenticate(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException, net.cinecable.ws.sec.WebServiceException;
}
