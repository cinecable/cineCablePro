package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Motivos;
@Local
public interface IMotivosDao extends IGenericDao<Motivos, Integer>{
	List<Motivos> getMotivosByTipoOperacion(int tipOpe);
}
