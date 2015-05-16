package dao.datos;

import java.util.Date;

import net.cinecable.model.base.ParamAsignacionOrden;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TbparamasigordenDAO {

	public int maxId(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idparamasigord) as max from ParamAsignacionOrden ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public ParamAsignacionOrden consultarPorFechaTipoOperacion(Session session, Date fechaasignacion, int idtipooperacion) throws Exception {
		ParamAsignacionOrden paramAsignacionOrden = null;
		
		Criteria criteria = session.createCriteria(ParamAsignacionOrden.class)
				.add(Restrictions.eq("fechaasignacion", fechaasignacion))
				.add(Restrictions.eq("tipoOperacion.idtipooperacion", idtipooperacion));
		
		paramAsignacionOrden = (ParamAsignacionOrden) criteria.uniqueResult();
		
		return paramAsignacionOrden;
	}
	
	public void actualizarTbparamasigorden(Session session, ParamAsignacionOrden paramAsignacionOrden) throws Exception {
		session.update(paramAsignacionOrden);
	}
	
	public void ingresarTbparamasigorden(Session session, ParamAsignacionOrden paramAsignacionOrden) throws Exception {
		session.save(paramAsignacionOrden);
	}
}
