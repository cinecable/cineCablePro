package dao.datos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Usuariomenu;

public class UsuariomenuDAO {

	@SuppressWarnings("unchecked")
	public List<Usuariomenu> lisUsuariomenu(Session session, int idusuario,int idtipomenu) throws Exception{
		List<Usuariomenu> lisUsuariomenu = new ArrayList<Usuariomenu>();
		
		Criteria criteria = session.createCriteria(Usuariomenu.class)
				.add( Restrictions.eq("idusuario", (Integer)idusuario) )
				.add( Restrictions.eq("idmenu", (Integer)idtipomenu) )
				.add( Restrictions.eq("idestado", (Integer)1) );
		
		lisUsuariomenu = (List<Usuariomenu>) criteria.list();
		
		return lisUsuariomenu;
	}
}
