package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.model.base.TipoMaterial;
import pojo.annotations.Comandos;

@Local
public interface IComandosDao extends IGenericDao<Comandos, Long> {

	List<Comandos> listarPorTipoMaterial(TipoMaterial tipMaterial);
}
