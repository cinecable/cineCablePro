package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.dm.PersonaDM;
import net.cinecable.enums.TipoPersona;
import net.cinecable.exception.EntidadNoBorradaException;
import net.cinecable.exception.EntidadNoGrabadaException;
import pojo.annotations.Persona;
@Local
public interface IPersonaServices {
	void insPersona(PersonaDM personaDM) throws EntidadNoGrabadaException;
	void delPersona(PersonaDM personaDM) throws EntidadNoBorradaException;
	List<Persona> getPersonas();
	List<Persona> getListaPersonasbyTipo(TipoPersona tipo);
	void updPersona(PersonaDM personaDM) throws Exception;
}
