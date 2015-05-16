package net.cinecable.model.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.annotations.Persona;

@Entity
@Table(name = "tb_materiales_declarados_bodega")
public class MaterialesDeclaradosBodega extends EntityBase {

	private Long idMatDecBod;

	private Persona tecnico;

	private List<MaterialesEntregaTecnico> materialesEntregados;

	@Id
	@Column(name = "idmaterialdeclaradoid")
	@SequenceGenerator(name = "sec_material_declarado_generator", sequenceName = "seq_material_declarado_generator", allocationSize = 1)
	@GeneratedValue(generator = "sec_material_declarado_generator", strategy = GenerationType.SEQUENCE)
	public Long getIdMatDecBod() {
		return idMatDecBod;
	}

	public void setIdMatDecBod(Long idMatDecBod) {
		this.idMatDecBod = idMatDecBod;
	}

	@ManyToOne
	@JoinColumn(name = "id_persona_tecnico")
	public Persona getTecnico() {
		return tecnico;
	}

	public void setTecnico(Persona tecnico) {
		this.tecnico = tecnico;
	}

	@OneToMany(mappedBy = "materialBodega", cascade = CascadeType.ALL)
	public List<MaterialesEntregaTecnico> getMaterialesEntregados() {
		return materialesEntregados;
	}

	public void setMaterialesEntregados(List<MaterialesEntregaTecnico> materialesEntregados) {
		this.materialesEntregados = materialesEntregados;
	}

}
