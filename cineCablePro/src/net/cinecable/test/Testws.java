package net.cinecable.test;

import java.rmi.RemoteException;

import net.cinecable.ws.Account;
import net.cinecable.ws.AccountManagement;
import net.cinecable.ws.AccountManagementProxy;
import net.cinecable.ws.Device;
import net.cinecable.ws.WebServiceException;
import net.cinecable.ws.sc.ServiceClassList;
import net.cinecable.ws.sc.ServiceClassManagement;
import net.cinecable.ws.sc.ServiceClassManagementProxy;
import net.cinecable.ws.sec.SecurityManagement;
import net.cinecable.ws.sec.SecurityManagementProxy;

public class Testws {

	public static void main(String[] args) {

		SecurityManagementProxy proxysec = new SecurityManagementProxy();
		SecurityManagement wsec = proxysec.getSecurityManagement();

		net.cinecable.ws.User us=null;
		try {
			us = wsec.authenticate("paul", "20142015");
		} catch (net.cinecable.ws.sec.WebServiceException e1) {
		
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		AccountManagementProxy test = new AccountManagementProxy();
		AccountManagement ws = test.getAccountManagement();
		
		ServiceClassManagementProxy sclss= new ServiceClassManagementProxy();
		ServiceClassManagement sc=sclss.getServiceClassManagement();
		
		try {
			ServiceClassList scl= sc.getServiceClassNames(us);
			System.out.println("");
		} catch (net.cinecable.ws.sc.WebServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//
//		try {
////			
////			Device dev= ws.getDeviceByMac("00:26:F3:C0:16:30", us);
////			//para activaciones
//////			Account acc= new Account();
//////			acc.setAccountNumber("3000");
//////			acc.setExternalAccountID("3000");
//////			acc.setFirstName("Test Contrisis");
//////			ws.createAccount(acc, us);
//////			Account acc[] = ws.searchAccountsByExternalAccountId("3000", us);
////			test.deleteDeviceFromAccountByMac("3000", "00:26:F3:C0:16:30", us);
////			
////			//test.deactivateDevice("00:26:F3:C0:16:30", "test", us);
//////			acc.setExternalAccountID("1737");
//////			ws.updateAccountById(acc, us);
////			//System.out.println(acc[0].getExternalAccountID());
//		} catch (WebServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
