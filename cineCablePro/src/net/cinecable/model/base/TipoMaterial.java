package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.cinecable.enums.TipoEquipo;

@Entity
@Table(name = "ttipmaterial")
public class TipoMaterial extends EntityBase {

	private Long idTipMaterial;
	private String Descripcion;
	private net.cinecable.enums.TipoMaterial tipMaterialGen;
	private TipoEquipo tipEquipoMaterial;
	private Double nroLimiteMaterialxDefecto;
	private Double nroCostoMaterialxDefecto;

	@Id
	@Column(name = "idtipmaterial")
	@SequenceGenerator(name = "sec_tipmaterialGenerator", sequenceName = "seq_tipmaterial", allocationSize = 1)
	@GeneratedValue(generator = "sec_tipmaterialGenerator", strategy = GenerationType.SEQUENCE)
	public Long getIdTipMaterial() {
		return idTipMaterial;
	}

	public void setIdTipMaterial(Long idTipMaterial) {
		this.idTipMaterial = idTipMaterial;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipmatgen")
	public net.cinecable.enums.TipoMaterial getTipMaterialGen() {
		return tipMaterialGen;
	}

	public void setTipMaterialGen(net.cinecable.enums.TipoMaterial tipMaterialGen) {
		this.tipMaterialGen = tipMaterialGen;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipequipo")
	public TipoEquipo getTipEquipoMaterial() {
		return tipEquipoMaterial;
	}

	public void setTipEquipoMaterial(TipoEquipo tipEquipoMaterial) {
		this.tipEquipoMaterial = tipEquipoMaterial;
	}

	@Column(name = "nrolimitematerialpordefecto")
	public Double getNroLimiteMaterialxDefecto() {
		return nroLimiteMaterialxDefecto;
	}

	public void setNroLimiteMaterialxDefecto(Double nroLimiteMaterialxDefecto) {
		this.nroLimiteMaterialxDefecto = nroLimiteMaterialxDefecto;
	}

	@Column(name = "nrocostomaterialxdefecto")
	public Double getNroCostoMaterialxDefecto() {
		return nroCostoMaterialxDefecto;
	}

	public void setNroCostoMaterialxDefecto(Double nroCostoMaterialxDefecto) {
		this.nroCostoMaterialxDefecto = nroCostoMaterialxDefecto;
	}

}
