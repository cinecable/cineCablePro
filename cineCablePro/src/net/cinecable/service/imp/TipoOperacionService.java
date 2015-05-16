package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Tipooperacion;
import net.cinecable.dao.ITipoOperacionDao;
import net.cinecable.service.ITipoOperacionService;

@Stateless
public class TipoOperacionService implements ITipoOperacionService {

	@EJB
	ITipoOperacionDao itipoOperacionDao;

	@Override
	public List<Tipooperacion> getAll() {
		List<Tipooperacion> tipoOperacion = itipoOperacionDao.getAll();
		return tipoOperacion;
	}

}
