package dao.datos;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Conyuge;

public class ConyugeDAO {

	public int maxIdconyuge(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idconyuge) as max from Conyuge ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Conyuge getConyugeByIdcliente(Session session, String idcliente) throws Exception {
		Conyuge conyuge = null;
		
		Criteria criteria = session.createCriteria(Conyuge.class)
				.createAlias("clientes", "cli")
				.add( Restrictions.eq("cli.idcliente",idcliente));
		
		conyuge = (Conyuge) criteria.uniqueResult();
		
		return conyuge;
	}
	
	public void saveConyuge(Session session, Conyuge conyuge) throws Exception {
		session.save(conyuge);
	}
	
	public void updateConyuge(Session session, Conyuge conyuge) throws Exception {
		session.update(conyuge);
	}
}
