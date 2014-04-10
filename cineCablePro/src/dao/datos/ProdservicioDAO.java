package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Prodservicio;

public class ProdservicioDAO {

	@SuppressWarnings("unchecked")
	public List<Prodservicio> lisProdservicioByIdProducto(Session session, int idproducto) throws Exception {
		List<Prodservicio> lisProdservicio = null;
		
		String hql = " from Prodservicio as ps inner join ps.servicio as s inner join s.costoservicio ";
		hql += " where ps.producto.idproducto = :idproducto ";
		
		Query query = session.createQuery(hql)
				.setInteger("idproducto", idproducto);
		
		lisProdservicio = (List<Prodservicio>) query.list();
		
		return lisProdservicio;
	}
}
