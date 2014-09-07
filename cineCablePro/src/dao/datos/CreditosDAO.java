package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Creditos;

public class CreditosDAO {

	public int maxIdCreditos(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idcredito) as max from Creditos ").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Creditos getCreditoById(Session session, int idcredito) throws Exception {
		Creditos creditos = null;
		
		Criteria criteria = session.createCriteria(Creditos.class)
				.add( Restrictions.eq("idcredito", idcredito));
		
		creditos = (Creditos) criteria.uniqueResult();
		
		return creditos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Creditos> lisCreditosActivosByFactura(Session session, String idfactura, int idempresa) throws Exception {
		List<Creditos> lisCreditos = null;
		
		String hql = " from Creditos ";
		hql += " where idfactura = :idfactura ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setString("idfactura", idfactura)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 3);
		
		lisCreditos = (List<Creditos>) query.list();
		
		return lisCreditos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Creditos> lisCreditosActivosByCuenta(Session session, int idcuenta, int idempresa) throws Exception {
		List<Creditos> lisCreditos = null;
		
		String hql = " from Creditos ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 3);
		
		lisCreditos = (List<Creditos>) query.list();
		
		return lisCreditos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Creditos> lisCreditosUsadosByFactura(Session session, String idfactura, int idempresa) throws Exception {
		List<Creditos> lisCreditos = null;
		
		String hql = " from Creditos ";
		hql += " where idfactura = :idfactura ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setString("idfactura", idfactura)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 4);
		
		lisCreditos = (List<Creditos>) query.list();
		
		return lisCreditos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Creditos> lisCreditosUsadosByCuenta(Session session, int idcuenta, int idempresa) throws Exception {
		List<Creditos> lisCreditos = null;
		
		String hql = " from Creditos ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 4);
		
		lisCreditos = (List<Creditos>) query.list();
		
		return lisCreditos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Creditos> lisCreditosByFechas(Session session, int idcuenta, int idempresa, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Creditos> lisCreditos = null;
		
		String hql = " from Creditos ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and empresa.idempresa = :idempresa ";
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
		
		lisCreditos = (List<Creditos>) query.list();
		
		return lisCreditos;
	}
	
	public void actualizarCreditos(Session session, Creditos creditos) throws Exception {
		session.update(creditos);
	}
	
	public void ingresarCreditos(Session session, Creditos creditos) throws Exception {
		session.save(creditos);
	}
}
