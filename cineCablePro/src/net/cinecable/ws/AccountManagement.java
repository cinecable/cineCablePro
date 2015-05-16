/**
 * AccountManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws;

public interface AccountManagement extends java.rmi.Remote {

    /**
     * Get ISP object by ID. <b>Access Global ISP List</b> permission
     * is required for this operation to succeed.
     *             <p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>id</td><td>The ID of the ISP to search for</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.ISP getIspById(java.lang.Integer id, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Get ISP object by ISP Name. <b>Access Global ISP List</b> permission
     * is required for this operation to succeed.
     *             <p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>ispName</td><td>The Name of the ISP to search for</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.ISP getIspByName(java.lang.String ispName, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Get the list of the ISPs User has access to. <b>Access Global
     * ISP List</b> permission is required for this operation to succeed.
     */
    public net.cinecable.ws.ISP[] getMyIsps(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Get the list of Dwelling Types available to User.
     *             <p>Access <b>Special Serviceable Address Elements</b>
     * permission must be granted to get full Dwelling Types list.<br />
     *             Without this permission granted only reduced set of Dwelling
     * Types will be returned, Restricted Dwelling Types won't be included
     * in the list.</p>
     */
    public java.lang.String[] getAllDwellingTypes(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Adds new Account to IPS. <b>Access Accounts</b>, <b>Manage
     * Accounts</b> permissions are required for this operation to succeed.
     * 			<p>Key Account object fields:</p>
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     * 				<tr><th>Field Name</th><th>Field Type</th><th>Comments</th></tr>
     * 				<tr><td>accountId</td><td>Ignored</td><td>field value is generated
     * by IPS automatically</td></tr>
     * 				<tr><td>accountNumber</td><td nowrap="">Mandatory/Ignored</td><td>If
     * Automatic Account Number Generation feature is enabled, accountNumber
     * field value is generated automatically by IPS and will be ignored.<br
     * /> In other cases, accountNumber is a mandatory field.</td></tr>
     * 				<tr><td>externalAccountID</td><td nowrap="">Optional/Mandatory</td><td>If
     * "Serviceable Address Support" feature is enabled, all the Serviceable
     * Address related fields' values will be ignored and filled up by IPS.<br
     * /> externalAccountID is the mandatory field in Account object in this
     * case. externalAccountID must be an existing externalAccountID (LocationID).</td></tr>
     * 				<tr><td>ispId</td><td nowrap="">Mandatory/Ignored</td><td>If IPS
     * works in multi-ISP mode, ispId is the mandatory field in Account object
     * for this operation. If IPS works in single-ISP mode, ispId will be
     * ignored.</td></tr>
     * 			</table>
     */
    public net.cinecable.ws.Account createAccount(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Updates Account data in IPS, using accountId to locate Account.
     * <b>Access Accounts</b>, <b>Manage Accounts</b> permissions are required
     * for this operation to succeed.
     * 			<p>Key Account object fields:</p>
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     * 				<tr><th>Field Name</th><th>Field Type</th><th>Comments</th></tr>
     * 				<tr><td>accountId</td><td>Mandatory</td><td>&nbsp;</td></tr>
     * 				<tr><td>accountNumber</td><td>Ignored</td><td>Field must be present
     * in query</td></tr>
     * 				<tr><td>externalAccountID</td><td>Optional/Mandatory</td><td>If
     * "Serviceable Address Support" feature is enabled, all the Serviceable
     * Address related fields' values will be ignored and filled up by IPS.<br
     * />
     * 				externalAccountID is the mandatory field in Account object in
     * this case. externalAccountID must be an existing externalAccountID.</td></tr>
     * 				<tr><td>expirationDate</td><td>Mandatory</td><td>0 if there is
     * no expirationDate</td></tr>
     * 				<tr><td>status</td><td>Mandatory</td><td>"0" if Account active
     * and "1" - if Account deactivated</td></tr>
     * 				<tr><td>ispId</td><td>Ignored</td><td>Field must be present in
     * query</td></tr>
     * 				<tr><td>installdate</td><td>Ignored</td><td>Field must be present
     * in query</td></tr>
     * 			</table>
     */
    public void updateAccountById(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Updates Account data in IPS, using accountNumber to locate
     * Account. <b>Access Accounts</b>, <b>Manage Accounts</b> permissions
     * are required for this operation to succeed.
     * 			<p>Key Account object fields:</p>
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     * 				<tr><th>Field Name</th><th>Field status</th><th>Comments</th></tr>
     * 				<tr><td>accountId</td><td>Ignored</td><td>Field must be present
     * in query</td></tr>
     * 				<tr><td>accountNumber</td><td>Mandatory/Ignored</td><td>If Automatic
     * Account Number Generation feature is enabled, accountNumber field
     * value will be ignored and generated automatically by IPS.<br />
     * 				In other cases, accountNumber is a mandatory field.</td></tr>
     * 				<tr><td>externalAccountID</td><td>Optional/Mandatory</td><td>If
     * "Serviceable Address Support" feature is enabled, all the Serviceable
     * Address related fields' values will be ignored and filled up by IPS.<br
     * />
     * 				externalAccountID is the mandatory field in Account object in
     * this case. externalAccountID must be an existing externalAccountID.</td></tr>
     * 				<tr><td>expirationDate</td><td>Mandatory</td><td>"0" if Account
     * never expires</td></tr>
     * 				<tr><td>status</td><td>Mandatory</td><td>"0" if Account active
     * and "1" - if Account deactivated</td></tr>
     * 				<tr><td>ispId</td><td>Ignored</td><td>Field must be present in
     * query</td></tr>
     * 				<tr><td>installdate</td><td>Ignored</td><td>Field must be present
     * in query</td></tr>
     * 			</table>
     */
    public void updateAccountByNumber(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Updates Account with new Serviceable address. <b>Access Accounts</b>,
     * <b>Manage Accounts</b> permissions are required for this operation
     * to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account to
     * be updated</td></tr>
     * 			<tr><td>externalAccountID</td><td>External Account ID - LocationID
     * of new Serviceable Address to be assigned to the Account</td></tr>
     * 			</table>
     * 			</p>
     */
    public void changeAccountServiceableAddress(java.lang.String accountNumber, java.lang.String externalAccountID, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Lock device, so that users without <b>Manage Device Locking</b>
     * permission are not able to manage this device. <b>Access Accounts</b>,
     * <b>Manage Accounts</b>, <b>Manage Device Locking</b> permissions are
     * required for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>mac</td><td>MAC address of the Device to be locked</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History</td></tr>
     * 			</table>
     * 			</p>
     */
    public void lockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Unlock locked device. <b>Access Accounts</b>, <b>Manage Accounts</b>,
     * <b>Manage Device Locking</b> permissions are required for this operation
     * to succeed.
     * 			<p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>mac</td><td>MAC address of the Device to be locked</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History</td></tr>
     * 			</table>
     * 			</p>
     */
    public void unlockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Retrieves Account object by its Account Number. <b>Access Accounts</b>
     * permission is required for this operation to succeed.
     * 			<p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account to
     * be retrieved</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Account getAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Retrieves Account object by its ID. <b>Access Accounts</b>
     * permission is required for this operation to succeed.
     *             <p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountId</td><td>ID of the Account to be retrieved</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Account getAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Retrieves Account object(s) by its External Account ID. <b>Access
     * Accounts</b> permission is required for this operation to succeed.
     * <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>externalAccountId</td><td>External Account ID of the Account(s)
     * to be retrieved</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Account[] searchAccountsByExternalAccountId(java.lang.String externalAccountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Activate the specified Account. <b>Manage Accounts</b> permission
     * is required for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account to
     * be activated</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History.</td></tr>
     * 			</table>
     * 			</p>
     */
    public void activateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Deactivate the specified Account. <b>Manage Accounts</b> permission
     * are required for this operation to succeed.
     * 			<p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account to
     * be deactivated</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History.</td></tr>
     * 			</table>
     * 			</p>
     */
    public void deactivateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Adds a Device to the specified Account. <b>Manage Accounts</b>
     * and <b>Access Service Classes</b> permissions are required for this
     * operation to succeed.
     * 			<p>Key Device object fields:</p>
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     *               <tr><th>Field Name</th><th>Field Type</th><th>Comments</th></tr>
     * <tr><td>accountId</td><td>Mandatory</td><td> You can resolve Account
     * ID by Account Name by issuing getAccountByName request</td></tr>
     *               <tr><td>primaryMacAddress</td><td>Mandatory</td><td>
     * in form of 12 hexits (0-f); e.g. 012345678abc<br/></td></tr>
     *               <tr><td>secondaryMacAddress</td><td>Ignored</td><td>
     * This field is for API compatibility only</td></tr>
     *               <tr><td>serviceClass</td><td>Mandatory</td> <td>Service
     * Type of the Service Class that is being assigned to a device must
     * match Dwelling Type in Account, and the Initiator must be allowed
     * to access this Service Class</td></tr>
     *               <tr><td>deviceType</td><td>Mandatory</td><td>0 - DOCSIS
     * device, 1 - Packetcable device</td></tr>
     *               <tr><td>addressFromAccount</td><td>Mandatory</td><td><b>true
     * or false.</b> <br/><b>Note:</b> If addressFromAccount is set to true
     * then the address fields are inherited from the parent Account. If
     * "Separate Installation Address for Device" feature is not enabled
     * in the license, then all Address related fields will be ignored and
     * Address from Account will be saved as Installation Address to the
     * database automatically</td></tr>
     *               <tr><td>ispId</td><td>Ignored</td><td> ISP ID is inherited
     * from the parent Account</td></tr>
     *               <tr><td>accountNumber</td><td>Ignored</td><td> </td></tr>
     * 			</table>
     */
    public void addDeviceToAccountById(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Adds a Device to the specified Account. <b>Manage Accounts</b>
     * and <b>Access Service Classes</b> permissions are required for this
     * operation to succeed.
     * 			<p>Key Device object fields:</p>
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     *               <tr><th>Field Name</th> <th>Field Type</th><th> Comments</th></tr>
     * <tr><td>accountNumber</td> <td>Mandatory</td><td> You can resolve
     * Account Number by Account Name by issuing getAccountByName request</td></tr>
     * <tr><td>primaryMacAddress</td> <td>Mandatory</td><td> in form of 12
     * hexits (0-f); e.g. 012345678abc</td></tr>
     *               <tr><td>secondaryMacAddress</td> <td>Ignored</td><td>
     * This field is for API compatibility only</td></tr>
     *               <tr><td>serviceClass</td> <td>Mandatory</td> <td>Service
     * Type of the Service Class that is being assigned to a device must
     * match Dwelling Type in Account, and the Initiator must be allowed
     * to access this Service Class</td></tr>
     *               <tr><td>deviceType</td> <td>Mandatory</td><td> 0 - docsis
     * device, 1-packet cable device</td></tr>
     *               <tr><td>addressFromAccount</td> <td>Mandatory</td><td>
     * <b>true or false.</b> <br/><b>Note:</b> If addressFromAccount is set
     * to true then the address fields are inherited from the parent Account.
     * If "Separate Installation Address for Device" feature is not enabled
     * in the license, then all Address related fields will be ignored and
     * Address from Account will be saved as Installation Address to the
     * database automatically</td></tr>
     *               <tr><td>ispId</td> <td>Ignored</td><td> ISP ID is inherited
     * from the parent Account</td></tr>
     *               <tr><td>accountId</td> <td>Ignored</td><td> </td></tr>
     * 			</table>
     */
    public void addDeviceToAccountByNumber(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Retrieves a Device object.
     *             <p><b>Access Service Classes</b> and <b>Access Accounts</b>
     * permissions are required for this operation to succeed.</p>
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>deviceId</td><td>ID of the Device object to be retrieved</td></tr>
     * 			<tr><td>accountId</td><td>Account ID of the Account Device(s) belong(s)
     * to</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Device getDeviceById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Get full list of devices that belong to the specified Account.
     * <p><b>Access Service Classes</b> permission is required for this operation
     * to succeed.</p>
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountId</td><td>Account ID of the Account Device(s) belong(s)
     * to</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Device[] getDevicesForAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Get full list of devices that belong to the specified Account.
     * <p><b>Access Service Classes</b> permission is required for this operation
     * to succeed.</p>
     *             <p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account Device(s)
     * belong(s) to</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Device[] getDevicesForAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Retrieves a Device object with the specified MAC address. <b>Access
     * Accounts</b> and <b>Access Service Class</b> permissions are required
     * for this operation to succeed.
     *             <p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>mac</td><td>MAC address of the Device to be retrieved</td></tr>
     * 			</table>
     * 			</p>
     */
    public net.cinecable.ws.Device getDeviceByMac(java.lang.String mac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Replaces a Device object with the new Device object. <b>Manage
     * Accounts</b> permission is required for this operation to succeed.
     * <p>If "Separate Installation Address for Device" feature is not enabled
     * in the license, <br />
     *             then all Address related fields will be ignored and Address
     * from Account will be saved as Installation Address to the database
     * automatically.</p>
     * 			<p>Key parameter:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>device</td><td>The object that needs to be written as the
     * Device update</td></tr>
     * 			</table>
     * 			</p>
     * 			<p>The new Device object replaces the Device object that has the
     * same ID as the new Device object.</p>
     * 			<p>Key Device object fields:
     * 			<table border="1" cellpadding="5" style="border: 1px solid rgb(255,
     * 255, 255);">
     * 			<tr><th>Field Name</th><th>Field Type</th><th>Comments</th></tr>
     * 			<tr><td>deviceId</td><td>Mandatory</td><td>You can resolve Device
     * ID by Account Name by issuing getDeviceByMac request</td></tr>
     * 			<tr><td>accountId</td><td>Mandatory</td><td>You can resolve Account
     * ID by Account Name by issuing getAccountByName request</td></tr>
     * 			<tr><td>primaryMacAddress</td><td>Mandatory</td><td>Note: new Device
     * object's primaryMacAddress and deviceId must match existing Device's
     * corresponding fields</td></tr>
     * 			<tr><td>serviceClass</td><td>Mandatory</td><td>Service Type of
     * the Service Class that is being assigned to a Device must match Dwelling
     * Type in Account,<br /> if this restriction is enabled in IPS configuration,
     * and the Initiator must be allowed to access this Service Class</td></tr>
     * 			</table>
     * 			</p>
     * 			<p>Note: If you don't specify value for a field, then the value
     * of this field won't be changed in the database.</p>
     */
    public void updateDevice(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Delete a Device object with the specified MAC address. <b>Manage
     * Accounts</b> permission is required for this operation to succeed.
     * 			<p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>accountNumber</td><td>Account Number of the Account that
     * contains the Device to be deleted</td></tr>
     * 			<tr><td>deviceMac</td><td>MAC address of the Device to be deleted</td></tr>
     * 			</table>
     * 			</p>
     */
    public void deleteDeviceFromAccountByMac(java.lang.String accountNumber, java.lang.String deviceMac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Delete a Device object with the specified ID. <b>Manage Accounts</b>
     * permission is required for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>deviceId</td><td>ID of the Device to be deleted</td></tr>
     * 			<tr><td>accountId</td><td>ID of the Account that contains the Device
     * to be deleted</td></tr>
     * 			</table>
     * 			</p>
     */
    public void deleteDeviceFromAccountById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Allows to search for Serviceable Addresses in Serviceable Addresses
     * proxy database (works only if Serviceable Address Support is enabled).
     * <p>Usage mapping:</p>
     *             <table border="1" cellpadding="5" style="border: 1px solid
     * rgb(255, 255, 255);">
     *             <tr><th>String</th><th>Integer value</th></tr>
     *             <tr><td>"not in use"</td><td>0</td></tr>
     *             <tr><td>"Used by other ISP(s)"</td><td>1</td></tr>
     *             <tr><td>"Used by own ISP(s)"</td><td>2</td></tr>
     *             </table>
     */
    public net.cinecable.ws.ServiceableAddressResult[] serviceableAddressSearch(net.cinecable.ws.ServiceableSearchParams params, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Activate a Device. <b>Manage Accounts</b> permission is required
     * for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>macAddress</td><td>MAC address of the Device to be activated.</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History.</td></tr>
     * 			</table>
     * 			</p>
     */
    public void activateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Deactivate a Device. <b>Manage Accounts</b> permission is required
     * for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>macAddress</td><td>MAC address of the Device to be deactivated.</td></tr>
     * 			<tr><td>changeComment</td><td>The reason why this operation is
     * performed. Comment value will be written into Account History.</td></tr>
     * 			</table>
     * 			</p>
     */
    public void deactivateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Returns an array of MIB Objects associated with specified Packetcable
     * device. <b>Access Accounts</b> permission is required for this operation
     * to succeed.
     */
    public net.cinecable.ws.WsMibObject[] getAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Adds new MIB Object to specified Packetcable device. <b>Access
     * Accounts</b> and <b>Manage Accounts</b> permissions are required for
     * this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>macAddress</td><td>MAC address of the Packetcable Device.</td></tr>
     * 			<tr><td>mibObject</td><td>MIB Object that will be added to specified
     * Packetcable device.</td></tr>
     * 			</table>
     * 			</p>
     * 			<p>MIB Object Type mapping:</p>
     *             <table border="1" cellpadding="5" style="border: 1px solid
     * rgb(255, 255, 255);">
     *             <tr><th>Type</th><th>Integer code</th></tr>
     *             <tr><td>Octet String</td><td>1</td></tr>
     *             <tr><td>HEX String</td><td>2</td></tr>
     *             <tr><td>Integer</td><td>3</td></tr>
     *             <tr><td>Counter</td><td>4</td></tr>
     *             <tr><td>Gauge</td><td>5</td></tr>
     *             <tr><td>IPAddress</td><td>6</td></tr>
     *             <tr><td>OID</td><td>7</td></tr>
     *             </table>
     */
    public void addDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Deletes all MIB Objects associated with specified Packetcable
     * device. <br />
     *             <b>Access Accounts</b> and <b>Manage Accounts</b> permissions
     * are required for this operation to succeed.
     */
    public void deleteAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Deletes MIB Object for specified Packetcable device. MIB Object
     * is identified by specified OID. <br />
     *             <b>Access Accounts</b> and <b>Manage Accounts</b> permissions
     * are required for this operation to succeed.
     *             <p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>macAddress</td><td>MAC address of the Packetcable Device.</td></tr>
     * 			<tr><td>oid</td><td>OID that identified MIB Object to delete.</td></tr>
     * 			</table>
     * 			</p>
     */
    public void deleteDeviceMibObject(java.lang.String macAddress, java.lang.String oid, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;

    /**
     * Updates MIB Object for specified Packetcable device. New MIB
     * Object's OID must correspond to OID of any of MIB Objects associated
     * with specified Packetcable device. <br />
     *             <b>Access Accounts</b> and <b>Manage Accounts</b> permissions
     * are required for this operation to succeed.
     * 			<p>Key parameters:
     *             <table  border="1" cellpadding="5" style="border: 1px
     * solid rgb(255, 255, 255);">
     * 			<tr><th>Parameter Name</th><th>Description</th></tr>
     * 			<tr><td>macAddress</td><td>MAC address of the Packetcable Device.</td></tr>
     * 			<tr><td>mibObject</td><td>New MIB Object</td></tr>
     * 			</table>
     * 			</p>
     */
    public void updateDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException;
}
