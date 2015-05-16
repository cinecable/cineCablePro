package net.cinecable.dm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Ordenes;
import pojo.annotations.Persona;

@ManagedBean(name = "asignacionOperacionesDM")
@SessionScoped
public class AsignacionOrdenesDM {

	private String estadoOrden, tipoOperacion;

	private List<Ordenes> ordenes;
	private List<Ordenes> ordenesTecnico;

	private List<Persona> tecnicos;
	private List<Persona> supervisores;

	private Persona tecnicoSeleccionado;
	private Persona supervisorSeleccionado;
	private Long codTecnico;
	private Long codSupervisor;

	public AsignacionOrdenesDM() {
		this.ordenes = new ArrayList<Ordenes>();
		this.ordenesTecnico = new ArrayList<Ordenes>();
	}

	public List<Ordenes> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Ordenes> ordenes) {
		this.ordenes = ordenes;
	}

	public List<Ordenes> getOrdenesTecnico() {
		return ordenesTecnico;
	}

	public void setOrdenesTecnico(List<Ordenes> ordenesTecnico) {
		this.ordenesTecnico = ordenesTecnico;
	}

	public String getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public List<Persona> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Persona> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public List<Persona> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<Persona> supervisores) {
		this.supervisores = supervisores;
	}

	public Persona getTecnicoSeleccionado() {
		return tecnicoSeleccionado;
	}

	public void setTecnicoSeleccionado(Persona tecnicoSeleccionado) {
		this.tecnicoSeleccionado = tecnicoSeleccionado;
	}

	public Persona getSupervisorSeleccionado() {
		return supervisorSeleccionado;
	}

	public void setSupervisorSeleccionado(Persona supervisorSeleccionado) {
		this.supervisorSeleccionado = supervisorSeleccionado;
	}

	public Long getCodTecnico() {
		return codTecnico;
	}

	public void setCodTecnico(Long codTecnico) {
		this.codTecnico = codTecnico;
		this.tecnicoSeleccionado=null;
		for (int i = 0; i < tecnicos.size(); i++) {
			if (tecnicos.get(i).getIdpersona() == codTecnico.intValue()) {
				tecnicoSeleccionado = tecnicos.get(i);
				break;
			}
		}
	}

	public Long getCodSupervisor() {
		return codSupervisor;
	}

	public void setCodSupervisor(Long codSupervisor) {
		this.codSupervisor = codSupervisor;
		this.supervisorSeleccionado=null;
		for (int i = 0; i < supervisores.size() && codSupervisor != null; i++) {
			if (supervisores.get(i).getIdpersona() == codSupervisor.intValue()) {
				supervisorSeleccionado = supervisores.get(i);
				break;
			}
		}

	}

}
