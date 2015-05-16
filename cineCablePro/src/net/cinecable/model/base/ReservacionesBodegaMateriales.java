package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import pojo.annotations.Producto;

@Entity
@Table(name = "tbreservacionesbodegamaterial")
public class ReservacionesBodegaMateriales extends EntityBase {

	private Long idReservacionBodegaMaterial;
	private Materiales material;
	private ReservacionesOrdenesBodega reservaOrdenBodega;
	private Double cantidad;
	private Double cantidadAgregada;
	private Double cantidadTotal;
	private String observaciones;
	private String modificadoPor;
	private String idtemp;
	private Double valorPagarMaterialExtra;
	private Producto producto;
	private boolean modificadoBaja;

	@Id
	@Column(name = "idreservacionboegamaterial")
	@SequenceGenerator(name = "sec_reserva_bodega_maerial", sequenceName = "seq_res_bod_mat", allocationSize = 1)
	@GeneratedValue(generator = "sec_reserva_bodega_maerial", strategy = GenerationType.SEQUENCE)
	public Long getIdReservacionBodegaMaterial() {
		return idReservacionBodegaMaterial;
	}

	@Column(name = "valorpagarmaterialextra")
	public Double getValorPagarMaterialExtra() {
		return valorPagarMaterialExtra;
	}

	public void setValorPagarMaterialExtra(Double valorPagarMaterialExtra) {
		this.valorPagarMaterialExtra = valorPagarMaterialExtra;
	}

	public void setIdReservacionBodegaMaterial(Long idReservacionBodegaMaterial) {
		this.idReservacionBodegaMaterial = idReservacionBodegaMaterial;
	}

	@ManyToOne
	@JoinColumn(name = "idUnidad")
	public Materiales getMaterial() {
		return material;
	}

	public void setMaterial(Materiales material) {
		this.material = material;
	}

	@ManyToOne
	@JoinColumn(name = "idReservaOrden", nullable = false)
	public ReservacionesOrdenesBodega getReservaOrdenBodega() {
		return reservaOrdenBodega;
	}

	public void setReservaOrdenBodega(ReservacionesOrdenesBodega reservaOrdenBodega) {
		this.reservaOrdenBodega = reservaOrdenBodega;
	}

	@Column(name = "cantidad")
	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "cantidadtotal")
	public Double getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Double cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	@Column(name = "observaciones")
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	@Transient
	public String getIdtemp() {
		return idtemp;
	}

	@Column(name = "modificadopor")
	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public void setIdtemp(String idtemp) {
		this.idtemp = idtemp;
	}

	@Column(name = "cantidadagregada")
	public Double getCantidadAgregada() {
		return cantidadAgregada;
	}

	public void setCantidadAgregada(Double cantidadAgregada) {
		this.cantidadAgregada = cantidadAgregada;
	}

	@Override
	public boolean equals(Object obj) {
		ReservacionesBodegaMateriales other = (ReservacionesBodegaMateriales) obj;
		if (other.idReservacionBodegaMaterial != null)
			return other.idReservacionBodegaMaterial.equals(this.idReservacionBodegaMaterial) ? true : false;
		if (!other.getIdtemp().isEmpty())
			return other.idtemp.equals(this.idtemp) ? true : false;
		return false;
	}

	@ManyToOne
	@JoinColumn(name = "idproducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Transient
	public boolean isModificadoBaja() {
		return modificadoBaja;
	}

	public void setModificadoBaja(boolean modificadoBaja) {
		this.modificadoBaja = modificadoBaja;
	}

}
