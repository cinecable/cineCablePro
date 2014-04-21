package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Clientes;
import pojo.annotations.Empresa;
import pojo.annotations.Tipocliente;
import pojo.annotations.Usuario;
import pojo.annotations.custom.Genero;
import pojo.annotations.custom.TipoEstadoCivil;
import pojo.annotations.custom.TipoIdDoc;
import pojo.annotations.custom.TipoPersona;


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
	
	private String nomEmpresa;
	private String titNombres;
	private String nombre1;
	private boolean habEmpresa;
	
	

	private Clientes clientes;
	
	public DbasCliBean() {
	
	 tipoIdDocSelect = new TipoIdDoc(0, null);
	 tipoPersonaSelect=new TipoPersona(0, null);
	 titNombres="Datos Persona";
	 //clientes = new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null);
	 clientes = new Clientes(null, new Tipocliente(), new Usuario(), new Empresa(), null, null, null, null, null, null, 0, new Date(), null, 1, 0, 0, 1, 1);
     habEmpresa=false;		
	 CargaTDoc();
	 
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
	
	
}
