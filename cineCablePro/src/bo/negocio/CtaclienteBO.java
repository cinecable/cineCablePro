/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.negocio;

import dao.datos.ClienteDAO;
import dao.datos.CtaclienteDAO;
import dao.datos.CtasprodDAO;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import bean.controladores.UsuarioBean;
import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Estado;
import pojo.annotations.Producto;
import pojo.annotations.Tipocliente;
import pojo.annotations.custom.ProductoId;
import util.FacesUtil;
import util.HibernateUtil;

public class CtaclienteBO {
    
    private CtaclienteDAO ctaclienteDAO;

    public CtaclienteBO() {
    	ctaclienteDAO = new CtaclienteDAO();
    }
    
    public Ctacliente getCtaclienteById(int idcuenta) throws Exception {
    	Ctacliente ctacliente = null;
    	Session session = null;
    	
    	try{
            session = HibernateUtil.getSessionFactory().openSession();
            ctacliente = ctaclienteDAO.getCtaclienteById(session, idcuenta);
        }catch(Exception he){
            throw new Exception(he);
        }finally{
        	session.close();
        }
    	
    	return ctacliente;
    }
    
    public Ctacliente getCtaclienteByNombre(String nombre) throws Exception {
		Ctacliente ctacliente = null;
		Session session = null;
    	
    	try{
            session = HibernateUtil.getSessionFactory().openSession();
            ctacliente = ctaclienteDAO.getCtaclienteByNombre(session, nombre);
        }catch(Exception he){
            throw new Exception(he);
        }finally{
    		session.close();
        }
    	
    	return ctacliente;
    }
    
    public List<Ctacliente> lisCtaclienteByPage(int pageSize, int pageNumber, int args[], String nombre1, String nombre2, String apellido1, String apellido2, String numeroIdentificacion, String empresa) throws RuntimeException {
        List<Ctacliente> lisCtacliente = null;
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCtacliente = ctaclienteDAO.lisCtacliente(session, nombre1, nombre2, apellido1, apellido2, numeroIdentificacion, empresa, pageSize, pageNumber, args);
        }catch(Exception he){
                throw new RuntimeException(he);
        }finally{
                session.close();
        }

        return lisCtacliente;
    }
    
    public boolean grabarCliente(Ctacliente ctacliente, Clientes clientes, List<ProductoId> lisProductoId) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	
    	try{
    		ClienteDAO clientesDAO = new ClienteDAO();
    		CtasprodDAO ctasprodDAO = new CtasprodDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			//primero grabar cliente por fk con ctacliente
			Tipocliente tipocliente = new Tipocliente();
			tipocliente.setIdtipocliente(1);
			clientes.setTipocliente(tipocliente);
			clientes.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			
			//Auditoria cliente
			clientes.setFecha(fecharegistro);
			clientes.setIp(usuarioBean.getIp());
			clientes.setUsuario(usuarioBean.getUsuario());
			
			//grabar
			clientesDAO.saveClientes(session, clientes);
			
			
			//ahora si grabar ctacliente
			//codigo secuencial
			int idsecuenciactacliente = ctaclienteDAO.maxIdcuenta(session) + 1;
			ctacliente.setIdcuenta(idsecuenciactacliente);
			ctacliente.setEmpresa(usuarioBean.getUsuario().getEmpresa());
			ctacliente.setIdestado(3);
			
			//Auditoria
			ctacliente.setIp(usuarioBean.getIp());
			ctacliente.setIdusuario(usuarioBean.getUsuario().getIdusuario());
			fecharegistro = new Date();
			ctacliente.setFecha(fecharegistro);
			
			//grabar
			ctaclienteDAO.ingresarCtacliente(session, ctacliente);
			
			//luego grabar las demas
			//Ctasprod
			for(ProductoId productoId : lisProductoId ){
				for(int i=0;i<productoId.getCantidad();i++){
					
					//secuencia
					int idsecuenciactasprod = ctasprodDAO.maxIdprodcuentas(session) + 1;
					
					Ctasprod ctasprod = new Ctasprod();
					ctasprod.setIdprodcuentas(idsecuenciactasprod);
					ctasprod.setCtacliente(ctacliente);
					
					Producto producto = new Producto();
					producto.setIdproducto(productoId.getIdProducto());
					ctasprod.setProducto(producto);
					
					Estado estado = new Estado();
					estado.setIdestado(3);
					ctasprod.setEstado(estado);
					
					ctasprod.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					
					//grabar
					ctasprodDAO.saveCtasprod(session, ctasprod);
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
