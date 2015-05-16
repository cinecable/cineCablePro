package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.annotations.Area;
import pojo.annotations.Estado;

@Entity
@Table(name="tcargo")
public class Tcargo {
	@Id
	@SequenceGenerator(name = "TCARGO_GENERATOR", sequenceName = "SEQ_TCARGO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TCARGO_GENERATOR")
	private Integer idtcagro;
	@Column
	private String nombre;
	@OneToOne
	@JoinColumn(name="idarea")
	private Area area;
	@OneToOne
	@JoinColumn(name="idestado")
	private Estado estado;
	public Integer getIdtcagro() {
		return idtcagro;
	}
	public void setIdtcagro(Integer idtcagro) {
		this.idtcagro = idtcagro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
