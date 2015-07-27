package dao.datos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.custom.IngresosEgresosCierreCaja;

public class EgresoDAO {

	public EgresoDAO() {
	}
	
	@SuppressWarnings("unchecked")
	public List<IngresosEgresosCierreCaja> lisSumEgresosByFechas(Session session, int idusuario, int idempresa, Date fechaDesde, Date fechaHasta) throws Exception {
		List<IngresosEgresosCierreCaja> lisIngresosEgresosCierreCaja = null;
		
		String hql = " select new pojo.annotations.custom.IngresosEgresosCierreCaja(e.usuario.idusuario, f.idfpago, f.nombre, sum(e.valor)) ";
		hql += " from Egreso e, Fpago f ";
		hql += " where (e.fechaegreso between :fechadesde and :fechahasta) ";
		hql += " and e.usuario.idusuario = :idusuario ";
		hql += " and e.empresa.idempresa = :idempresa ";
		hql += " and f.idfpago = e.fpago.idfpago ";
		hql += " group by e.usuario.idusuario,f.idfpago,f.nombre ";
		
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
		
		lisIngresosEgresosCierreCaja = (List<IngresosEgresosCierreCaja>) query.list();
		
		return lisIngresosEgresosCierreCaja;
	}
}
