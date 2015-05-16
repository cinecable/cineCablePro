package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import pojo.annotations.Tipomenu;
import net.cinecable.dao.ITipoMenuDao;
@Stateless
public class TipoMenuDao extends GenericDao<Tipomenu, Integer> implements ITipoMenuDao{

	public TipoMenuDao() {
		super(Tipomenu.class);
	}

}
