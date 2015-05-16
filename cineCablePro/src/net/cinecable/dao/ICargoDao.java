package net.cinecable.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Cargos;

@Local
public interface ICargoDao extends IGenericDao<Cargos, Long> {

	List<Cargos> getCargosByIdgeneracion(Integer idGeneracion);

	public String generarCargos(int idcuenta, Date fecha, int estadoCuenta, int codProducto, boolean cargoNegativo, int idUsuario);

}
