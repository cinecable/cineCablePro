package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Menu;
@Local
public interface IMenuDao extends IGenericDao<Menu, Integer>{
	List<Menu> getMenubyNivel(Integer nivel);
	List<Menu> getMenubyNivelTipoMenu(Integer nivel,Integer tipoMenu);
	List<Menu> getMenubyNivelIdPadre(Integer idPadre, Integer nivel);
}
