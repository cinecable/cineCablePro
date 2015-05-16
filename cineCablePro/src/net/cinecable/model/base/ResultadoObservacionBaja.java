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
@Table(name = "tresultadoObservacionbaja")
public class ResultadoObservacionBaja extends EntityBase {

	private Long idResultadoObservacion;
	private BajaOrdenesObservacion bajaOrdenesObservacion;
	private String Observacion;
	private BajaOrdenes bajaOrden;

	@Id
	@Column(name = "idresbajaorden")
	@SequenceGenerator(name = "sec_obs_res", allocationSize = 1, sequenceName = "seq_obs_res")
	@GeneratedValue(generator = "sec_obs_res", strategy = GenerationType.SEQUENCE)
	public Long getIdResultadoObservacion() {
		return idResultadoObservacion;
	}

	public void setIdResultadoObservacion(Long idResultadoObservacion) {
		this.idResultadoObservacion = idResultadoObservacion;
	}

	@ManyToOne
	@JoinColumn(name = "idBajaOrdenObservacion")
	public BajaOrdenesObservacion getBajaOrdenesObservacion() {
		return bajaOrdenesObservacion;
	}

	public void setBajaOrdenesObservacion(BajaOrdenesObservacion bajaOrdenesObservacion) {
		this.bajaOrdenesObservacion = bajaOrdenesObservacion;
	}

	@Column(name = "observacionresultado")
	public String getObservacion() {
		return Observacion;
	}

	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

	@ManyToOne
	@JoinColumn(name = "idBajaOrden")
	public BajaOrdenes getBajaOrden() {
		return bajaOrden;
	}

	public void setBajaOrden(BajaOrdenes bajaOrden) {
		this.bajaOrden = bajaOrden;
	}

}
