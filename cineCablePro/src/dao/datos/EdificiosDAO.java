package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Edificio;

public class EdificiosDAO {
	@SuppressWarnings("unchecked")
	public List<Edificio> EdificioxSector(Session session, int idSector) throws Exception {
        List<Edificio> lisEdificio;

        Criteria criteria = session.createCriteria(Edificio.class)
                .add( Restrictions.eq("sector.idsector", idSector))
                .add( Restrictions.eq("estado.idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisEdificio = (List<Edificio>)criteria.list();

        return lisEdificio;
    }
	
	@SuppressWarnings("unchecked")
	public List<Edificio> EdificioxEdificio(Session session, int idEdificio) throws Exception {
        List<Edificio> lisEdificio;

        Criteria criteria = session.createCriteria(Edificio.class)
                .add( Restrictions.eq("idedifico", idEdificio))
                .add( Restrictions.eq("estado.idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisEdificio = (List<Edificio>)criteria.list();

        return lisEdificio;
    }
}
