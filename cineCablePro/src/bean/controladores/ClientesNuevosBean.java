package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import net.cinecable.dao.IMotivosDao;
import net.cinecable.enums.TipoSolicitudes;
import net.cinecable.model.base.Ordenes;

import exceptions.VerificarIdException;
import global.Parametro;

import bo.negocio.CtaclienteBO;
import bo.negocio.PersonaBO;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Persona;
import pojo.annotations.Tipooperacion;
import pojo.annotations.Usuario;

import util.FacesUtil;
import util.MessageUtil;
import util.VerificarId;

@ManagedBean
@ViewScoped
public class ClientesNuevosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6075128135422100812L;
	
	@ManagedProperty(value="#{dbasCliBean}")
	private DbasCliBean dbasCliBean;
	
	@ManagedProperty(value="#{productosBean}") 
	private ProductosBean productosBean;
	
	private DireccionBean direccionInstalacionBean;
	private DireccionBean direccionCorrespondenciaBean;
	private DireccionBean direccionConbranzaBean;
	
	@ManagedProperty(value="#{debitosBancariosBean}")
	private DebitosBancariosBean debitosBancariosBean;
	
	@ManagedProperty(value="#{telefonosBean}")
	private TelefonosBean telefonosBean;
	
	private Ctacliente ctacliente;
	private Persona cobrador;
	private Persona vendedor;
	
	private int activeIndex;
	@ManagedProperty(value = "#{calendarOrdenesBean}")
	private CalendarOrdenesBean calendarOrdenesBean;
	private int mes;
	private int anio;
	private Ordenes ordenes;
	private List<Motivos> lisMotivosHorarios;
	@EJB
	private IMotivosDao iMotivoDao;
	
	public ClientesNuevosBean() {
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes(), "", "", 0);
		cobrador = new Persona();
		vendedor = new Persona();
		direccionInstalacionBean = new DireccionBean();
		direccionCorrespondenciaBean = new DireccionBean();
		direccionConbranzaBean = new DireccionBean();
		
		activeIndex = 0;
		mes = 0;
		anio = Calendar.getInstance().get(Calendar.YEAR);
		ordenes = new Ordenes();
		ordenes.setCuentaCliente(new Ctacliente());
		ordenes.setEmpresa(new Empresa());
		ordenes.setEstado(new Estado());
		ordenes.setHorario(new Motivos());
		ordenes.setMotivo(new Motivos());
		ordenes.setProducto(new Ctasprod());
		ordenes.setTipoOperacion(new Tipooperacion());
		ordenes.setUsuario(new Usuario());
		lisMotivosHorarios = new ArrayList<Motivos>();
	}
	
	@PostConstruct
	public void PostClientesNuevosBean() {
		calendarOrdenesBean.inicializarAnios();
		calendarOrdenesBean.inicializarMeses();
		llenarHorarios();
		dbasCliBean.getClientes().setIdtipopersona(Parametro.TIPO_PERSONA_NATURAL);
		dbasCliBean.getClientes().getEstadocivil().setIdestadocivil(Parametro.ESTADO_CIVIL_SOLTERO);
		dbasCliBean.getClientes().setGenero(Parametro.GENERO_MASCULINO);
		dbasCliBean.getClientes().getTipocliente().setIdtipocliente(Parametro.TIPO_CLIENTE_NORMAL);
		dbasCliBean.getClientes().setEmail("clientegeneral@standard.com");
		dbasCliBean.getClientes().getTipoidentidad().setIdtidentidad(Parametro.TIPO_IDENTIFICACION_CEDULA);
	}
	
	public void muestraCalendario(){
		if(mes > 0){
			//Si he seleccionado un mes, busco fechas del mes y muestro calendario
			Calendar fecha = Calendar.getInstance();
			if(anio == fecha.get(Calendar.YEAR) && (mes-1) == fecha.get(Calendar.MONTH)){
				fecha.set(anio,mes-1,Calendar.DAY_OF_MONTH,0,0,0);
			}else{
				fecha.set(anio,mes-1,1,0,0,0);
			}
			calendarOrdenesBean.setFecha(fecha.getTime());
			
			calendarOrdenesBean.mostrarEventosAgendados(TipoSolicitudes.InstNueva.getDescripcion(), mes);
			
			calendarOrdenesBean.setMostrarCalendar(true);
		}else{
			//si no he seleccionado mes oculto calendario
			calendarOrdenesBean.setMostrarCalendar(false);
			calendarOrdenesBean.setFecha(null);
		}
	}
	
	public void llenarHorarios() {
		lisMotivosHorarios = new ArrayList<Motivos>();
		lisMotivosHorarios = iMotivoDao.getMotivosByTipoOperacion(TipoSolicitudes.Horarios.getDescripcion());
	}

	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}
	
	public DbasCliBean getDbasCliBean() {
		return dbasCliBean;
	}

	public void setDbasCliBean(DbasCliBean dbasCliBean) {
		this.dbasCliBean = dbasCliBean;
	}

	public DebitosBancariosBean getDebitosBancariosBean() {
		return debitosBancariosBean;
	}

	public void setDebitosBancariosBean(DebitosBancariosBean debitosBancariosBean) {
		this.debitosBancariosBean = debitosBancariosBean;
	}

	public TelefonosBean getTelefonosBean() {
		return telefonosBean;
	}

	public void setTelefonosBean(TelefonosBean telefonosBean) {
		this.telefonosBean = telefonosBean;
	}

	public void onChange(TabChangeEvent event) {
		String idTab = event.getTab().getId();
		
		if(idTab.equalsIgnoreCase("idProductoCliente")){
			String nombreCuentaCliente = "";
			
			if(dbasCliBean.getClientes() != null && dbasCliBean.getClientes().getNombre1() != null && dbasCliBean.getClientes().getNombre1().trim().length() > 0){
				nombreCuentaCliente += dbasCliBean.getClientes().getNombre1();
			}
			if(dbasCliBean.getClientes() != null && dbasCliBean.getClientes().getApellido1() != null && dbasCliBean.getClientes().getApellido1().trim().length() > 0){
				nombreCuentaCliente += (nombreCuentaCliente.trim().length() > 0 ? " " : "") +  dbasCliBean.getClientes().getApellido1();
			}
			if(dbasCliBean.getClientes() != null && dbasCliBean.getClientes().getApellido2() != null && dbasCliBean.getClientes().getApellido2().trim().length() > 0){
				nombreCuentaCliente += (nombreCuentaCliente.trim().length() > 0 ? " " : "") +  dbasCliBean.getClientes().getApellido2();
			}
			
			ctacliente.setNombre(nombreCuentaCliente);
			RequestContext.getCurrentInstance().update("form-contentpage:acc:idtxncta");
		}else{
			if(idTab.equalsIgnoreCase("idDebitoBancario")){
				if(dbasCliBean.getClientes().getTipoidentidad().getIdtidentidad() > 0 && dbasCliBean.getClientes().getIdcliente() != null && dbasCliBean.getClientes().getIdcliente().trim().length() > 0){
					if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() <= 0){
						if(debitosBancariosBean.getDebitobco().getIdentificacion() == null || debitosBancariosBean.getDebitobco().getIdentificacion().trim().length() == 0){
							debitosBancariosBean.getDebitobco().setIdtipoidentificacion(dbasCliBean.getClientes().getTipoidentidad().getIdtidentidad());
							debitosBancariosBean.getDebitobco().setIdentificacion(dbasCliBean.getClientes().getIdcliente());
							
							RequestContext.getCurrentInstance().update("form-contentpage:acc:idcomponentedbancario:form-dbancarioscomponent:idCmbTdcli");
							RequestContext.getCurrentInstance().update("form-contentpage:acc:idcomponentedbancario:form-dbancarioscomponent:idtxIdentidad");
						}
					}
				}
			}else{
				if(idTab.equalsIgnoreCase("idOrdenInstalacion")){
					mes = Calendar.getInstance().get(Calendar.MONTH);
					RequestContext.getCurrentInstance().update("form-contentpage:acc:idMes");
					//<p:ajax update="idcontrolcalendar" listener="#{clientesNuevosBean.muestraCalendario()}"></p:ajax>
				}
			}
		}
	}
	
	public void grabarNuevoCliente(){
		if(validacionOk()){
			try{
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				
				//Asignamos data a ctacliente
				if(dbasCliBean.getClientes() != null){
					ctacliente.setClientes(dbasCliBean.getClientes());
				}else{
					ctacliente.setClientes(null);
				}
				if(cobrador != null && cobrador.getIdpersona() > 0){
					ctacliente.setIdcobrador(cobrador.getIdpersona());
				}
				if(vendedor != null && vendedor.getIdpersona() > 0){
					ctacliente.setIdvendedor(vendedor.getIdpersona());
				}
				
				//calles
				if(direccionInstalacionBean.getDireccion().getCalleprincipal() != null && direccionInstalacionBean.getDireccion().getCalleprincipal().getIdcalleprincipal() == 0){
					direccionInstalacionBean.getDireccion().setCalleprincipal(null);
				}
				if(direccionInstalacionBean.getDireccion().getCallesecundaria() != null && direccionInstalacionBean.getDireccion().getCallesecundaria().getIdcallesecundaria() == 0){
					direccionInstalacionBean.getDireccion().setCallesecundaria(null);
				}
				if(direccionCorrespondenciaBean.getDireccion().getCalleprincipal() != null && direccionCorrespondenciaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() == 0){
					direccionCorrespondenciaBean.getDireccion().setCalleprincipal(null);
				}
				if(direccionCorrespondenciaBean.getDireccion().getCallesecundaria() != null && direccionCorrespondenciaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() == 0){
					direccionCorrespondenciaBean.getDireccion().setCallesecundaria(null);
				}
				if(direccionConbranzaBean.getDireccion().getCalleprincipal() != null && direccionConbranzaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() == 0){
					direccionConbranzaBean.getDireccion().setCalleprincipal(null);
				}
				if(direccionConbranzaBean.getDireccion().getCallesecundaria() != null && direccionConbranzaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() == 0){
					direccionConbranzaBean.getDireccion().setCallesecundaria(null);
				}
				
				//Ubicacion
				if(direccionInstalacionBean.getDireccion().getUbicacion() != null && direccionInstalacionBean.getDireccion().getUbicacion().getIdubicacion() == 0){
					direccionInstalacionBean.getDireccion().setUbicacion(null);
				}
				if(direccionCorrespondenciaBean.getDireccion().getUbicacion() != null && direccionCorrespondenciaBean.getDireccion().getUbicacion().getIdubicacion() == 0){
					direccionCorrespondenciaBean.getDireccion().setUbicacion(null);
				}
				if(direccionConbranzaBean.getDireccion().getUbicacion() != null && direccionConbranzaBean.getDireccion().getUbicacion().getIdubicacion() == 0){
					direccionConbranzaBean.getDireccion().setUbicacion(null);
				}
				
				//Edificio
				if(direccionInstalacionBean.getDireccion().getEdificio() != null && direccionInstalacionBean.getDireccion().getEdificio().getIdedificio() == 0){
					direccionInstalacionBean.getDireccion().setEdificio(null);
				}
				if(direccionCorrespondenciaBean.getDireccion().getEdificio() != null && direccionCorrespondenciaBean.getDireccion().getEdificio().getIdedificio() == 0){
					direccionCorrespondenciaBean.getDireccion().setEdificio(null);
				}
				if(direccionConbranzaBean.getDireccion().getEdificio() != null && direccionConbranzaBean.getDireccion().getEdificio().getIdedificio() == 0){
					direccionConbranzaBean.getDireccion().setEdificio(null);
				}
				
				//referencia
				if(direccionInstalacionBean.getReferenciadir() != null && direccionInstalacionBean.getReferenciadir().getReferencia() != null && direccionInstalacionBean.getReferenciadir().getReferencia().trim().length() == 0){
					direccionInstalacionBean.setReferenciadir(null);
				}
				if(direccionCorrespondenciaBean.getReferenciadir() != null && direccionCorrespondenciaBean.getReferenciadir().getReferencia() != null && direccionCorrespondenciaBean.getReferenciadir().getReferencia().trim().length() == 0){
					direccionCorrespondenciaBean.setReferenciadir(null);
				}
				if(direccionConbranzaBean.getReferenciadir() != null && direccionConbranzaBean.getReferenciadir().getReferencia() != null && direccionConbranzaBean.getReferenciadir().getReferencia().trim().length() == 0){
					direccionConbranzaBean.setReferenciadir(null);
				}
				
				//banco
				if(debitosBancariosBean.getDebitobco() != null && debitosBancariosBean.getDebitobco().getBancos() != null && debitosBancariosBean.getDebitobco().getBancos().getIdbanco() == 0){
					debitosBancariosBean.getDebitobco().setBancos(null);
				}
				
				//orden
				ordenes.setFechaEjecucion(calendarOrdenesBean.getFecha());
				
				if(ordenes.getCuentaCliente() != null && ordenes.getCuentaCliente().getIdcuenta() == 0){
					ordenes.setCuentaCliente(null);
				}
				
				ctaclienteBO.grabarCliente(ctacliente, dbasCliBean.getConyuge(), productosBean.getLisProductosId(), direccionInstalacionBean.getDireccion(), direccionCorrespondenciaBean.getDireccion(), direccionConbranzaBean.getDireccion(), debitosBancariosBean.getDebitobco(), telefonosBean.getLisTelefonos(), direccionInstalacionBean.getReferenciadir(), direccionCorrespondenciaBean.getReferenciadir(), direccionConbranzaBean.getReferenciadir(), ordenes);
				
				FacesUtil facesUtil = new FacesUtil();
				try {
					facesUtil.redirect("pagos.jsf?faces-redirect=true&idcuenta=" + ctacliente.getIdcuenta());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
			}
		}
	}
	
	private boolean validacionOk(){
		boolean ok = false;
		
		if(validacionCliente()){
			if(!dbasCliBean.cedulaExiste()){
				if(dbasCliBean.validacionIdentificacion()){
					if(nombreCuentaExiste()){
						if(validacionProductoOk()){
							if(validacionDireccionOk()){
								if(validacionDebitoBcoOk()){
									if(validacionTelefonoOk()){
										if(validacionOrden()){
											ok = true;
										}
									}
								}
							}
						}
					}else{
						new MessageUtil().showWarnMessage("Nombre de cuenta ya existe", null);
					}
				}
			}
		}
									
		return ok;
	}
	
	private boolean validacionCliente(){
		boolean ok = false;
		
		try{
		
			VerificarId verificarId  = new VerificarId();
			
			if(dbasCliBean.getClientes().getTipoidentidad().getIdtidentidad() > 0){
				if(dbasCliBean.getClientes().getTipoidentidad().getIdtidentidad() == Parametro.TIPO_IDENTIFICACION_OTRO || verificarId.verificarId(dbasCliBean.getClientes().getIdcliente())){
					if(dbasCliBean.getClientes().getIdtipopersona() > 0){
						if(dbasCliBean.getClientes().getNombre1() != null && dbasCliBean.getClientes().getNombre1().trim().length() > 0){
							if(dbasCliBean.getClientes().getApellido1() != null && dbasCliBean.getClientes().getApellido1().trim().length() > 0){
								if(dbasCliBean.getClientes().getEmail() != null && dbasCliBean.getClientes().getEmail().trim().length() > 0){
									if(dbasCliBean.getClientes().getEstadocivil().getIdestadocivil()  > 0){
										if(dbasCliBean.getClientes().getGenero()  > 0){
											dbasCliBean.getClientes().setIdentificacion(dbasCliBean.getClientes().getIdcliente());
											ok = true;
										}else{
											activeIndex = 0;
											new MessageUtil().showWarnMessage("Seleccionar Género en seccion Datos Basicos", null);
										}
									}else{
										activeIndex = 0;
										new MessageUtil().showWarnMessage("Seleccionar Estado Civil en seccion Datos Basicos", null);
									}
								}else{
									activeIndex = 0;
									new MessageUtil().showWarnMessage("Ingresar Correo Electrónico en seccion Datos Basicos", null);
								}
							}else{
								activeIndex = 0;
								new MessageUtil().showWarnMessage("Ingresar Primer Apellido en seccion Datos Basicos", null);
							}
						}else{
							activeIndex = 0;
							new MessageUtil().showWarnMessage("Ingresar Primer Nombre en seccion Datos Basicos", null);
						}
					}else{
						activeIndex = 0;
						new MessageUtil().showWarnMessage("Seleccionar Tipo Persona en seccion Datos Basicos", null);
					}
				}else{
					activeIndex = 0;
					new MessageUtil().showWarnMessage("Ingresar # Identidad en seccion Datos Basicos", null);
				}
			}else{
				activeIndex = 0;
				new MessageUtil().showWarnMessage("Seleccionar Tipo Identidad en seccion Datos Basicos", null);
			}
			
			if(ok){
				if(dbasCliBean.getClientes().getEstadocivil().getIdestadocivil() == Parametro.ESTADO_CIVIL_CASADO || dbasCliBean.getClientes().getEstadocivil().getIdestadocivil() == Parametro.ESTADO_CIVIL_UNION_LIBRE){
					if(dbasCliBean.getConyuge().getNombre1() == null || dbasCliBean.getConyuge().getNombre1().trim().length() == 0){
						ok = false;
						activeIndex = 0;
						new MessageUtil().showWarnMessage("Ingresar el Primer Nombre del Cónyugue en seccion Datos Basicos", null);
					}else{
						if(dbasCliBean.getConyuge().getApellido1() == null || dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
							ok = false;
							activeIndex = 0;
							new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del Cónyugue en seccion Datos Basicos", null);
						}else{
							if(dbasCliBean.getConyuge().getIdentificacion() == null || dbasCliBean.getConyuge().getIdentificacion().trim().length() == 0){
								ok = false;
								activeIndex = 0;
								new MessageUtil().showWarnMessage("Ingresar # Identidad del Cónyugue en seccion Datos Basicos", null);
							}else{
								 if(!verificarId.verificarId(dbasCliBean.getConyuge().getIdentificacion())){
									 ok = false;
									 activeIndex = 0;
								 }
							}
						}
					}	
				}
			}
			
			if(ok){
				if(dbasCliBean.getClientes().getTipoidentidad().getIdtidentidad() == Parametro.TIPO_IDENTIFICACION_RUC &&  dbasCliBean.getClientes().getIdtipopersona() == Parametro.TIPO_PERSONA_JURIDICO){
					if(dbasCliBean.getClientes().getEmpresa_1() == null || dbasCliBean.getClientes().getEmpresa_1().trim().length() == 0){
						ok = false;
						activeIndex = 0;
						new MessageUtil().showWarnMessage("Ingresar Nombre Empresa en seccion Datos Basicos", null);
					}
				}
			}
			
		}catch(VerificarIdException e){
			ok = false;
			e.printStackTrace();
			new MessageUtil().showWarnMessage(e.getMessage() + " Seccion Datos Basicos.", e.getMessage() + " Seccion Datos Basicos.");
		}
		
		return ok;
	}
	
	private boolean validacionProductoOk(){
		boolean ok = false;
		
		if(cobrador != null && cobrador.getIdpersona() > 0){
			if(vendedor != null && vendedor.getIdpersona() > 0){
				if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
					ok = true;
				}else{
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe seleccionar al menos un producto en seccion Productos Cliente", "");
				}
			}else{
				activeIndex = 1;
				new MessageUtil().showWarnMessage("Debe ingresar Nombre Vendedor en seccion Productos Cliente", null);
			}
		}else{
			activeIndex = 1;
			new MessageUtil().showWarnMessage("Debe ingresar Nombre Cobrador en seccion Productos Cliente", null);
		}
		
		return ok;
	}
	
	private boolean validacionDireccionOk(){
		boolean ok = false;
		
		//validaciones de direccion de instalacion
		if(direccionInstalacionBean.getCiudadSelected() != null && direccionInstalacionBean.getCiudadSelected().getIdciudad() > 0){
			if(direccionInstalacionBean.getDireccion().getSector() != null && direccionInstalacionBean.getDireccion().getSector().getIdsector() > 0){
				ok = true;
			}else{
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Instalacion", null);
			}
		}else{
			activeIndex = 2;
			new MessageUtil().showWarnMessage("Debe seleccionar la ciudad en la seccion Direccion de Instalacion", null);
		}
		
		if(ok){
			if(direccionInstalacionBean.getDireccion().getTiposector() != null && direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() > 0){
				if(direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionInstalacionBean.getDireccion().getCalleprincipal() != null && direccionInstalacionBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionInstalacionBean.getDireccion().getCallesecundaria() != null && direccionInstalacionBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Instalacion", null);
						}
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Instalacion", null);
					}
				}else{
					if(direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionInstalacionBean.getDireccion().getUbicacion() != null && direccionInstalacionBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionInstalacionBean.getDireccion().getSolar() != null && direccionInstalacionBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 2;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Instalacion", null);
							}
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Instalacion", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar Tipo Sector en la seccion Direccion de Instalacion", null);
			}
		}
		
		if(ok){
			if(direccionInstalacionBean.getDireccion().getNodos() != null && direccionInstalacionBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionInstalacionBean.getDireccion().getNumero() > 0){
					if(direccionInstalacionBean.getDireccion().getVineta() != null && direccionInstalacionBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Instalacion", null);
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar nodo en la seccion Direccion de Instalacion", null);
			}
		}
		
		if(ok){
			if(direccionInstalacionBean.getDireccion().getEdificio() != null && direccionInstalacionBean.getDireccion().getEdificio().getIdedificio() > 0){
				if(direccionInstalacionBean.getDireccion().getPiso() > 0){
					if(direccionInstalacionBean.getDireccion().getDepartamento() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero de piso en la seccion Direccion de Instalacion", null);
				}
			}
		}
		
		//validaciones de direccion de correspondencia
		if(ok){
			if(direccionCorrespondenciaBean.getCiudadSelected() != null && direccionCorrespondenciaBean.getCiudadSelected().getIdciudad() > 0){
				if(direccionCorrespondenciaBean.getDireccion().getSector() != null && direccionCorrespondenciaBean.getDireccion().getSector().getIdsector() > 0){
					ok = true;
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar la ciudad de Instalación en la seccion Direccion de Correspondencia", null);
			}
		}
		
		if(ok){
			if(direccionCorrespondenciaBean.getDireccion().getTiposector() != null && direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() > 0){
				if(direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionCorrespondenciaBean.getDireccion().getCalleprincipal() != null && direccionCorrespondenciaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionCorrespondenciaBean.getDireccion().getCallesecundaria() != null && direccionCorrespondenciaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Correspondencia", null);
						}
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Correspondencia", null);
					}
				}else{
					if(direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionCorrespondenciaBean.getDireccion().getUbicacion() != null && direccionCorrespondenciaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionCorrespondenciaBean.getDireccion().getSolar() != null && direccionCorrespondenciaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 2;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Correspondencia", null);
							}
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Correspondencia", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar Tipo Sector en la seccion Direccion de Correspondencia", null);
			}
		}
		
		if(ok){
			if(direccionCorrespondenciaBean.getDireccion().getNodos() != null && direccionCorrespondenciaBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionCorrespondenciaBean.getDireccion().getNumero() > 0){
					if(direccionCorrespondenciaBean.getDireccion().getVineta() != null && direccionCorrespondenciaBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar nodo en la seccion Direccion de Correspondencia", null);
			}
		}
		
		if(ok){
			if(direccionCorrespondenciaBean.getDireccion().getEdificio() != null && direccionCorrespondenciaBean.getDireccion().getEdificio().getIdedificio() > 0){
				if(direccionCorrespondenciaBean.getDireccion().getPiso() > 0){
					if(direccionCorrespondenciaBean.getDireccion().getDepartamento() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero de piso en la seccion Direccion de Correspondencia", null);
				}
			}
		}
		
		//validaciones de direccion de cobranza
		if(ok){
			if(direccionConbranzaBean.getCiudadSelected() != null && direccionConbranzaBean.getCiudadSelected().getIdciudad() > 0){
				if(direccionConbranzaBean.getDireccion().getSector() != null && direccionConbranzaBean.getDireccion().getSector().getIdsector() > 0){
					ok = true;
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar la ciudad en la seccion Direccion de Cobranza", null);
			}
		}
		
		if(ok){
			if(direccionConbranzaBean.getDireccion().getTiposector() != null && direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() > 0){
				if(direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionConbranzaBean.getDireccion().getCalleprincipal() != null && direccionConbranzaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionConbranzaBean.getDireccion().getCallesecundaria() != null && direccionConbranzaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Cobranza", null);
						}
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Cobranza", null);
					}
				}else{
					if(direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionConbranzaBean.getDireccion().getUbicacion() != null && direccionConbranzaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionConbranzaBean.getDireccion().getSolar() != null && direccionConbranzaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 2;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Cobranza", null);
							}
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Cobranza", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar Tipo Sector en la seccion Direccion de Cobranza", null);
			}
		}
		
		if(ok){
			if(direccionConbranzaBean.getDireccion().getNodos() != null && direccionConbranzaBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionConbranzaBean.getDireccion().getNumero() > 0){
					if(direccionConbranzaBean.getDireccion().getVineta() != null && direccionConbranzaBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar nodo en la seccion Direccion de Cobranza", null);
			}
		}
		
		if(ok){
			if(direccionConbranzaBean.getDireccion().getEdificio() != null && direccionConbranzaBean.getDireccion().getEdificio().getIdedificio() > 0){
				if(direccionConbranzaBean.getDireccion().getPiso() > 0){
					if(direccionConbranzaBean.getDireccion().getDepartamento() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe ingresar un numero de piso en la seccion Direccion de Cobranza", null);
				}
			}
		}
		
		return ok;
	}
	
	public boolean validacionDebitoBcoOk(){
		boolean ok = false;
		
		try{
			
			VerificarId verificarId  = new VerificarId();
			
			if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() > 0){
				ok = true;
			}else{
				activeIndex = 3;
				new MessageUtil().showWarnMessage("Debe seleccionar una forma de cobro en seccion Debito Bancario", null);
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO || debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_TARJETA){
					if(debitosBancariosBean.getDebitobco().getBancos().getIdbanco() > 0){
						if(debitosBancariosBean.getDebitobco().getNrodebito() != null && debitosBancariosBean.getDebitobco().getNrodebito().trim().length() > 0){
							if(debitosBancariosBean.getDebitobco().getPropietario() != null && debitosBancariosBean.getDebitobco().getPropietario().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 3;
								new MessageUtil().showWarnMessage("Debe ingresar propietario en seccion Debito Bancario", null);
							}
						}else{
							ok = false;
							activeIndex = 3;
							new MessageUtil().showWarnMessage("Debe ingresar Nro Doc en seccion Debito Bancario", null);
						}
					}else{
						ok = false;
						activeIndex = 3;
						new MessageUtil().showWarnMessage("Debe seleccionar banco/tarjeta en seccion Debito Bancario", null);
					}
				}
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getTipodebito().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO){
					if(debitosBancariosBean.getDebitobco().getIdtipocuenta() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 3;
						new MessageUtil().showWarnMessage("Debe seleccionar tipo de cuenta en seccion Debito Bancario", null);
					}
				}
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() > 0){
					if(debitosBancariosBean.getDebitobco().getIdentificacion() != null && debitosBancariosBean.getDebitobco().getIdentificacion().trim().length() > 0){
						if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() == Parametro.TIPO_IDENTIFICACION_OTRO || verificarId.verificarId(debitosBancariosBean.getDebitobco().getIdentificacion())){
							ok = true;
						}else{
							ok = false;
							activeIndex = 3;
						}
					}else{
						ok = false;
						activeIndex = 3;
						new MessageUtil().showWarnMessage("Debe ingresar # identidad en seccion Debito Bancario", null);
					}
				}else{
					ok = false;
					activeIndex = 3;
					new MessageUtil().showWarnMessage("Debe seleccionar tipo identidad en seccion Debito Bancario", null);
				}
			}
			
			if(ok){
				if(debitosBancariosBean.validacionIdentificacion()){
					ok = true;
				}else{
					ok = false;
					activeIndex = 3;
				}
			}
		}catch(VerificarIdException e){
			ok = false;
			e.printStackTrace();
			new MessageUtil().showWarnMessage(e.getMessage() + " Seccion Debito Bancario.", e.getMessage() + " Seccion Debito Bancario.");
		}
		
		return ok;
	}
	
	private boolean validacionTelefonoOk(){
		boolean ok = false;
		
		if(telefonosBean.getLisTelefonos() != null && telefonosBean.getLisTelefonos().size() > 0){
			ok = true;
		}else{
			activeIndex = 4;
			new MessageUtil().showWarnMessage("Debe agregar al menos un telefono en seccion Telefonos Cliente", null);
		}
		
		return ok;
	}
	
	private boolean validacionOrden(){
		boolean ok = false;
		
		if(calendarOrdenesBean.getFecha() != null){
			ok = true;
		}else{
			activeIndex = 5;
			new MessageUtil().showWarnMessage("Debe seleccionar la fecha de Instalacion en seccion Ordenes de Instalacion", null);
		}
		
		if(ok && ordenes.getHorario().getIdmotivo() > 0){
			ok = true;
		}else{
			ok = false;
			activeIndex = 5;
			new MessageUtil().showWarnMessage("Debe seleccionar el horario en seccion Ordenes de Instalacion", null);
		}
		
		return ok;
	}
	
	public List<Persona> buscarCobrador(String query) {
		List<Persona> lisCobradores = new ArrayList<Persona>();
		
		lisCobradores = buscarPersona(query, Parametro.AREA_COBRANZAS);
		
		return lisCobradores;
	}
	
	public List<Persona> buscarVendedor(String query) {
		List<Persona> lisVendedores = new ArrayList<Persona>();
		
		lisVendedores = buscarPersona(query, Parametro.AREA_VENTAS);
		
		return lisVendedores;
	}
	
	private List<Persona> buscarPersona(String query, int idarea) {
		List<Persona> lista = new ArrayList<Persona>();
		
		List<Persona> lisPersona = new ArrayList<Persona>();
		PersonaBO personaBO = new PersonaBO();
		int args[] = {0};
		String[] nombres = null;
		if(query != null && query.trim().length() > 0){
			nombres = query.split(" ");
		}
		lisPersona = personaBO.lisPersonaByPage(nombres, idarea, 10, 0, args);
		
		if(lisPersona != null && lisPersona.size() > 0){
			for(Persona persona : lisPersona){
				lista.add(persona);
			}
		}
		
		return lista;
	}
	
	public boolean nombreCuentaExiste(){
		boolean ok = false;
		
		try{
			CtaclienteBO ctaclienteBO = new CtaclienteBO();
			Ctacliente ctaclientetmp = ctaclienteBO.getCtaclienteByNombre(ctacliente.getNombre());
			
			if(ctaclientetmp == null || ctaclientetmp.getIdcuenta() == 0){
				ok = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
		}
		
		return ok;
	}
	
	public void direccionCorrespondenciaIgual(){
		try {
			direccionCorrespondenciaBean.setPaisSelected(direccionInstalacionBean.getPaisSelected().clonar());
			direccionCorrespondenciaBean.llenarLisProvincia();
			direccionCorrespondenciaBean.setProvinciaSelected(direccionInstalacionBean.getProvinciaSelected().clonar());
			direccionCorrespondenciaBean.llenarLisCiudad();
			direccionCorrespondenciaBean.setCiudadSelected(direccionInstalacionBean.getCiudadSelected().clonar());
			direccionCorrespondenciaBean.llenarDependientes();//llena sectores, tipos sector
			direccionCorrespondenciaBean.setDireccion(direccionInstalacionBean.getDireccion().clonar());//selecciona sector, tipo sector
			direccionCorrespondenciaBean.llenarDependientesSector();//borra(calle principal, calle secundaria, ubicacion, edificio, nodo), llena(tipos sector, nodos, edificios, ubicaciones)
			//direccionCorrespondenciaBean.llenarDependientesTipoSector();//borra(calle principal, calle secundaria, ubicacion, edificio, nodo), llena(ubicaciones)
			direccionCorrespondenciaBean.setDireccion(direccionInstalacionBean.getDireccion().clonar());
			direccionCorrespondenciaBean.setReferenciadir(direccionInstalacionBean.getReferenciadir().clonar());
			
			new MessageUtil().showInfoMessage("Listo, Direccion de Correspondencia igual que Instalacion", "");
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
		}
	}
	
	public void direccionCobranzaIgual(){
		try {
			direccionConbranzaBean.setPaisSelected(direccionInstalacionBean.getPaisSelected().clonar());
			direccionConbranzaBean.llenarLisProvincia();
			direccionConbranzaBean.setProvinciaSelected(direccionInstalacionBean.getProvinciaSelected().clonar());
			direccionConbranzaBean.llenarLisCiudad();
			direccionConbranzaBean.setCiudadSelected(direccionInstalacionBean.getCiudadSelected().clonar());
			direccionConbranzaBean.llenarDependientes();//llena sectores, tipos sector
			direccionConbranzaBean.setDireccion(direccionInstalacionBean.getDireccion().clonar());//selecciona sector, tipo sector
			direccionConbranzaBean.llenarDependientesSector();//borra(calle principal, calle secundaria, ubicacion, edificio, nodo), llena(tipos sector, nodos, edificios, ubicaciones)
			//direccionConbranzaBean.llenarDependientesTipoSector();
			direccionConbranzaBean.setDireccion(direccionInstalacionBean.getDireccion().clonar());
			direccionConbranzaBean.setReferenciadir(direccionInstalacionBean.getReferenciadir().clonar());
			
			new MessageUtil().showInfoMessage("Listo, Direccion de Cobranza igual que Instalacion", "");
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
		}
	}
	
	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public Persona getCobrador() {
		return cobrador;
	}

	public void setCobrador(Persona cobrador) {
		this.cobrador = cobrador;
	}

	public Persona getVendedor() {
		return vendedor;
	}

	public void setVendedor(Persona vendedor) {
		this.vendedor = vendedor;
	}

	public DireccionBean getDireccionInstalacionBean() {
		return direccionInstalacionBean;
	}

	public void setDireccionInstalacionBean(DireccionBean direccionInstalacionBean) {
		this.direccionInstalacionBean = direccionInstalacionBean;
	}

	public DireccionBean getDireccionCorrespondenciaBean() {
		return direccionCorrespondenciaBean;
	}

	public void setDireccionCorrespondenciaBean(
			DireccionBean direccionCorrespondenciaBean) {
		this.direccionCorrespondenciaBean = direccionCorrespondenciaBean;
	}

	public DireccionBean getDireccionConbranzaBean() {
		return direccionConbranzaBean;
	}

	public void setDireccionConbranzaBean(DireccionBean direccionConbranzaBean) {
		this.direccionConbranzaBean = direccionConbranzaBean;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public CalendarOrdenesBean getCalendarOrdenesBean() {
		return calendarOrdenesBean;
	}

	public void setCalendarOrdenesBean(CalendarOrdenesBean calendarOrdenesBean) {
		this.calendarOrdenesBean = calendarOrdenesBean;
	}

	

	public List<Motivos> getLisMotivosHorarios() {
		return lisMotivosHorarios;
	}

	public void setLisMotivosHorarios(List<Motivos> lisMotivosHorarios) {
		this.lisMotivosHorarios = lisMotivosHorarios;
	}

	public Ordenes getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Ordenes ordenes) {
		this.ordenes = ordenes;
	}
	
}
