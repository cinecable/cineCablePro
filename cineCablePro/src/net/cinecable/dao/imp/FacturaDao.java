package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IFacturaDao;

import org.hibernate.Query;

import pojo.annotations.Factura;

@Stateless
public class FacturaDao extends GenericDao<Factura, Long> implements IFacturaDao {

	public FacturaDao() {
		super(Factura.class);
	}

	@Override
	public Factura getFacturabyReferenciaSecuencia(Long secuencia) {
		StringBuilder sql = null;
		sql = new StringBuilder("from Factura o ");
		sql.append("where o.idsecuencia=:idsec");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idsec", secuencia.intValue());
		Factura fac = (Factura) query.uniqueResult();
		return fac;
	}

}
