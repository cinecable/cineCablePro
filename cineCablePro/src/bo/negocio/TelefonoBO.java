package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Estado;
import pojo.annotations.Telefono;
import util.FacesUtil;
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
	
	public List<Telefono> lisTelefonoPorCuenta(int idcuenta) throws Exception {
		List<Telefono> lisTelefono = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisTelefono = telefonoDAO.lisTelefonoByIdcuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception();
        }
        finally{
            session.close();
        }
        
        return lisTelefono;
	}
	
	public boolean modificar(List<Telefono> lisTelefono, List<Telefono> lisTelefonoClon) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	boolean cambios = false;
    	
    	try{
    		TelefonoDAO telefonoDAO = new TelefonoDAO();
    		UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			//Si hay nuevos se ingresan
			for(Telefono telefonoNew : lisTelefono){
				if(!lisTelefonoClon.contains(telefonoNew)){
					//secuencia
					int maxIdtelefono =  telefonoDAO.maxIdtelefono(session) + 1;
					telefonoNew.setIdtelefono(maxIdtelefono);
					
					Estado estadoTelefono = new Estado();
					estadoTelefono.setIdestado(1);
					telefonoNew.setEstado(estadoTelefono);
					
					//auditoria
					telefonoNew.setIdusuario(usuarioBean.getUsuario().getIdusuario());
					Date fecharegistro = new Date();
					telefonoNew.setFecha(fecharegistro);
						
					//grabar
					telefonoDAO.saveTelefono(session, telefonoNew);
					
					cambios = true;
				}
			}
			
			//si faltan se eliminan
			for(Telefono telefonoElim : lisTelefonoClon){
				if(!lisTelefono.contains(telefonoElim)){
					Estado estadoTelefono = new Estado();
					estadoTelefono.setIdestado(2);
					telefonoElim.setEstado(estadoTelefono);
					
					//auditoria
					telefonoElim.setIdusuario(usuarioBean.getUsuario().getIdusuario());
					Date fecharegistro = new Date();
					telefonoElim.setFecha(fecharegistro);
						
					//grabar
					telefonoDAO.updateTelefono(session, telefonoElim);
					
					cambios = true;
				}
			}
			
			if(cambios){
				session.getTransaction().commit();
				ok = true;
			}
    	}catch(Exception e){
    		session.getTransaction().rollback();
            throw new Exception(e);
        }finally{
        	session.close();
        }
    	
    	return ok;
    }
}
