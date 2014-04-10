package dao.datos;

import global.Parametro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Cargos;

public class CargosDAO {

	public int maxIdCargos(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcargo) as max from Cargos ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosFacturaNivelDetalle(Session session, String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("idfactura", idfactura))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_SERVICIO_MIN, Parametro.CARGO_NIVEL_SERVICIO_MAX))
				.addOrder(Order.asc("nivel"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosFacturaNivelDescuento(Session session, String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("idfactura", idfactura))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_DESCUENTO_MIN, Parametro.CARGO_NIVEL_DESCUENTO_MAX))
				.addOrder(Order.asc("nivel"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosFacturaNivelImpuesto(Session session, String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("idfactura", idfactura))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_IMPUESTO_MIN, Parametro.CARGO_NIVEL_IMPUESTO_MAX))
				.addOrder(Order.asc("nivel"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosFacturaNivelImpuestoSum(Session session, String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		
		String hql = " select new Cargos(idfactura, factura.idgeneracion, nivel, motivo, sum(valpendiente)) from Cargos ";
		hql += " where idfactura = :idfactura ";
		hql += " and estado.idestado = :idestado ";
		hql += " and nivel between :nivel_impuesto_min and :nivel_impuesto_max ";
		hql += " group by idfactura, factura.idgeneracion, nivel, motivo ";
		hql += " order by nivel ";
		
		Query query = session.createQuery(hql)
				.setString("idfactura", idfactura)
				.setInteger("idestado", 3)
				.setShort("nivel_impuesto_min", Parametro.CARGO_NIVEL_IMPUESTO_MIN)
				.setShort("nivel_impuesto_max", Parametro.CARGO_NIVEL_IMPUESTO_MAX);
		
		lisCargos = (List<Cargos>) query.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelDetalle(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_SERVICIO_MIN, Parametro.CARGO_NIVEL_SERVICIO_MAX))
				.addOrder(Order.asc("motivo"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelDescuento(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_DESCUENTO_MIN, Parametro.CARGO_NIVEL_DESCUENTO_MAX))
				.addOrder(Order.asc("motivo"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelImpuesto(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.between("nivel", Parametro.CARGO_NIVEL_IMPUESTO_MIN, Parametro.CARGO_NIVEL_IMPUESTO_MAX))
				.addOrder(Order.asc("nivel"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	/**
	 * Retorna una lista de tipo Cargos con los impuestos sumados por tipo
	 * Depende del constructor 
	 * public Cargos(String idfactura, int idgeneracion, short nivel, String motivo, Double valpendiente){
	 *	this.idcargo = 0;
	 *	this.estado = new Estado(0, null);
	 *	Factura factura = new Factura();
	 *	factura.setIdgeneracion(idgeneracion);
	 *	this.factura = factura;
	 *	this.usuario = new Usuario();
	 *	this.empresa = new Empresa();
	 *	this.idfactura = idfactura;
	 *	this.valcargo = 0;
	 *	this.nivel = nivel;
	 *	this.fecha = null;
	 *	this.motivo = motivo;
	 *	this.valpendiente = valpendiente.floatValue();
	 *	this.valbase = 0f;
	 *	this.descuento = 0f;
	 *	this.idrubropadre = 0;
	 *}
	 * @param session
	 * @param idgeneracion
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelImpuestoSum(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;

		String hql = " select new Cargos(idfactura, factura.idgeneracion, nivel, motivo, sum(valpendiente)) from Cargos ";
		hql += " where factura.idsecuencia = :idsecuencia ";
		hql += " and estado.idestado = :idestado ";
		hql += " and nivel between :nivel_impuesto_min and :nivel_impuesto_max ";
		hql += " group by idfactura, factura.idgeneracion, nivel, motivo ";
		hql += " order by motivo ";
		
		Query query = session.createQuery(hql)
				.setInteger("idsecuencia", idsecuencia)
				.setInteger("idestado", 3)
				.setShort("nivel_impuesto_min", Parametro.CARGO_NIVEL_IMPUESTO_MIN)
				.setShort("nivel_impuesto_max", Parametro.CARGO_NIVEL_IMPUESTO_MAX);
		
		lisCargos = (List<Cargos>) query.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelIva(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.eq("nivel", Parametro.CARGO_NIVEL_IVA_SERVICIOS));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionNivelIce(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.add( Restrictions.eq("nivel", Parametro.CARGO_NIVEL_ICE_SERVICIOS));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargos> lisCargosGeneracionById(Session session, int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		
		Criteria criteria = session.createCriteria(Cargos.class)
				.add( Restrictions.eq("factura.idsecuencia", idsecuencia))
				.add( Restrictions.eq("estado.idestado", 3))
				.addOrder(Order.asc("nivel"));
				
		lisCargos = (List<Cargos>) criteria.list();
		
		return lisCargos;
	}
	
	public void actualizarCargos(Session session, Cargos cargos) throws Exception {
		session.update(cargos);
	}
	
	public void ingresarCargos(Session session, Cargos cargos) throws Exception {
		session.save(cargos);
	}
}
