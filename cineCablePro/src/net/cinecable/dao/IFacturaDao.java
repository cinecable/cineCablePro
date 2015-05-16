package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Factura;

@Local
public interface IFacturaDao extends IGenericDao<Factura, Long> {
	Factura getFacturabyReferenciaSecuencia(Long secuencia);
}
