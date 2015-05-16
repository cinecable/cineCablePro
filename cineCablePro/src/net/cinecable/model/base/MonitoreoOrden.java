package net.cinecable.model.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pojo.annotations.Persona;

@Entity
@Table(name = "tmonitoreo")
public class MonitoreoOrden extends EntityBase {

	private Long idMonitoreo;

	private Ordenes orden;

	private Date fechaInicioVisita;

	private Date fechaFinVisita;

	private Persona tecnico;

	private String observacionMonitoreo;

	private List<MonitoreoTraza> monitoreoTrazas;

	@Id
	@Column
	@SequenceGenerator(name = "sec_monitoreo_generator", sequenceName = "seq_monitoreo", allocationSize = 1)
	@GeneratedValue(generator = "sec_monitoreo_generator", strategy = GenerationType.SEQUENCE)
	public Long getIdMonitoreo() {
		return idMonitoreo;
	}

	public void setIdMonitoreo(Long idMonitoreo) {
		this.idMonitoreo = idMonitoreo;
	}

	@OneToOne
	@JoinColumn(name = "idOrdenes")
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechainiciovisita")
	public Date getFechaInicioVisita() {
		return fechaInicioVisita;
	}

	public void setFechaInicioVisita(Date fechaInicioVisita) {
		this.fechaInicioVisita = fechaInicioVisita;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechafinvisita")
	public Date getFechaFinVisita() {
		return fechaFinVisita;
	}

	public void setFechaFinVisita(Date fechaFinVisita) {
		this.fechaFinVisita = fechaFinVisita;
	}

	@ManyToOne
	@JoinColumn(name = "idpersona")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

	@Column(name = "observacion", length = 300)
	public String getObservacionMonitoreo() {
		return observacionMonitoreo;
	}

	public void setObservacionMonitoreo(String observacionMonitoreo) {
		this.observacionMonitoreo = observacionMonitoreo;
	}

	@OneToMany(mappedBy = "monitoreo", cascade = CascadeType.ALL)
	public List<MonitoreoTraza> getMonitoreoTrazas() {
		return monitoreoTrazas;
	}

	public void setMonitoreoTrazas(List<MonitoreoTraza> monitoreoTrazas) {
		this.monitoreoTrazas = monitoreoTrazas;
	}

}
