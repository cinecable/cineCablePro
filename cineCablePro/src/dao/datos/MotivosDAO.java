package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Motivos;

public class MotivosDAO {

	public Motivos getMotivosById(Session session, int idmotivo) throws Exception {
		Motivos motivos = null;
		
		String hql = " from Motivos ";
		hql += " where idmotivo = :idmotivo ";
		
		Query query = session.createQuery(hql)
				.setInteger("idmotivo", idmotivo);
		
		motivos = (Motivos) query.uniqueResult();
		
		return motivos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Motivos> lisMotivosByTipoMotivo(Session session, int idtipomotivo, int idempresa) throws Exception {
		List<Motivos> lisMotivos = null;
		
		Criteria criteria = session.createCriteria(Motivos.class)
				.add( Restrictions.eq("tipomotivo.idtipomotivo", idtipomotivo))
				.add( Restrictions.eq("estado.idestado", 1))
				.add( Restrictions.eq("empresa.idempresa", idempresa))
				.addOrder(Order.asc("nombre"));
				
		lisMotivos = (List<Motivos>) criteria.list();
		
		return lisMotivos;
	}
}
