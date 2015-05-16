package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pojo.annotations.Persona;

@Entity
@Table(name = "tecnicosupervisor")
public class TecnicoSupervisor extends EntityBase {

	private Long idtecSup;
	private Persona tecnico;
	private Persona supervisor;
	private Date fechaAsignacion;

	@Id
	@Column(name = "idtecsup")
	@SequenceGenerator(name = "sq_tec_sup_generator", sequenceName = "sec_tec_sup", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tec_sup_generator")
	public Long getIdtecSup() {
		return idtecSup;
	}

	public void setIdtecSup(Long idtecSup) {
		this.idtecSup = idtecSup;
	}

	@ManyToOne
	@JoinColumn(name = "idpersonat")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

	@ManyToOne
	@JoinColumn(name = "idpersonas")
	public Persona getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Persona supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public boolean equals(Object obj) {
		TecnicoSupervisor other = (TecnicoSupervisor) obj;
		return this.idtecSup.equals(other.idtecSup) ? true : false;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaasignacion")
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

}
