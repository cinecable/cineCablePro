package dao.datos;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Mensajes;

public class MensajesDAO {

	public int maxIdmensajes(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idmensajes) as max from Mensajes ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Mensajes getMensajesByIdcliente(Session session, String idcliente) {
		Mensajes mensajes = null;
		
		String hql = " from Mensajes ";
		hql += " where idcliente = :idcliente ";
		hql += " and estado = :estado ";
		
		Query query = session.createQuery(hql)
				.setString("idcliente", idcliente)
				.setBoolean("estado", true);
		
		mensajes = (Mensajes) query.uniqueResult();
		
		return mensajes;
	}
	
	public void actualizarMensajes(Session session, Mensajes mensajes) throws Exception {
		session.update(mensajes);
	}
	
	public void ingresarMensajes(Session session, Mensajes mensajes) throws Exception {
		session.save(mensajes);
	}
	
	public void modificarMensajes(Session session, Mensajes mensajes) throws Exception {
		session.update(mensajes);
	}
}
