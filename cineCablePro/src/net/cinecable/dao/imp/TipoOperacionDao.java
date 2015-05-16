package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.ITipoOperacionDao;
import net.cinecable.enums.Estados;
import pojo.annotations.Tipooperacion;

@Stateless
public class TipoOperacionDao extends GenericDao<Tipooperacion, Long> implements ITipoOperacionDao {

	public TipoOperacionDao() {
		super(Tipooperacion.class);

	}

	@Override
	public List<Tipooperacion> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("from Tipooperacion o ");
		sql.append("where o.idestado=:estado");
		Query query = em.createQuery(sql.toString());
		query.setParameter("estado", Estados.ACTIVO.getDescription());
		List<Tipooperacion> tipoOperacion = query.list();
		return tipoOperacion;
	}

}
