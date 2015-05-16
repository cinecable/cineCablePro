package net.cinecable.dm;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.ParamAsignacionOrden;

import pojo.annotations.Tipooperacion;

@ManagedBean(name = "asignacionSolicitudDM")
@SessionScoped
public class AsignacionSolicitudDM {
	private List<Tipooperacion> tipoOperaciones;
	private ParamAsignacionOrden paramAsigOrd;
	private List<ParamAsignacionOrden> listaParametros;
	private Date fechaDesde;
	private Date fechaHasta;

	public List<ParamAsignacionOrden> getListaParametros() {
		return listaParametros;
	}

	public void setListaParametros(List<ParamAsignacionOrden> listaParametros) {
		this.listaParametros = listaParametros;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public ParamAsignacionOrden getParamAsigOrd() {
		return paramAsigOrd;
	}

	public void setParamAsigOrd(ParamAsignacionOrden paramAsigOrd) {
		this.paramAsigOrd = paramAsigOrd;
	}

	public List<Tipooperacion> getTipoOperaciones() {
		return tipoOperaciones;
	}

	public void setTipoOperaciones(List<Tipooperacion> tipoOperaciones) {
		this.tipoOperaciones = tipoOperaciones;
	}

}
