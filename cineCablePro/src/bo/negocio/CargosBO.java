package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Cargos;
import util.HibernateUtil;
import dao.datos.CargosDAO;

public class CargosBO {

	CargosDAO cargosDAO;
	
	public CargosBO() {
		cargosDAO = new CargosDAO();
	}
	
	public List<Cargos> lisCargosFacturaNivelDetalle(String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosFacturaNivelDetalle(session, idfactura);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosFacturaNivelDescuento(String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosFacturaNivelDescuento(session, idfactura);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosFacturaNivelImpuesto(String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosFacturaNivelImpuesto(session, idfactura);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosFacturaNivelImpuestoSum(String idfactura) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosFacturaNivelImpuestoSum(session, idfactura);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosGeneracionNivelDetalle(int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosGeneracionNivelDetalle(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosGeneracionNivelDescuento(int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosGeneracionNivelDescuento(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosGeneracionNivelImpuesto(int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosGeneracionNivelImpuesto(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosGeneracionNivelImpuestoSum(int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosGeneracionNivelImpuestoSum(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
	public List<Cargos> lisCargosGeneracionById(int idsecuencia) throws Exception {
		List<Cargos> lisCargos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCargos = cargosDAO.lisCargosGeneracionById(session, idsecuencia);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisCargos;
	}
	
}
