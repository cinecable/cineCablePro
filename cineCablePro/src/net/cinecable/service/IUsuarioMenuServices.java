package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.dm.UsuarioMenuDM;
import net.cinecable.exception.EntidadNoEncontradaException;

import org.primefaces.model.TreeNode;

import pojo.annotations.Usuariomenu;

@Local
public interface IUsuarioMenuServices {
	List<Usuariomenu> getUsuarioMenuByIdUsuario(Integer idUsua);
	
	List<Usuariomenu> getUsuarioMenuByIdUsuarioTipoUsuario(Integer idUsua,Integer tipoMenu);
	
	TreeNode getEstructuraUsuario(List<Usuariomenu> listaUsuaMenu) throws NumberFormatException, EntidadNoEncontradaException;
	
	void ingUsuarioMenu(UsuarioMenuDM usuarioMenuDM) throws Exception;

	void elimUsuarioMenu(UsuarioMenuDM usuarioMenuDM) throws Exception;
}
