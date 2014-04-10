package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Tipodebito;


public class TiposDebitoDAO {
	@SuppressWarnings("unchecked")
	public List<Tipodebito> TipoDebitoxTipoDebito(Session session, int idTipoDebito) throws Exception {
        List<Tipodebito> lisTipodb;

        Criteria criteria = session.createCriteria(Tipodebito.class)
        	    .add( Restrictions.eq("idtipodebito", idTipoDebito))
                .add( Restrictions.eq("idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisTipodb = (List<Tipodebito>)criteria.list();

        return lisTipodb;
    }
	@SuppressWarnings("unchecked")
	public List<Tipodebito> TipoDebitos(Session session) throws Exception {
        List<Tipodebito> lisTipodb;

        Criteria criteria = session.createCriteria(Tipodebito.class)
        	    .add( Restrictions.eq("idestado", (int)1))
                .addOrder( Order.asc("nombre"));

        lisTipodb = (List<Tipodebito>)criteria.list();

        return lisTipodb;
    }
	
	
}
