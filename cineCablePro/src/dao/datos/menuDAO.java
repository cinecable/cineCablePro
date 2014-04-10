package dao.datos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Menu;


public class menuDAO {
	@SuppressWarnings("unchecked")
	public List<Menu> lisMenu(Session session, int idempresa,int idtipomenu) throws Exception{
		List<Menu> lisUsuariomenu = new ArrayList<Menu>();
		
		Criteria criteria = session.createCriteria(Menu.class)
				.add( Restrictions.eq("idestado", (Integer)1) )
				.add( Restrictions.eq("idempresa", idempresa) )
				.createAlias("tipomenu", "tmo")
				.add( Restrictions.eq("tmo.idtipomenu", idtipomenu) );
		
		lisUsuariomenu = (List<Menu>) criteria.list();
		
		return lisUsuariomenu;
	}
}
