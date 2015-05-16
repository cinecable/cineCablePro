package net.cinecable.service;

import javax.ejb.Local;

import net.cinecable.model.base.ParametroMaterialOrdenes;

@Local
public interface IParametroMaterialService {

	ParametroMaterialOrdenes getParametroByIdTipOperacion(Long tipOperacion);

}
