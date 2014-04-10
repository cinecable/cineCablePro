package dao.datos;

import org.hibernate.Query;
import org.hibernate.Session;

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
}
