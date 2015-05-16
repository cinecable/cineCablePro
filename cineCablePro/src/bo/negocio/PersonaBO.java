package bo.negocio;

import java.util.List;

import net.cinecable.enums.TipoPersona;

import org.hibernate.Session;

import pojo.annotations.Persona;
import util.HibernateUtil;

import dao.datos.PersonaDAO;

public class PersonaBO {

	private PersonaDAO personaDAO;
	
	public PersonaBO() {
		personaDAO = new PersonaDAO();
	}
	
	public Persona getPersonaById(int idpersona) throws Exception {
		Persona persona = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            persona = personaDAO.getPersonaById(session, idpersona);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return persona;
	}
	
	public List<Persona> getListaPersonasbyCargo(TipoPersona tipoPersona)  throws Exception {
		List<Persona> lisPersona = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisPersona = personaDAO.getPersonaCargo(session, tipoPersona);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisPersona;
	}
	
	public List<Persona> lisPersonaByPage(String[] nombres, int idarea, int pageSize, int pageNumber, int args[]) throws RuntimeException{
		List<Persona> lisPersona = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			lisPersona = personaDAO.lisPersonaByPage(session, nombres, idarea, pageSize, pageNumber, args);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			session.close();
		}
		
		return lisPersona;
	}
}
