package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Evento;
import util.FacesUtil;
import util.HibernateUtil;

import dao.datos.EventoDAO;

public class EventoBO {
	EventoDAO eventoDAO;
	
	public EventoBO() {
		eventoDAO = new EventoDAO();
	}
	
	public boolean newEvento(Evento evento) throws Exception{
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			int max = eventoDAO.maxIdCotevento(session)+1;
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			evento.setIdevento(max);
			evento.setFecharegistro(fecharegistro);
			evento.setIdestado(1);
			evento.setIplog(usuarioBean.getIp());
			evento.setIdusuario(usuarioBean.getUsuario().getIdusuario());
			
			eventoDAO.saveEvento(session, evento);
			
			session.getTransaction().commit();
			ok = true;
		} catch(Exception e){
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			session.close();
		}
		
		return ok;
	}
	
	public boolean updateEvento(Evento evento) throws Exception{
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			evento.setFecharegistro(fecharegistro);
			evento.setIplog(usuarioBean.getIp());
			evento.setIdusuario(usuarioBean.getUsuario().getIdusuario());
			eventoDAO.updateEvento(session, evento);
			
			session.getTransaction().commit();
			ok = true;
		} catch(Exception e){
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			session.close();
		}
		
		return ok;
	}
	
	public boolean deleteEvento(int id) throws Exception{
		boolean ok = false;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			eventoDAO.deleteEvento(session, id);
			session.getTransaction().commit();
			ok = true;
		} catch(Exception e){
			session.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			session.close();
		}
		
		return ok;
	}
	
	public List<Evento> lisEvento(Date fechadesde, Date fechahasta) throws RuntimeException{
		List<Evento> lisEvento = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lisEvento = eventoDAO.lisEvento(session, fechadesde, fechahasta);
		} catch(Exception e){
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
		
		return lisEvento;
	}

}
