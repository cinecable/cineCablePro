package net.cinecable.dm;

import pojo.annotations.Tipooperacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoOperacionesDM")
@SessionScoped
public class TipoOperacionesDM {

	private List<Tipooperacion> tipoOperaciones = new ArrayList<Tipooperacion>();
	private Tipooperacion tipoOperacionSeleccionada;

	private Long codOperacionSeleccionada;

	public Tipooperacion getTipoOperacionSeleccionada() {
		return tipoOperacionSeleccionada;
	}

	public void setTipoOperacionSeleccionada(Tipooperacion tipoOperacionSeleccionada) {
		this.tipoOperacionSeleccionada = tipoOperacionSeleccionada;
	}

	public List<Tipooperacion> getTipoOperaciones() {
		return tipoOperaciones;
	}

	public void setTipoOperaciones(List<Tipooperacion> tipoOperaciones) {
		this.tipoOperaciones = tipoOperaciones;
	}

	public Long getCodOperacionSeleccionada() {
		return codOperacionSeleccionada;
	}

	public void setCodOperacionSeleccionada(Long codOperacionSeleccionada) {
		this.codOperacionSeleccionada = codOperacionSeleccionada;
		for (int i = 0; i < tipoOperaciones.size(); i++) {
			if (tipoOperaciones.get(i).getIdtipooperacion() == codOperacionSeleccionada.intValue()) {
				tipoOperacionSeleccionada = tipoOperaciones.get(i);
				break;
			}
		}
	}

}
