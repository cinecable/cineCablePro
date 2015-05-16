package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.exception.EntidadNoEncontradaException;

import pojo.annotations.Menu;

@Local
public interface IMenuServices {
	List<Menu> getMenu();
	Menu getMenuById(Integer id) throws EntidadNoEncontradaException;
	List<Menu> getMenubyNivelTipoMenu(Integer nivel, Integer tipoMenu);
	List<Menu> getMenubyNivel(Integer nivel);
	List<Menu> getMenubyNivelIdPadre(Integer idPadre, Integer nivel);
}
