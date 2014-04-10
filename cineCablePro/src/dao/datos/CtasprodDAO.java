package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Ctasprod;

public class CtasprodDAO {

	public int maxIdprodcuentas(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idprodcuentas) as max from Ctasprod ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ctasprod> lisCtasprod(Session session, int idcuenta, int idempresa) throws Exception {
		List<Ctasprod> lisCtasprod = null;
		
		String hql = " select  from Ctasprod as c join c.producto as p ";
		hql += " where c.ctacliente.idcuenta = :idcuenta ";
		hql += " and c.estado.idestado = :idestado ";
		hql += " and c.empresa.idempresa = :idempresa ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idestado", 1)
				.setInteger("idempresa", idempresa);
		
		lisCtasprod = (List<Ctasprod>) query.list();
		
		return lisCtasprod;
	}
	
	public void saveCtasprod(Session session, Ctasprod ctasprod) throws Exception {
		session.save(ctasprod);
	}
	
	public void updateCtasprod(Session session, Ctasprod ctasprod) throws Exception {
		session.update(ctasprod);
	}
}
