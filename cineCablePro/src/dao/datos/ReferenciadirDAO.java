package dao.datos;

import org.hibernate.Session;

import pojo.annotations.Referenciadir;

public class ReferenciadirDAO {

	public int maxIdreferencia(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idreferencia) as max from Referenciadir ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void saveReferenciadir(Session session, Referenciadir referenciadir) throws Exception {
		session.save(referenciadir);
	}
	
	public void updateReferenciadir(Session session, Referenciadir referenciadir) throws Exception {
		session.update(referenciadir);
	}
}
