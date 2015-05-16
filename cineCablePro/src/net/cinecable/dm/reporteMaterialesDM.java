package net.cinecable.dm;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.TipoMaterial;

@ManagedBean(name="reporteMaterialesDM")
@SessionScoped
public class reporteMaterialesDM {
	private Date fechaDesde;
	private Date fechaHasta;
	private TipoMaterial tipoMaterial;
	private List<TipoMaterial> listaMaterial;
	
	
	
	
	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}
	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	public List<TipoMaterial> getListaMaterial() {
		return listaMaterial;
	}
	public void setListaMaterial(List<TipoMaterial> listaMaterial) {
		this.listaMaterial = listaMaterial;
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
	
}
