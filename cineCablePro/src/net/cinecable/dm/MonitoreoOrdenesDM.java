package net.cinecable.dm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.MonitoreoOrden;
import net.cinecable.model.base.MonitoreoTraza;
import net.cinecable.model.base.Ordenes;
import pojo.annotations.Comandos;
import pojo.annotations.Persona;

@ManagedBean(name = "monitoreoOrdenDM")
@SessionScoped
public class MonitoreoOrdenesDM {

	private List<MonitoreoTraza> monitoreoTraza = new ArrayList<MonitoreoTraza>();

	private MonitoreoOrden monitoreo;

	private Ordenes ordenSeleccionada;

	private List<Ordenes> ordenesTecnico = new ArrayList<Ordenes>();

	private List<Persona> Tecnicos = new ArrayList<Persona>();

	private Persona TecnicoSeleecionado;

	private Long codTecSelecc;

	private Long codOrdenSelecc;

	private Date fechaInicio, FechaFin;

	private String observacionMonitoreo;

	private List<Materiales> materialCliente = new ArrayList<Materiales>();

	private List<Materiales> materialTecnico = new ArrayList<Materiales>();

	private Long codMonitoreoEquipoSeleccionado;

	private Materiales materialMonitoreoSeleccionado;

	private Long codMonitoreoEquipoSeleccionadoCliente;

	private Materiales materialMonitoreoSeleccionadoCliente;

	private List<Comandos> comandosEquipo;

	private Comandos comandoSeleccionado;

	private Long codComando;

	private String respuestaControlador;

	public String getRespuestaControlador() {
		return respuestaControlador;
	}

	public void setRespuestaControlador(String respuestaControlador) {
		this.respuestaControlador = respuestaControlador;
	}

	public List<Comandos> getComandosEquipo() {
		return comandosEquipo;
	}

	public void setComandosEquipo(List<Comandos> comandosEquipo) {
		this.comandosEquipo = comandosEquipo;
	}

	public Comandos getComandoSeleccionado() {
		return comandoSeleccionado;
	}

	public void setComandoSeleccionado(Comandos comandoSeleccionado) {
		this.comandoSeleccionado = comandoSeleccionado;
	}

	public Long getCodComando() {
		return codComando;
	}

	public void setCodComando(Long codComando) {
		this.codComando = codComando;
		this.comandoSeleccionado = null;
		if (codComando == null || comandosEquipo == null)
			return;
		for (Comandos comandox : comandosEquipo) {
			if (comandox.getIdcomando().equals(codComando)) {
				this.comandoSeleccionado = comandox;
				break;
			}
		}
	}

	public Long getCodMonitoreoEquipoSeleccionadoCliente() {
		return codMonitoreoEquipoSeleccionadoCliente;
	}

	public void setCodMonitoreoEquipoSeleccionadoCliente(Long codMonitoreoEquipoSeleccionadoCliente) {
		this.codMonitoreoEquipoSeleccionadoCliente = codMonitoreoEquipoSeleccionadoCliente;
		this.materialMonitoreoSeleccionadoCliente = null;
		if (codMonitoreoEquipoSeleccionadoCliente == null)
			return;
		for (Materiales mat : materialCliente) {
			if (mat.getIdUnidad().equals(codMonitoreoEquipoSeleccionadoCliente)) {
				this.materialMonitoreoSeleccionadoCliente = mat;
				break;
			}
		}
	}

	public Materiales getMaterialMonitoreoSeleccionadoCliente() {
		return materialMonitoreoSeleccionadoCliente;
	}

	public void setMaterialMonitoreoSeleccionadoCliente(Materiales materialMonitoreoSeleccionadoCliente) {
		this.materialMonitoreoSeleccionadoCliente = materialMonitoreoSeleccionadoCliente;
	}

	public Long getCodMonitoreoEquipoSeleccionado() {
		return codMonitoreoEquipoSeleccionado;
	}

