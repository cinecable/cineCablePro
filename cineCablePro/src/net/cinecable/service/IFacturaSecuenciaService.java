package net.cinecable.service;

import javax.ejb.Local;

import exceptions.SecuenciaFacturaException;

import pojo.annotations.Nrosfactura;

import net.cinecable.dao.IGenericDao;

@Local
public interface IFacturaSecuenciaService extends IGenericDao<Nrosfactura, Long> {
	String consultaSecuenciaFactura() throws SecuenciaFacturaException;
}
