package net.cinecable.ws.rst;

public class ResetModemProxy implements net.cinecable.ws.rst.ResetModem {
  private String _endpoint = null;
  private net.cinecable.ws.rst.ResetModem resetModem = null;
  
  public ResetModemProxy() {
    _initResetModemProxy();
  }
  
  public ResetModemProxy(String endpoint) {
    _endpoint = endpoint;
    _initResetModemProxy();
  }
  
  private void _initResetModemProxy() {
    try {
      resetModem = (new net.cinecable.ws.rst.ResetModemServiceLocator()).getResetModem();
      if (resetModem != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)resetModem)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)resetModem)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (resetModem != null)
      ((javax.xml.rpc.Stub)resetModem)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.cinecable.ws.rst.ResetModem getResetModem() {
    if (resetModem == null)
      _initResetModemProxy();
    return resetModem;
  }
  
  public void resetModem(java.lang.String mac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.rst.WebServiceException{
    if (resetModem == null)
      _initResetModemProxy();
    resetModem.resetModem(mac, initiator);
  }
  
  
}