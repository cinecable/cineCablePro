package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Persona;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.ControlBodega;

@Local
public interface IControlBodegaDao extends IGenericDao<ControlBodega, Long> {

	List<ControlBodega> getControlBodegabyTecnicoYestado(Persona tecnico);
}
