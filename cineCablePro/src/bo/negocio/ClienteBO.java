/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.negocio;

import dao.datos.ClienteDAO;
import dao.datos.ConyugeDAO;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import bean.controladores.UsuarioBean;
import pojo.annotations.Clientes;
import pojo.annotations.Conyuge;
import util.FacesUtil;
import util.HibernateUtil;

/**
 *
 * @author lyance
 */
public class ClienteBO {
    
    public ClienteBO() {
    }
    
    public Clientes getClientesById(String idcliente) throws Exception {
    	Clientes clientes = new Clientes();
    	Session session = null;
    	
    	try{
            session = HibernateUtil.getSessionFactory().openSession();
            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.getClientesById(session, idcliente);
        }catch(Exception he){
			throw new Exception(he);
        }finally{
			session.close();
        }

        return clientes;
    }
    
    public List<Clientes> lisClientesByPage(int pageSize, int pageNumber, int args[], String nombre1, String apellido1, String empresa) throws RuntimeException {
            List<Clientes> lisClientes = null;
            Session session = null;

            try{
                session = HibernateUtil.getSessionFactory().openSession();
                ClienteDAO clienteDAO = new ClienteDAO();
                lisClientes = clienteDAO.lisClientes(session, nombre1, apellido1, empresa, pageSize, pageNumber, args);
            }catch(Exception he){
                throw new RuntimeException(he);
            }finally{
            	session.close();
            }

            return lisClientes;
    }
    
    public List<Clientes> lisClientesByPageNombres(String[] nombres, int pageSize, int pageNumber, int args[]) throws RuntimeException{
		List<Clientes> lisClientes = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			ClienteDAO clienteDAO = new ClienteDAO();
			lisClientes = clienteDAO.lisClientesByPageNombres(session, nombres, pageSize, pageNumber, args);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			session.close();
		}
		
		return lisClientes;
	}
    
    public boolean modificar(Clientes clientes, Clientes clientesClon, Conyuge conyuge, Conyuge conyugeClon) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	
    	try{
    		ClienteDAO clientesDAO = new ClienteDAO();
    		ConyugeDAO conyugeDAO = new ConyugeDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			boolean cambios = false;
			
			if(!clientes.equals(clientesClon)){
				//Auditoria cliente
				clientes.setFecha(fecharegistro);
				clientes.setHora(fecharegistro);
				clientes.setIp(usuarioBean.getIp());
				clientes.setUsuario(usuarioBean.getUsuario());
				
				//modificar
				clientesDAO.actualizarClientes(session, clientes);
				cambios = true;
			}
			
			if(conyuge != null){
				if(conyuge.getIdconyuge() == 0 && conyuge.getNombre1() != null && conyuge.getNombre1().trim().length() > 0 && conyuge.getApellido1() != null && conyuge.getApellido1().trim().length() > 0){
					int idsecuenciaconyuge = conyugeDAO.maxIdconyuge(session) + 1;
					conyuge.setIdconyuge(idsecuenciaconyuge);
					conyuge.setClientes(clientes);
					
					//ingresar
					conyugeDAO.saveConyuge(session, conyuge);
					cambios = true;
				}else{
					if(!conyuge.equals(conyugeClon)){
						//modificar
						conyugeDAO.updateConyuge(session, conyuge);
						cambios = true;
					}
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
