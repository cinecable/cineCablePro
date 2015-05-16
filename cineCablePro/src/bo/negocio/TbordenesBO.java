package bo.negocio;

import java.util.List;

import net.cinecable.model.base.Ordenes;

import org.hibernate.Session;

import dao.datos.TbordenesDAO;

import util.HibernateUtil;

public class TbordenesBO {

	public List<Ordenes> lisOrdenesHijasByIdPadre(Long idordenes) throws Exception {
		List<Ordenes> lisOrdenes = null;
		Session session = null;
	
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			TbordenesDAO tbordenesDAO = new TbordenesDAO();
			lisOrdenes = tbordenesDAO.lisOrdenesHijasByIdPadre(session, idordenes);
		}
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		
		return lisOrdenes;
	}
}
