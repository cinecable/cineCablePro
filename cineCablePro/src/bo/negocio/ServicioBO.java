package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Servicio;
import util.HibernateUtil;
import dao.datos.ServicioDAO;

public class ServicioBO {
	ServicioDAO servicioDAO;
	
	public ServicioBO() {
		servicioDAO = new ServicioDAO();
	}
	
	public List<Servicio> lisServicioByIdCuenta(int idcuenta) throws Exception {
		List<Servicio> lisServicio = null;
		Session session = null;
	
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisServicio = servicioDAO.lisServicioByIdCuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisServicio;
	}
	
	public List<Servicio> lisServicioByIdProducto(int idproducto) throws Exception {
		List<Servicio> lisServicio = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisServicio = servicioDAO.lisServicioByIdProducto(session, idproducto);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisServicio;
	}
}
