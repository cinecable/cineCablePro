package dao.datos;

import org.hibernate.Session;

import pojo.annotations.Cierrecaja;

public class CierrecajaDAO {

	public int maxId(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcierrecaja) as max from Cierrecaja ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void actualizar(Session session, Cierrecaja cierrecaja) throws Exception {
		session.update(cierrecaja);
	}
	
	public void ingresar(Session session, Cierrecaja cierrecaja) throws Exception {
		session.save(cierrecaja);
	}
}
