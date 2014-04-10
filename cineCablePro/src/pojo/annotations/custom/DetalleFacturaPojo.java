package pojo.annotations.custom;

import java.io.Serializable;
import java.util.List;

import pojo.annotations.Cargos;
import pojo.annotations.Creditos;
import pojo.annotations.Factura;
import pojo.annotations.Pagos;
import pojo.annotations.Tpagos;

public class DetalleFacturaPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8845082688628848887L;
	private Factura factura;
	private List<Cargos> lisCargosNivelDetalle;
	private List<Cargos> lisCargosNivelDescuento;
	private List<Cargos> lisCargosNivelImpuesto;
	private List<Creditos> lisCreditosByFactura;
	private float totalCreditosByFactura;
	private List<Pagos> lisPagosByFactura;//abonos, cabecera
	private float totalPagosByFactura;
	/**
	 * Contiene todos los abonos hechos a la factura sin contar los creditos y los excedentes
	 */
	private List<Tpagos> lisTpagosDetalleAbonosByFactura;//abonos, detalle
	/**
	 * Total de todos los abonos hechos a la factura sin contar los creditos y los excedentes
	 */
	private float totalTpagosAbonosByFactura;
	
	public DetalleFacturaPojo() {
	}
	
	public DetalleFacturaPojo(Factura factura, List<Cargos> lisCargosNivelDetalle, List<Cargos> lisCargosNivelDescuento, List<Cargos> lisCargosNivelImpuesto, List<Creditos> lisCreditosByFactura, float totalCreditosByFactura, List<Pagos> lisPagosByFactura, float totalPagosByFactura, List<Tpagos> lisTpagosDetalleAbonosByFactura, float totalTpagosAbonosByFactura) {
		this.factura = factura;
		this.lisCargosNivelDetalle = lisCargosNivelDetalle;
		this.lisCargosNivelDescuento = lisCargosNivelDescuento;
		this.lisCargosNivelImpuesto = lisCargosNivelImpuesto;
		this.lisCreditosByFactura = lisCreditosByFactura;
		this.totalCreditosByFactura = totalCreditosByFactura;
		this.lisPagosByFactura = lisPagosByFactura;
		this.totalPagosByFactura = totalPagosByFactura;
		this.lisTpagosDetalleAbonosByFactura = lisTpagosDetalleAbonosByFactura;
		this.totalTpagosAbonosByFactura = totalTpagosAbonosByFactura;
	}
	
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<Cargos> getLisCargosNivelDetalle() {
		return lisCargosNivelDetalle;
	}

	public void setLisCargosNivelDetalle(List<Cargos> lisCargosNivelDetalle) {
		this.lisCargosNivelDetalle = lisCargosNivelDetalle;
	}

	public List<Cargos> getLisCargosNivelDescuento() {
		return lisCargosNivelDescuento;
	}

	public void setLisCargosNivelDescuento(List<Cargos> lisCargosNivelDescuento) {
		this.lisCargosNivelDescuento = lisCargosNivelDescuento;
	}

	public List<Cargos> getLisCargosNivelImpuesto() {
		return lisCargosNivelImpuesto;
	}

	public void setLisCargosNivelImpuesto(List<Cargos> lisCargosNivelImpuesto) {
		this.lisCargosNivelImpuesto = lisCargosNivelImpuesto;
	}

	public List<Creditos> getLisCreditosByFactura() {
		return lisCreditosByFactura;
	}

	public void setLisCreditosByFactura(List<Creditos> lisCreditosByFactura) {
		this.lisCreditosByFactura = lisCreditosByFactura;
	}

	public float getTotalCreditosByFactura() {
		return totalCreditosByFactura;
	}

	public void setTotalCreditosByFactura(float totalCreditosByFactura) {
		this.totalCreditosByFactura = totalCreditosByFactura;
	}

	public List<Pagos> getLisPagosByFactura() {
		return lisPagosByFactura;
	}

	public void setLisPagosByFactura(List<Pagos> lisPagosByFactura) {
		this.lisPagosByFactura = lisPagosByFactura;
	}

	public float getTotalPagosByFactura() {
		return totalPagosByFactura;
	}

	public void setTotalPagosByFactura(float totalPagosByFactura) {
		this.totalPagosByFactura = totalPagosByFactura;
	}

	public List<Tpagos> getLisTpagosDetalleAbonosByFactura() {
		return lisTpagosDetalleAbonosByFactura;
	}

	public void setLisTpagosDetalleAbonosByFactura(List<Tpagos> lisTpagosDetalleAbonosByFactura) {
		this.lisTpagosDetalleAbonosByFactura = lisTpagosDetalleAbonosByFactura;
	}

	public float getTotalTpagosAbonosByFactura() {
		return totalTpagosAbonosByFactura;
	}

	public void setTotalTpagosAbonosByFactura(float totalTpagosAbonosByFactura) {
		this.totalTpagosAbonosByFactura = totalTpagosAbonosByFactura;
	}
	
}
