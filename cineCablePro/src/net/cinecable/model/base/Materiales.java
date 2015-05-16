package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import net.cinecable.enums.EstadosUnidades;
import net.cinecable.enums.TipoUnidadMedida;

@Entity
@Table(name = "tbmateriales")
public class Materiales extends EntityBase {

	private Long idUnidad;
	private String nroSerie;
	private String observacion;
	private EstadosUnidades estadoUnidad;
	private Date fechaIngreso;
	private Date fechaBaja;
	private TipoUnidadMedida tipoUnidad;
	private Double cantidad;
	private Double cantidadIngresada;
	private TipoMaterial tipoMaterial;
	private boolean modificadoMaterial = false;
	private Double valorPorLimite;
	private String macAddres;
	private Double valorUnidad;
	private int key;

	@Id
	@Column(name = "idunidad")
	@SequenceGenerator(name = "sec_unidades_generator", sequenceName = "sec_unidades", allocationSize = 1)
	@GeneratedValue(generator = "sec_unidades_generator", strategy = GenerationType.SEQUENCE)
	public Long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}

	@Column(name = "nroserie")
	public String getNroSerie() {
		return nroSerie;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie.toUpperCase();
	}

	@Column(name = "estadounidad")
	@Enumerated(EnumType.STRING)
	public EstadosUnidades getEstadoUnidad() {
		return estadoUnidad;
	}

	public void setEstadoUnidad(EstadosUnidades estadoUnidad) {
		this.estadoUnidad = estadoUnidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaingreso")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechabaja")
	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "tipounidad")
	@Enumerated(EnumType.STRING)
	public TipoUnidadMedida getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(TipoUnidadMedida tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	@Column(name = "cantidad")
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToOne
	@JoinColumn(name = "idtipmaterial")
	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	@Override
	public boolean equals(Object obj) {
		Materiales other = (Materiales) obj;
		if (other.idUnidad == null) {
			if (other.getNroSerie().equals(this.getNroSerie()) || other.getMacAddres().equals(this.getMacAddres())) {
				return true;
			} else
				return false;
		}
		return this.idUnidad.equals(other.idUnidad) ? true : false;
	}

	@Transient
	public boolean isModificadoMaterial() {
		return modificadoMaterial;
	}

	public void setModificadoMaterial(boolean modificadoMaterial) {
		this.modificadoMaterial = modificadoMaterial;
	}

	@Column(name = "valorporlimite")
	public Double getValorPorLimite() {
		return valorPorLimite;
	}

	public void setValorPorLimite(Double valorPorLimite) {
		this.valorPorLimite = valorPorLimite;
	}

	@Column(name = "mac", length = 20)
	public String getMacAddres() {
		return macAddres;
	}

	public void setMacAddres(String macAddres) {
		this.macAddres = macAddres.toLowerCase();
	}

	@Transient
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Column(name = "valorunidad")
	public Double getValorUnidad() {
		return valorUnidad;
	}

	public void setValorUnidad(Double valorUnidad) {
		this.valorUnidad = valorUnidad;
	}

	@Column(name = "cantidadingresada")
	public Double getCantidadIngresada() {
		return cantidadIngresada;
	}

	public void setCantidadIngresada(Double cantidadIngresada) {
		this.cantidadIngresada = cantidadIngresada;
	}

}
