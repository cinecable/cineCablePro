package net.cinecable.dm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.annotations.Persona;
import net.cinecable.enums.EstadosUnidades;
import net.cinecable.enums.TipoUnidadMedida;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.MaterialesEntregaTecnico;
import net.cinecable.model.base.TipoMaterial;

@ManagedBean(name = "materialesdm")
@SessionScoped
public class MaterialesDM {

	private List<Persona> tecnicos;

	private Long codTecnico;

	private Persona tecnicoSeleccionado;

	private Materiales material = new Materiales();

	private Materiales materialSeleccionado;

	private MaterialesEntregaTecnico materialSeleccionadoDeclarado;

	private String unidadMedida, estadoMaterial;

	private int codOp = 0;

	private int opDes = 1;

	private List<TipoMaterial> tipoMateriales= new ArrayList<TipoMaterial>();

	private Long codTipoMaterial;

	private List<Materiales> materialesAgregados;

	private List<MaterialesEntregaTecnico> materialesDeclarados;

	private String serieOmac;

	public int getCodOp() {
		return codOp;
	}

	public void setCodOp(int codOp) {
		this.codOp = codOp;
	}

	public List<TipoMaterial> getTipoMateriales() {
		return tipoMateriales;
	}

	public void setTipoMateriales(List<TipoMaterial> tipoMateriales) {
		this.tipoMateriales = tipoMateriales;
	}

	public Long getCodTipoMaterial() {
		return codTipoMaterial;
	}

	public void setCodTipoMaterial(Long codTipoMaterial) {
		this.codTipoMaterial = codTipoMaterial;
		this.material.setTipoMaterial(null);
		if (codTipoMaterial == null)
			return;
		for (int i = 0; i < this.tipoMateriales.size(); i++) {
			if (tipoMateriales.get(i).getIdTipMaterial().equals(codTipoMaterial)) {
				this.material.setTipoMaterial(tipoMateriales.get(i));
				break;
			}
		}
	}

	public List<EstadosUnidades> getEstadosUnidad() {
		return Arrays.asList(EstadosUnidades.values());
	}

	public List<TipoUnidadMedida> getUnidadesMedida() {
		return Arrays.asList(TipoUnidadMedida.values());
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
		this.material.setTipoUnidad(null);
		if (unidadMedida == null)
			return;
		for (TipoUnidadMedida uni : TipoUnidadMedida.values()) {
			if (uni.getValue().equals(unidadMedida)) {
				this.material.setTipoUnidad(uni);
				break;
			}
		}
	}

	public String getEstadoMaterial() {
		return estadoMaterial;
	}

	public void setEstadoMaterial(String estadoMaterial) {
		this.estadoMaterial = estadoMaterial;
		this.material.setEstadoUnidad(null);
		if (estadoMaterial == null)
			return;
		for (EstadosUnidades est : EstadosUnidades.values()) {
			if (est.getValue().equals(estadoMaterial)) {
				this.material.setEstadoUnidad(est);
				break;
			}
		}
	}

	public List<Materiales> getMaterialesAgregados() {
		return materialesAgregados;
	}

	public void setMaterialesAgregados(List<Materiales> materialesAgregados) {
		this.materialesAgregados = materialesAgregados;
	}

	public Materiales getMaterial() {
		return material;
	}

	public void setMaterial(Materiales material) {
		this.material = material;
	}

	public int getOpDes() {
		return opDes;
	}

	public void setOpDes(int opDes) {
		this.opDes = opDes;
	}

	public Materiales getMaterialSeleccionado() {
		return materialSeleccionado;
	}

	public void setMaterialSeleccionado(Materiales materialSeleccionado) {
		this.materialSeleccionado = materialSeleccionado;
	}

	public String getSerieOmac() {
		return serieOmac;
	}

	public void setSerieOmac(String serieOmac) {
		this.serieOmac = serieOmac;
	}

	public List<Persona> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(List<Persona> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Long getCodTecnico() {
		return codTecnico;
	}

	public void setCodTecnico(Long codTecnico) {
		this.codTecnico = codTecnico;
		this.tecnicoSeleccionado = null;
		if (codTecnico == null)
			return;
		for (int i = 0; i < this.tecnicos.size(); i++) {
			if (tecnicos.get(i).getIdpersona() == codTecnico.intValue()) {
				this.tecnicoSeleccionado = tecnicos.get(i);
				break;
			}

		}

	}

	public Persona getTecnicoSeleccionado() {
		return tecnicoSeleccionado;
	}

	public void setTecnicoSeleccionado(Persona tecnicoSeleccionado) {
		this.tecnicoSeleccionado = tecnicoSeleccionado;
	}

	public MaterialesEntregaTecnico getMaterialSeleccionadoDeclarado() {
		return materialSeleccionadoDeclarado;
	}

	public void setMaterialSeleccionadoDeclarado(MaterialesEntregaTecnico materialSeleccionadoDeclarado) {
		this.materialSeleccionadoDeclarado = materialSeleccionadoDeclarado;
	}

	public List<MaterialesEntregaTecnico> getMaterialesDeclarados() {
		return materialesDeclarados;
	}

	public void setMaterialesDeclarados(List<MaterialesEntregaTecnico> materialesDeclarados) {
		this.materialesDeclarados = materialesDeclarados;
	}

}
