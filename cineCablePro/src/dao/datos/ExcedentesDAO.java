package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Excedentes;

public class ExcedentesDAO {

	public int maxIdexcedente(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idexcedente) as max from Excedentes").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Excedentes getExcedenteById(Session session, int idexcedente) throws Exception {
		Excedentes excedentes = null;
		
		Criteria criteria = session.createCriteria(Excedentes.class)
				.add( Restrictions.eq("idexcedente", idexcedente));
		
		excedentes = (Excedentes) criteria.uniqueResult();
		
		return excedentes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Excedentes> lisExcedentesActivosByCuenta(Session session, int idcuenta, int idempresa) throws Exception {
		List<Excedentes> lisExcedentes = null;
		
		String hql = " from Excedentes ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
		
		Query query = session.createQuery(hql)
				.setInteger("idcuenta", idcuenta)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 3);
		
		lisExcedentes = (List<Excedentes>) query.list();
		
		return lisExcedentes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Excedentes> lisExcedentesByFechas(Session session, int idcuenta, int idempresa, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Excedentes> lisExcedentes = null;
		
		String hql = " from Excedentes ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
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
				.setInteger("idestado", 3)
				.setDate("fechaDesde", desde.getTime())
				.setDate("fechaHasta", hasta.getTime())
				.setMaxResults(10);
		
		lisExcedentes = (List<Excedentes>) query.list();
		
		return lisExcedentes;
	}
	
	public void actualizarExcedentes(Session session, Excedentes excedentes) throws Exception {
		session.update(excedentes);
	}
	
	public void ingresarExcedentes(Session session, Excedentes excedentes) throws Exception {
		session.save(excedentes);
	}
}
