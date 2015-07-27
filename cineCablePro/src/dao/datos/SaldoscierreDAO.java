package dao.datos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Saldoscierre;

public class SaldoscierreDAO {
	
	public int maxId(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idsaldoscierre) as max from Saldoscierre ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Saldoscierre getByNombreUsuario(Session session, String nombreusuario) throws Exception {
		Saldoscierre saldoscierre = null;
		
		Criteria criteria = session.createCriteria(Saldoscierre.class)
		.createAlias("usuarioByIdusuariocaja", "usu")
		.add( Restrictions.eq("usu.nombre", nombreusuario) )
		.add( Restrictions.eq("usu.idestado", (Integer)1));
		
		saldoscierre = (Saldoscierre) criteria.uniqueResult();
		
		return saldoscierre;
	}
	
	public Saldoscierre getByIdUsuario(Session session, int idusuariocaja) throws Exception {
		Saldoscierre saldoscierre = null;
		
		Criteria criteria = session.createCriteria(Saldoscierre.class)
		.add( Restrictions.eq("usuarioByIdusuariocaja.idusuario", idusuariocaja) );
		
		saldoscierre = (Saldoscierre) criteria.uniqueResult();
		
		return saldoscierre;
	}
	
	public void actualizar(Session session, Saldoscierre saldoscierre) throws Exception {
		session.update(saldoscierre);
	}
	
	public void ingresar(Session session, Saldoscierre saldoscierre) throws Exception {
		session.save(saldoscierre);
	}

}
