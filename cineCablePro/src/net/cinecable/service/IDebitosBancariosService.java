package net.cinecable.service;

import javax.ejb.Local;

import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.GeneracionDebitos;

@Local
public interface IDebitosBancariosService {
	void guardar(GeneracionDebitos debito) throws EntidadNoGrabadaException;
}
