package net.cinecable.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Area;
import net.cinecable.dao.IAreaDao;
import net.cinecable.service.IAreaServices;
@Stateless
public class AreaServices implements IAreaServices{
	@EJB
	private IAreaDao iAreaDao;
	@Override
	public List<Area> getAreas() {
		List<Area> listAreas= new ArrayList<Area>();
		listAreas=iAreaDao.obtenerTodos();
		return listAreas;
	}

	@Override
	public List<Area> getAreasbyEstado(int estado) {
		List<Area> listAreas= new ArrayList<Area>();
		listAreas=iAreaDao.getAreasbyEstado(1);
		return listAreas;
	}

}
