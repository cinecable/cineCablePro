package bo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Producto;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.ProductoDAO;

public class ProductoBO {

	ProductoDAO productoDAO;
	
	public ProductoBO() {
		productoDAO = new  ProductoDAO();
	}
	
	public Producto getProductoByIdproducto(int idproducto) throws Exception {
		Producto producto = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			producto = productoDAO.getProductoByIdproducto(session, idproducto);
		}
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		
		return producto;
	}
	
	public List<Producto> lisProductoByIdCuenta(int idcuenta) throws Exception {
		List<Producto> lisProducto = null;
		Session session = null;
	
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
			lisProducto = productoDAO.lisProductoByIdCuenta(session, idcuenta, idempresa);
		}
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		
		return lisProducto;
	}
	
	public List<Producto> getProductosById(int idProducto) throws Exception {
		Session session = null;
	    List<Producto> lisProducto = null;
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisProducto = productoDAO.getProductoById(session, idProducto);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisProducto;
	}
	
	public List<Producto> lisProductoByTipoEmpresa() throws Exception {
		List<Producto> lisProducto = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idEmpresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisProducto = productoDAO.lisProductoByEmpresa(session, idEmpresa);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisProducto;
	}
		
    public List<Producto> ConsultarCPxQuery(String query) throws Exception {
        List<Producto> lisProducto = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idEmpresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            
            lisProducto = productoDAO.lisProductoXQuery(session, idEmpresa, query);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisProducto;
    }
    
    public List<Producto> lisProductoByNombre(String nombre, int pageSize, int pageNumber, int args[]) throws Exception {
		List<Producto> lisProducto = null;
		Session session = null;
		
		try{
			lisProducto = new ArrayList<Producto>();
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idEmpresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisProducto = productoDAO.lisProductoByNombre(session, idEmpresa, nombre, pageSize, pageNumber, args);
		}catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisProducto;
    }
    
    public List<Producto> lisProductoByNombreJerarquia(String nombre, int jerarquia, String[] filtroIn, int pageSize, int pageNumber, int args[]) throws Exception {
		List<Producto> lisProducto = null;
		Session session = null;
		
		try{
			lisProducto = new ArrayList<Producto>();
			session = HibernateUtil.getSessionFactory().openSession();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idEmpresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisProducto = productoDAO.lisProductoByNombreJerarquia(session, idEmpresa, nombre, jerarquia, filtroIn, pageSize, pageNumber, args);
		}catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisProducto;
    }
}
