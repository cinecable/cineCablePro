package net.cinecable.model.extension;

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Debitobco;
import pojo.annotations.Factura;

public class FacturasDebito {

	private Debitobco debito;
	private Factura factura;
	private Clientes cliente;
	private Ctacliente cuentaCliente;

	public Ctacliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(Ctacliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Debitobco getDebito() {
		return debito;
	}

	public void setDebito(Debitobco debito) {
		this.debito = debito;
	}

}
