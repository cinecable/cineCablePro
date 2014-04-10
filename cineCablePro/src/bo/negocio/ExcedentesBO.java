package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Excedentes;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.ExcedentesDAO;

public class ExcedentesBO {

	ExcedentesDAO excedentesDAO;
	
	public ExcedentesBO() {
		excedentesDAO = new ExcedentesDAO();
	}
	
	public Excedentes getExcedenteById(int idexcedente) throws Exception {
		Excedentes excedentes = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            excedentes = excedentesDAO.getExcedenteById(session, idexcedente);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return excedentes;
	}
	
	public List<Excedentes> lisExcedentesActivosByCuenta(int idcuenta) throws Exception {
		List<Excedentes> lisExcedentes = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisExcedentes = excedentesDAO.lisExcedentesActivosByCuenta(session, idcuenta, idempresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisExcedentes;
	}
	
	public List<Excedentes> lisExcedentesByFechas(int idcuenta, Date fechaDesde, Date fechaHasta) throws Exception {
		List<Excedentes> lisExcedentes = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisExcedentes = excedentesDAO.lisExcedentesByFechas(session, idcuenta, idempresa, fechaDesde, fechaHasta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisExcedentes;
	}
}
