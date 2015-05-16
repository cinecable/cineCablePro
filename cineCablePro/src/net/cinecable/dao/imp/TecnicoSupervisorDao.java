package net.cinecable.dao.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.dao.ITecnicoSupervisorDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.base.TecnicoSupervisor;

import org.hibernate.Query;

@Stateless
public class TecnicoSupervisorDao extends GenericDao<TecnicoSupervisor, Long> implements ITecnicoSupervisorDao {

	public TecnicoSupervisorDao() {
		super(TecnicoSupervisor.class);

	}

	@SuppressWarnings("unchecked")
	@Override
	public TecnicoSupervisor getSupervisorbyIdTecnico(Long idTec) {
		StringBuilder sql = new StringBuilder();
		sql.append("from TecnicoSupervisor o ");
		sql.append("where o.tecnico.idpersona=:idT ");
		sql.append("and o.estado=:estado ");
		sql.append("and o.fechaAsignacion=:feAssing");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idT", idTec.intValue());
		query.setParameter("estado", Estados.ACTIVO.getDescription());
		query.setParameter("feAssing", new Date());
		List<TecnicoSupervisor> tecsuplist = query.list();
		if (!tecsuplist.isEmpty()) {
			return tecsuplist.get(0);
		} else
			return null;
	}

}
