package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Usuariomenu;
@Local
public interface IUsuarioMenuDao extends IGenericDao<Usuariomenu, Long>{
	List<Usuariomenu> getUsuarioMenuByIdUsuario(Integer idUsua);
	List<Usuariomenu> getUsuarioMenuByIdUsuarioTipoUsuario(Integer idUsua,Integer tipoMenu);
}
