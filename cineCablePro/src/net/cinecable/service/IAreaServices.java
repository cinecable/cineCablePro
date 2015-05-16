package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Area;

@Local
public interface IAreaServices {
	List<Area> getAreas();
	List<Area> getAreasbyEstado(int estado);
	
}
