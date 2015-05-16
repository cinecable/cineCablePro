package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Pagos;
import pojo.annotations.custom.IngresosCierreCaja;

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
	
	@SuppressWarnings("unchecked")
	public List<IngresosCierreCaja> lisSumIngresosByFechas(Session session, int idusuario, int idempresa, Date fechaDesde, Date fechaHasta) throws Exception {
		List<IngresosCierreCaja> lisIngresosCierreCaja = null;
		
		String hql = " select new pojo.annotations.custom.IngresosCierreCaja(p.usuario.idusuario, f.idfpago, f.nombre, sum(t.valpago)) ";
		hql += " from Pagos p, Tpagos t, Fpago f ";
		hql += " where (p.fecha between :fechadesde and :fechahasta) ";
		hql += " and p.usuario.idusuario = :idusuario ";
		hql += " and p.empresa.idempresa = :idempresa ";
		hql += " and t.pagos.idpago = p.idpago ";
		hql += " and f.idfpago = t.fpago.idfpago ";
		hql += " group by p.usuario.idusuario,f.idfpago,f.nombre ";
		
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
				.setTimestamp("fechadesde", desde.getTime())
				.setTimestamp("fechahasta", hasta.getTime())
				.setInteger("idusuario", idusuario)
				.setInteger("idempresa", idempresa);
		
		lisIngresosCierreCaja = (List<IngresosCierreCaja>) query.list();
		
		return lisIngresosCierreCaja;
	}
	
	public void actualizarPago(Session session, Pagos pagos) throws Exception {
		session.update(pagos);
	}
	
	public void ingresarPago(Session session, Pagos pagos) throws Exception {
		session.save(pagos);
	}
}
