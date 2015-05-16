package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pojo.annotations.Controlador;
import net.cinecable.dao.IUserWsDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.UserWs;

@Stateless
public class UsuarioWsDao extends GenericDao<UserWs, Long> implements IUserWsDao {

	public UsuarioWsDao() {
		super(UserWs.class);
	}

	@Override
	public UserWs getUsuario(Controlador ctrl) {
		StringBuilder sql = new StringBuilder("from UserWs o ");
		sql.append("where o.estado=:est");
		Query query = em.createQuery(sql.toString());
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<UserWs> result = query.list();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

}
