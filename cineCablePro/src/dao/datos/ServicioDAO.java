package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import pojo.annotations.Servicio;

public class ServicioDAO {

	@SuppressWarnings("unchecked")
	public List<Servicio> lisServicioByIdCuenta(Session session, int idcuenta) throws Exception {
		List<Servicio> lisServicio = null;
		
		String sql = " select {s.*} ";
		sql += " from servicio s, ";
		sql += " prodservicio prs, ";
		sql += " ctasprod cpr ";
		sql += " where cpr.idcuenta = " + idcuenta ;
		sql += " and cpr.idestado = 1 ";
		sql += " and prs.idproducto = cpr.idproducto ";
		sql += " and s.idservicio = prs.idservicio ";
		sql += " and s.idestado = 1 ";
		
		SQLQuery sqlquery = session.createSQLQuery(sql)
				.addEntity("s", Servicio.class);
		
		lisServicio = (List<Servicio>) sqlquery.list();
		
		return lisServicio;
	}
	
	@SuppressWarnings("unchecked")
	public List<Servicio> lisServicioByIdProducto(Session session, int idproducto) throws Exception {
		List<Servicio> lisServicio = null;
		
		String hql = " select s from Prodservicio as p inner join p.servicio as s inner join s.costoservicio ";
		hql += " where p.producto.idproducto = :idproducto ";
		
		Query query = session.createQuery(hql)
				.setInteger("idproducto", idproducto);
		
		lisServicio = (List<Servicio>) query.list();
		
		return lisServicio;
	}
}
