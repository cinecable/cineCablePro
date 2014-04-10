package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Ubicacion;


public class UbicacionDAO {
	@SuppressWarnings("unchecked")
	public List<Ubicacion> lisUbicaccionxSector(Session session, int idSector) throws Exception {
        List<Ubicacion> lisUbicacion;

        Criteria criteria = session.createCriteria(Ubicacion.class)
                .add( Restrictions.eq("sector.idsector", idSector))
                .add( Restrictions.eq("idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisUbicacion = (List<Ubicacion>)criteria.list();

        return lisUbicacion;
    }
  
  @SuppressWarnings("unchecked")
	public List<Ubicacion> liUbicacionxUbicacion(Session session, int idUbicacion) throws Exception {
        List<Ubicacion> lisUbicacion;

        Criteria criteria = session.createCriteria(Ubicacion.class)
                .add( Restrictions.eq("idciudad", idUbicacion))
                .add( Restrictions.eq("idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisUbicacion = (List<Ubicacion>)criteria.list();

        return lisUbicacion;
    }

}
