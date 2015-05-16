package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Clientes;
@Local
public interface IClienteDao extends IGenericDao<Clientes, String>{
	Clientes getClienteByIdentificacion(String identificacion);
	
	Clientes getClientesByCuenta(Long idCuenta,String nroCedula);
}
