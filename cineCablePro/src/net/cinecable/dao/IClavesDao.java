package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Claves;

@Local
public interface IClavesDao extends IGenericDao<Claves, Integer>{
	Claves getClavebyUsuario(int idUduario);
}
