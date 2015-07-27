package bo.negocio;

import org.hibernate.Session;

import dao.datos.SaldoscierreDAO;

import pojo.annotations.Saldoscierre;
import util.HibernateUtil;

public class SaldoscierreBO {
	
	public Saldoscierre getByIdUsuario(int idusuariocaja) throws Exception {
		Saldoscierre saldoscierre = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            SaldoscierreDAO saldoscierreDAO = new SaldoscierreDAO();
            saldoscierre = saldoscierreDAO.getByIdUsuario(session, idusuariocaja);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return saldoscierre;
	}
	
	public Saldoscierre getByNombreUsuario(String nombreusuario) throws Exception {
		Saldoscierre saldoscierre = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            SaldoscierreDAO saldoscierreDAO = new SaldoscierreDAO();
            saldoscierre = saldoscierreDAO.getByNombreUsuario(session, nombreusuario);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return saldoscierre;
	}

}
