package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;
import net.cinecable.model.base.TecnicoSupervisor;

@Local
public interface IOrdenesAsignacionesService {

	void guardarInfoAsignacion(List<OrdenesAsignaciones> list, List<Ordenes> ordenes, TecnicoSupervisor tecsupinfo);
	
	OrdenesAsignaciones getOrdenbyId(Ordenes orden);
}
