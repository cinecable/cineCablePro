package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import pojo.annotations.Tiposector;


public class TipoSectorDAO {
	 @SuppressWarnings("unchecked")
		public List<Tiposector> lisTipoSectorxTipoSector(Session session) throws Exception {
	        List<Tiposector> lisTipoSector;

	        Criteria criteria = session.createCriteria(Tiposector.class)
	             
	                .addOrder( Order.asc("nombre"));

	        lisTipoSector = (List<Tiposector>)criteria.list();

	        return lisTipoSector;
	    }
	  
}
