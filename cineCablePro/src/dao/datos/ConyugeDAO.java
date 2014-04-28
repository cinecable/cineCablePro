package dao.datos;

import org.hibernate.Session;

import pojo.annotations.Conyuge;

public class ConyugeDAO {

	public int maxIdconyuge(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idconyuge) as max from Conyuge ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void saveConyuge(Session session, Conyuge conyuge) throws Exception {
		session.save(conyuge);
	}
	
	public void updateConyuge(Session session, Conyuge conyuge) throws Exception {
		session.update(conyuge);
	}
}
