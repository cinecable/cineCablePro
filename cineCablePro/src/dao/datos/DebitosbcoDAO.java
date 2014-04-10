
package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Debitobco;


public class DebitosbcoDAO {
	@SuppressWarnings("unchecked")
	public List<Debitobco> DebitosbcoxTipoDebito(Session session, int idTipoDebito) throws Exception {
        List<Debitobco> lisDebitobco;

        Criteria criteria = session.createCriteria(Debitobco.class)
                .add( Restrictions.eq("tipodebito.idtipodebito", idTipoDebito))
                .add( Restrictions.eq("idestado", (int)1))
                .addOrder( Order.asc("tipodebitonombre"));

        lisDebitobco = (List<Debitobco>)criteria.list();

        return lisDebitobco;
    }
	
}
