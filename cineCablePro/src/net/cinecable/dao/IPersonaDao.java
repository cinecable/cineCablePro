package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.enums.TipoPersona;

import pojo.annotations.Persona;

@Local
public interface IPersonaDao extends IGenericDao<Persona, Long> {

	List<Persona> getListaPersonasbyTipo(TipoPersona tipo);

}
