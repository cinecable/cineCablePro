package net.cinecable.service.imp;

import java.rmi.RemoteException;

import net.cinecable.ws.Account;
import net.cinecable.ws.AccountManagement;
import net.cinecable.ws.AccountManagementProxy;
import net.cinecable.ws.WebServiceException;
import net.cinecable.ws.rst.ResetModem;
import net.cinecable.ws.rst.ResetModemProxy;

public class WsMetodos extends WsAutenticacion {

	protected Account getAccountByExternalId(Long id) throws WebServiceException, RemoteException {
		AccountManagementProxy proxy = new AccountManagementProxy();
		AccountManagement target = proxy.getAccountManagement();
		Account[] acc = null;
		acc = target.searchAccountsByExternalAccountId(id.toString(), getUsuarioWs());
		return acc.length > 0 ? acc[0] : null;
	}

	protected AccountManagement getWsPort() {
		AccountManagementProxy proxy = new AccountManagementProxy();
		AccountManagement target = proxy.getAccountManagement();
		return target;
	}

	protected ResetModem getWsPortDevice() {
		ResetModemProxy proxy = new ResetModemProxy();
		ResetModem ws = proxy.getResetModem();
		return ws;
	}

}
