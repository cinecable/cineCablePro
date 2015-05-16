package net.cinecable.dm;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.TipoMaterial;

@ManagedBean(name = "tipoMaterialDM")
@SessionScoped
public class TipoMaterialDM {

	private TipoMaterial materialSeleccionado;

	private Long codMaterialSeleccionado;

	private List<TipoMaterial> tipoMateriales;

	public TipoMaterial getMaterialSeleccionado() {
		return materialSeleccionado;
	}

	public Long getCodMaterialSeleccionado() {
		return codMaterialSeleccionado;
	}

	public void setCodMaterialSeleccionado(Long codMaterialSeleccionado) {
		this.codMaterialSeleccionado = codMaterialSeleccionado;
		this.materialSeleccionado = null;
		for (int i = 0; i < tipoMateriales.size(); i++) {
			if (tipoMateriales.get(i).getIdTipMaterial().equals(codMaterialSeleccionado)) {
				materialSeleccionado = tipoMateriales.get(i);
				break;
			}
		}
	}

	public void setMaterialSeleccionado(TipoMaterial materialSeleccionado) {
		this.materialSeleccionado = materialSeleccionado;
	}

	public List<TipoMaterial> getTipoMateriales() {
		return tipoMateriales;
	}

	public void setTipoMateriales(List<TipoMaterial> tipoMateriales) {
		this.tipoMateriales = tipoMateriales;
	}

}
