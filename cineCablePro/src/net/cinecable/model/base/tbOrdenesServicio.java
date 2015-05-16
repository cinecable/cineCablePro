package net.cinecable.model.base;

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

import pojo.annotations.Estado;
import pojo.annotations.Prodservicio;
@Entity
@Table(name = "tbordServicio")
public class tbOrdenesServicio {
	@Id
	@Column(name = "idordservicio")
	@SequenceGenerator(name = "sq_generacion_ordprod", sequenceName = "sec_generacion_ordprod", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_generacion_ordprod")
	private Long idordservicio;
	@ManyToOne
	@JoinColumn(name="idOrdenes",nullable=false)
	private Ordenes ordenes;
	@OneToOne
	@JoinColumn(name="idprodservicio")
	private Prodservicio prodServicio;
	@OneToOne
	@JoinColumn(name="idestado")
	private Estado estado;
	
	public Long getIdordservicio() {
		return idordservicio;
	}
	public void setIdordservicio(Long idordservicio) {
		this.idordservicio = idordservicio;
	}
	public Ordenes getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(Ordenes ordenes) {
		this.ordenes = ordenes;
	}
	public Prodservicio getProdServicio() {
		return prodServicio;
	}
	public void setProdServicio(Prodservicio prodServicio) {
		this.prodServicio = prodServicio;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
