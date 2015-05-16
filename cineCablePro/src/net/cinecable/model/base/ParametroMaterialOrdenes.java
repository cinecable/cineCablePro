package net.cinecable.model.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.annotations.Tipooperacion;

@Entity
@Table(name = "tparametromaterialesordenes")
public class ParametroMaterialOrdenes extends EntityBase {

	private Long idParametroMaterial;
	private Tipooperacion operacion;
	private List<TipoMaterialCantidad> materiales;

	@Id
	@Column(name = "idparametromaterial")
	@SequenceGenerator(name = "sec_parametro_material_generator", sequenceName = "sec_parametro_material")
	@GeneratedValue(generator = "sec_parametro_material_generator", strategy = GenerationType.SEQUENCE)
	public Long getIdParametroMaterial() {
		return idParametroMaterial;
	}

	public void setIdParametroMaterial(Long idParametroMaterial) {
		this.idParametroMaterial = idParametroMaterial;
	}

	@ManyToOne
	@JoinColumn(name = "idtipooperacion", nullable = false)
	public Tipooperacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Tipooperacion operacion) {
		this.operacion = operacion;
	}

	@OneToMany(mappedBy = "parametroMaterial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<TipoMaterialCantidad> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<TipoMaterialCantidad> materiales) {
		this.materiales = materiales;
	}

}
