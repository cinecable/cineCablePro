package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pojo.annotations.Persona;

@Entity
@Table(name = "ordasignaciones")
public class OrdenesAsignaciones extends EntityBase {

	private Long idOrdAsignacion;
	private Ordenes orden;
	private Date fechaAsignacion;
	private Persona supervisor;
	private Persona tecnico;

	@Id
	@Column(name = "idordasignacion")
	@SequenceGenerator(name = "ORD_ASIG_GENERATOR", sequenceName = "SEQ_ORD_ASIG_GENERATOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORD_ASIG_GENERATOR")
	public Long getIdOrdAsignacion() {
		return idOrdAsignacion;
	}

	public void setIdOrdAsignacion(Long idOrdAsignacion) {
		this.idOrdAsignacion = idOrdAsignacion;
	}

	@OneToOne
	@JoinColumn(name = "idOrdenes", nullable = false)
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@Temporal(value = TemporalType.DATE)
	@Column(name = "fechaasignacion")
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	@ManyToOne
	@JoinColumn(name = "idpersona")
	public Persona getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Persona supervisor) {
		this.supervisor = supervisor;
	}

	@ManyToOne
	@JoinColumn(name = "idpersonat")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

}
