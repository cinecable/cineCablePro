package net.cinecable.model.extension;

import java.util.List;

import net.cinecable.model.base.GeneracionBancoDepositos;
import pojo.annotations.Cargos;
import pojo.annotations.Factura;
import pojo.annotations.Pagos;

public class DebitosDetalle {

	private Factura factura;
	private Pagos pagos;
	private GeneracionBancoDepositos bancoDepositos;
	private List<Cargos> cargos;

	public List<Cargos> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargos> cargos) {
		this.cargos = cargos;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public GeneracionBancoDepositos getBancoDepositos() {
		return bancoDepositos;
	}

	public void setBancoDepositos(GeneracionBancoDepositos bancoDepositos) {
		this.bancoDepositos = bancoDepositos;
	}

}
