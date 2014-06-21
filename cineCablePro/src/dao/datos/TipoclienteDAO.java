package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Tipocliente;

public class TipoclienteDAO {

	@SuppressWarnings("unchecked")
	public List<Tipocliente> lisTipocliente(Session session) throws Exception {
        List<Tipocliente> lisTipocliente;

        Criteria criteria = session.createCriteria(Tipocliente.class)
        		.add( Restrictions.eq("estado.idestado", (int)1))
        		.addOrder( Order.asc("nombre"));

        lisTipocliente = (List<Tipocliente>)criteria.list();

        return lisTipocliente;
    }
}
