package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Ctacliente;
@Local
public interface ICtaClienteDao extends IGenericDao<Ctacliente, Integer>{
	List<Ctacliente> getCtaByCliente(String documento);
}
