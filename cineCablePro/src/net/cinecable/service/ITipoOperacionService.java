package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Tipooperacion;

@Local
public interface ITipoOperacionService {

	List<Tipooperacion> getAll();

}
