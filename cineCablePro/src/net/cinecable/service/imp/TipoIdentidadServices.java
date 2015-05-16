package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.ITipoIdentidadDao;
import net.cinecable.model.base.Tidentidad;
import net.cinecable.service.ITipoIdentidadServices;
@Stateless
public class TipoIdentidadServices implements ITipoIdentidadServices{
	@EJB
	private ITipoIdentidadDao itipoIdentidadDao;
	@Override
	public List<Tidentidad> getIdentidad() {
		
		return itipoIdentidadDao.obtenerTodos();
	}

}
