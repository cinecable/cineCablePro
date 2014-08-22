package dao.datos;

import global.Parametro;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;

import pojo.annotations.Factura;

public class FacturaDAO {

	public int maxIdSecuencia(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idsecuencia) as max from Factura").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public int maxIdGeneracion(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idgeneracion) as max from Factura").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public Factura getFacturaById(Session session, int idsecuencia) throws Exception {
		Factura factura = null;
		
		Criteria criteria = session.createCriteria(Factura.class)
				.add( Restrictions.eq("idsecuencia", idsecuencia));
				//.add( Restrictions.eq("idestado", 3));
		
		factura = (Factura) criteria.uniqueResult();
		
		return factura;
	}
	
	@SuppressWarnings("unchecked")
	public List<Factura> lisFacturaParaFacturacion(Session session, int idcuenta) throws Exception {
		List<Factura> lisFactura = null;
		
		/*Criteria criteria = session.createCriteria(Factura.class)
				.add( Restrictions.eq("idcuenta", idcuenta))
				.add( Restrictions.and(Restrictions.isNotNull("idfactura"), Restrictions.ne("idfactura","")))
				.add( Restrictions.eq("idestado", 1));
		
		lisFactura = (List<Factura>) criteria.list();*/
		
		String hql = " from Factura ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and idfactura is not null ";
		hql += " and trim(both ' ' from idfactura) <> '' ";
		hql += " and idestado in(:idestadoPendiente, :idestadoMora) ";
		hql += " order by idgeneracion ";
				
		Query query = session.createQuery(hql)
		.setInteger("idcuenta", idcuenta)
		.setInteger("idestadoPendiente", Parametro.FACTURA_ESTADO_PENDIENTE)
		.setInteger("idestadoMora", Parametro.FACTURA_ESTADO_MORA);
		
		lisFactura = (List<Factura>) query.list();
		
		return lisFactura;
	}
	
	@SuppressWarnings("unchecked")
	public List<Factura> lisFacturaParaCargosGenerados(Session session, int idcuenta) throws Exception {
		List<Factura> lisFactura = null;
		
		/*Criteria criteria = session.createCriteria(Factura.class)
				.add( Restrictions.eq("idcuenta", idcuenta))
				.add( Restrictions.or(Restrictions.isNull("idfactura"), Restrictions.eq("idfactura"," ")))
				.add( Restrictions.eq("idestado", 1));
				
		lisFactura = (List<Factura>) criteria.list();*/
		
		String hql = " from Factura ";
		hql += " where idcuenta = :idcuenta ";
		hql += " and ( idfactura is null or trim(both ' ' from idfactura) = '' ) ";
		hql += " and idestado = :idestado ";
				
		Query query = session.createQuery(hql)
		.setInteger("idcuenta", idcuenta)
		.setInteger("idestado", 3);
		
		lisFactura = (List<Factura>) query.list();
		
		return lisFactura;
	}
	
	@SuppressWarnings("unchecked")
	public List<Factura> lisFacturaByFechas(Session session, int idcuenta, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Factura> lisFactura = null;
		
		String hql = " from Factura ";
		hql += " where idcuenta = :idcuenta ";
		//hql += " and idestado = :idestado ";
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
		.setDate("fechaDesde", desde.getTime())
		.setDate("fechaHasta", hasta.getTime())
		.setMaxResults(10);
		//.setInteger("idestado", 3);
		
		lisFactura = (List<Factura>) query.list();
		
		return lisFactura;
	}
	
	public void obtenerRubrosMora(Session session, int idcuenta, Object args[]) throws Exception {
		ObtenerRubrosMora obtenerRubrosMora = new ObtenerRubrosMora(idcuenta, args);
		session.doWork(obtenerRubrosMora);
	}
	
	public void actualizarFactura(Session session, Factura factura) throws Exception {
		session.update(factura);
	}
	
	public void ingresarFactura(Session session, Factura factura) throws Exception {
		session.save(factura);
	}
	
	public class ObtenerRubrosMora implements Work {
		
		private Object resultado;
		private int idcuenta;
		private Object args[];
		
		public ObtenerRubrosMora(int idcuenta, Object args[]) {
			this.idcuenta = idcuenta;
			this.args = args;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			String query = "{ call facturacion(?, ?) }";
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, idcuenta);
			statement.setTimestamp(2,
					new java.sql.Timestamp(new Date().getTime()));
			statement.execute();

			ResultSet result = statement.getResultSet();
			if (result != null) {
				if (result.next()) {
					args[0] = result.getArray(1).getArray();
				}
			}

			statement.close();
		}

		public Object getResultado() {
			return resultado;
		}

		public Object[] getArgs() {
			return args;
		}
	}
}
