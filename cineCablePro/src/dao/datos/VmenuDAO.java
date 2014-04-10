package dao.datos;

import global.Parametro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.custom.Vmenu;

public class VmenuDAO {

	@SuppressWarnings("unchecked")
	public List<Vmenu> lisVmenuPrincipal(Session session, List<Integer> idsmenu) throws Exception{
		List<Vmenu> lisVmenu = null;
		
		Criteria criteria = session.createCriteria(Vmenu.class)
				.add(Restrictions.in("idmenu", idsmenu))
				.add(Restrictions.eq("idtipomenu", Parametro.TIPOMENU_PRINCIPAL));
		
		lisVmenu = (List<Vmenu>) criteria.list();
		
		return lisVmenu;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vmenu> lisVmenuCliente(Session session, List<Integer> idsmenu) throws Exception{
		List<Vmenu> lisVmenu = null;
		
		Criteria criteria = session.createCriteria(Vmenu.class)
				.add(Restrictions.in("idmenu", idsmenu))
				.add(Restrictions.eq("idtipomenu", Parametro.TIPOMENU_CLIENTE));
		
		lisVmenu = (List<Vmenu>) criteria.list();
		
		return lisVmenu;
	}

}
