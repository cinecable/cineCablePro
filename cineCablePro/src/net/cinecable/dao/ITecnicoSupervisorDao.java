package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.model.base.TecnicoSupervisor;

@Local
public interface ITecnicoSupervisorDao extends IGenericDao<TecnicoSupervisor, Long> {
	TecnicoSupervisor getSupervisorbyIdTecnico(Long idTec);
}
