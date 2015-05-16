package net.cinecable.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Tidentidad;

import pojo.annotations.Area;
import pojo.annotations.Claves;
import pojo.annotations.Empresa;
import pojo.annotations.Persona;
import pojo.annotations.Usuario;
import pojo.annotations.custom.Genero;

@ManagedBean(name="personaDM")
@SessionScoped
public class PersonaDM implements Serializable{
	private List<Area> listaAreas= new ArrayList<Area>();
	private List<Genero> lisGenero= new ArrayList<Genero>();
	//private List<Cargos> listaCargos;
	private Persona persona= new Persona();
	private Usuario usuario= new Usuario();
	private Empresa empresaSeleccionada= new Empresa();
	private Claves claves= new Claves();
	private List<Empresa> listaEmpresa= new ArrayList<Empresa>();
	private List<Persona> listaPersonas=new ArrayList<Persona>();
	private Persona personaSeleccionada= new Persona();
	private Area areaSeleccionada= new Area();
	private List<Tidentidad> tipoIdentidad= new ArrayList<Tidentidad>();
	private Tidentidad tidentidadSeleccionada= new Tidentidad();
	
	
	public Tidentidad getTidentidadSeleccionada() {
		return tidentidadSeleccionada;
	}
	public void setTidentidadSeleccionada(Tidentidad tidentidadSeleccionada) {
		this.tidentidadSeleccionada = tidentidadSeleccionada;
	}
	public List<Tidentidad> getTipoIdentidad() {
		return tipoIdentidad;
	}
	public void setTipoIdentidad(List<Tidentidad> tipoIdentidad) {
		this.tipoIdentidad = tipoIdentidad;
	}
	public Area getAreaSeleccionada() {
		return areaSeleccionada;
	}
	public void setAreaSeleccionada(Area areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}
	public Persona getPersonaSeleccionada() {
		return personaSeleccionada;
	}
	public void setPersonaSeleccionada(Persona personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}
	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}
	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	public Claves getClaves() {
		return claves;
	}
	public void setClaves(Claves claves) {
		this.claves = claves;
	}
	public PersonaDM() {
		
	}
	

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Area> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<Area> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public List<Genero> getLisGenero() {
		return lisGenero;
	}

	public void setLisGenero(List<Genero> lisGenero) {
		this.lisGenero = lisGenero;
	}
	
	
}
