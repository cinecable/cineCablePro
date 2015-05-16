package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.ICostoServicioDao;
import pojo.annotations.Costoservicio;

@Stateless
public class CostoServicioDao extends GenericDao<Costoservicio, Long> implements ICostoServicioDao {

	public CostoServicioDao() {
		super(Costoservicio.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Costoservicio> consultarServiciosCostoPorIdTipoOperacionYTipoServicioAbrev(String tipServAbrev) {
		StringBuilder sql = new StringBuilder("from Costoservicio o ");
		sql.append("where o.servicio.tiposervicio.abreviado=:descAbrev ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("descAbrev", tipServAbrev);
		List<Costoservicio> result = query.list();
		return result;
	}

}
