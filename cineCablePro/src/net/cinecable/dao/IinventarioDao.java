package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.Inventario;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;

@Local
public interface IinventarioDao extends IGenericDao<Inventario, Long> {

	Inventario getInventarioActivobyMaterial(Materiales material);

}
