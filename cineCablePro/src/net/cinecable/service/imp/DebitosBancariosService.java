package net.cinecable.service.imp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IDebitosBancariosDao;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.GeneracionDebitos;
import net.cinecable.service.IDebitosBancariosService;

@Stateless
public class DebitosBancariosService implements IDebitosBancariosService {

	@EJB
	IDebitosBancariosDao idebitoBancario;

	public void guardar(GeneracionDebitos debito) throws EntidadNoGrabadaException {
		idebitoBancario.crear(debito);
	}
}
