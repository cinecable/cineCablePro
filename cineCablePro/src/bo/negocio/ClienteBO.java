/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.negocio;

import dao.datos.ClienteDAO;
import java.util.List;
import org.hibernate.Session;
import pojo.annotations.Clientes;
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
}
