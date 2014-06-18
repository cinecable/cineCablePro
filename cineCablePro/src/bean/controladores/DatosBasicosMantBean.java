package bean.controladores;

import exceptions.VerificarIdException;
import global.Parametro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;

import util.FacesUtil;
import util.MessageUtil;
import util.VerificarId;
import bo.negocio.ClienteBO;
import bo.negocio.CtaclienteBO;

@ManagedBean
@ViewScoped
public class DatosBasicosMantBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2683944060802251270L;

	@ManagedProperty(value="#{dbasCliBean}")
	private DbasCliBean dbasCliBean;
	
	private int idcuenta;
	private Ctacliente ctacliente;
	
	private boolean modificable;
	
	public DatosBasicosMantBean() {
		ctacliente = new Ctacliente(0, new Empresa(), new Clientes());
	}
	
	@PostConstruct
	public void initDatosBasicosMantBean() {
		try{
			FacesUtil facesUtil = new FacesUtil();
			idcuenta = Integer
					.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
							.getParametroUrl("idcuenta").toString() : "0");
	
			if (idcuenta > 0) {
				//Al recibir por parametro el idcuenta, consultamos
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				dbasCliBean.consultarDatosBasicos();
				
				if(dbasCliBean.getClientes() != null && dbasCliBean.getClientes().getIdcliente() != null && dbasCliBean.getClientes().getIdcliente().trim().length() > 0){
					modificable = true;
				}else{
					modificable = false;
					new MessageUtil().showWarnMessage("Cliente no posee Datos Basicos ingresados!", "");
				}
			}
		}
		catch(Exception re){
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
		}
	}
	
	public void grabar(){
		if(validacionOk()){
			try{
				ClienteBO clienteBO = new ClienteBO();
				
				boolean ok = false;
				
				if(dbasCliBean.getIdcuenta() > 0){
					if(dbasCliBean.getClientes().getEstadocivil() != Parametro.ESTADO_CIVIL_CASADO && dbasCliBean.getClientes().getEstadocivil() != Parametro.ESTADO_CIVIL_UNION_LIBRE){
						dbasCliBean.getConyuge().setNombre1(null);
						dbasCliBean.getConyuge().setNombre2(null);
						dbasCliBean.getConyuge().setApellido1(null);
						dbasCliBean.getConyuge().setApellido2(null);
						dbasCliBean.getConyuge().setIdentificacion(null);
					}
					ok = clienteBO.modificar(dbasCliBean.getClientes(), dbasCliBean.getClientesClon(), dbasCliBean.getConyuge(), dbasCliBean.getConyugeClon());
				}else{
					
				}
				
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
	
	private boolean validacionOk(){
		boolean ok = false;
		
		try{
		
			VerificarId verificarId  = new VerificarId();
			
			if(dbasCliBean.getClientes().getIdtipoidentificacion() > 0){
				if(dbasCliBean.getClientes().getIdtipoidentificacion() == Parametro.TIPO_IDENTIFICACION_OTRO || verificarId.verificarId(dbasCliBean.getClientes().getIdcliente())){
					if(dbasCliBean.getClientes().getIdtipopersona() > 0){
						if(dbasCliBean.getClientes().getNombre1() != null && dbasCliBean.getClientes().getNombre1().trim().length() > 0){
							if(dbasCliBean.getClientes().getApellido1() != null && dbasCliBean.getClientes().getApellido1().trim().length() > 0){
								if(dbasCliBean.getClientes().getEmail() != null && dbasCliBean.getClientes().getEmail().trim().length() > 0){
									if(dbasCliBean.getClientes().getEstadocivil()  > 0){
										if(dbasCliBean.getClientes().getGenero()  > 0){
											ok = true;
										}else{
											new MessageUtil().showWarnMessage("Seleccionar Género en seccion Datos Basicos", null);
										}
									}else{
										new MessageUtil().showWarnMessage("Seleccionar Estado Civil en seccion Datos Basicos", null);
									}
								}else{
									new MessageUtil().showWarnMessage("Ingresar Correo Electrónico en seccion Datos Basicos", null);
								}
							}else{
								new MessageUtil().showWarnMessage("Ingresar Primer Apellido en seccion Datos Basicos", null);
							}
						}else{
							new MessageUtil().showWarnMessage("Ingresar Primer Nombre en seccion Datos Basicos", null);
						}
					}else{
						new MessageUtil().showWarnMessage("Seleccionar Tipo Persona en seccion Datos Basicos", null);
					}
				}else{
					new MessageUtil().showWarnMessage("Ingresar # Identidad en seccion Datos Basicos", null);
				}
			}else{
				new MessageUtil().showWarnMessage("Seleccionar Tipo Identidad en seccion Datos Basicos", null);
			}
			
			if(ok){
				if(dbasCliBean.getClientes().getEstadocivil() == Parametro.ESTADO_CIVIL_CASADO || dbasCliBean.getClientes().getEstadocivil() == Parametro.ESTADO_CIVIL_UNION_LIBRE){
					if(dbasCliBean.getConyuge().getNombre1() == null || dbasCliBean.getConyuge().getNombre1().trim().length() == 0){
						ok = false;
						new MessageUtil().showWarnMessage("Ingresar el Primer Nombre del Cónyugue en seccion Datos Basicos", null);
					}else{
						if(dbasCliBean.getConyuge().getApellido1() == null || dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
							ok = false;
							new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del Cónyugue en seccion Datos Basicos", null);
						}else{
							if(dbasCliBean.getConyuge().getIdentificacion() == null || dbasCliBean.getConyuge().getIdentificacion().trim().length() == 0){
								ok = false;
								new MessageUtil().showWarnMessage("Ingresar # Identidad del Cónyugue en seccion Datos Basicos", null);
							}else{
								 if(!verificarId.verificarId(dbasCliBean.getConyuge().getIdentificacion())){
									 ok = false;
								 }
							}
						}
					}	
				}
			}
			
			if(ok){
				if(dbasCliBean.getClientes().getIdtipoidentificacion() == Parametro.TIPO_IDENTIFICACION_RUC &&  dbasCliBean.getClientes().getIdtipopersona() == Parametro.TIPO_PERSONA_JURIDICO){
					if(dbasCliBean.getClientes().getEmpresa_1() == null || dbasCliBean.getClientes().getEmpresa_1().trim().length() == 0){
						ok = false;
						new MessageUtil().showWarnMessage("Ingresar Nombre Empresa en seccion Datos Basicos", null);
					}
				}
			}
		}catch(VerificarIdException e){
			ok = false;
			e.printStackTrace();
			new MessageUtil().showWarnMessage(e.getMessage(), e.getMessage());
		}
		
		return ok;
	}
	
	public DbasCliBean getDbasCliBean() {
		return dbasCliBean;
	}

	public void setDbasCliBean(DbasCliBean dbasCliBean) {
		this.dbasCliBean = dbasCliBean;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public Ctacliente getCtacliente() {
		return ctacliente;
	}

	public void setCtacliente(Ctacliente ctacliente) {
		this.ctacliente = ctacliente;
	}

	public boolean isModificable() {
		return modificable;
	}

	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}

}
