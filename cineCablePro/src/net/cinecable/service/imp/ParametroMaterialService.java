package net.cinecable.service.imp;

import javax.ejb.EJB;

import net.cinecable.dao.IParametroMaterialOrdenesDao;
import net.cinecable.model.base.ParametroMaterialOrdenes;
import net.cinecable.service.IParametroMaterialService;

public class ParametroMaterialService implements IParametroMaterialService {

	@EJB
	IParametroMaterialOrdenesDao iParametroMaterialDao;

	@Override
	public ParametroMaterialOrdenes getParametroByIdTipOperacion(Long tipOperacion) {
		return iParametroMaterialDao.getParametroByIdTipOperacion(tipOperacion);
	}

}
