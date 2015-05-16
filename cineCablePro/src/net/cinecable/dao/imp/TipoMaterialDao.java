package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.ITipoMaterialDao;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoEquipo;
import net.cinecable.model.base.TipoMaterial;

@Stateless
public class TipoMaterialDao extends GenericDao<TipoMaterial, Long> implements ITipoMaterialDao {

	public TipoMaterialDao() {
		super(TipoMaterial.class);
	}

	@Override
	public List<TipoMaterial> getAllTipoMaterial(Estados estado) {
		StringBuilder sql = new StringBuilder();
		sql.append("from TipoMaterial o ");
		if (estado != null)
			sql.append("where o.estado=:tipest");
		Query query = em.createQuery(sql.toString());
		if (estado != null)
			query.setParameter("tipest", estado.getDescription());
		List<TipoMaterial> result = query.list();
		return result;
	}

	public TipoMaterial getTipoMaterialbyTipoEquipo(TipoEquipo tipoEquipo) {
		StringBuilder sql = new StringBuilder("from TipoMaterial o ");
		sql.append("where o.tipEquipoMaterial=:tip ");
		sql.append("and o.estado=:est");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tip", tipoEquipo);
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<TipoMaterial> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
