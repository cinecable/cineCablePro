package dao.datos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Referenciadir;

public class ReferenciadirDAO {

	public int maxIdreferencia(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idreferencia) as max from Referenciadir ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Referenciadir getReferenciadirById(Session session, int idreferencia) throws Exception {
		Referenciadir referenciadir = null;
		
		Criteria criteria = session.createCriteria(Referenciadir.class)
				.add(Restrictions.eq("idreferencia", idreferencia));
		
		referenciadir = (Referenciadir)criteria.uniqueResult();
		
		return referenciadir;
	}
	
	public Referenciadir getReferenciadirByIdDireccion(Session session, int iddireccion) throws Exception {
		Referenciadir referenciadir = null;
		
		Criteria criteria = session.createCriteria(Referenciadir.class)
				.add(Restrictions.eq("direccion.iddireccion", iddireccion));
		
		referenciadir = (Referenciadir)criteria.uniqueResult();
		
		return referenciadir;
	}
	
	public void saveReferenciadir(Session session, Referenciadir referenciadir) throws Exception {
		session.save(referenciadir);
	}
	
	public void updateReferenciadir(Session session, Referenciadir referenciadir) throws Exception {
		session.update(referenciadir);
	}
}
