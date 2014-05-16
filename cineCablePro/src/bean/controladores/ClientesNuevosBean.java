package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import exceptions.VerificarIdException;
import global.Parametro;

import bo.negocio.CtaclienteBO;
import bo.negocio.PersonaBO;

import pojo.annotations.Ctacliente;
import pojo.annotations.Persona;

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
	
	
	public ClientesNuevosBean() {
		ctacliente = new Ctacliente();
		cobrador = new Persona();
		vendedor = new Persona();
		direccionInstalacionBean = new DireccionBean();
		direccionCorrespondenciaBean = new DireccionBean();
		direccionConbranzaBean = new DireccionBean();
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

	public void grabarNuevoCliente(){
		if(validacionOk()){
			if(nombreCuentaExiste()){
				if(validacionProductoOk()){
					if(validacionDireccionOk()){
						if(validacionDebitoBcoOk()){
							if(validacionTelefonoOk()){
								try{
									CtaclienteBO ctaclienteBO = new CtaclienteBO();
									
									//Asignamos data a ctacliente
									ctacliente.setClientes(dbasCliBean.getClientes());
									ctacliente.setIdcobrador(cobrador.getIdpersona());
									ctacliente.setIdvendedor(vendedor.getIdpersona());
									
									ctaclienteBO.grabarCliente(ctacliente, dbasCliBean.getConyuge(), productosBean.getLisProductosId(), direccionInstalacionBean.getDireccion(), direccionCorrespondenciaBean.getDireccion(), direccionConbranzaBean.getDireccion(), debitosBancariosBean.getDebitobco(), telefonosBean.getLisTelefonos());
									
									new MessageUtil().showInfoMessage("Listo!", "Grabado con exito");
								} catch(Exception re) {
									re.printStackTrace();
									new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", null);
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
							if(dbasCliBean.getConyuge().getApellido1() == null && dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
								ok = false;
								new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del Cónyugue en seccion Datos Basicos", null);
							}else{
								if(dbasCliBean.getConyuge().getIdentificacion() == null && dbasCliBean.getConyuge().getIdentificacion().trim().length() == 0){
									ok = false;
									new MessageUtil().showWarnMessage("Ingresar # Identidad del Cónyugue en seccion Datos Basicos", null);
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
			
			if(ok){
				if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() > 0){
					if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() == Parametro.TIPO_IDENTIFICACION_OTRO || verificarId.verificarId(debitosBancariosBean.getDebitobco().getIdentificacion())){
						ok = true;
					}else{
						new MessageUtil().showWarnMessage("Ingresar # Identidad en seccion Debito Bancario", null);
					}
				}else{
					new MessageUtil().showWarnMessage("Seleccionar Tipo Identidad en seccion Debito Bancario", null);
				}
			}
		
		}catch(VerificarIdException e){
			e.printStackTrace();
			new MessageUtil().showWarnMessage("Aviso!", e.getMessage());
		}
		
		return ok;
	}
	
	private boolean validacionProductoOk(){
		boolean ok = false;
		
		if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar al menos un producto en seccion Productos Cliente", null);
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
	
	public boolean validacionDebitoBcoOk(){
		boolean ok = false;
		
		if(debitosBancariosBean.getDebitobco().getIdtipodebito() > 0){
			ok = true;
		}else{
			new MessageUtil().showWarnMessage("Debe seleccionar una forma de cobro en seccion Debito Bancario", null);
		}
		
		if(ok){
			if(debitosBancariosBean.getDebitobco().getIdtipodebito() == Parametro.TIPO_DEBITO_BANCARIO || debitosBancariosBean.getDebitobco().getIdtipodebito() == Parametro.TIPO_DEBITO_TARJETA){
				if(debitosBancariosBean.getDebitobco().getBancos().getIdbanco() > 0){
					if(debitosBancariosBean.getDebitobco().getPropietario() != null && debitosBancariosBean.getDebitobco().getPropietario().trim().length() > 0){
						if(debitosBancariosBean.getDebitobco().getNrodebito() != null && debitosBancariosBean.getDebitobco().getNrodebito().trim().length() > 0){
							ok = true;
						}else{
							ok = false;
							new MessageUtil().showWarnMessage("Debe ingresar Nro Doc en seccion Debito Bancario", null);
						}
					}else{
						ok = false;
						new MessageUtil().showWarnMessage("Debe ingresar propietario en seccion Debito Bancario", null);
					}
				}else{
					ok = false;
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
					new MessageUtil().showWarnMessage("Debe seleccionar tipo de cuenta en seccion Debito Bancario", null);
				}
			}
		}
		
		if(ok){
			if(debitosBancariosBean.getDebitobco().getIdtipoidentificacion() > 0){
				if(debitosBancariosBean.getDebitobco().getIdentificacion() != null && debitosBancariosBean.getDebitobco().getIdentificacion().trim().length() > 0){
					ok = true;
				}else{
					ok = false;
					new MessageUtil().showWarnMessage("Debe ingresar # identidad en seccion Debito Bancario", null);
				}
			}else{
				ok = false;
				new MessageUtil().showWarnMessage("Debe seleccionar tipo identidad en seccion Debito Bancario", null);
			}
		}
		
		return ok;
	}
	
	private boolean validacionTelefonoOk(){
		boolean ok = false;
		
		if(telefonosBean.getLisTelefonos() != null && telefonosBean.getLisTelefonos().size() > 0){
			ok = true;
		}else{
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
	
}
