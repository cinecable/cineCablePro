package bean.controladores;

import global.Parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.ClienteBO;
import bo.negocio.ConyugeBO;
import bo.negocio.CtaclienteBO;
import bo.negocio.TipoclienteBO;

import pojo.annotations.Clientes;
import pojo.annotations.Conyuge;
import pojo.annotations.Ctacliente;
import pojo.annotations.Empresa;
import pojo.annotations.Estadocivil;
import pojo.annotations.Tipocliente;
import pojo.annotations.Tipoidentidad;
import pojo.annotations.Usuario;
import pojo.annotations.custom.Genero;
import pojo.annotations.custom.TipoEstadoCivil;
import pojo.annotations.custom.TipoIdDoc;
import pojo.annotations.custom.TipoPersona;
import util.FacesUtil;
import util.MessageUtil;


@ManagedBean
@ViewScoped
public class DbasCliBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -127134812362094359L;
	private List<TipoIdDoc> lisTipoIddoc;
	private TipoIdDoc tipoIdDocSelect;
	
	private List<TipoPersona> lisTipoPersona;
	private TipoPersona tipoPersonaSelect;
	
	private List<TipoEstadoCivil> lisTipoEstadoCivil;
	private List<Genero> lisGenero;
	private List<Tipocliente> lisTipocliente;
	
	private String nomEmpresa;
	private String titNombres;
	private String nombre1;
	private boolean habEmpresa;

	private Clientes clientes;
	private Clientes clientesClon;
	private Conyuge conyuge;
	private Conyuge conyugeClon;
	
	private int idcuenta;
	private boolean tieneConyuge;
	
	public DbasCliBean() {
	
	 tipoIdDocSelect = new TipoIdDoc(0, null);
	 tipoPersonaSelect=new TipoPersona(0, null);
	 titNombres="Datos Persona";
	 clientes = new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null, null, null, null, new Date(), null,new Estadocivil(), 0, 1, new Date(), null, new Tipoidentidad());
	 clientesClon = new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null, null, null, null, new Date(), null, new Estadocivil(), 0,  1, new Date(), null,new Tipoidentidad());
	 conyuge = new Conyuge(0, new Clientes(), null, null, null, null, null);
	 conyugeClon = new Conyuge(0, new Clientes(), null, null, null, null, null);
	 
	 
	 lisTipocliente = new ArrayList<Tipocliente>();
	 
     habEmpresa=false;		
     tieneConyuge = false;
	 CargaTDoc();
	 ListaTipoCliente();
		
	
	}
	
	private void ListaTipoCliente(){
		try{
			Tipocliente tipocliente = new Tipocliente();
			tipocliente.setIdtipocliente(0);
			tipocliente.setNombre("Seleccione");
			
			lisTipocliente.add(tipocliente);
			
			TipoclienteBO tipoclienteBO = new TipoclienteBO();
			List<Tipocliente> lisTmp = tipoclienteBO.lisTipocliente();
			
			if(lisTmp != null && lisTmp.size() > 0){
				lisTipocliente.addAll(lisTmp);
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
		}
	}
	
	@PostConstruct
	public void initDbasCliBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarDatosBasicos();
		}
	}

	public void consultarDatosBasicos(){
		if(this.idcuenta > 0){
			try {
				CtaclienteBO ctaclienteBO = new CtaclienteBO();
				Ctacliente ctacliente = ctaclienteBO.getCtaclienteById(idcuenta);
				
				if(ctacliente != null && ctacliente.getClientes() != null && ctacliente.getClientes().getIdcliente() != null && ctacliente.getClientes().getIdcliente().trim().length() > 0){
					clientes = ctacliente.getClientes();
					clientesClon = clientes.clonar();
					
					validarEstadoCivil();
					
					//consultar conyuge
					ConyugeBO conyugeBO = new ConyugeBO();
					conyuge = conyugeBO.getConyugeByIdcliente(clientes.getIdcliente());
					
					if(conyuge != null){
						if(conyuge.getClientes() == null){
							conyuge.setClientes(new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null, null, null, null, new Date(), null, new Estadocivil(), 0, 1, new Date(), null,new Tipoidentidad()));
						}
						
						conyugeClon = conyuge.clonar();
					}else{
						conyuge = new Conyuge(0, new Clientes(),"", "", "", "", "");
						conyugeClon = new Conyuge(0, new Clientes(),"", "", "", "", "");
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Error!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	private void CargaTDoc() {
		lisTipoIddoc=new ArrayList<TipoIdDoc>();
		lisTipoIddoc.add(new TipoIdDoc(0, "Sel.Tipo Identidad"));
		lisTipoIddoc.add(new TipoIdDoc(1, "Cedula"));
		lisTipoIddoc.add(new TipoIdDoc(2, "RUC"));
		lisTipoIddoc.add(new TipoIdDoc(3, "Pasaporte"));
		lisTipoIddoc.add(new TipoIdDoc(4, "Otro Documento"));
		
		lisTipoPersona=new ArrayList<TipoPersona>();
		lisTipoPersona.add(new TipoPersona(0, "Sel.Tipo Persona"));
		lisTipoPersona.add(new TipoPersona(1, "Natural"));
		lisTipoPersona.add(new TipoPersona(2, "Juridica"));
		
		lisTipoEstadoCivil = new ArrayList<TipoEstadoCivil>();
		lisTipoEstadoCivil.add(new TipoEstadoCivil(0,"Sel.Estado Civil"));
		lisTipoEstadoCivil.add(new TipoEstadoCivil(1,"Soltero"));
		lisTipoEstadoCivil.add(new TipoEstadoCivil(2,"Casado"));
		lisTipoEstadoCivil.add(new TipoEstadoCivil(3,"Divorciado"));
		lisTipoEstadoCivil.add(new TipoEstadoCivil(4,"Union Libre"));
		lisTipoEstadoCivil.add(new TipoEstadoCivil(5,"Otro Estado"));
		
		lisGenero = new ArrayList<Genero>();
		lisGenero.add(new Genero(0,"Sel.Genero"));
		lisGenero.add(new Genero(1,"Masculino"));
		lisGenero.add(new Genero(2,"Femenino"));
		lisGenero.add(new Genero(3,"Otro Genero"));
	}
	
	public void CmbTitEmpresa() {
		//int tipoPersona = tipoPersonaSelect.getIdTipoPersona();
		int tipoPersona =clientes.getIdtipopersona();
		
		if (tipoPersona == 2) {
			titNombres="Datos Representante Legal";
			habEmpresa=true;
		} else {
			titNombres="Datos Persona";
			habEmpresa=false;
		}
		
		creaNombreEmpresa();
	}
	
	private void creaNombreEmpresa(){
		if(getClientes().getTipoidentidad().getIdtidentidad() == Parametro.TIPO_IDENTIFICACION_RUC && getClientes().getIdtipopersona() == Parametro.TIPO_PERSONA_NATURAL){
			String NombreCliente = getClientes().getNombre1() + " " + getClientes().getNombre2() + " " + getClientes().getApellido1() + " " + getClientes().getApellido2();
			getClientes().setEmpresa_1(NombreCliente);
		}
	}
	
	public void eventoIdentidadPersona(){
		creaNombreEmpresa();
		getClientes().setIdcliente(null);
	}
	
	public void validarEstadoCivil(){
		if(clientes.getEstadocivil().getIdestadocivil() == Parametro.ESTADO_CIVIL_CASADO || clientes.getEstadocivil().getIdestadocivil() == Parametro.ESTADO_CIVIL_UNION_LIBRE){
			tieneConyuge = true;
		}else{
			tieneConyuge = false;
		}
	}

	public boolean cedulaExiste(){
		boolean ok = false;
		
		ClienteBO clienteBO = new ClienteBO();
		
		try {
			Clientes clientes = clienteBO.getClientesById(getClientes().getIdcliente());
			
			if(clientes != null && clientes.getIdcliente() != null && clientes.getIdcliente().trim().length() > 0){
				ok = true;
				new MessageUtil().showWarnMessage("Numero identificacion ya existe. "+clientes.getNombre1()+ " " + clientes.getApellido1(),"");
			}
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ha ocurrido un error. Comunicar al Webmaster.","");
		}
		
		return ok;
	}
	
	public boolean validacionIdentificacion(){
		boolean ok = false;
		
		try {
			if(clientes != null && clientes.getTipoidentidad() != null && clientes.getTipoidentidad().getIdtidentidad() > 0 &&
					clientes.getIdcliente() != null && clientes.getIdcliente().trim().length() > 0){
				if(clientes.getTipoidentidad().getIdtidentidad() == Parametro.TIPO_IDENTIFICACION_CEDULA &&
						clientes.getIdcliente().trim().length() != 10){
					new MessageUtil().showWarnMessage("Longitud de Cedula incorrecto en Seccion de Datos Basicos","");
				}else{
					if(clientes.getTipoidentidad().getIdtidentidad() == Parametro.TIPO_IDENTIFICACION_RUC &&
							clientes.getIdcliente().trim().length() != 13){
						new MessageUtil().showWarnMessage("Longitud de RUC incorrecto en Seccion de Datos Basicos","");
					}else{
						ok = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			new MessageUtil().showErrorMessage("Ha ocurrido un error. Comunicar al Webmaster.","");
		}
		
		return ok;
	}
	
	public List<TipoIdDoc> getLisTipoIddoc() {
		return lisTipoIddoc;
	}

	public void setLisTipoIddoc(List<TipoIdDoc> lisTipoIddoc) {
		this.lisTipoIddoc = lisTipoIddoc;
	}

	public TipoIdDoc getTipoIdDocSelect() {
		return tipoIdDocSelect;
	}

	public void setTipoIdDocSelect(TipoIdDoc tipoIdDocSelect) {
		this.tipoIdDocSelect = tipoIdDocSelect;
	}

	public List<TipoPersona> getLisTipoPersona() {
		return lisTipoPersona;
	}

	public void setLisTipoPersona(List<TipoPersona> lisTipoPersona) {
		this.lisTipoPersona = lisTipoPersona;
	}

	public TipoPersona getTipoPersonaSelect() {
		return tipoPersonaSelect;
	}

	public void setTipoPersonaSelect(TipoPersona tipoPersonaSelect) {
		this.tipoPersonaSelect = tipoPersonaSelect;
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getTitNombres() {
		return titNombres;
	}

	public void setTitNombres(String titNombres) {
		this.titNombres = titNombres;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Clientes getClientesClon() {
		return clientesClon;
	}

	public void setClientesClon(Clientes clientesClon) {
		this.clientesClon = clientesClon;
	}

	public Conyuge getConyugeClon() {
		return conyugeClon;
	}

	public void setConyugeClon(Conyuge conyugeClon) {
		this.conyugeClon = conyugeClon;
	}

	public boolean isHabEmpresa() {
		return habEmpresa;
	}

	public void setHabEmpresa(boolean habEmpresa) {
		this.habEmpresa = habEmpresa;
	}

	public List<TipoEstadoCivil> getLisTipoEstadoCivil() {
		return lisTipoEstadoCivil;
	}

	public void setLisTipoEstadoCivil(List<TipoEstadoCivil> lisTipoEstadoCivil) {
		this.lisTipoEstadoCivil = lisTipoEstadoCivil;
	}

	public List<Genero> getLisGenero() {
		return lisGenero;
	}

	public void setLisGenero(List<Genero> lisGenero) {
		this.lisGenero = lisGenero;
	}

	public Conyuge getConyuge() {
		return conyuge;
	}

	public void setConyuge(Conyuge conyuge) {
		this.conyuge = conyuge;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public boolean isTieneConyuge() {
		return tieneConyuge;
	}

	public void setTieneConyuge(boolean tieneConyuge) {
		this.tieneConyuge = tieneConyuge;
	}

	public List<Tipocliente> getLisTipocliente() {
		return lisTipocliente;
	}

	public void setLisTipocliente(List<Tipocliente> lisTipocliente) {
		this.lisTipocliente = lisTipocliente;
	}
	
	
}
