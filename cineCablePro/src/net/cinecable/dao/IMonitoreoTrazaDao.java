package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.MonitoreoTraza;

@Local
public interface IMonitoreoTrazaDao extends IGenericDao<MonitoreoTraza, Long> {

}
