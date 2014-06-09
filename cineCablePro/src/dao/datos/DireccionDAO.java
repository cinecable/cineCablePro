package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Direccion;

public class DireccionDAO {

	public int maxIddireccion(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(iddireccion) as max from Direccion ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public List<Direccion> lisDireccionByIdcuenta(Session session, int idcuenta) throws Exception {
		List<Direccion> lisDireccion = null;
		
		String hql = " select d from Direccion as d inner join d.calleprincipal inner join d.callesecundaria ";
		hql += " where d.ctacliente.idcuenta = :idcuenta ";
		hql += " order by iddireccion ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta);
		
		lisDireccion = (List<Direccion>) query.list();
		
		return lisDireccion;
	}
	
	public Direccion direccionByIdcuentaTipo(Session session, int idcuenta, String tipo) throws Exception {
		Direccion direccion = null;
		
		/*String hql = " select d from Direccion as d inner join d.calleprincipal inner join d.callesecundaria ";
		hql += " where d.ctacliente.idcuenta = :idcuenta ";
		hql += " and d.correspondencia = :tipo ";
		hql += " order by iddireccion ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setString("tipo", tipo);
		
		direccion = (Direccion)query.uniqueResult();*/
		
		Criteria criteria = session.createCriteria(Direccion.class)
				.add(Restrictions.eq("ctacliente.idcuenta", idcuenta))
				.add(Restrictions.eq("correspondencia", tipo))
				.createAlias("calleprincipal", "cp", Criteria.LEFT_JOIN)
				.createAlias("callesecundaria", "cs", Criteria.LEFT_JOIN)
				.createAlias("ubicacion", "ub", Criteria.LEFT_JOIN)
				.createAlias("referenciadir", "rf", Criteria.LEFT_JOIN)
				//.createAlias("ctacliente", "cta", Criteria.LEFT_JOIN)
				.createAlias("edificio", "ed", Criteria.LEFT_JOIN)
				.createAlias("tiposector", "ts", Criteria.LEFT_JOIN)
				.createAlias("nodos", "no", Criteria.LEFT_JOIN)
				.createAlias("sector", "se", Criteria.LEFT_JOIN)
				.addOrder(Order.asc("iddireccion"));
		
		direccion = (Direccion)criteria.uniqueResult();
		
		return direccion;
	}
	
	public Direccion dirxCuenta(Session session,int idcuenta) throws Exception {
		Direccion direccion = null;
		String corresp ="I";
		
		String hql = " from Direccion ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idestado = :idestado ";
		hql += " and correspondencia = :correspondencia ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idestado", 1)
				.setString("correspondencia", corresp);
		direccion = (Direccion) query.uniqueResult();
		
		return direccion;
	}
	
	public void saveDireccion(Session session, Direccion direccion) throws Exception {
		session.save(direccion);
	}
	
	public void updateDireccion(Session session, Direccion direccion) throws Exception {
		session.update(direccion);
	}
}
