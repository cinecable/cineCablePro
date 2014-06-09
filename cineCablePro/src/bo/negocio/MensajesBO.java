package bo.negocio;

import java.util.Date;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Mensajes;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.MensajesDAO;

public class MensajesBO {

	private MensajesDAO mensajesDAO;
	
	public MensajesBO() {
		mensajesDAO = new MensajesDAO();
	}
	
	public Mensajes getMensajesByIdcliente(String idcliente) throws Exception {
		Mensajes mensajes = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			mensajes = mensajesDAO.getMensajesByIdcliente(session, idcliente);
		}catch(Exception e){
			throw new Exception();
		}finally{
			session.close();
		}
		
		return mensajes;
		
	}
	
	public boolean ingresarMensaje(Mensajes mensajes) throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			MensajesDAO mensajesDAO = new MensajesDAO();
			
			//secuencia
			int maxidmensajes = mensajesDAO.maxIdmensajes(session)+1;
			mensajes.setIdmensajes(maxidmensajes);
			
			//auditoria
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			mensajes.setFecharegistro(fecharegistro);
			mensajes.setIplog(usuarioBean.getIp());
			
			mensajes.setEstado(true);
			
			//ingresar
			mensajesDAO.ingresarMensajes(session, mensajes);
			
			session.getTransaction().commit();
			
			ok = true;
		}catch(Exception e){
			session.getTransaction().rollback();
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
	public boolean modificarMensaje(Mensajes mensajes) throws Exception {
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			MensajesDAO mensajesDAO = new MensajesDAO();
			
			//auditoria
			Date fechamodificacion = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			mensajes.setFechamodificacion(fechamodificacion);
			mensajes.setIplog(usuarioBean.getIp());
			
			//modificar
			mensajesDAO.modificarMensajes(session, mensajes);
			
			session.getTransaction().commit();
			
			ok = true;
		}catch(Exception e){
			session.getTransaction().rollback();
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return ok;
	}
	
}
