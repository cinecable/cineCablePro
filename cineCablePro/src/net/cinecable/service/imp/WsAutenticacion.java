package net.cinecable.service.imp;

import java.rmi.RemoteException;

import javax.ejb.EJB;

import net.cinecable.dao.IUserWsDao;
import net.cinecable.model.base.UserWs;
import net.cinecable.ws.sec.SecurityManagement;
import net.cinecable.ws.sec.SecurityManagementProxy;
import net.cinecable.ws.sec.WebServiceException;

public class WsAutenticacion {

	@EJB
	IUserWsDao iUserDao;

	private static net.cinecable.ws.User user = null;

	protected net.cinecable.ws.User getUsuarioWs() {
		if (user == null) {
			SecurityManagementProxy proxysec = new SecurityManagementProxy();
			SecurityManagement wsec = proxysec.getSecurityManagement();
			UserWs usuario = iUserDao.getUsuario(null);
			try {
				user = wsec.authenticate(usuario.getNombreUser(), usuario.getPassWd());
			} catch (WebServiceException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

}
