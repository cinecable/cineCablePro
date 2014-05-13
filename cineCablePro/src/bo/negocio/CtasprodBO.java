package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Estado;
import pojo.annotations.Producto;
import pojo.annotations.custom.ProductoId;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CtasprodDAO;

public class CtasprodBO {

	CtasprodDAO ctasprodDAO;
	
	public CtasprodBO() {
		ctasprodDAO = new CtasprodDAO();
	}
	
	public List<Ctasprod> lisCtasprod(int idcuenta) throws Exception {
		List<Ctasprod> lisCtasprod = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisCtasprod = ctasprodDAO.lisCtasprod(session, idcuenta, idempresa);
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			session.close();
		}
		
		return lisCtasprod;
	}
	
	public boolean grabarProductos(int idcuenta, List<ProductoId> lisProductosId, List<ProductoId> lisProductosIdClon) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		CtasprodDAO ctasprodDAO = new CtasprodDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			//Verificar si hay productos nuevos y grabar
			for(ProductoId productoId : lisProductosId){
				//si un producto no esta en la lista original entonces es nuevo
				if(!lisProductosIdClon.contains(productoId)){
					//secuencia
					int idsecuenciactasprod = ctasprodDAO.maxIdprodcuentas(session) + 1;
					
					Ctasprod ctasprod = new Ctasprod();
					ctasprod.setIdprodcuentas(idsecuenciactasprod);
					
					Ctacliente ctacliente = new Ctacliente();
					ctacliente.setIdcuenta(idcuenta);
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
			
			//Verificar si hay productos eliminados y inactivar
			for(ProductoId productoId : lisProductosIdClon){
				//si el producto original no se encuentra en la lista entonces inactivar registro
				if(!lisProductosId.contains(productoId)){
					Ctasprod ctasprod = ctasprodDAO.getCtasprodById(session, productoId.getIdprodcuenta());
					
					ctasprod.getEstado().setIdestado(0);
					
					//actualizar
					ctasprodDAO.updateCtasprod(session, ctasprod);
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
