package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Impservicios;

public class ImpserviciosDAO {

	@SuppressWarnings("unchecked")
	public List<Impservicios> lisImpserviciosByIdTipoServicio(Session session, int idtiposervicio) throws Exception {
		List<Impservicios> lisImpservicios = null;
		
		String hql = " from Impservicios ";
		hql += " where idtiposervicio = :idtiposervicio ";
		hql += " order by idimpservicios ";
		
		Query query = session.createQuery(hql)
				.setInteger("idtiposervicio", idtiposervicio);
		
		lisImpservicios = (List<Impservicios>) query.list();
		
		return lisImpservicios;
	}
}
