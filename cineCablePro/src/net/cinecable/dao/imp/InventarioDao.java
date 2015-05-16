package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.IinventarioDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.Inventario;
import net.cinecable.model.base.Materiales;

import org.hibernate.Query;

@Stateless
public class InventarioDao extends GenericDao<Inventario, Long> implements IinventarioDao {

	public InventarioDao() {
		super(Inventario.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Inventario getInventarioActivobyMaterial(Materiales material) {
		StringBuilder sql = new StringBuilder("from Inventario o ");
		sql.append("where o.estado=:est ");
		sql.append("and o.unidad=:uni ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", Estados.ACTIVO.getDescription());
		query.setParameter("uni", material);
		List<Inventario> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
