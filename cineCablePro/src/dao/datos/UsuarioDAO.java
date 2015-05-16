package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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
		.add( Restrictions.eq("cla.clave", clave) )
		.createAlias("empresa", "emp");
		
		usuario = (Usuario) criteria.uniqueResult();
		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> lisUsuario(Session session, int idempresa) throws Exception {
		List<Usuario> lisUsuario = null;
		
		String hql = " from Usuario ";
		hql += " where empresa.idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1);
		
		lisUsuario = (List<Usuario>) query.list();
		
		return lisUsuario;
	}
	
}
