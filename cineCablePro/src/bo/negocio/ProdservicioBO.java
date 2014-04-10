package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Prodservicio;
import util.HibernateUtil;
import dao.datos.ProdservicioDAO;

public class ProdservicioBO {

	ProdservicioDAO prodservicioDAO;
	
	public ProdservicioBO() {
		prodservicioDAO = new ProdservicioDAO();
	}
	
	public List<Prodservicio> lisProdservicioByIdProducto(int idproducto) throws Exception {
		List<Prodservicio> lisProdservicio = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			lisProdservicio = prodservicioDAO.lisProdservicioByIdProducto(session, idproducto);
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return lisProdservicio;
	}
}
