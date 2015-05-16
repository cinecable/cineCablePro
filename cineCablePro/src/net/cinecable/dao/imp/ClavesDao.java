package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IClavesDao;
import pojo.annotations.Claves;
@Stateless
public class ClavesDao extends GenericDao<Claves, Integer> implements IClavesDao{

	public ClavesDao() {
		super(Claves.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Claves getClavebyUsuario(int idUduario) {
		StringBuilder sql= new StringBuilder();
		sql.append("select o from Claves o ");
		sql.append("where o.idusuario= :idusu");
		Query query= em.createQuery(sql.toString());
		query.setParameter("idusu", idUduario);
		Claves clave=(Claves) query.uniqueResult();
		return clave;
	}

}
