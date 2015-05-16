package net.cinecable.service.imp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Factura;

import net.cinecable.dao.IFacturaDao;
import net.cinecable.service.IFacturaService;

@Stateless
public class FacturaService implements IFacturaService {

	@EJB
	IFacturaDao iFacturaDao;

	@Override
	public Factura getFacturabyReferenciaSecuencia(Long secuencia) {
		return iFacturaDao.getFacturabyReferenciaSecuencia(secuencia);
	}

}
