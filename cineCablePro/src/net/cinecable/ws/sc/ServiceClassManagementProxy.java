package net.cinecable.ws.sc;

public class ServiceClassManagementProxy implements net.cinecable.ws.sc.ServiceClassManagement {
  private String _endpoint = null;
  private net.cinecable.ws.sc.ServiceClassManagement serviceClassManagement = null;
  
  public ServiceClassManagementProxy() {
    _initServiceClassManagementProxy();
  }
  
  public ServiceClassManagementProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceClassManagementProxy();
  }
  
  private void _initServiceClassManagementProxy() {
    try {
      serviceClassManagement = (new net.cinecable.ws.sc.ServiceClassManagementServiceLocator()).getServiceClassManagement();
      if (serviceClassManagement != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceClassManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceClassManagement)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceClassManagement != null)
      ((javax.xml.rpc.Stub)serviceClassManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.cinecable.ws.sc.ServiceClassManagement getServiceClassManagement() {
    if (serviceClassManagement == null)
      _initServiceClassManagementProxy();
    return serviceClassManagement;
  }
  
  public net.cinecable.ws.sc.ServiceClassList getServiceClassNames(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.sc.WebServiceException{
    if (serviceClassManagement == null)
      _initServiceClassManagementProxy();
    return serviceClassManagement.getServiceClassNames(initiator);
  }
  
  
}