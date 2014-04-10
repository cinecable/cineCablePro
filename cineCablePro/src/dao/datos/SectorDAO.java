package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Sector;

public class SectorDAO {
	@SuppressWarnings("unchecked")
	public List<Sector> lisSectorByCiudad(Session session, int idCiudad) throws Exception {
        List<Sector> lisSector;

        Criteria criteria = session.createCriteria(Sector.class)
                .add( Restrictions.eq("ciudad.idciudad", idCiudad))
                .add( Restrictions.eq("estado.idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisSector = (List<Sector>)criteria.list();

        return lisSector;
    }
	
	@SuppressWarnings("unchecked")
	public List<Sector> lisSectorBySector(Session session, int idsector) throws Exception {
        List<Sector> lisSector;

        Criteria criteria = session.createCriteria(Sector.class)
                .add( Restrictions.eq("idsector", idsector))
                .add( Restrictions.eq("estado.idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisSector = (List<Sector>)criteria.list();

        return lisSector;
    }
}
