package net.cinecable.dm;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="reporteMonitoreoDM")
@SessionScoped
public class reporteMonitoreoDM {
	private Date fechaDesde;
	private Date fechaHasta;
	private Long tipooperacion;
	private Long nodo;
	private Long estado;
	private Long tecnico;
	private String tOperacion;
	private String estadoNombre;
	private String tecnicoNombre;
	private String nodoNombre;
	private Long supervisor;
	
	
	
	
	public String gettOperacion() {
		return tOperacion;
	}
	public void settOperacion(String tOperacion) {
		this.tOperacion = tOperacion;
	}
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	public String getTecnicoNombre() {
		return tecnicoNombre;
	}
	public void setTecnicoNombre(String tecnicoNombre) {
		this.tecnicoNombre = tecnicoNombre;
	}
	public String getNodoNombre() {
		return nodoNombre;
	}
	public void setNodoNombre(String nodoNombre) {
		this.nodoNombre = nodoNombre;
	}
	public Long getTecnico() {
		return tecnico;
	}
	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Long getNodo() {
		return nodo;
	}
	public void setNodo(Long nodo) {
		this.nodo = nodo;
	}
	public Long getTipooperacion() {
		return tipooperacion;
	}
	public void setTipooperacion(Long tipooperacion) {
		this.tipooperacion = tipooperacion;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Long getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Long supervisor) {
		this.supervisor = supervisor;
	}
	
	
}
