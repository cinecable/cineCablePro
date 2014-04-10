package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import dao.datos.DireccionDAO;

import pojo.annotations.Direccion;
import util.HibernateUtil;

public class DireccionBO {

	private DireccionDAO direccionDAO;
	
	public DireccionBO() {
		direccionDAO = new DireccionDAO();
	}
	
	public String consultarDireccionPorCuenta(int idcuenta) throws Exception {
		String direccion = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            List<Direccion> lisDireccion = direccionDAO.lisDireccionByIdcuenta(session, idcuenta);
            
            if(lisDireccion != null && lisDireccion.size() > 0){
            	direccion = lisDireccion.get(0).getCalleprincipal().getNombre() + " " + lisDireccion.get(0).getNumero() + " " + lisDireccion.get(0).getCallesecundaria().getNombre();
            }
        }
        catch(Exception ex){
            throw new Exception();
        }
        finally{
            session.close();
        }
        
        return direccion;
	}
	
	public Direccion getDirByCta(int idcuenta) throws Exception {
		Direccion direccion = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            direccion = direccionDAO.dirxCuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return direccion;
	}
}
