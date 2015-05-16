package net.cinecable.ws;

public class AccountManagementProxy implements net.cinecable.ws.AccountManagement {
  private String _endpoint = null;
  private net.cinecable.ws.AccountManagement accountManagement = null;
  
  public AccountManagementProxy() {
    _initAccountManagementProxy();
  }
  
  public AccountManagementProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccountManagementProxy();
  }
  
  private void _initAccountManagementProxy() {
    try {
      accountManagement = (new net.cinecable.ws.AccountManagementServiceLocator()).getAccountManagement();
      if (accountManagement != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)accountManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)accountManagement)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (accountManagement != null)
      ((javax.xml.rpc.Stub)accountManagement)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.cinecable.ws.AccountManagement getAccountManagement() {
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement;
  }
  
  public net.cinecable.ws.ISP getIspById(java.lang.Integer id, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getIspById(id, initiator);
  }
  
  public net.cinecable.ws.ISP getIspByName(java.lang.String ispName, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getIspByName(ispName, initiator);
  }
  
  public net.cinecable.ws.ISP[] getMyIsps(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getMyIsps(initiator);
  }
  
  public java.lang.String[] getAllDwellingTypes(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getAllDwellingTypes(initiator);
  }
  
  public net.cinecable.ws.Account createAccount(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.createAccount(acc, initiator);
  }
  
  public void updateAccountById(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.updateAccountById(acc, initiator);
  }
  
  public void updateAccountByNumber(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.updateAccountByNumber(acc, initiator);
  }
  
  public void changeAccountServiceableAddress(java.lang.String accountNumber, java.lang.String externalAccountID, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.changeAccountServiceableAddress(accountNumber, externalAccountID, initiator);
  }
  
  public void lockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.lockDevice(mac, changeComment, initiator);
  }
  
  public void unlockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.unlockDevice(mac, changeComment, initiator);
  }
  
  public net.cinecable.ws.Account getAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getAccountByNumber(accountNumber, initiator);
  }
  
  public net.cinecable.ws.Account getAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getAccountById(accountId, initiator);
  }
  
  public net.cinecable.ws.Account[] searchAccountsByExternalAccountId(java.lang.String externalAccountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.searchAccountsByExternalAccountId(externalAccountId, initiator);
  }
  
  public void activateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.activateAccount(accountNumber, changeComment, initiator);
  }
  
  public void deactivateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deactivateAccount(accountNumber, changeComment, initiator);
  }
  
  public void addDeviceToAccountById(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.addDeviceToAccountById(device, initiator);
  }
  
  public void addDeviceToAccountByNumber(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.addDeviceToAccountByNumber(device, initiator);
  }
  
  public net.cinecable.ws.Device getDeviceById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getDeviceById(deviceId, accountId, initiator);
  }
  
  public net.cinecable.ws.Device[] getDevicesForAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getDevicesForAccountById(accountId, initiator);
  }
  
  public net.cinecable.ws.Device[] getDevicesForAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getDevicesForAccountByNumber(accountNumber, initiator);
  }
  
  public net.cinecable.ws.Device getDeviceByMac(java.lang.String mac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getDeviceByMac(mac, initiator);
  }
  
  public void updateDevice(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.updateDevice(device, initiator);
  }
  
  public void deleteDeviceFromAccountByMac(java.lang.String accountNumber, java.lang.String deviceMac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deleteDeviceFromAccountByMac(accountNumber, deviceMac, initiator);
  }
  
  public void deleteDeviceFromAccountById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deleteDeviceFromAccountById(deviceId, accountId, initiator);
  }
  
  public net.cinecable.ws.ServiceableAddressResult[] serviceableAddressSearch(net.cinecable.ws.ServiceableSearchParams params, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.serviceableAddressSearch(params, initiator);
  }
  
  public void activateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.activateDevice(macAddress, changeComment, initiator);
  }
  
  public void deactivateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deactivateDevice(macAddress, changeComment, initiator);
  }
  
  public net.cinecable.ws.WsMibObject[] getAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    return accountManagement.getAllDeviceMibObjects(macAddress, initiator);
  }
  
  public void addDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.addDeviceMibObject(macAddress, mibObject, initiator);
  }
  
  public void deleteAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deleteAllDeviceMibObjects(macAddress, initiator);
  }
  
  public void deleteDeviceMibObject(java.lang.String macAddress, java.lang.String oid, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.deleteDeviceMibObject(macAddress, oid, initiator);
  }
  
  public void updateDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException{
    if (accountManagement == null)
      _initAccountManagementProxy();
    accountManagement.updateDeviceMibObject(macAddress, mibObject, initiator);
  }
  
  
}