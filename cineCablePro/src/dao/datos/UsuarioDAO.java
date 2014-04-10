package dao.datos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Usuario;

public class UsuarioDAO {
	
	public Usuario getByUserPasswd(Session session, String nombreusuario, String clave) throws Exception {
		Usuario usuario = null;
		
		Criteria criteria = session.createCriteria(Usuario.class)
		.add( Restrictions.eq("nombre", nombreusuario) )
		.add( Restrictions.eq("idestado", (Integer)1))
		.createAlias("claves", "cla")
		.add( Restrictions.eq("cla.clave", clave) );
		
		usuario = (Usuario) criteria.uniqueResult();
		
		return usuario;
	}
	
}
