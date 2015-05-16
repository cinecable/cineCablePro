package dao.datos;


import java.util.List;

import net.cinecable.model.base.Ordenes;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class TbordenesDAO {
	
	public int maxIdordenes(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idOrdenes) as max from Ordenes").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Ordenes ordenesById(Session session, Long idordenes) throws Exception {
		Ordenes ordenes = null;

        Criteria criteria = session.createCriteria(Ordenes.class)
        		.add(Restrictions.eq("idOrdenes", idordenes))
        		.createAlias("estado", "est");

        ordenes = (Ordenes)criteria.uniqueResult();

        return ordenes;
    }
	
	@SuppressWarnings("unchecked")
	public List<Ordenes> lisOrdenesHijasByIdPadre(Session session, Long idordenes) throws Exception {
		List<Ordenes> lisOrdenes = null;
		
		String hql = " select hijo ";
		hql += " from Ordenes as padre, Ctasprod prodpadre, Ordenes hijo ";
		hql += " where padre.idOrdenes = :idordenes ";
		hql += " and prodpadre.idprodcuentas = padre.producto.idprodcuentas ";
		hql += " and hijo.idproductoprincipal = prodpadre.producto.idproducto "; 
		hql += " and hijo.cuentaCliente.idcuenta = padre.cuentaCliente.idcuenta ";
		hql += " and  padre.idproductoprincipal = 0 ";

		
		Query query = session.createQuery(hql)
				.setLong("idordenes", idordenes);
		
		lisOrdenes = (List<Ordenes>)query.list();
		
		return lisOrdenes;
	}
	
	public void ingresarOrdenes(Session session, Ordenes ordenes) throws Exception {
		session.save(ordenes);
	}
	
	public void actualizarOrdenes(Session session, Ordenes ordenes) throws Exception {
		session.update(ordenes);
	}
	
}
