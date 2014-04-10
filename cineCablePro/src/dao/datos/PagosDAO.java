package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Pagos;

public class PagosDAO {

	public int maxIdPagos(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idpago) as max from Pagos").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Pagos getPagosById(Session session, int idpago) throws Exception {
		Pagos pagos = null;
		
		Criteria criteria = session.createCriteria(Pagos.class)
				.add( Restrictions.eq("idpago", idpago))
				.createAlias("estado", "e")
				.createAlias("usuario", "u")
				.createAlias("empresa", "m");
		
		pagos = (Pagos) criteria.uniqueResult();
		
		return pagos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagos> lisPagosAbonosActivosByFactura(Session session, String idfactura, int idempresa) throws Exception {
		List<Pagos> lisPagos = null;
		
		String hql = " from Pagos ";
		hql += " where idfactura = :idfactura ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setString("idfactura", idfactura)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1);
		
		lisPagos = (List<Pagos>) query.list();
		
		return lisPagos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagos> lisPagosAbonosActivosByCuenta(Session session, int idcuenta, int idempresa) throws Exception {
		List<Pagos> lisPagos = null;
		
		String hql = " from Pagos ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1);
		
		lisPagos = (List<Pagos>) query.list();
		
		return lisPagos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pagos> lisPagosByFechas(Session session, int idcuenta, int idempresa, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Pagos> lisPagos = null;
		
		String hql = " from Pagos ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and idfactura is not null ";
		hql += " and trim(both ' ' from idfactura) <> '' ";
		hql += " and fecha between :fechaDesde and :fechaHasta ";
		hql += " order by fecha desc ";
		
		Calendar desde = Calendar.getInstance();
		desde.setTime(fechaDesde);
		desde.set(Calendar.HOUR_OF_DAY, 0);
		desde.set(Calendar.MINUTE, 0);
		desde.set(Calendar.SECOND, 0);
		
		Calendar hasta = Calendar.getInstance();
		hasta.setTime(fechaHasta);
		hasta.set(Calendar.HOUR_OF_DAY, 23);
		hasta.set(Calendar.MINUTE, 59);
		hasta.set(Calendar.SECOND, 59);
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idempresa", idempresa)
				.setDate("fechaDesde", desde.getTime())
				.setDate("fechaHasta", hasta.getTime())
				.setMaxResults(10);
		
		lisPagos = (List<Pagos>) query.list();
		
		return lisPagos;
	}
	
	public void actualizarPago(Session session, Pagos pagos) throws Exception {
		session.update(pagos);
	}
	
	public void ingresarPago(Session session, Pagos pagos) throws Exception {
		session.save(pagos);
	}
}
