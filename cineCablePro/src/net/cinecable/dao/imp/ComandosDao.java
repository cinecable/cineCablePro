package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IComandosDao;
import net.cinecable.model.base.TipoMaterial;
import pojo.annotations.Comandos;

@Stateless
public class ComandosDao extends GenericDao<Comandos, Long> implements IComandosDao {

	public ComandosDao() {
		super(Comandos.class);
	}

	@Override
	public List<Comandos> listarPorTipoMaterial(TipoMaterial tipMaterial) {
		StringBuilder sql = new StringBuilder("select o.comandoMaterial from MaterialesComandos o ");
		sql.append("where o.tipoMaterial=:tmat");
		Query query = em.createQuery(sql.toString());
		query.setParameter("tmat", tipMaterial);
		List<Comandos> comandos = query.list();
		return comandos;
	}

}
