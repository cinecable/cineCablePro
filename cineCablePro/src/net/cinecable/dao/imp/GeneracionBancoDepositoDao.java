package net.cinecable.dao.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.ICargoDao;
import net.cinecable.dao.IFacturaDao;
import net.cinecable.dao.IGeneracionBancoDepositoDao;
import net.cinecable.dao.IPagosDao;
import net.cinecable.model.base.GeneracionBancoDepositos;
import net.cinecable.model.extension.DebitosDetalle;
import net.cinecable.service.IComandoActivacionService;
import net.cinecable.service.IFacturaSecuenciaService;
import net.cinecable.util.UtilAudit;

import org.hibernate.Query;

import exceptions.SecuenciaFacturaException;
import pojo.annotations.Bancos;
import pojo.annotations.Cargos;
import pojo.annotations.Factura;

@Stateless
public class GeneracionBancoDepositoDao extends GenericDao<GeneracionBancoDepositos, Long> implements IGeneracionBancoDepositoDao {

	public GeneracionBancoDepositoDao() {
		super(GeneracionBancoDepositos.class);
	}

	@EJB
	IPagosDao ipagosDao;
	@EJB
	ICargoDao icargoDao;
	@EJB
	IFacturaDao ifacturaDao;
	@EJB
	IFacturaSecuenciaService ifacturaSecuenciaService;
	@EJB
	IComandoActivacionService icomandosService;

	@Override
	public void guardarDebitosRecibidos(List<DebitosDetalle> debitosDetalle) throws Exception {
		for (DebitosDetalle debito : debitosDetalle) {

			// factura numero
			String facsecId = ifacturaSecuenciaService.consultaSecuenciaFactura();
			if (facsecId == null) {
				throw new Exception("No existen numeros de factura disponibles");
			}

			GeneracionBancoDepositos genbanTemp = getGeneracionbyIdUnicoAndBanco(debito.getBancoDepositos().getIdgeneracionUnico(), debito.getBancoDepositos().getBanco());
			// verifica si existe la generacion anterior
			if (genbanTemp != null) {
				throw new SecuenciaFacturaException("La generación se ha ejecutado anteriormente");
			} else {
				UtilAudit.setAudit(debito.getBancoDepositos());
				crear(debito.getBancoDepositos());
			}

			debito.getPagos().setIdfactura(facsecId);
			// Cargos act
			List<Cargos> cargosActuales = icargoDao.getCargosByIdgeneracion(debito.getFactura().getIdgeneracion());

			double totaldebitado = debito.getPagos().getValtotal();
			double valcargodeb[] = new double[cargosActuales.size()];
			double porc = totaldebitado * 100 / debito.getFactura().getValpendiente();

			for (int i = 0; i < cargosActuales.size(); i++) {
				if (cargosActuales.get(i).getValcargo() <= 0)
					continue;
				valcargodeb[i] = cargosActuales.get(i).getValpendiente() * porc / 100;
			}

			for (int i = 0; i < cargosActuales.size(); i++) {
				Cargos cargo = cargosActuales.get(i);
				cargo.setIdfactura(debito.getPagos().getIdfactura());
				cargo.setValpendiente(cargo.getValcargo() - (float) valcargodeb[i]);
				// verificar servicios a desactivar por controlador

				icargoDao.actualizar(cargo);
			}
			// creacion de nueva Factura
			Factura facturaNueva = null;
			try {
				facturaNueva = debito.getFactura().clonar();
				facturaNueva.setIdsecuencia(0);
				facturaNueva.setIdfactura(debito.getPagos().getIdfactura());
				facturaNueva.setValpendiente(facturaNueva.getValpendiente() - debito.getPagos().getValtotal());
				ifacturaDao.crear(facturaNueva);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Actualizar Factura Anterior
			debito.getFactura().setValpendiente(debito.getFactura().getValpendiente() - debito.getPagos().getValtotal());
			ifacturaDao.actualizar(debito.getFactura());

			//
			crear(debito.getBancoDepositos());
			debito.getPagos().setFecha(new Date());
			debito.getPagos().setIdcuenta(debito.getFactura().getIdcuenta());
			ipagosDao.crear(debito.getPagos());
		}

	}

	@Override
	public GeneracionBancoDepositos getGeneracionbyIdUnicoAndBanco(Long idUnico, Bancos banco) {
		StringBuilder sql = new StringBuilder("from GeneracionBancoDepositos o ");
		sql.append("where o.idgeneracionUnico=:idgen ");
		sql.append("and o.banco=:bancos");
		Query query = em.createQuery(sql.toString());
		query.setParameter("idgen", idUnico);
		query.setParameter("bancos", banco);
		List<GeneracionBancoDepositos> result = query.list();
		if (!result.isEmpty())
			return result.get(0);
		return null;
	}

}
