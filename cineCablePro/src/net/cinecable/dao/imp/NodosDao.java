package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import pojo.annotations.Nodos;
import net.cinecable.dao.INodosDao;
@Stateless
public class NodosDao extends GenericDao<Nodos, Long> implements INodosDao  {

	public NodosDao() {
		super(Nodos.class);
		// TODO Auto-generated constructor stub
	}

}
