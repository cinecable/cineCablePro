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
    
    private ClienteDAO clienteDAO;

    public ClienteBO() {
        clienteDAO = new ClienteDAO();
    }
    
    public Clientes getClientesById(String idcliente) throws Exception {
    	Clientes clientes = new Clientes();
    	Session session = null;
    	
    	try{
            session = HibernateUtil.getSessionFactory().openSession();
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
                lisClientes = clienteDAO.lisClientes(session, nombre1, apellido1, empresa, pageSize, pageNumber, args);
            }catch(Exception he){
                throw new RuntimeException(he);
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
			
			if(!clientes.equals(clientesClon)){
				//Auditoria cliente
				clientes.setFecha(fecharegistro);
				clientes.setHora(fecharegistro);
				clientes.setIp(usuarioBean.getIp());
				clientes.setUsuario(usuarioBean.getUsuario());
				
				//modificar
				clientesDAO.actualizarClientes(session, clientes);
			}
			
			if(conyuge != null){
				if(conyuge.getIdconyuge() == null || conyuge.getIdconyuge().trim().length() == 0){
					conyuge.setIdconyuge(conyuge.getIdentificacion());
					conyuge.setClientes(clientes);
					
					//ingresar
					conyugeDAO.saveConyuge(session, conyuge);
				}else{
					if(!conyuge.equals(conyugeClon)){
						//modificar
						conyugeDAO.updateConyuge(session, conyuge);
					}
				}
			}
			
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
