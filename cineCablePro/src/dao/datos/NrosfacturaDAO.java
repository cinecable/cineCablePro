package dao.datos;

import java.util.Calendar;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Nrosfactura;

public class NrosfacturaDAO {

	public boolean hayFacturasDisponibles(Session session, int idempresa, String serieptovta) throws Exception {
		boolean ok = false;
		
		String hql = " from Nrosfactura ";
		hql += " where idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
		hql += " and serieptovta = :serieptovta ";
		hql += " and fcaducidad >= :fcaducidad ";
		hql += " and minfactura < maxfactura ";
		
		Calendar hoy = Calendar.getInstance();
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1)
				.setString("serieptovta", serieptovta)
				.setDate("fcaducidad", hoy.getTime());
		
		Nrosfactura nrosfactura = (Nrosfactura) query.uniqueResult();
		
		if(nrosfactura != null && nrosfactura.getIdnrofactura() > 0){
			ok = true;
		}
		
		return ok;
	}
	
	public Nrosfactura getNrosfacturaByIdptovta(Session session, int idempresa, String serieptovta) throws Exception {
		Nrosfactura nrosfactura = null;
		
		String hql = " from Nrosfactura ";
		hql += " where idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
		hql += " and serieptovta = :serieptovta ";
		hql += " and fcaducidad >= :fcaducidad ";
		hql += " and minfactura < maxfactura ";
		
		Calendar hoy = Calendar.getInstance();
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1)
				.setString("serieptovta", serieptovta)
				.setDate("fcaducidad", hoy.getTime());
		
		nrosfactura = (Nrosfactura) query.uniqueResult();
		
		return nrosfactura;
	}
	
	public Nrosfactura getNrosfacturaByIdptovtaForUpdate(Session session, int idempresa, String serieptovta, int idestado) throws Exception {
		Nrosfactura nrosfactura = null;
		
		String hql = " from Nrosfactura f ";
		hql += " where f.idempresa = :idempresa ";
		hql += " and f.idestado = :idestado ";
		hql += " and f.serieptovta = :serieptovta ";
		//hql += " and f.fcaducidad >= :fcaducidad ";
		//hql += " and f.minfactura < f.maxfactura ";
		
		//Calendar hoy = Calendar.getInstance();
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", idestado)
				.setString("serieptovta", serieptovta)
				//.setDate("fcaducidad", hoy.getTime())
				.setLockOptions(LockOptions.UPGRADE);
		
		nrosfactura = (Nrosfactura) query.uniqueResult();
		
		return nrosfactura;
	}
	
	public void actualizarNrosfactura(Session session, Nrosfactura nrosfactura) throws Exception {
		session.update(nrosfactura);
	}
	
	public void ingresarNrosfactura(Session session, Nrosfactura nrosfactura) throws Exception {
		session.save(nrosfactura);
	}
}
