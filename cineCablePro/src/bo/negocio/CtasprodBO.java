package bo.negocio;

import global.Parametro;

import java.util.List;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;
import net.cinecable.model.base.ParamAsignacionOrden;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Persona;
import pojo.annotations.Producto;
import pojo.annotations.Tipooperacion;
import pojo.annotations.custom.ProductoId;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.CtasprodDAO;
import dao.datos.OrdasignacionesDAO;
import dao.datos.TbordenesDAO;
import dao.datos.TbparamasigordenDAO;

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
	
	public boolean grabarProductos(int idcuenta, List<ProductoId> lisProductosId, List<ProductoId> lisProductosIdClon, Ordenes ordenesParam) throws Exception {
		boolean ok = false;
    	Session session = null;
    	
    	try{
    		CtasprodDAO ctasprodDAO = new CtasprodDAO();
    		TbordenesDAO tbordenesDAO = new TbordenesDAO();
    		OrdasignacionesDAO ordasignacionesDAO = new OrdasignacionesDAO();
    		TbparamasigordenDAO tbparamasigordenDAO = new TbparamasigordenDAO();
    		
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
					
					//ordenes
					//codigo secuencial
					Ordenes ordenes = ordenesParam.clonar();
					int idOrdenes = tbordenesDAO.maxIdordenes(session) + 1;
					ordenes.setIdOrdenes(Long.valueOf(idOrdenes));
					
					//orden por cada ctasprod
					ordenes.setProducto(ctasprod);

					Motivos motivos = new Motivos();
					motivos.setIdmotivo(Parametro.MOTIVO_INSTALACION_NUEVA);
					ordenes.setMotivo(motivos);
					
					Tipooperacion tipoOperacion = new Tipooperacion();
					tipoOperacion.setIdtipooperacion(Parametro.TIPO_OPERACION_INSTALACION_NUEVA);
					ordenes.setTipoOperacion(tipoOperacion);
					
					ordenes.setEmpresa(usuarioBean.getUsuario().getEmpresa());
					ordenes.setCuentaCliente(ctacliente);
					
					Estado estadoOrden = new Estado();
					estadoOrden.setIdestado(Parametro.ESTADO_PENDIENTE);
					ordenes.setEstado(estadoOrden);
					
					ordenes.setIdproductoprincipal(productoId.getIdproductoprincipal());
					
					//Auditoria
					ordenes.setIp(usuarioBean.getIp());
					ordenes.setUsuario(usuarioBean.getUsuario());
					
					//grabar ordenes
					tbordenesDAO.ingresarOrdenes(session, ordenes);
					
					//ordasignaciones
					OrdenesAsignaciones ordenesAsignaciones = new OrdenesAsignaciones();
					ordenesAsignaciones.setEstado(8);//TODO estado
					
					Empresa empresa = new Empresa();
					empresa.setIdempresa(usuarioBean.getUsuario().getEmpresa().getIdempresa());
					ordenesAsignaciones.setEmpresa(empresa);

					ordenesAsignaciones.setFechaAsignacion(ordenes.getFechaEjecucion());
					ordenesAsignaciones.setIp(usuarioBean.getIp());
					ordenesAsignaciones.setOrden(ordenes);
					
					Persona personaSupervisor = new Persona();
					personaSupervisor.setIdpersona(-1);
					ordenesAsignaciones.setSupervisor(personaSupervisor);

					Persona personaTecnico = new Persona();
					personaTecnico.setIdpersona(-1);
					ordenesAsignaciones.setTecnico(personaTecnico);

					ordenesAsignaciones.setUsuario(usuarioBean.getUsuario());
					
					//grabar ordasignaciones
					ordasignacionesDAO.ingresarOrdasignaciones(session, ordenesAsignaciones);
					
					//tbparamasigOrden
					ParamAsignacionOrden paramAsignacionOrden = new ParamAsignacionOrden();
					
					//consultar la asignacion
					paramAsignacionOrden = tbparamasigordenDAO.consultarPorFechaTipoOperacion(session, ordenes.getFechaEjecucion(), ordenes.getTipoOperacion().getIdtipooperacion());
					
					//disminuir en 1 numero de asignaciones
					if(paramAsignacionOrden != null && paramAsignacionOrden.getNoasignaciones() >0){
						paramAsignacionOrden.setNoasignaciones(paramAsignacionOrden.getNoasignaciones()-1);
						
						//grabar tbparamasigOrden
						tbparamasigordenDAO.actualizarTbparamasigorden(session, paramAsignacionOrden);
					}
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
