package net.cinecable.ws.sec;

public class SecurityManagementProxy implements net.cinecable.ws.sec.SecurityManagement {
  private String _endpoint = null;
  private net.cinecable.ws.sec.SecurityManagement securityManagement = null;
  
  public SecurityManagementProxy() {
    _initSecurityManagementProxy();
  }
  
  public SecurityManagementProxy(String endpoint) {
    _endpoint = endpoint;
    _initSecurityManagementProxy();
  }
  
  private void _initSecurityManagementProxy() {
    try {
      securityManagement = (new net.cinecable.ws.sec.SecurityManagementServiceLocator()).getSecurityManagement();
      if (securityManagement != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)securityManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)securityManagement)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (securityManagement != null)
      ((javax.xml.rpc.Stub)securityManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.cinecable.ws.sec.SecurityManagement getSecurityManagement() {
    if (securityManagement == null)
      _initSecurityManagementProxy();
    return securityManagement;
  }
  
  public net.cinecable.ws.User authenticate(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException, net.cinecable.ws.sec.WebServiceException{
    if (securityManagement == null)
      _initSecurityManagementProxy();
    return securityManagement.authenticate(login, password);
  }
  
  
}