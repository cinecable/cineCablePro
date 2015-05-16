/**
 * ServiceClassManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sc;

public interface ServiceClassManagement extends java.rmi.Remote {

    /**
     * Returns the list of Service Classes that are present in the
     * system.
     * 			Only Service Classes that User is allowed to access according to
     * ISP restrictions are returned.
     */
    public net.cinecable.ws.sc.ServiceClassList getServiceClassNames(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.sc.WebServiceException;
}
