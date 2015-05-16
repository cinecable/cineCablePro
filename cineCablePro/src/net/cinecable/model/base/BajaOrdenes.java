package net.cinecable.model.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbajaordenes")
public class BajaOrdenes extends EntityBase {

	private Long idBajaOrden;
	private Ordenes orden;
	private Date fechaBaja;
	private String observacionBaja;
	private List<ResultadoObservacionBaja> resultadoObservacion;

	@Id
	@Column(name = "idbajaorden")
	@SequenceGenerator(name = "bajaordengenerator", sequenceName = "seqbajaorden", allocationSize = 1)
	@GeneratedValue(generator = "bajaordengenerator", strategy = GenerationType.SEQUENCE)
	public Long getIdBajaOrden() {
		return idBajaOrden;
	}

	public void setIdBajaOrden(Long idBajaOrden) {
		this.idBajaOrden = idBajaOrden;
	}

	@OneToOne
	@JoinColumn(name = "idOrdenes")
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechabaja")
	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "observacion")
	public String getObservacionBaja() {
		return observacionBaja;
	}

	public void setObservacionBaja(String observacionBaja) {
		this.observacionBaja = observacionBaja;
	}

	@OneToMany(mappedBy = "bajaOrden", cascade = CascadeType.ALL)
	public List<ResultadoObservacionBaja> getResultadoObservacion() {
		return resultadoObservacion;
	}

	public void setResultadoObservacion(List<ResultadoObservacionBaja> resultadoObservacion) {
		this.resultadoObservacion = resultadoObservacion;
	}

}
