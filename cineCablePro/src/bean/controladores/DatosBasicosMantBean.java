package bean.controladores;

import exceptions.VerificarIdException;
import global.Parametro;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Clientes;

import util.MessageUtil;
import util.VerificarId;
import bo.negocio.ClienteBO;

@ManagedBean
@ViewScoped
public class DatosBasicosMantBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2683944060802251270L;

	@ManagedProperty(value="#{dbasCliBean}")
	private DbasCliBean dbasCliBean;
	
	private Clientes clientes;

	public DatosBasicosMantBean() {
		clientes = new Clientes();
	}
	
	public void grabarNuevoCliente(){
		if(validacionOk()){
			try{
				ClienteBO clienteBO = new ClienteBO();
				
				boolean ok = false;
				
				if(dbasCliBean.getIdcuenta() > 0){
					ok = clienteBO.modificar(dbasCliBean.getClientes(), dbasCliBean.getClientesClon(), dbasCliBean.getConyuge(), dbasCliBean.getConyugeClon());
				}else{
					
				}
				
				if(ok){
					new MessageUtil().showInfoMessage("Grabado con exito", null);
				}else{
					new MessageUtil().showInfoMessage("No existen cambios que guardar", null);
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
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
											new MessageUtil().showWarnMessage("Seleccionar G�nero en seccion Datos Basicos", null);
										}
									}else{
										new MessageUtil().showWarnMessage("Seleccionar Estado Civil en seccion Datos Basicos", null);
									}
								}else{
									new MessageUtil().showWarnMessage("Ingresar Correo Electr�nico en seccion Datos Basicos", null);
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
						new MessageUtil().showWarnMessage("Ingresar el Primer Nombre del C�nyugue en seccion Datos Basicos", null);
					}else{
						if(dbasCliBean.getConyuge().getApellido1() == null || dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
							ok = false;
							new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del C�nyugue en seccion Datos Basicos", null);
						}else{
							if(dbasCliBean.getConyuge().getIdentificacion() == null || dbasCliBean.getConyuge().getIdentificacion().trim().length() == 0){
								ok = false;
								new MessageUtil().showWarnMessage("Ingresar # Identidad del C�nyugue en seccion Datos Basicos", null);
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

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}
	
}