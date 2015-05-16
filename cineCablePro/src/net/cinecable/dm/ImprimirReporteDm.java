package net.cinecable.dm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Ordenes;
import pojo.annotations.Persona;

@ManagedBean(name = "imprimirReporteDm")
@SessionScoped
public class ImprimirReporteDm {

	private Persona tecnicoSeleccionado;
	private List<Persona> tecnicos;
	private Long codTecnicoSeleccionado;
	private List<Ordenes> listOrdenes;

	public List<Ordenes> getListOrdenes() {
		return listOrdenes;
	}

	public void setListOrdenes(List<Ordenes> listOrdenes) {
		this.listOrdenes = listOrdenes;
	}

	public Persona getTecnicoSeleccionado() {
		return tecnicoSeleccionado;
	}

	public void setTecnicoSeleccionado(Persona tecnicoSeleccionado) {
		this.tecnicoSeleccionado = tecnicoSeleccionado;
	}

	public List<Persona> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Persona> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Long getCodTecnicoSeleccionado() {
		return codTecnicoSeleccionado;
	}

	public void setCodTecnicoSeleccionado(Long codTecnicoSeleccionado) {
		this.codTecnicoSeleccionado = codTecnicoSeleccionado;
		this.tecnicoSeleccionado = null;
		if (codTecnicoSeleccionado == null)
			return;
		for (int i = 0; i < tecnicos.size(); i++) {
			if (this.codTecnicoSeleccionado.intValue() == tecnicos.get(i).getIdpersona()) {
				this.tecnicoSeleccionado = tecnicos.get(i);
				break;
			}
		}
	}

}
