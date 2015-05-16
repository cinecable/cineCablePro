package net.cinecable.model.base;


import java.util.Calendar;

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

import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;


@Entity
@Table(name = "tbmonitoreo_traza")
public class MonitoreoTraza extends EntityBase {

	private Long idMonitoreoTraza;
	private Ordenes orden;
	private Materiales material;
	private Calendar tiempoComandoEjecucion;
	private Ctacliente cuentaCliente;
	private Clientes cliente;
	private MonitoreoOrden monitoreo;
	private boolean activadoSinError;
	private String menError;

	@Id
	@Column(name = "idmonitoreotraza")
	@SequenceGenerator(name = "sec_monitoreo_traza", sequenceName = "seq_monitoreo_traza", allocationSize = 1)
	@GeneratedValue(generator = "sec_monitoreo_traza", strategy = GenerationType.SEQUENCE)
	public Long getIdMonitoreoTraza() {
		return idMonitoreoTraza;
	}

	public void setIdMonitoreoTraza(Long idMonitoreoTraza) {
		this.idMonitoreoTraza = idMonitoreoTraza;
	}

	@ManyToOne
	@JoinColumn(name = "idordenes")
	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	@ManyToOne
	@JoinColumn(name = "idunidad")
	public Materiales getMaterial() {
		return material;
	}

	public void setMaterial(Materiales material) {
		this.material = material;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getTiempoComandoEjecucion() {
		return tiempoComandoEjecucion;
	}

	public void setTiempoComandoEjecucion(Calendar tiempoComandoEjecucion) {
		this.tiempoComandoEjecucion = tiempoComandoEjecucion;
	}

	@ManyToOne
	@JoinColumn(name = "idcuenta")
	public Ctacliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(Ctacliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	@ManyToOne
	@JoinColumn(name = "idcliente")
	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	@ManyToOne
	@JoinColumn(name = "idOrdenes")
	public MonitoreoOrden getMonitoreo() {
		return monitoreo;
	}

	public void setMonitoreo(MonitoreoOrden monitoreo) {
		this.monitoreo = monitoreo;
	}

	@Column(name = "activado")
	public boolean isActivadoSinError() {
		return activadoSinError;
	}

	public void setActivadoSinError(boolean activadoSinError) {
		this.activadoSinError = activadoSinError;
	}

	@Column(name = "menerror")
	public String getMenError() {
		return menError;
	}

	public void setMenError(String menError) {
		this.menError = menError;
	}

}
