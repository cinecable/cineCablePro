package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.IParametrosBancoDao;
import net.cinecable.model.base.ParametrosBancos;

@Stateless
public class ParametrosBancoDao extends GenericDao<ParametrosBancos, Long> implements IParametrosBancoDao {

	public ParametrosBancoDao() {
		super(ParametrosBancos.class);
	}

	@Override
	public ParametrosBancos consultaParametrosbyIdBanco(Long bancoId, String TipGen) {
		StringBuilder sql = new StringBuilder("from ParametrosBancos o ");
		sql.append("where o.banco.idbanco=:codbanco ");
		if (TipGen != null) {
			sql.append("and o.parametrosCondicion.tipoGeneracion=:tipgen");
		}
		Query query = em.createQuery(sql.toString());
		query.setParameter("codbanco", bancoId.intValue());
		if (TipGen != null) {
			query.setParameter("tipgen", TipGen);
		}
		ParametrosBancos ret = (ParametrosBancos) query.uniqueResult();
		if (ret != null && ret.getParametros() != null)
			ret.getParametros().size();
		return ret;
	}

}
