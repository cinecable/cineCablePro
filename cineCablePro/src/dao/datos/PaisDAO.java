package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Pais;

public class PaisDAO {
	 @SuppressWarnings("unchecked")
		public List<Pais> lisPais(Session session) throws Exception {
	        List<Pais> lisPais;

	        Criteria criteria = session.createCriteria(Pais.class)
	        		.add( Restrictions.eq("idestado", (int)1))
	        		.addOrder( Order.asc("nombre"));

	        lisPais = (List<Pais>)criteria.list();

	        return lisPais;
	    }
}
