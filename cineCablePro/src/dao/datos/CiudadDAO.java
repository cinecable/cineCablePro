package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Ciudad;

public class CiudadDAO {
	  @SuppressWarnings("unchecked")
		public List<Ciudad> lisCiudadByProvincia(Session session, int idprovincia) throws Exception {
	        List<Ciudad> lisCiudad;

	        Criteria criteria = session.createCriteria(Ciudad.class)
	                .add( Restrictions.eq("provincia.idprovincia", idprovincia))
	                .add( Restrictions.eq("estado.idestado", 1))
	                .addOrder( Order.asc("nombre"));

	        lisCiudad = (List<Ciudad>)criteria.list();

	        return lisCiudad;
	    }
	  
	  @SuppressWarnings("unchecked")
		public List<Ciudad> lisCiudadByCiudad(Session session, int idCiudad) throws Exception {
	        List<Ciudad> lisCiudad;

	        Criteria criteria = session.createCriteria(Ciudad.class)
	                .add( Restrictions.eq("idciudad", idCiudad))
	                .add( Restrictions.eq("estado.idestado", 1))
	                .addOrder( Order.asc("nombre"));

	        lisCiudad = (List<Ciudad>)criteria.list();

	        return lisCiudad;
	    }
}
