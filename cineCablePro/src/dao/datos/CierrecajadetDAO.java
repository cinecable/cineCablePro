package dao.datos;

import org.hibernate.Session;

import pojo.annotations.Cierrecajadet;

public class CierrecajadetDAO {

	public int maxId(Session session, int idcierrecaja) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(id.idsecuencia) as max from Cierrecajadet where id.idcierrecaja = :idcierrecaja ").
				setInteger("idcierrecaja", idcierrecaja).
				uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void actualizar(Session session, Cierrecajadet cierrecajadet) throws Exception {
		session.update(cierrecajadet);
	}
	
	public void ingresar(Session session, Cierrecajadet cierrecajadet) throws Exception {
		session.save(cierrecajadet);
	}
}
