package net.cinecable.dm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Ordenes;

@ManagedBean(name = "ordenesDM")
@SessionScoped
public class OrdenesDM {

	private List<Ordenes> ordenes;

	private List<Ordenes> ordenesTecnico;

	public List<Ordenes> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Ordenes> ordenes) {
		this.ordenes = ordenes;
	}

	public List<Ordenes> getOrdenesTecnico() {
		return ordenesTecnico;
	}

	public void setOrdenesTecnico(List<Ordenes> ordenesTecnico) {
		this.ordenesTecnico = ordenesTecnico;
	}

}
