package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IPagosDao;
import pojo.annotations.Pagos;

@Stateless
public class PagosDao extends GenericDao<Pagos, Long> implements IPagosDao {

	public PagosDao() {
		super(Pagos.class);
	}

}
