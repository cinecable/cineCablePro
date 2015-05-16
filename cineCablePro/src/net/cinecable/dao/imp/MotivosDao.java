package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IMotivosDao;
import pojo.annotations.Motivos;

@Stateless
public class MotivosDao extends GenericDao<Motivos, Integer> implements IMotivosDao {

	public MotivosDao() {
		super(Motivos.class);
	}

	@Override
	public List<Motivos> getMotivosByTipoOperacion(int tipOpe) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Motivos o ");
		sql.append("where o.tipomotivo.idtipomotivo= :tipO");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tipO", tipOpe);
		List<Motivos> listaMotivos = query.list();
		return listaMotivos;
	}

}
