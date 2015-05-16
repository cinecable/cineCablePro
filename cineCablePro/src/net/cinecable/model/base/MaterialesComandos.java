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

import pojo.annotations.Comandos;

@Entity
@Table(name = "tmaterialescomandos")
public class MaterialesComandos extends EntityBase {

	private Long idMaterialesComando;
	private TipoMaterial tipoMaterial;
	private Comandos comandoMaterial;

	@Id
	@Column(name = "idmaterialescomando")
	@SequenceGenerator(name = "secmaterialcomandogen", sequenceName = "seqmaterialcomandogen", allocationSize = 1)
	@GeneratedValue(generator = "secmaterialcomandogen", strategy = GenerationType.SEQUENCE)
	public Long getIdMaterialesComando() {
		return idMaterialesComando;
	}

	public void setIdMaterialesComando(Long idMaterialesComando) {
		this.idMaterialesComando = idMaterialesComando;
	}

	@ManyToOne
	@JoinColumn(name = "idTipMaterial")
	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	@ManyToOne
	@JoinColumn(name = "idComando")
	public Comandos getComandoMaterial() {
		return comandoMaterial;
	}

	public void setComandoMaterial(Comandos comandoMaterial) {
		this.comandoMaterial = comandoMaterial;
	}

}
