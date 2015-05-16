package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import dao.datos.TipooperacionDAO;

import pojo.annotations.Tipooperacion;
import util.HibernateUtil;

public class TipooperacionBO {
	private TipooperacionDAO tipooperacionDAO;

	public TipooperacionBO() {
		tipooperacionDAO = new TipooperacionDAO();
	}

	public List<Tipooperacion> TipooperacionPorId()
			throws Exception {
		List<Tipooperacion> tipooperacion = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tipooperacion = tipooperacionDAO.tipooperacionById(session);
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			session.close();
		}

		return tipooperacion;
	}

}
