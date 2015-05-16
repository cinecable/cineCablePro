package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.ParametroMaterialOrdenes;

@Local
public interface IParametroMaterialOrdenesDao extends IGenericDao<ParametroMaterialOrdenes, Long> {

	ParametroMaterialOrdenes getParametroByIdTipOperacion(Long tipOperacion);

}
