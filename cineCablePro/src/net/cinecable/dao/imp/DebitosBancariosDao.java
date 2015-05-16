package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IDebitosBancariosDao;
import net.cinecable.model.base.GeneracionDebitos;

@Stateless
public class DebitosBancariosDao extends GenericDao<GeneracionDebitos, Long> implements IDebitosBancariosDao {

	public DebitosBancariosDao() {
		super(GeneracionDebitos.class);
	}

}
