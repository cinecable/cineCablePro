package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_materiales_entrega")
public class MaterialesEntregaTecnico extends EntityBase {

	private Long idMatEnt;

	private MaterialesDeclaradosBodega materialBodega;

	private Materiales material;

	private Date fechaDeclaracion;

	private int cantidad;

	private int key;

	@Id
	@Column(name = "id_materiales_entrega_tecnico")
	@SequenceGenerator(name = "sec_materiales_entrega_tecnico", sequenceName = "seq_materiales_entrega_tecnico", allocationSize = 1)
	@GeneratedValue(generator = "sec_materiales_entrega_tecnico", strategy = GenerationType.SEQUENCE)
	public Long getIdMatEnt() {
		return idMatEnt;
	}

	public void setIdMatEnt(Long idMatEnt) {
		this.idMatEnt = idMatEnt;
	}

	@ManyToOne
	@JoinColumn(name = "idmaterialdeclaradoid")
	public MaterialesDeclaradosBodega getMaterialBodega() {
		return materialBodega;
	}

	public void setMaterialBodega(MaterialesDeclaradosBodega materialBodega) {
		this.materialBodega = materialBodega;
	}

	@ManyToOne
	@JoinColumn(name = "idunidad")
	public Materiales getMaterial() {
		return material;
	}

	public void setMaterial(Materiales material) {
		this.material = material;
	}

	@Column(name = "fechadeclaracion")
	@Temporal(TemporalType.DATE)
	public Date getFechaDeclaracion() {
		return fechaDeclaracion;
	}

	public void setFechaDeclaracion(Date fechaDeclaracion) {
		this.fechaDeclaracion = fechaDeclaracion;
	}

	@Transient
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Column(name = "cantidad")
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
