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

import pojo.annotations.Estado;

@Entity
@Table(name="tipoidentidad")
public class Tidentidad {
	@Id
	@SequenceGenerator(name = "tidentidad_GENERATOR", sequenceName = "SEQ_TIPOIDENTIDAD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tidentidad_GENERATOR")
	@Column(name = "idtidentidad", unique = true, nullable = false)
	private int idtidentidad;
	@Column(name="descripcion")
	private String descripcion;
	@OneToOne
	@JoinColumn(name="idestado")
	private Estado estado;
	public int getIdtidentidad() {
		return idtidentidad;
	}
	public void setIdtidentidad(int idtidentidad) {
		this.idtidentidad = idtidentidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
