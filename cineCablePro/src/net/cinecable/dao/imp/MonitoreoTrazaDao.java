package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IMonitoreoTrazaDao;
import net.cinecable.model.base.MonitoreoTraza;

@Stateless
public class MonitoreoTrazaDao extends GenericDao<MonitoreoTraza, Long> implements IMonitoreoTrazaDao {

	public MonitoreoTrazaDao() {
		super(MonitoreoTraza.class);
	}

}
