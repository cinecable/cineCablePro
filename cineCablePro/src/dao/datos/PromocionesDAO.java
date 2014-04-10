package dao.datos;

import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Promociones;

public class PromocionesDAO {

	public Promociones getPromocionesVip(Session session, int idtipocliente, int idproducto, int idempresa) throws Exception {
		Promociones promociones = null;
		
		String hql = " from Promociones ";
		hql += " where idtipocliente = :idtipocliente ";
		hql += " and producto.idproducto = :idproducto ";
		hql += " and idempresa = :idempresa ";
		hql += " and fcaducidad >= :hoy ";
		hql += " and idestado >= :idestado ";
		
		Calendar hoy = Calendar.getInstance();
				
		Query query = session.createQuery(hql)
				.setInteger("idtipocliente", idtipocliente)
				.setInteger("idproducto", idproducto)
				.setInteger("idempresa", idempresa)
				.setDate("hoy", hoy.getTime())
				.setInteger("idestado", 1);
		
		promociones = (Promociones) query.uniqueResult();
		
		return promociones;
	}
}