	public void setCodMonitoreoEquipoSeleccionado(Long codMonitoreoEquipoSeleccionado) {
		this.codMonitoreoEquipoSeleccionado = codMonitoreoEquipoSeleccionado;
		this.materialMonitoreoSeleccionado = null;
		if (codMonitoreoEquipoSeleccionado == null)
			return;
		for (Materiales mat : materialTecnico) {
			if (mat.getIdUnidad().equals(codMonitoreoEquipoSeleccionado)) {
				this.materialMonitoreoSeleccionado = mat;
				break;
			}
		}
	}

	public Materiales getMaterialMonitoreoSeleccionado() {
		return materialMonitoreoSeleccionado;
	}

	public void setMaterialMonitoreoSeleccionado(Materiales materialMonitoreoSeleccionado) {
		this.materialMonitoreoSeleccionado = materialMonitoreoSeleccionado;
	}

	public MonitoreoOrden getMonitoreo() {
		return monitoreo;
	}

	public void setMonitoreo(MonitoreoOrden monitoreo) {
		this.monitoreo = monitoreo;
	}

	public Ordenes getOrdenSeleccionada() {
		return ordenSeleccionada;
	}

	public void setOrdenSeleccionada(Ordenes ordenSeleccionada) {
		this.ordenSeleccionada = ordenSeleccionada;
	}

	public List<Ordenes> getOrdenesTecnico() {
		return ordenesTecnico;
	}

	public void setOrdenesTecnico(List<Ordenes> ordenesTecnico) {
		this.ordenesTecnico = ordenesTecnico;
	}

	public List<Persona> getTecnicos() {
		return Tecnicos;
	}

	public void setTecnicos(List<Persona> tecnicos) {
		Tecnicos = tecnicos;
	}

	public Persona getTecnicoSeleecionado() {
		return TecnicoSeleecionado;
	}

	public void setTecnicoSeleecionado(Persona tecnicoSeleecionado) {
		TecnicoSeleecionado = tecnicoSeleecionado;
	}

	public Long getCodTecSelecc() {
		return codTecSelecc;
	}

	public void setCodTecSelecc(Long codTecSelecc) {
		this.codTecSelecc = codTecSelecc;
		if (this.codTecSelecc == null)
			return;
		this.TecnicoSeleecionado = null;
		for (Persona per : this.Tecnicos) {

			if (per.getIdpersona() == codTecSelecc.intValue()) {
				this.TecnicoSeleecionado = per;
				break;
			}
		}
	}

	public Long getCodOrdenSelecc() {
		return codOrdenSelecc;
	}

	public void setCodOrdenSelecc(Long codOrdenSelecc) {
		this.codOrdenSelecc = codOrdenSelecc;
		this.ordenSeleccionada = null;
		if (codOrdenSelecc == null)
			return;
		for (Ordenes ord : this.ordenesTecnico) {
			if (ord.getIdOrdenes().equals(codOrdenSelecc)) {
				this.ordenSeleccionada = ord;
				break;
			}
		}
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}

	public List<Materiales> getMaterialCliente() {
		return materialCliente;
	}

	public void setMaterialCliente(List<Materiales> materialCliente) {
		this.materialCliente = materialCliente;
	}

	public List<Materiales> getMaterialTecnico() {
		return materialTecnico;
	}

	public void setMaterialTecnico(List<Materiales> materialTecnico) {
		this.materialTecnico = materialTecnico;
	}

	public String getObservacionMonitoreo() {
		return observacionMonitoreo;
	}

	public void setObservacionMonitoreo(String observacionMonitoreo) {
		this.observacionMonitoreo = observacionMonitoreo;
	}

	public List<MonitoreoTraza> getMonitoreoTraza() {
		return monitoreoTraza;
	}

	public void setMonitoreoTraza(List<MonitoreoTraza> monitoreoTraza) {
		this.monitoreoTraza = monitoreoTraza;
	}

}
