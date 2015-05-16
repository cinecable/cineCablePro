package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ttipomaterialcantidad")
public class TipoMaterialCantidad extends EntityBase {

	private Long idTipMatCant;
	private TipoMaterial tipoMaterial;
	private double cantMaterial;
	private ParametroMaterialOrdenes parametroMaterial;

	@Id
	@Column(name = "idtipmatcant")
	@SequenceGenerator(name = "sec_tip_materia_cant_generator", sequenceName = "seq_tip_maeria_cant", allocationSize = 1)
	@GeneratedValue(generator = "sec_tip_materia_cant_generator", strategy = GenerationType.SEQUENCE)
	public Long getIdTipMatCant() {
		return idTipMatCant;
	}

	public void setIdTipMatCant(Long idTipMatCant) {
		this.idTipMatCant = idTipMatCant;
	}

	@ManyToOne
	@JoinColumn(name = "idtipmaterial")
	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	@ManyToOne
	@JoinColumn(name = "idParametroMaterial", nullable = false)
	public ParametroMaterialOrdenes getParametroMaterial() {
		return parametroMaterial;
	}

	@Column(name = "cantidadmaterial")
	public double getCantMaterial() {
		return cantMaterial;
	}

	public void setCantMaterial(double cantMaterial) {
		this.cantMaterial = cantMaterial;
	}

	public void setParametroMaterial(ParametroMaterialOrdenes parametroMaterial) {
		this.parametroMaterial = parametroMaterial;
	}

}
