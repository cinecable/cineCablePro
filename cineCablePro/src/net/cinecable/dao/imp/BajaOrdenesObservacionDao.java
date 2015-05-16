package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.IBajaOrdenesObservacionDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.BajaOrdenesObservacion;

import org.hibernate.Query;

import pojo.annotations.Tipooperacion;

@Stateless
public class BajaOrdenesObservacionDao extends GenericDao<BajaOrdenesObservacion, Long> implements IBajaOrdenesObservacionDao {

	public BajaOrdenesObservacionDao() {
		super(BajaOrdenesObservacion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BajaOrdenesObservacion> getParametroBajaOrdenesObservacion(Tipooperacion tipop) {
		StringBuilder sql = new StringBuilder("from BajaOrdenesObservacion o ");
		sql.append("where o.tipoOperacion=:tipop and o.estado=:est ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tipop", tipop);
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<BajaOrdenesObservacion> result = query.list();
		return result;
	}
}
