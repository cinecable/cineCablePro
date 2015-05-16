package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IOrdenesAsignacioneDao;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;

@Stateless
public class OrdenesAsignacionesDao extends GenericDao<OrdenesAsignaciones, Long> implements IOrdenesAsignacioneDao {

	public OrdenesAsignacionesDao() {
		super(OrdenesAsignaciones.class);
	}

	@Override
	public OrdenesAsignaciones getOrdenbyId(Ordenes orden) {
		StringBuilder sql = new StringBuilder();
		sql.append("from OrdenesAsignaciones o ");
		sql.append("where o.orden=:ord");
		Query query = em.createQuery(sql.toString());
		query.setParameter("ord", orden);
		List<OrdenesAsignaciones> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
