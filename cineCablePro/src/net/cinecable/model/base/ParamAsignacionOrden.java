package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pojo.annotations.Estado;
import pojo.annotations.Tipooperacion;

@Entity
@Table(name="tbparamasigOrden")
public class ParamAsignacionOrden {
	@Id
	@SequenceGenerator(name = "ParamAsigORd_GENERATOR", sequenceName = "SEQ_PARAM_ASIG_ORDENES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ParamAsigORd_GENERATOR")
	@Column(name = "idparamasigord", unique = true, nullable = false)
	private long idparamasigord;
	@OneToOne
	@JoinColumn(name="idtipooperacion")
	private Tipooperacion tipoOperacion;
	@Column(name="nroasignaciones")
	private int noasignaciones;
	@Temporal(TemporalType.DATE)
	@Column(name="fechaasignacion")
	private Date fechaasignacion;
	@Column(name="ip")
	private String ip;
	@Column(name="usuario")
	private String usuario;
	@OneToOne
	@JoinColumn(name="idestado")
	private Estado estado;
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public long getIdparamasigord() {
		return idparamasigord;
	}
	public void setIdparamasigord(long idparamasigord) {
		this.idparamasigord = idparamasigord;
	}
	public Tipooperacion getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(Tipooperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public int getNoasignaciones() {
		return noasignaciones;
	}
	public void setNoasignaciones(int noasignaciones) {
		this.noasignaciones = noasignaciones;
	}
	public Date getFechaasignacion() {
		return fechaasignacion;
	}
	public void setFechaasignacion(Date fechaasignacion) {
		this.fechaasignacion = fechaasignacion;
	}
	
	
}
