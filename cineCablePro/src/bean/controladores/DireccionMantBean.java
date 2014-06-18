package bean.controladores;

import global.Parametro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Calleprincipal;
import pojo.annotations.Callesecundaria;
import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Edificio;
import pojo.annotations.Empresa;
import pojo.annotations.Referenciadir;
import pojo.annotations.Ubicacion;

import util.FacesUtil;
import util.MessageUtil;
import bo.negocio.CtaclienteBO;
import bo.negocio.DireccionBO;

@ManagedBean
@ViewScoped
public class DireccionMantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311714452524923038L;
	
	private DireccionBean direccionInstalacionBean;
	private DireccionBean direccionCorrespondenciaBean;
	private DireccionBean direccionConbranzaBean;

	private int idcuenta;
	private Ctacliente ctacliente;
	private String vacio;
	private boolean modificable;
	
	public DireccionMantBean() {
		direccionInstalacionBean = new DireccionBean();
		direccionCorrespondenciaBean = new DireccionBean();
		direccionConbranzaBean = new DireccionBean();
		
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
	}
	
	@PostConstruct
	public void initDireccionMantBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
			
			if(idcuenta > 0){
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				direccionInstalacionBean.consultarDireccionPorTipo(idcuenta, "I");
				direccionInstalacionBean.consultarSectoresCiudadesProvinciasPaises();
				
				direccionCorrespondenciaBean.consultarDireccionPorTipo(idcuenta, "C");
				direccionCorrespondenciaBean.consultarSectoresCiudadesProvinciasPaises();
				
				direccionConbranzaBean.consultarDireccionPorTipo(idcuenta, "B");
				direccionConbranzaBean.consultarSectoresCiudadesProvinciasPaises();

				if(direccionInstalacionBean.getDireccion() != null && direccionInstalacionBean.getDireccion().getIddireccion() > 0){	
					modificable = true;
				}else{
					modificable = false;
					new MessageUtil().showWarnMessage("Cliente no posee direccion ingresada!", "");
				}
			}
		}
		catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
		}
	}

	public void grabar(){
		if(validacionDireccionOk()){
			try{
				DireccionBO direccionBO = new DireccionBO();
				
				//calles
				if(direccionInstalacionBean.getDireccion().getCalleprincipal() == null){
					if(direccionInstalacionBean.getDireccionClon().getCalleprincipal() != null && direccionInstalacionBean.getDireccionClon().getCalleprincipal().getIdcalleprincipal() == 0){
						direccionInstalacionBean.getDireccion().setCalleprincipal(new Calleprincipal());
					}
				}
				if(direccionInstalacionBean.getDireccion().getCallesecundaria() == null){
					if(direccionInstalacionBean.getDireccionClon().getCallesecundaria() != null && direccionInstalacionBean.getDireccionClon().getCallesecundaria().getIdcallesecundaria() == 0){
						direccionInstalacionBean.getDireccion().setCallesecundaria(new Callesecundaria());
					}
				}
				
				if(direccionCorrespondenciaBean.getDireccion().getCalleprincipal() == null){
					if(direccionCorrespondenciaBean.getDireccionClon().getCalleprincipal() != null && direccionCorrespondenciaBean.getDireccionClon().getCalleprincipal().getIdcalleprincipal() == 0){
						direccionCorrespondenciaBean.getDireccion().setCalleprincipal(new Calleprincipal());
					}
				}
				if(direccionCorrespondenciaBean.getDireccion().getCallesecundaria() == null){
					if(direccionCorrespondenciaBean.getDireccionClon().getCallesecundaria() != null && direccionCorrespondenciaBean.getDireccionClon().getCallesecundaria().getIdcallesecundaria() == 0){
						direccionCorrespondenciaBean.getDireccion().setCallesecundaria(new Callesecundaria());
					}
				}
				
				if(direccionConbranzaBean.getDireccion().getCalleprincipal() == null){
					if(direccionConbranzaBean.getDireccionClon().getCalleprincipal() != null && direccionConbranzaBean.getDireccionClon().getCalleprincipal().getIdcalleprincipal() == 0){
						direccionConbranzaBean.getDireccion().setCalleprincipal(new Calleprincipal());
					}
				}
				if(direccionConbranzaBean.getDireccion().getCallesecundaria() == null){
					if(direccionConbranzaBean.getDireccionClon().getCallesecundaria() != null && direccionConbranzaBean.getDireccionClon().getCallesecundaria().getIdcallesecundaria() == 0){
						direccionConbranzaBean.getDireccion().setCallesecundaria(new Callesecundaria());
					}
				}
				
				//Ubicacion
				if(direccionInstalacionBean.getDireccion().getUbicacion() == null){
					if(direccionInstalacionBean.getDireccionClon().getUbicacion() != null && direccionInstalacionBean.getDireccionClon().getUbicacion().getIdubicacion() == 0){
						direccionInstalacionBean.getDireccion().setUbicacion(new Ubicacion());
					}
				}
				if(direccionCorrespondenciaBean.getDireccion().getUbicacion() == null){
					if(direccionCorrespondenciaBean.getDireccionClon().getUbicacion() != null && direccionCorrespondenciaBean.getDireccionClon().getUbicacion().getIdubicacion() == 0){
						direccionCorrespondenciaBean.getDireccion().setUbicacion(new Ubicacion());
					}
				}
				if(direccionConbranzaBean.getDireccion().getUbicacion() == null){
					if(direccionConbranzaBean.getDireccionClon().getUbicacion() != null && direccionConbranzaBean.getDireccionClon().getUbicacion().getIdubicacion() == 0){
						direccionConbranzaBean.getDireccion().setUbicacion(new Ubicacion());
					}
				}
				
				//Edificio
				if(direccionInstalacionBean.getDireccion().getEdificio() == null){
					if(direccionInstalacionBean.getDireccionClon().getEdificio() != null && direccionInstalacionBean.getDireccionClon().getEdificio().getIdedificio() == 0){
						direccionInstalacionBean.getDireccion().setEdificio(new Edificio());
					}
				}
				if(direccionCorrespondenciaBean.getDireccion().getEdificio() == null){
					if(direccionCorrespondenciaBean.getDireccionClon().getEdificio() != null && direccionCorrespondenciaBean.getDireccionClon().getEdificio().getIdedificio() == 0){
						direccionCorrespondenciaBean.getDireccion().setEdificio(new Edificio());
					}
				}
				if(direccionConbranzaBean.getDireccion().getEdificio() == null){
					if(direccionConbranzaBean.getDireccionClon().getEdificio() != null && direccionConbranzaBean.getDireccionClon().getEdificio().getIdedificio() == 0){
						direccionConbranzaBean.getDireccion().setEdificio(new Edificio());
					}
				}
				
				//referencia
				if(direccionInstalacionBean.getReferenciadir() == null){
					if(direccionInstalacionBean.getReferenciadirClon() != null && direccionInstalacionBean.getReferenciadirClon().getIdreferencia() == 0){
						direccionInstalacionBean.setReferenciadir(new Referenciadir());
					}
				}
				if(direccionCorrespondenciaBean.getReferenciadir() == null){
					if(direccionCorrespondenciaBean.getReferenciadirClon() != null && direccionCorrespondenciaBean.getReferenciadirClon().getIdreferencia() == 0){
						direccionCorrespondenciaBean.setReferenciadir(new Referenciadir());
					}
				}
				if(direccionConbranzaBean.getReferenciadir() == null){
					if(direccionConbranzaBean.getReferenciadirClon() != null && direccionConbranzaBean.getReferenciadirClon().getIdreferencia() == 0){
						direccionConbranzaBean.setReferenciadir(new Referenciadir());
					}
				}
				
				boolean ok = direccionBO.modificar(idcuenta, direccionInstalacionBean.getDireccion(), direccionInstalacionBean.getDireccionClon(), direccionCorrespondenciaBean.getDireccion(), direccionCorrespondenciaBean.getDireccionClon(), direccionConbranzaBean.getDireccion(), direccionConbranzaBean.getDireccionClon(), direccionInstalacionBean.getReferenciadir(), direccionInstalacionBean.getReferenciadirClon(), direccionCorrespondenciaBean.getReferenciadir(), direccionCorrespondenciaBean.getReferenciadirClon(), direccionConbranzaBean.getReferenciadir(), direccionConbranzaBean.getReferenciadirClon());
				
				if(ok){
					FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+idcuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					new MessageUtil().showInfoMessage("No existen cambios que guardar", "");
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}

	private boolean validacionDireccionOk(){
		boolean ok = false;
		
		//validaciones de direccion de instalacion
		if(direccionInstalacionBean.getCiudadSelected() != null && direccionInstalacionBean.getCiudadSelected().getIdciudad() > 0){
			if(direccionInstalacionBean.getDireccion().getSector() != null && direccionInstalacionBean.getDireccion().getSector().getIdsector() > 0){
				ok = true;
			}else{
				new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Instalacion", null);
			}
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar la ciudad en la seccion Direccion de Instalacion", null);
		}
		
		if(ok){
			if(direccionInstalacionBean.getDireccion().getTiposector() != null){
				if(direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionInstalacionBean.getDireccion().getCalleprincipal() != null && direccionInstalacionBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionInstalacionBean.getDireccion().getCallesecundaria() != null && direccionInstalacionBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Instalacion", null);
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Instalacion", null);
					}
				}else{
					if(direccionInstalacionBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionInstalacionBean.getDireccion().getUbicacion() != null && direccionInstalacionBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionInstalacionBean.getDireccion().getSolar() != null && direccionInstalacionBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Instalacion", null);
							}
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Instalacion", null);
						}
					}
				}
			}
		}
		
		if(ok){
			if(direccionInstalacionBean.getDireccion().getNodos() != null && direccionInstalacionBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionInstalacionBean.getDireccion().getNumero() > 0){
					if(direccionInstalacionBean.getDireccion().getVineta() != null && direccionInstalacionBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Instalacion", null);
				}
			}else{
				ok = false;
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
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Instalacion", null);
					}
				}else{
					ok = false;
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
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
				new MessageUtil().showWarnMessage("Debe seleccionar la ciudad de Instalación en la seccion Direccion de Correspondencia", null);
			}
		}
		
		if(ok){
			if(direccionCorrespondenciaBean.getDireccion().getTiposector() != null){
				if(direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionCorrespondenciaBean.getDireccion().getCalleprincipal() != null && direccionCorrespondenciaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionCorrespondenciaBean.getDireccion().getCallesecundaria() != null && direccionCorrespondenciaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Correspondencia", null);
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Correspondencia", null);
					}
				}else{
					if(direccionCorrespondenciaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionCorrespondenciaBean.getDireccion().getUbicacion() != null && direccionCorrespondenciaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionCorrespondenciaBean.getDireccion().getSolar() != null && direccionCorrespondenciaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Correspondencia", null);
							}
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Correspondencia", null);
						}
					}
				}
			}
		}
		
		if(ok){
			if(direccionCorrespondenciaBean.getDireccion().getNodos() != null && direccionCorrespondenciaBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionCorrespondenciaBean.getDireccion().getNumero() > 0){
					if(direccionCorrespondenciaBean.getDireccion().getVineta() != null && direccionCorrespondenciaBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Correspondencia", null);
				}
			}else{
				ok = false;
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
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Correspondencia", null);
					}
				}else{
					ok = false;
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
					new MessageUtil().showWarnMessage("Debe seleccionar el sector en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
				new MessageUtil().showWarnMessage("Debe seleccionar la ciudad en la seccion Direccion de Cobranza", null);
			}
		}
		
		if(ok){
			if(direccionConbranzaBean.getDireccion().getTiposector() != null){
				if(direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_ENTRECALLES){
					if(direccionConbranzaBean.getDireccion().getCalleprincipal() != null && direccionConbranzaBean.getDireccion().getCalleprincipal().getIdcalleprincipal() > 0){
						if(direccionConbranzaBean.getDireccion().getCallesecundaria() != null && direccionConbranzaBean.getDireccion().getCallesecundaria().getIdcallesecundaria() > 0){
							ok = true;
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar calle secundaria en la seccion Direccion de Cobranza", null);
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe seleccionar calle principal en la seccion Direccion de Cobranza", null);
					}
				}else{
					if(direccionConbranzaBean.getDireccion().getTiposector().getIdtiposector() == Parametro.TIPO_SECTOR_URBANIZACION){
						if(direccionConbranzaBean.getDireccion().getUbicacion() != null && direccionConbranzaBean.getDireccion().getUbicacion().getIdubicacion() > 0){
							if(direccionConbranzaBean.getDireccion().getSolar() != null && direccionConbranzaBean.getDireccion().getSolar().trim().length() > 0){
								ok = true;
							}else{
								ok = false;
								new MessageUtil().showWarnMessage("Debe seleccionar solar en la seccion Direccion de Cobranza", null);
							}
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe seleccionar ubicacion en la seccion Direccion de Cobranza", null);
						}
					}
				}
			}
		}
		
		if(ok){
			if(direccionConbranzaBean.getDireccion().getNodos() != null && direccionConbranzaBean.getDireccion().getNodos().getIdnodo() > 0){
				if(direccionConbranzaBean.getDireccion().getNumero() > 0){
					if(direccionConbranzaBean.getDireccion().getVineta() != null && direccionConbranzaBean.getDireccion().getVineta().trim().length() > 0){
						ok = true;
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe ingresar una viñeta en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe ingresar un numero en la seccion Direccion de Cobranza", null);
				}
			}else{
				ok = false;
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
						new MessageUtil().showWarnMessage("Debe ingresar un numero de departamento en la seccion Direccion de Cobranza", null);
					}
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe ingresar un numero de piso en la seccion Direccion de Cobranza", null);
				}
			}
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
			
			
			new MessageUtil().showInfoMessage("Listo!", null);
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
			
			
			new MessageUtil().showInfoMessage("Listo!", null);
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
		}
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

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public String getVacio() {
		return vacio;
	}

	public void setVacio(String vacio) {
		this.vacio = vacio;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}
}
