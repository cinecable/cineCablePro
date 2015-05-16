package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Tipooperacion;

@Local
public interface ITipoOperacionDao extends IGenericDao<Tipooperacion, Long> {

	List<Tipooperacion> getAll();

}
