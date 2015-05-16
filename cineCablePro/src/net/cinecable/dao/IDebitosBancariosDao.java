package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.GeneracionDebitos;

@Local
public interface IDebitosBancariosDao extends IGenericDao<GeneracionDebitos, Long> {

}
