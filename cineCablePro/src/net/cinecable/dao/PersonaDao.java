package net.cinecable.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import pojo.annotations.Persona;
import net.cinecable.dao.imp.GenericDao;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoPersona;

@Stateless
public class PersonaDao extends GenericDao<Persona, Long> implements IPersonaDao {

	public PersonaDao() {
		super(Persona.class);
	}

	@Override
	public List<Persona> getListaPersonasbyTipo(TipoPersona tipo) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Persona o where upper(o.cargo) like :tip and o.estado.idestado=:est");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tip", tipo.getValue()+"%");
		query.setParameter("est", Estados.ACTIVO.getDescription());
		List<Persona> lpers = query.list();
		return lpers;
	}

}
