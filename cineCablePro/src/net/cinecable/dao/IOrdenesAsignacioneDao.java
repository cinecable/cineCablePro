package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;

@Local
public interface IOrdenesAsignacioneDao extends IGenericDao<OrdenesAsignaciones, Long> {

	OrdenesAsignaciones getOrdenbyId(Ordenes orden);
}
