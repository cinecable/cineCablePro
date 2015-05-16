package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.cinecable.enums.BajaObservacion;
import pojo.annotations.Tipooperacion;

@Entity
@Table(name = "tbajaordenesobservacion")
public class BajaOrdenesObservacion extends EntityBase {

	private Long idBajaOrdenObservacion;
	private Tipooperacion tipoOperacion;
	private BajaObservacion bajaObservacion;

	@Id
	@Column(name = "idbajaorden")
	@SequenceGenerator(name = "sec_baja_orden", allocationSize = 1, sequenceName = "seq_baja_orden")
	@GeneratedValue(generator = "sec_baja_orden", strategy = GenerationType.SEQUENCE)
	public Long getIdBajaOrdenObservacion() {
		return idBajaOrdenObservacion;
	}

	public void setIdBajaOrdenObservacion(Long idBajaOrdenObservacion) {
		this.idBajaOrdenObservacion = idBajaOrdenObservacion;
	}

	@ManyToOne
	@JoinColumn(name = "idtipooperacion")
	public Tipooperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Tipooperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 5)
	public BajaObservacion getBajaObservacion() {
		return bajaObservacion;
	}

	public void setBajaObservacion(BajaObservacion bajaObservacion) {
		this.bajaObservacion = bajaObservacion;
	}

}
