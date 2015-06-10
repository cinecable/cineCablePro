/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.negocio;

import dao.datos.ClienteDAO;
import dao.datos.ConyugeDAO;
import dao.datos.CtaclienteDAO;
import dao.datos.CtasprodDAO;
import dao.datos.DebitosbcoDAO;
import dao.datos.DireccionDAO;
import dao.datos.OrdasignacionesDAO;
import dao.datos.ReferenciadirDAO;
import dao.datos.TbordenesDAO;
import dao.datos.TbparamasigordenDAO;
import dao.datos.TelefonoDAO;
import global.Parametro;

import java.util.Date;
import java.util.List;

import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.OrdenesAsignaciones;
import net.cinecable.model.base.ParamAsignacionOrden;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;
import pojo.annotations.Conyuge;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Debitobco;
import pojo.annotations.Direccion;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Persona;
import pojo.annotations.Producto;
import pojo.annotations.Referenciadir;
import pojo.annotations.Telefono;
import pojo.annotations.Tipocliente;
import pojo.annotations.Tipooperacion;
import pojo.annotations.custom.ConsultaCliente;
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
    
    public List<ConsultaCliente> lisConsultaClienteByPage(String nombre1, String nombre2, String apellido1, String apellido2, String numeroIdentificacion, String empresa, String vineta, String nrodebito, String idcliente, int idcuenta, int pageSize, int pageNumber, int args[]) throws RuntimeException {
        List<ConsultaCliente> lisConsultaCliente = null;
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisConsultaCliente = ctaclienteDAO.lisConsultaCliente(session, nombre1, nombre2, apellido1, apellido2, numeroIdentificacion, empresa, vineta, nrodebito, idcliente, idcuenta, pageSize, pageNumber, args);
        }catch(Exception he){
                throw new RuntimeException(he);
        }finally{
                session.close();
        }

        return lisConsultaCliente;
    }
    
    public boolean grabarCliente(Ctacliente ctacliente, Conyuge conyuge, List<ProductoId> lisProductoId, Direccion direccionInstalacion, Direccion direccionCorrespondencia, Direccion direccionCobranza, Debitobco debitobco, List<Telefono> lisTelefonos, Referenciadir referenciadirInstalacion, Referenciadir referenciadirCorrespondencia, Referenciadir referenciadirCobranza, Ordenes ordenesParam) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	
    	try{
    		ClienteDAO clientesDAO = new ClienteDAO();
    		ConyugeDAO conyugeDAO = new ConyugeDAO();
    		CtasprodDAO ctasprodDAO = new CtasprodDAO();
    		DireccionDAO direccionDAO = new DireccionDAO();
    		ReferenciadirDAO referenciadirDAO = new ReferenciadirDAO();
    		DebitosbcoDAO debitosbcoDAO = new DebitosbcoDAO();
    		TelefonoDAO telefonoDAO = new TelefonoDAO();
    		TbordenesDAO tbordenesDAO = new TbordenesDAO();
    		OrdasignacionesDAO ordasignacionesDAO = new OrdasignacionesDAO();
    		TbparamasigordenDAO tbparamasigordenDAO = new TbparamasigordenDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			//primero grabar cliente por fk con ctacliente
			Tipocliente tipocliente = new Tipocliente();
			tipocliente.setIdtipocliente(1);
			ctacliente.getClientes().setTipocliente(tipocliente);
			ctacliente.getClientes().setEmpresa(usuarioBean.getUsuario().getEmpresa());
			
			//Auditoria cliente
			ctacliente.getClientes().setFecha(fecharegistro);
			ctacliente.getClientes().setHora(fecharegistro);
			ctacliente.getClientes().setIp(usuarioBean.getIp());
			ctacliente.getClientes().setUsuario(usuarioBean.getUsuario());
			
			//grabar
			clientesDAO.ingresarClientes(session, ctacliente.getClientes());
			
			
			//grabar conyuge
			if(conyuge != null && conyuge.getNombre1() != null && conyuge.getNombre1().trim().length() > 0 && conyuge.getApellido1() != null && conyuge.getApellido1().trim().length() > 0){
				int idsecuenciaconyuge = conyugeDAO.maxIdconyuge(session) + 1;
				conyuge.setIdconyuge(idsecuenciaconyuge);
				conyuge.setClientes(ctacliente.getClientes());
				
				//grabar
				conyugeDAO.saveConyuge(session, conyuge);
			}
			
			
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
			
			//direccion de instalacion
			int maxIddireccioninstalacion = direccionDAO.maxIddireccion(session) + 1;
			direccionInstalacion.setIddireccion(maxIddireccioninstalacion);
			direccionInstalacion.setCorrespondencia("I");
			direccionInstalacion.setCtacliente(ctacliente);
			
			Estado estado = new Estado();
			estado.setIdestado(1);
			direccionInstalacion.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionInstalacion);
			
			if(referenciadirInstalacion != null && referenciadirInstalacion.getReferencia() != null && referenciadirInstalacion.getReferencia().trim().length() > 0){
				//referenciadir de instalacion
				int maxIdreferenciainstalacion = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirInstalacion.setIdreferencia(maxIdreferenciainstalacion);
				referenciadirInstalacion.setDireccion(direccionInstalacion);
				referenciadirInstalacion.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirInstalacion);
			}
			
			//direccion de correspondencia
			int maxIddireccioncorrespondencia = direccionDAO.maxIddireccion(session) + 1;
			direccionCorrespondencia.setIddireccion(maxIddireccioncorrespondencia);
			direccionCorrespondencia.setCorrespondencia("C");
			direccionCorrespondencia.setCtacliente(ctacliente);
			
			direccionCorrespondencia.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionCorrespondencia);

			if(referenciadirCorrespondencia != null && referenciadirCorrespondencia.getReferencia() != null && referenciadirCorrespondencia.getReferencia().trim().length() > 0){
				//referenciadir de correspondencia
				int maxIdreferenciacorrespondencia = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirCorrespondencia.setIdreferencia(maxIdreferenciacorrespondencia);
				referenciadirCorrespondencia.setDireccion(direccionCorrespondencia);
				referenciadirCorrespondencia.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirCorrespondencia);
			}
			
			//direccion de cobranza
			int maxIddireccioncobranza = direccionDAO.maxIddireccion(session) + 1;
			direccionCobranza.setIddireccion(maxIddireccioncobranza);
			direccionCobranza.setCorrespondencia("B");
			direccionCobranza.setCtacliente(ctacliente);
			direccionCobranza.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionCobranza);
			
			if(referenciadirCobranza != null && referenciadirCobranza.getReferencia() != null && referenciadirCobranza.getReferencia().trim().length() > 0){
				//referenciadir de correspondencia
				int maxIdreferenciacobranza = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirCobranza.setIdreferencia(maxIdreferenciacobranza);
				referenciadirCobranza.setDireccion(direccionCobranza);
				referenciadirCobranza.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirCobranza);
			}
			
			//debitobco
			int maxIddebitobco = debitosbcoDAO.maxIddebitobco(session) + 1;
			debitobco.setIddebitobco(maxIddebitobco);
			debitobco.setCtacliente(ctacliente);
			debitobco.setIdestado(1);
			
			//grabar
			debitosbcoDAO.saveDebitobco(session, debitobco);
			
			//actualizar iddebito de cuentacliente
			ctacliente.setDebitobco(debitobco);
			ctaclienteDAO.actualizarCtacliente(session, ctacliente);
			
			//telefono
			for(Telefono telefono : lisTelefonos){
				//secuencia
				int maxIdtelefono =  telefonoDAO.maxIdtelefono(session) + 1;
				telefono.setIdtelefono(maxIdtelefono);
				telefono.setCtacliente(ctacliente);
				
				Estado estadoTelefono = new Estado();
				estadoTelefono.setIdestado(1);
				telefono.setEstado(estadoTelefono);
				
				//auditoria
				telefono.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				fecharegistro = new Date();
				telefono.setFecha(fecharegistro);
					
				//grabar
				telefonoDAO.saveTelefono(session, telefono);
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
    
    public boolean grabarCuenta(Ctacliente ctacliente, List<ProductoId> lisProductoId, Direccion direccionInstalacion, Direccion direccionCorrespondencia, Direccion direccionCobranza, Debitobco debitobco, List<Telefono> lisTelefonos, Referenciadir referenciadirInstalacion, Referenciadir referenciadirCorrespondencia, Referenciadir referenciadirCobranza, Ordenes ordenesParam) throws Exception {
    	boolean ok = false;
    	Session session = null;
    	
    	try{
    		CtasprodDAO ctasprodDAO = new CtasprodDAO();
    		DireccionDAO direccionDAO = new DireccionDAO();
    		ReferenciadirDAO referenciadirDAO = new ReferenciadirDAO();
    		DebitosbcoDAO debitosbcoDAO = new DebitosbcoDAO();
    		TelefonoDAO telefonoDAO = new TelefonoDAO();
    		TbordenesDAO tbordenesDAO = new TbordenesDAO();
    		OrdasignacionesDAO ordasignacionesDAO = new OrdasignacionesDAO();
    		TbparamasigordenDAO tbparamasigordenDAO = new TbparamasigordenDAO();
    		
    		session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Date fecharegistro = new Date();
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			
			//primero consultar el cliente
			Ctacliente tmp = ctaclienteDAO.getCtaclienteById(session, ctacliente.getIdcuenta());
			ctacliente.setClientes(tmp.getClientes());
			
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
			
			//direccion de instalacion
			int maxIddireccioninstalacion = direccionDAO.maxIddireccion(session) + 1;
			direccionInstalacion.setIddireccion(maxIddireccioninstalacion);
			direccionInstalacion.setCorrespondencia("I");
			direccionInstalacion.setCtacliente(ctacliente);
			
			Estado estado = new Estado();
			estado.setIdestado(1);
			direccionInstalacion.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionInstalacion);
			
			if(referenciadirInstalacion != null && referenciadirInstalacion.getReferencia() != null && referenciadirInstalacion.getReferencia().trim().length() > 0){
				//referenciadir de instalacion
				int maxIdreferenciainstalacion = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirInstalacion.setIdreferencia(maxIdreferenciainstalacion);
				referenciadirInstalacion.setDireccion(direccionInstalacion);
				referenciadirInstalacion.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirInstalacion);
			}
			
			//direccion de correspondencia
			int maxIddireccioncorrespondencia = direccionDAO.maxIddireccion(session) + 1;
			direccionCorrespondencia.setIddireccion(maxIddireccioncorrespondencia);
			direccionCorrespondencia.setCorrespondencia("C");
			direccionCorrespondencia.setCtacliente(ctacliente);
			direccionCorrespondencia.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionCorrespondencia);
			
			if(referenciadirCorrespondencia != null && referenciadirCorrespondencia.getReferencia() != null && referenciadirCorrespondencia.getReferencia().trim().length() > 0){
				//referenciadir de correspondencia
				int maxIdreferenciacorrespondencia = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirCorrespondencia.setIdreferencia(maxIdreferenciacorrespondencia);
				referenciadirCorrespondencia.setDireccion(direccionCorrespondencia);
				referenciadirCorrespondencia.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirCorrespondencia);
			}
			
			//direccion de cobranza
			int maxIddireccioncobranza = direccionDAO.maxIddireccion(session) + 1;
			direccionCobranza.setIddireccion(maxIddireccioncobranza);
			direccionCobranza.setCorrespondencia("B");
			direccionCobranza.setCtacliente(ctacliente);
			direccionCobranza.setEstado(estado);
			
			//grabar
			direccionDAO.saveDireccion(session, direccionCobranza);
			
			if(referenciadirCobranza != null && referenciadirCobranza.getReferencia() != null && referenciadirCobranza.getReferencia().trim().length() > 0){
				//referenciadir de correspondencia
				int maxIdreferenciacobranza = referenciadirDAO.maxIdreferencia(session) + 1;
				
				referenciadirCobranza.setIdreferencia(maxIdreferenciacobranza);
				referenciadirCobranza.setDireccion(direccionCobranza);
				referenciadirCobranza.setIdcuenta(ctacliente.getIdcuenta());
				
				//grabar
				referenciadirDAO.saveReferenciadir(session, referenciadirCobranza);
			}
			
			//debitobco
			int maxIddebitobco = debitosbcoDAO.maxIddebitobco(session) + 1;
			debitobco.setIddebitobco(maxIddebitobco);
			debitobco.setCtacliente(ctacliente);
			debitobco.setIdestado(1);
			
			//grabar
			debitosbcoDAO.saveDebitobco(session, debitobco);
			
			//actualizar iddebito de cuentacliente
			ctacliente.setDebitobco(debitobco);
			ctaclienteDAO.actualizarCtacliente(session, ctacliente);
			
			//telefono
			for(Telefono telefono : lisTelefonos){
				//secuencia
				int maxIdtelefono =  telefonoDAO.maxIdtelefono(session) + 1;
				telefono.setIdtelefono(maxIdtelefono);
				telefono.setCtacliente(ctacliente);
				
				Estado estadoTelefono = new Estado();
				estadoTelefono.setIdestado(1);
				telefono.setEstado(estadoTelefono);
				
				//auditoria
				telefono.setIdusuario(usuarioBean.getUsuario().getIdusuario());
				fecharegistro = new Date();
				telefono.setFecha(fecharegistro);
					
				//grabar
				telefonoDAO.saveTelefono(session, telefono);
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
