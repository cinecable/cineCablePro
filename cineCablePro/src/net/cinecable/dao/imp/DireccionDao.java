package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IDireccionDao;
import net.cinecable.enums.Estados;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;

@Stateless
public class DireccionDao extends GenericDao<Direccion, Long> implements IDireccionDao {

	public DireccionDao() {
		super(Direccion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Direccion getDireccionbyEstadoYCuenta(Ctacliente cta, Estados estado) {
		StringBuilder sql = new StringBuilder("o from Direccion o ");
		sql.append("where o.idestado=:est ");
		sql.append("and o.ctacliente=:cta ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", estado.getDescription());
		query.setParameter("cta", cta);
		List<Direccion> dir = query.list();
		if (!dir.isEmpty()) {
			return dir.get(0);
		}
		return null;
	}

}
