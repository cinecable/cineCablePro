package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.ITipoIdentidadDao;
import net.cinecable.model.base.Tidentidad;

@Stateless
public class TipoIdentidadDao extends GenericDao<Tidentidad, Integer> implements ITipoIdentidadDao{

	public TipoIdentidadDao() {
		super(Tidentidad.class);
		// TODO Auto-generated constructor stub
	}

}
