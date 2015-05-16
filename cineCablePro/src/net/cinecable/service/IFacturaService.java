package net.cinecable.service;

import javax.ejb.Local;

import pojo.annotations.Factura;

@Local
public interface IFacturaService {
	Factura getFacturabyReferenciaSecuencia(Long secuencia);
}
