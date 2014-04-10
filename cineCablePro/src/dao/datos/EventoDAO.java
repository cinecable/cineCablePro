package dao.datos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Evento;

public class EventoDAO {
	
	public int maxIdCotevento(Session session) {
		int max = 0;
		
		Criteria criteria = session.createCriteria(Evento.class)
		.setProjection(Projections.max("idevento"));
						
		Object object = criteria.uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void saveEvento(Session session, Evento evento) throws Exception {
		session.save(evento);
	}

	public void updateEvento(Session session, Evento evento) throws Exception {
		session.update(evento);
	}

	@SuppressWarnings("unchecked")
	public List<Evento> lisEvento(Session session, Date fechadesde, Date fechahasta) throws Exception {
		List<Evento> lisEvento = null;
		
		Criteria criteria = session.createCriteria(Evento.class)
		.add(Restrictions.eq("idestado", 1))
		.add(Restrictions.between("fechadesde", fechadesde, fechahasta))
		.add(Restrictions.between("fechahasta", fechadesde, fechahasta));
		
		lisEvento = (List<Evento>) criteria.list();
		
		return lisEvento;
	}

	public void deleteEvento(Session session, int id) throws Exception {
		String hqlUpdate = "delete Evento evento where evento.idevento = :idevento";
		session.createQuery( hqlUpdate )
		.setInteger( "idevento", id )
		.executeUpdate();
	}

}
