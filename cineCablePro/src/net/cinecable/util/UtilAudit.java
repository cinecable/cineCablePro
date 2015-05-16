package net.cinecable.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.FacesUtil;
import bean.controladores.UsuarioBean;

public class UtilAudit {
	private static Logger LOGGER = Logger.getLogger(UtilAudit.class.getName());

	public static <T> void setAudit(T... obj) {
		UsuarioBean usuarioBean = (UsuarioBean) new FacesUtil().getSessionBean("usuarioBean");
		try {
			for (T o : obj) {
				Field _ip = o.getClass().getSuperclass().getDeclaredField("ip");
				_ip.setAccessible(true);
				_ip.set(o, usuarioBean.getIp());

				Field _user = o.getClass().getSuperclass().getDeclaredField("usuario");
				_user.setAccessible(true);
				_user.set(o, usuarioBean.getUsuario());
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

}
