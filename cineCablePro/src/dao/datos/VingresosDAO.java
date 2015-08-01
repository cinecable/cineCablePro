package dao.datos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.custom.Vingresos;

public class VingresosDAO {

	@SuppressWarnings("unchecked")
	public List<Vingresos> lisVingresos(Session session, int idusuario, int idfpago, Date fechaDesde, Date fechaHasta) throws Exception{
		List<Vingresos> lisVingresos = null;
		
		Criteria criteria = session.createCriteria(Vingresos.class)
				.add(Restrictions.eq("idusuario", idusuario))
				.add(Restrictions.eq("idfpago", idfpago))
				.add(Restrictions.between("fecha", fechaDesde, fechaHasta));
		
		lisVingresos = (List<Vingresos>) criteria.list();
		
		return lisVingresos;
	}
}
