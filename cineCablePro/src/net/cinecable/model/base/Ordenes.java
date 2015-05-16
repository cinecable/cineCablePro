package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Motivos;
import pojo.annotations.Tipooperacion;
import pojo.annotations.Usuario;

@Entity
@Table(name = "tbordenes")
public class Ordenes implements Cloneable /*extends EntityBase*/ {

	private Long idOrdenes;
	private Long tipoMotivoCancelacion;
	private Motivos motivo;
	private Motivos horario;
	private String Observacion;
	private Date fechaEjecucion;
	private Date fechaAsignacion;
	private Date fechaFinalizacion;
	private Date fechaMonitoreo;
	private Tipooperacion tipoOperacion;
	private Ctacliente cuentaCliente;
	private Ctasprod producto;
	private Estado estado;
	private String ip;
	private Usuario usuario;
	private Empresa empresa;
	private int idproductoprincipal;
	
	@Id
	@Column(name = "idordenes")
	@SequenceGenerator(name = "sq_generacion_ordenes", sequenceName = "sec_generacion_ordenes", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_generacion_ordenes")
	public Long getIdOrdenes() {
		return idOrdenes;
	}

	public void setIdOrdenes(Long idOrdenes) {
		this.idOrdenes = idOrdenes;
	}

	@Column(name = "tipmotivocancelacion")
	public Long getTipoMotivoCancelacion() {
		return tipoMotivoCancelacion;
	}

	public void setTipoMotivoCancelacion(Long tipoMotivoCancelacion) {
		this.tipoMotivoCancelacion = tipoMotivoCancelacion;
	}

	@ManyToOne
	@JoinColumn(name = "idmotivo", nullable = false)
	public Motivos getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivos motivo) {
		this.motivo = motivo;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return Observacion;
	}

	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

	@ManyToOne
	@JoinColumn(name = "idtipooperacion", nullable = false)
	public Tipooperacion getTipoOperacion() {
		return tipoOperacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaejecucion")
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setTipoOperacion(Tipooperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	@Transient
	@Override
	public boolean equals(Object obj) {

		Ordenes other = (Ordenes) obj;
		return this.idOrdenes.equals(other.idOrdenes) ? true : false;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idOrdenes != null ? idOrdenes.hashCode() : 0);
		return hash;
	}

	@ManyToOne
	@JoinColumn(name = "idcuenta")
	public Ctacliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(Ctacliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechafinalizacion")
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaasignacion")
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	@OneToOne
	@JoinColumn(name = "idctaprod")
	public Ctasprod getProducto() {
		return producto;
	}

	public void setProducto(Ctasprod producto) {
		this.producto = producto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechamonitoreo")
	public Date getFechaMonitoreo() {
		return fechaMonitoreo;
	}

	public void setFechaMonitoreo(Date fechaMonitoreo) {
		this.fechaMonitoreo = fechaMonitoreo;
	}

	@OneToOne
	@JoinColumn(name = "idhorario")
	public Motivos getHorario() {
		return horario;
	}

	public void setHorario(Motivos horario) {
		this.horario = horario;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idestado", nullable = false)
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Column(name = "idproductoprincipal")
	public int getIdproductoprincipal() {
		return idproductoprincipal;
	}

	public void setIdproductoprincipal(int idproductoprincipal) {
		this.idproductoprincipal = idproductoprincipal;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Ordenes clonar() throws Exception {
		Ordenes ordenes = (Ordenes) this.clone();
		
		return ordenes;
	}

}
