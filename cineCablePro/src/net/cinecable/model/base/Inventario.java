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

import pojo.annotations.Ctacliente;
import net.cinecable.enums.MovimientoInventario;
import net.cinecable.enums.TipoPropietario;
import net.cinecable.enums.TipoUnidadMedida;

@Entity
@Table(name = "tinventario")
public class Inventario extends EntityBase {

	private Long idInventario;
	private double nroUnidades;
	private Materiales unidad;
	private TipoUnidadMedida tipoUnidad;
	private Date FechaMovimiento;
	private MovimientoInventario tipMovimiento;
	private TipoPropietario propietario;
	private Ordenes orden;
	private Ctacliente ctaCliente;
	private String nroSerie;

	@Id
	@Column(name = "idinv")
	@SequenceGenerator(name = "sq_inv_tec_generator", sequenceName = "sec_inv_tec", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_inv_tec_generator")
	public Long getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Long idInventario) {
		this.idInventario = idInventario;
	}

	@Column(name = "nrounidades")
	public double getNroUnidades() {
		return nroUnidades;
	}

	public void setNroUnidades(double nroUnidades) {
		this.nroUnidades = nroUnidades;
	}

	@Column(name = "tipounidad")
	@Enumerated(EnumType.STRING)
	public TipoUnidadMedida getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(TipoUnidadMedida tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechamovimiento")
	public Date getFechaMovimiento() {
		return FechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		FechaMovimiento = fechaMovimiento;
	}

	@Column(name = "tipmovimiento")
	@Enumerated(EnumType.STRING)
	public MovimientoInventario getTipMovimiento() {
		return tipMovimiento;
	}

	public void setTipMovimiento(MovimientoInventario tipMovimiento) {
		this.tipMovimiento = tipMovimiento;
	}

	@Column(name = "tippropietario")
	@Enumerated(EnumType.STRING)
	public TipoPropietario getPropietario() {
		return propietario;
	}

	public void setPropietario(TipoPropietario propietario) {
		this.propietario = propietario;
	}

	@ManyToOne
	@JoinColumn(name = "idUnidad")
	public Materiales getUnidad() {
		return unidad;
	}

	public void setUnidad(Materiales unidad) {
		this.unidad = unidad;
	}

	@ManyToOne
	@JoinColumn(name = "idOrdenes")
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@ManyToOne
	@JoinColumn(name = "idcuenta")
	public Ctacliente getCtaCliente() {
		return ctaCliente;
	}

	public void setCtaCliente(Ctacliente ctaCliente) {
		this.ctaCliente = ctaCliente;
	}

	@Column(name = "nroserie", length = 20)
	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

}
