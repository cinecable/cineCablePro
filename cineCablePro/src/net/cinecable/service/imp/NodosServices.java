package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Nodos;
import net.cinecable.dao.INodosDao;
import net.cinecable.service.Inodos;

@Stateless
public class NodosServices implements Inodos {
	@EJB
	private INodosDao nodosDao;

	@Override
	public List<Nodos> getAllNodos() {
		return nodosDao.obtenerTodos();
	}

}
