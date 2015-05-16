package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IMaterialesDeclaradosBodegaDao;
import net.cinecable.model.base.MaterialesDeclaradosBodega;

@Stateless
public class MaterialesDeclaradosBodegaDao extends GenericDao<MaterialesDeclaradosBodega, Long> implements IMaterialesDeclaradosBodegaDao {

	public MaterialesDeclaradosBodegaDao() {
		super(MaterialesDeclaradosBodega.class);
	}

}
