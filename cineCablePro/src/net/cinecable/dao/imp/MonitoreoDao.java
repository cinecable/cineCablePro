package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IMonitoreoDao;
import net.cinecable.model.base.MonitoreoOrden;

@Stateless
public class MonitoreoDao extends GenericDao<MonitoreoOrden, Long> implements IMonitoreoDao {

	public MonitoreoDao() {
		super(MonitoreoOrden.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MonitoreoOrden getMonitoreobyOrden(Long codOrden) {
		StringBuilder sql = new StringBuilder();
		sql.append("from MonitoreoOrden o ");
		sql.append("where o.orden.idOrdenes=:codord");
		Query query = em.createQuery(sql.toString());
		query.setParameter("codord", codOrden);
		List<MonitoreoOrden> result = query.list();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

}
