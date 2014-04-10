package dao.datos;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Costoservicio;

public class CostoservicioDAO {

	@SuppressWarnings("unchecked")
	public List<Costoservicio> lisCostoservicioByIdCuenta(Session session, int idcuenta) throws Exception {
		List<Costoservicio> lisCostoservicio = null;
		
		/*select s.*,cs.* --lista de servicios
		from servicio s,
		prodservicio prs,
		ctasprod cpr,
		costoservicio cs
		where cpr.idcuenta = 2 --param
		and cpr.idestado = 1
		and prs.idproducto = cpr.idproducto
		and s.idservicio = prs.idservicio
		and s.idestado = 1
		and cs.idservicio = s.idservicio*/
		
		/*String sql = " select {cs.*}, {s.*} ";
		sql += " from servicio s, ";
		sql += " prodservicio prs, ";
		sql += " ctasprod cpr, ";
		sql += " costoservicio cs ";
		sql += " where cpr.idcuenta = " + idcuenta ;
		sql += " and cpr.idestado = 1 ";
		sql += " and prs.idproducto = cpr.idproducto ";
		sql += " and s.idservicio = prs.idservicio ";
		sql += " and cs.idservicio = s.idservicio ";*/
		
		String hql = " select cs ";
		hql += " from Prodservicio prs, ";
		hql += " Ctasprod cpr, ";
		hql += " Costoservicio cs inner join cs.servicio ";
		hql += " where cpr.ctacliente.idcuenta = " + idcuenta ;
		hql += " and cpr.estado.idestado = 1 ";
		hql += " and prs.producto.idproducto = cpr.producto.idproducto ";
		hql += " and cs.servicio.idservicio = prs.servicio.idservicio ";
		
		//String hql = " select cs ";
		//hql += " from Costoservicio cs inner join cs.servicio ";
		//hql += " where cta.idcuenta = " + idcuenta;
		//hql += " and cpr.estado.idestado = 1 ";
		//hql += " and prs.producto.idproducto = cpr.producto.idproducto ";
		//hql += " and ser.idservicio = prs.servicio.idservicio ";
		
		lisCostoservicio = session.createQuery(hql)
				.list();
		
		//lisCostoservicio = (List<Costoservicio>) sqlquery.list();
		
		return lisCostoservicio;
	}
}
