package net.cinecable.dao.imp;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IFacturaDao;
import net.cinecable.dao.IFacturaGeneracionDebitoBancarioDao;
import net.cinecable.dao.IGeneracionBancoDepositoDao;
import net.cinecable.enums.Estados;
import net.cinecable.model.extension.FacturasDebito;

import org.hibernate.Query;

import pojo.annotations.Bancos;
import pojo.annotations.Clientes;
import pojo.annotations.Debitobco;
import pojo.annotations.Factura;

@Stateless
public class FacturaGeneracionDebitoBancarioDao extends GenericDao<Factura, Long> implements IFacturaGeneracionDebitoBancarioDao {

	@EJB
	IGeneracionBancoDepositoDao iGeneracionBanco;
	@EJB
	IFacturaDao iFacturaDao;

	public FacturaGeneracionDebitoBancarioDao() {
		super(Factura.class);
	}

	@SuppressWarnings("unchecked")
	//@Override
	public void getDebitosClientesBancoId2(List<FacturasDebito> debito, List<Bancos> codBanco/*, Date fechaEjecucion*/, boolean todos) {
		List<Object> listado = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from Factura o ");
			sql.append("where o.idestado=1 ");
			sql.append("and o.valpendiente!=0 ");

			if (todos == false)
				sql.append("and not exists(from GeneracionDebitosDetalle h where h.idGeneracion=o.idgeneracion and h.estado=:pestado) ");

			sql.append("order by o.idsecuencia ");

			Query query = em.createQuery(sql.toString());

			if (todos == false)
				query.setParameter("pestado", Estados.ACTIVO.getDescription());
			listado = query.list();

			FacturasDebito fdeb = new FacturasDebito();

			for (Object fac : listado) {
				Factura factura = (Factura) fac;
				fdeb.setFactura(factura);

				Clientes cliente = null;
				sql = new StringBuilder();
				sql.append("from Clientes o ");
				sql.append("where o.idcliente=:codcliente ");
				query = em.createQuery(sql.toString());
				//query.setParameter("codcliente", fdeb.getFactura().getIdcliente());
				query.setParameter("codcliente", fdeb.getFactura().getClientes().getIdcliente());
				cliente = (Clientes) query.uniqueResult();
				fdeb.setCliente(cliente);

				sql = new StringBuilder();
				sql.append("from Debitobco o ");
				sql.append("left outer join fetch  o.bancos ");
				sql.append("where o.ctacliente.idcuenta=:codcuenta ");

				if (!codBanco.isEmpty()) {
					sql.append("and o.bancos.idbanco in (:bcos)");
				}
				Integer[] bancocod = null;
				if (!codBanco.isEmpty()) {
					bancocod = new Integer[codBanco.size()];
					for (int i = 0; i < codBanco.size(); i++) {
						bancocod[i] = codBanco.get(i).getIdbanco();
					}
				}
				query = em.createQuery(sql.toString());
				query.setParameter("codcuenta", fdeb.getFactura().getIdcuenta());
				query.setParameterList("bcos", bancocod);
				Debitobco dbito = (Debitobco) query.uniqueResult();
				if (dbito == null || dbito.getBancos() == null || dbito.getBancos().getNombre() == null)
					continue;
				fdeb.setDebito(dbito);

				debito.add(fdeb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getDebitosClientesBancoId(List<FacturasDebito> debito, List<Bancos> codBanco/*, Date fechaEjecucion*/, boolean todos) {
		List<Factura> listado = null;
		String Cadena;
		try {
			Cadena="FROM Factura fac INNER JOIN FETCH fac.clientes cli INNER JOIN FETCH fac.debitobco dbco LEFT OUTER JOIN FETCH dbco.bancos bco " +
					"WHERE fac.idestado=1 AND fac.valpendiente > 0 ";
							
				StringBuilder sql = new StringBuilder();
				sql.append(Cadena);
				
				
				if (!codBanco.isEmpty()) {
					sql.append("AND dbco.bancos.idbanco IN (:bcos)");
				}
				Integer[] bancocod = null;
				if (!codBanco.isEmpty()) {
					bancocod = new Integer[codBanco.size()];
					for (int i = 0; i < codBanco.size(); i++) {
						bancocod[i] = codBanco.get(i).getIdbanco();
					}
				}
				
				
				if (todos == false)
					sql.append("and not exists(from GeneracionDebitosDetalle gdb where gdb.idGeneracion=fac.idgeneracion and gdb.estado=:pestado) ");
				Query query = em.createQuery(sql.toString());
				
				if (todos == false)
					query.setParameter("pestado", Estados.ACTIVO.getDescription());
					query.setParameterList("bcos", bancocod);
				
				sql.append("order by fac.idsecuencia ");
	
				
				listado = query.list();
				
				for (Factura factura : listado) {
					FacturasDebito fdeb = new FacturasDebito();
					fdeb.setFactura(factura);
					fdeb.setCliente(factura.getClientes());
					fdeb.setDebito(factura.getDebitobco());

					debito.add(fdeb);
				}

			} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
