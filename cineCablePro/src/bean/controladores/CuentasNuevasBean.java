package bean.controladores;

import exceptions.VerificarIdException;
import global.Parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;
import pojo.annotations.Persona;

import bo.negocio.CtaclienteBO;
import bo.negocio.PersonaBO;

import util.FacesUtil;
import util.MessageUtil;
import util.VerificarId;

@ManagedBean
@ViewScoped
public class CuentasNuevasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8766998507305891779L;
	
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
	
	private int idcuenta;
	private int activeIndex;

	public CuentasNuevasBean() {
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes(), null, null, 0);
		cobrador = new Persona();
		vendedor = new Persona();
		direccionInstalacionBean = new DireccionBean();
		direccionCorrespondenciaBean = new DireccionBean();
		direccionConbranzaBean = new DireccionBean();
		idcuenta = 0;
		
		activeIndex = 0;
	}
	
	@PostConstruct
	public void initCuentasNuevasBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarProductos();
		}
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
	
	public void grabar(){
		if(nombreCuentaExiste()){
			if(validacionProductoOk()){
				if(validacionDireccionOk()){
					if(validacionDebitoBcoOk()){
						if(validacionTelefonoOk()){
							try{
								CtaclienteBO ctaclienteBO = new CtaclienteBO();
								
								//Asignamos data a ctacliente
								if(cobrador != null && cobrador.getIdpersona() > 0){
									ctacliente.setIdcobrador(cobrador.getIdpersona());
								}
								if(vendedor != null && vendedor.getIdpersona() > 0){
									ctacliente.setIdvendedor(vendedor.getIdpersona());
								}
								ctacliente.setIdcuenta(idcuenta);
								
								//Calles
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
								
								ctaclienteBO.grabarCuenta(ctacliente, productosBean.getLisProductosId(), direccionInstalacionBean.getDireccion(), direccionCorrespondenciaBean.getDireccion(), direccionConbranzaBean.getDireccion(), debitosBancariosBean.getDebitobco(), telefonosBean.getLisTelefonos(), direccionInstalacionBean.getReferenciadir(), direccionCorrespondenciaBean.getReferenciadir(), direccionConbranzaBean.getReferenciadir());
								
								FacesUtil facesUtil = new FacesUtil();
								try {
									facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} catch(Exception re) {
								re.printStackTrace();
								new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
							}
						}
					}
				}
			}
		}else{
			activeIndex = 0;
			new MessageUtil().showWarnMessage("Nombre de cuenta ya existe en seccion Productos Cliente", "");
		}
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
	
	private boolean validacionProductoOk(){
		boolean ok = false;
		
		if(cobrador != null && cobrador.getIdpersona() > 0){
			if(vendedor != null && vendedor.getIdpersona() > 0){
				if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
					ok = true;
				}else{
					activeIndex = 0;
					new MessageUtil().showWarnMessage("Debe seleccionar al menos un producto en seccion Productos Cliente", "");
				}
			}else{
				activeIndex = 0;
				new MessageUtil().showWarnMessage("Debe ingresar Nombre Vendedor en seccion Productos Cliente", null);
			}
		}else{
			activeIndex = 0;
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
				activeIndex = 1;
				new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Instalacion", null);
			}
		}else{
			activeIndex = 1;
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
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Instalacion", null);
						}
					}else{
						ok = false;
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Instalacion", null);
					}
				}else{
					if(direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionInstalacionBean.getDireccion().getUbicacion() != null && direccionInstalacionBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionInstalacionBean.getDireccion().getSolar() != null && direccionInstalacionBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 1;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Instalacion", null);
							}
						}else{
							ok = false;
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Instalacion", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Instalacion", null);
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
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
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
				activeIndex = 1;
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
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Correspondencia", null);
						}
					}else{
						ok = false;
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Correspondencia", null);
					}
				}else{
					if(direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionCorrespondenciaBean.getDireccion().getUbicacion() != null && direccionCorrespondenciaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionCorrespondenciaBean.getDireccion().getSolar() != null && direccionCorrespondenciaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 1;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Correspondencia", null);
							}
						}else{
							ok = false;
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Correspondencia", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
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
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
				activeIndex = 1;
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
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Cobranza", null);
						}
					}else{
						ok = false;
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Cobranza", null);
					}
				}else{
					if(direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionConbranzaBean.getDireccion().getUbicacion() != null && direccionConbranzaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionConbranzaBean.getDireccion().getSolar() != null && direccionConbranzaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 1;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Cobranza", null);
							}
						}else{
							ok = false;
							activeIndex = 1;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Cobranza", null);
						}
					}
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
				activeIndex = 1;
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
						activeIndex = 1;
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					activeIndex = 1;
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
			
			if(debitosBancariosBean.getDebitobco().getIdtipodebito() > 0){
				ok = true;
			}else{
				activeIndex = 2;
				new MessageUtil().showWarnMessage("Debe seleccionar una forma de cobro en seccion Debito Bancario", null);
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO || debitosBancariosBean.getDebitobco().getIdtipodebito() == Parametro.TIPO_DEBITO_TARJETA){
					if(debitosBancariosBean.getDebitobco().getBancos().getIdbanco() > 0){
						if(debitosBancariosBean.getDebitobco().getNrodebito() != null && debitosBancariosBean.getDebitobco().getNrodebito().trim().length() > 0){
							if(debitosBancariosBean.getDebitobco().getPropietario() != null && debitosBancariosBean.getDebitobco().getPropietario().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								activeIndex = 2;
								new MessageUtil().showWarnMessage("Debe ingresar propietario en seccion Debito Bancario", null);
							}
						}else{
							ok = false;
							activeIndex = 2;
							new MessageUtil().showWarnMessage("Debe ingresar Nro Doc en seccion Debito Bancario", null);
						}
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe seleccionar banco/tarjeta en seccion Debito Bancario", null);
					}
				}
			}
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO){
					if(debitosBancariosBean.getDebitobco().getIdtipocuenta() > 0){
						ok = true;
					}else{
						ok = false;
						activeIndex = 2;
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
							activeIndex = 2;
						}
					}else{
						ok = false;
						activeIndex = 2;
						new MessageUtil().showWarnMessage("Debe ingresar # identidad en seccion Debito Bancario", null);
					}
				}else{
					ok = false;
					activeIndex = 2;
					new MessageUtil().showWarnMessage("Debe seleccionar tipo identidad en seccion Debito Bancario", null);
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
			activeIndex = 3;
			new MessageUtil().showWarnMessage("Debe agregar al menos un telefono en seccion Telefonos Cliente", null);
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

	public DebitosBancariosBean getDebitosBancariosBean() {
		return debitosBancariosBean;
	}

	public void setDebitosBancariosBean(DebitosBancariosBean debitosBancariosBean) {
		this.debitosBancariosBean = debitosBancariosBean;
	}

	public ProductosBean getProductosBean() {
		return productosBean;
	}

	public void setProductosBean(ProductosBean productosBean) {
		this.productosBean = productosBean;
	}

	public TelefonosBean getTelefonosBean() {
		return telefonosBean;
	}

	public void setTelefonosBean(TelefonosBean telefonosBean) {
		this.telefonosBean = telefonosBean;
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

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
}
