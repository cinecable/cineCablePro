package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IClienteDao;

import org.hibernate.Query;

import pojo.annotations.Clientes;

@Stateless
public class ClienteDao extends GenericDao<Clientes, String> implements IClienteDao{

	public ClienteDao() {
		super(Clientes.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Clientes getClienteByIdentificacion(String identificacion) {
		StringBuilder sql= new StringBuilder();
		sql.append("from Clientes o where o.identificacion= :iden");
		Query query=em.createQuery(sql.toString());
		query.setParameter("iden", identificacion);
		Clientes cliente=(Clientes) query.uniqueResult();
		return cliente;
	}

	@Override
	public Clientes getClientesByCuenta(Long idCuenta,String nroCedula) {
		StringBuilder   sql= new StringBuilder("");
		
		return null;
	}

}
