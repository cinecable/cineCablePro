package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import dao.datos.EmpresaDAO;

import pojo.annotations.Empresa;
import util.HibernateUtil;

public class EmpresaBO {
	 private EmpresaDAO empresaDAO;

	    public EmpresaBO() {
	    	empresaDAO = new EmpresaDAO();
	    }
	    
	    public List<Empresa> consultarEmpresas(int idEmpresa) throws Exception {
	        List<Empresa> lisEmpresa = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisEmpresa = empresaDAO.lisEmpresa(session);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisEmpresa;
	    }
	    
	    public List<Empresa> consultarEmpresasId(int idEmpresa) throws Exception {
	        List<Empresa> lisEmpresa = null;
	        Session session = null;
	        
	        try{
	            session = HibernateUtil.getSessionFactory().openSession();
	            lisEmpresa = empresaDAO.lisIdEmpresa(session,idEmpresa);
	        }
	        catch(Exception ex){
	            throw new Exception(ex);
	        }
	        finally{
	            session.close();
	        }
	        
	        return lisEmpresa;
	    }
}
