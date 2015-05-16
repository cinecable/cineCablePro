package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.MonitoreoOrden;

@Local
public interface IMonitoreoDao extends IGenericDao<MonitoreoOrden, Long> {

	MonitoreoOrden getMonitoreobyOrden(Long codOrden);

}
