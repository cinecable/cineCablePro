
package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pojo.annotations.Debitobco;


public class DebitosbcoDAO {
	
	public int maxIddebitobco(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(iddebitobco) as max from Debitobco ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
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
	
	public Debitobco getDebitobcoByIdcuenta(Session session, int idcuenta) throws Exception {
		Debitobco debitobco = null;
		
		Criteria criteria = session.createCriteria(Debitobco.class)
				.add( Restrictions.eq("idcuenta",idcuenta))
				.createAlias("bancos", "bco",1)
				.createAlias("bancosEmisor", "bcoE",1)
				.createAlias("tipodebito", "tdbo",1);
				
		debitobco = (Debitobco) criteria.uniqueResult();
		
		return debitobco;		
		
	}
	
	public void saveDebitobco(Session session, Debitobco debitobco) throws Exception {
		session.save(debitobco);
	}
	
	public void updateDebitobco(Session session, Debitobco debitobco) throws Exception {
		session.update(debitobco);
	}
	
}
