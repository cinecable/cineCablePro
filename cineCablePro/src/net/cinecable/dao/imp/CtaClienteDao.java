package net.cinecable.dao.imp;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;

import net.cinecable.dao.ICtaClienteDao;
import net.cinecable.enums.Estados;
import pojo.annotations.Ctacliente;

@Stateless
public class CtaClienteDao extends GenericDao<Ctacliente, Integer> implements ICtaClienteDao {

	public CtaClienteDao() {
		super(Ctacliente.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Ctacliente> getCtaByCliente(String documento) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Ctacliente o ");
		sql.append("where o.clientes.idcliente= :doc and o.idestado in (:estadoCtaClie)");
		Query query = em.createQuery(sql.toString());
		query.setParameter("doc", documento);
		List<Integer> estado = Arrays.asList(Estados.ACTIVO.getDescription(), Estados.INGRESADOPAGADO.getDescription());
		query.setParameterList("estadoCtaClie", estado);
		List<Ctacliente> listaCta = query.list();
		return listaCta;
	}

}
