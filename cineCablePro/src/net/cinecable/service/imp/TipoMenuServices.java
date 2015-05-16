package net.cinecable.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Tipomenu;

import net.cinecable.dao.ITipoMenuDao;
import net.cinecable.service.ITipoMenuServices;

@Stateless
public class TipoMenuServices implements ITipoMenuServices{
	@EJB
	private ITipoMenuDao iTipoMenuDao;
	@Override
	public List<Tipomenu> getTipoMenu() {
		List<Tipomenu> listaTipMenu= new ArrayList<Tipomenu>();
		listaTipMenu=iTipoMenuDao.obtenerTodos();
		return listaTipMenu;
	}

}
