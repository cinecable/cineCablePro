package net.cinecable.service.imp;

import javax.ejb.Stateless;

import org.hibernate.Session;

import bo.negocio.NrosfacturaBO;

import exceptions.SecuenciaFacturaException;
import net.cinecable.dao.imp.GenericDao;
import net.cinecable.service.IFacturaSecuenciaService;
import pojo.annotations.Nrosfactura;
import util.HibernateUtil;

@Stateless
public class FacturaSecuenciaService extends GenericDao<Nrosfactura, Long> implements IFacturaSecuenciaService {

	public FacturaSecuenciaService() {
		super(Nrosfactura.class);
	}

	@Override
	public String consultaSecuenciaFactura() throws SecuenciaFacturaException {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		String codFactura = null;
		try {
			codFactura = new NrosfacturaBO().getNumeroFacturaForUpdate(ses, new Nrosfactura[2]);
		} catch (Exception e) {
			throw new SecuenciaFacturaException(e.getMessage());
		}
		return codFactura;
	}

}
