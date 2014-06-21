package dao.datos;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
		
		Calendar ahorita = Calendar.getInstance();
		
		Criteria criteria = session.createCriteria(Mensajes.class)
				.add( Restrictions.eq("idcliente", idcliente))
				.add( Restrictions.eq("estado", true))
				.add( Restrictions.gt("fechacaducidad", ahorita.getTime()));
		
		mensajes = (Mensajes) criteria.uniqueResult();
		
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
