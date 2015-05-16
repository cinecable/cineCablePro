package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Area;
@Local
public interface IAreaDao extends IGenericDao<Area, Long>{
	List<Area> getAreasbyEstado(int estado);
}
