package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Provincia;

public class ProvinciaDAO {
	
	public Provincia provinciaById(Session session, int idProvincia) throws Exception {
        Provincia provincia = null;

        Criteria criteria = session.createCriteria(Provincia.class)
                .add( Restrictions.eq("idprovincia", idProvincia));

        provincia = (Provincia)criteria.uniqueResult();

        return provincia;
    }
	
	@SuppressWarnings("unchecked")
	public List<Provincia> lisProvinciaByPais(Session session, int idpais) throws Exception {
        List<Provincia> lisProvincia;

        Criteria criteria = session.createCriteria(Provincia.class)
                .add( Restrictions.eq("pais.idpais", idpais))
                .add( Restrictions.eq("idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisProvincia = (List<Provincia>)criteria.list();

        return lisProvincia;
    }
	
	@SuppressWarnings("unchecked")
	public List<Provincia> lisProvinciaByProvincia(Session session, int idProvincia) throws Exception {
        List<Provincia> lisProvincia;

        Criteria criteria = session.createCriteria(Provincia.class)
                .add( Restrictions.eq("idprovincia", idProvincia))
                .add( Restrictions.eq("idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisProvincia = (List<Provincia>)criteria.list();

        return lisProvincia;
    }
}
