package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoEquipo;
import net.cinecable.model.base.TipoMaterial;

@Local
public interface ITipoMaterialDao extends IGenericDao<TipoMaterial, Long> {

	List<TipoMaterial> getAllTipoMaterial(Estados estado);

	TipoMaterial getTipoMaterialbyTipoEquipo(TipoEquipo tipoEquipo);
}
