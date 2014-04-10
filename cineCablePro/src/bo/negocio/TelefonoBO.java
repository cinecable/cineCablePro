package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Telefono;
import util.HibernateUtil;
import dao.datos.TelefonoDAO;

public class TelefonoBO {

	private TelefonoDAO telefonoDAO;
	
	public TelefonoBO() {
		telefonoDAO = new TelefonoDAO();
	}
	
	public String consultarTelefonoPorCuenta(int idcuenta) throws Exception {
		String telefono = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            List<Telefono> lisTelefonolisEmpresa = telefonoDAO.lisTelefonoByIdcuenta(session, idcuenta);
            
            if(lisTelefonolisEmpresa != null && lisTelefonolisEmpresa.size() > 0){
            	telefono = String.valueOf(lisTelefonolisEmpresa.get(0).getNumero());
            }
        }
        catch(Exception ex){
            throw new Exception();
        }
        finally{
            session.close();
        }
        
        return telefono;
	}
}
