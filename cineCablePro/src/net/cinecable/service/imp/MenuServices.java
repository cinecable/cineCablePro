package net.cinecable.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Menu;
import net.cinecable.dao.IMenuDao;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.service.IMenuServices;
@Stateless
public class MenuServices implements IMenuServices{
	@EJB
	private IMenuDao iMenuDao;
	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getMenubyNivel(Integer nivel) {
		List<Menu> listaMenu= new ArrayList<Menu>();
		
		if (nivel!=0){
			listaMenu=iMenuDao.getMenubyNivel(nivel);
		}
		return listaMenu;
	}
	
	@Override
	public List<Menu> getMenubyNivelTipoMenu(Integer nivel,Integer tipoMenu) {
		List<Menu> listaMenu= new ArrayList<Menu>();
		
		if (nivel!=0){
			listaMenu=iMenuDao.getMenubyNivelTipoMenu(nivel,tipoMenu);
		}
		return listaMenu;
	}

	@Override
	public List<Menu> getMenubyNivelIdPadre(Integer idPadre, Integer nivel) {
		List<Menu> listaMenu= new ArrayList<Menu>();
		if (nivel!=0 && idPadre!=0){
			listaMenu=iMenuDao.getMenubyNivelIdPadre(idPadre, nivel);
		}
		return listaMenu;
	}

	@Override
	public Menu getMenuById(Integer id) throws EntidadNoEncontradaException {
		Menu menu= new Menu();
		if (id!=0){
			menu=iMenuDao.recuperar(id);
		}
		return menu;
	}

}
