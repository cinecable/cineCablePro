package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IParametroMaterialOrdenesDao;
import net.cinecable.model.base.ParametroMaterialOrdenes;

@Stateless
public class ParametroMaterialOrdenesDao extends GenericDao<ParametroMaterialOrdenes, Long> implements IParametroMaterialOrdenesDao {

	public ParametroMaterialOrdenesDao() {
		super(ParametroMaterialOrdenes.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ParametroMaterialOrdenes getParametroByIdTipOperacion(Long tipOperacion) {
		StringBuilder sql = new StringBuilder();
		sql.append("from ParametroMaterialOrdenes o ");
		sql.append("where o.operacion.idtipooperacion=:tipop ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tipop", tipOperacion.intValue());
		List<ParametroMaterialOrdenes> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
