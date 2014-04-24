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
	
	@ManagedProperty(value="#{direccionBean}") 
	private DireccionBean direccionBean;
	
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

	public DireccionBean getDireccionBean() {
		return direccionBean;
	}

	public void setDireccionBean(DireccionBean direccionBean) {
		this.direccionBean = direccionBean;
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
			try{
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				
				//Asignamos data a ctacliente
				//Ctacliente ctacliente = new Ctacliente();
				ctacliente.setClientes(dbasCliBean.getClientes());
				//ctacliente.setNombre(this.nombreCuenta);
				
				ctaclienteBO.grabarCliente(ctacliente, dbasCliBean.getClientes(), productosBean.getLisProductosId());
				
				new MessageUtil().showInfoMessage("Listo!", "Grabado con exito");
				/*if(productosBean.getLisProductosId() != null && productosBean.getLisProductosId().size() > 0){
					String productos = "";
					for(ProductoId productoId : productosBean.getLisProductosId()){
						productos += productoId.getNombreProd() + "-";
					}
					new MessageUtil().showInfoMessage("Listo!", "Productos leidos de ProductosBean: -" + productos);
				}else{
					new MessageUtil().showWarnMessage("No!", "Agregue unos productos para ejemplo");
				}*/
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
											new MessageUtil().showWarnMessage("Seleccionar G�nero", null);
										}
									}else{
										new MessageUtil().showWarnMessage("Seleccionar Estado Civil", null);
									}
								}else{
									new MessageUtil().showWarnMessage("Ingresar Correo Electr�nico", null);
								}
							}else{
								new MessageUtil().showWarnMessage("Ingresar Primer Apellido", null);
							}
						}else{
							new MessageUtil().showWarnMessage("Ingresar Primer Nombre", null);
						}
					}else{
						new MessageUtil().showWarnMessage("Seleccionar Tipo Persona", null);
					}
				}else{
					new MessageUtil().showWarnMessage("Ingresar # Identidad", null);
				}
			}else{
				new MessageUtil().showWarnMessage("Seleccionar Tipo Identidad", null);
			}
			
			if(ok){
				if(dbasCliBean.getClientes().getEstadocivil() == Parametro.ESTADO_CIVIL_CASADO || dbasCliBean.getClientes().getEstadocivil() == Parametro.ESTADO_CIVIL_UNION_LIBRE){
					if(dbasCliBean.getConyuge().getNombre1() == null || dbasCliBean.getConyuge().getNombre1().trim().length() == 0){
						ok = false;
						new MessageUtil().showWarnMessage("Ingresar el Primer Nombre del C�nyugue", null);
					}else{
						if(dbasCliBean.getConyuge().getApellido1() == null || dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
							ok = false;
							new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del C�nyugue", null);
						}else{
							if(dbasCliBean.getConyuge().getApellido1() == null && dbasCliBean.getConyuge().getApellido1().trim().length() == 0){
								ok = false;
								new MessageUtil().showWarnMessage("Ingresar el Primer Apellido del C�nyugue", null);
							}else{
								if(dbasCliBean.getConyuge().getIdentificacion() == null && dbasCliBean.getConyuge().getIdentificacion().trim().length() == 0){
									ok = false;
									new MessageUtil().showWarnMessage("Ingresar # Identidad del C�nyugue", null);
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
						new MessageUtil().showWarnMessage("Ingresar Nombre Empresa", null);
					}
				}
			}
		
		}catch(VerificarIdException e){
			e.printStackTrace();
			new MessageUtil().showWarnMessage("Aviso!", e.getMessage());
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
	
	public List<Persona> buscarPersona(String query, int idarea) {
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
	
}
