package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Impservicios;
import util.HibernateUtil;
import dao.datos.ImpserviciosDAO;

public class ImpserviciosBO {

	private ImpserviciosDAO impserviciosDAO;
	
	public ImpserviciosBO() {
		impserviciosDAO = new ImpserviciosDAO();
	}
	
	public List<Impservicios> lisImpserviciosByIdTipoServicio(int idtiposervicio) throws Exception {
		List<Impservicios> lisImpservicios = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisImpservicios = impserviciosDAO.lisImpserviciosByIdTipoServicio(session, idtiposervicio);
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
        	session.close();
        }
		
		return lisImpservicios;
	}
}
