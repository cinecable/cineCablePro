package pojo.annotations;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "egreso", schema = "public")
public class Egreso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2394306075592437758L;
	private int idegreso;
	private Tipoegreso tipoegreso;
	private float valor;
	private Date fechaegreso;
	private String descripcion;
	private Estado estado;
	private Empresa empresa;
	private Usuario usuario;
	private Date fecha;
	private String iplog;
	
	public Egreso(int idegreso,Tipoegreso tipoegreso,float valor,Date fechaegreso,String descripcion,
				Estado estado,Empresa empresa,Usuario usuario,Date fecha,String iplog) {
		this.idegreso = idegreso;
		this.tipoegreso = tipoegreso;
		this.valor = valor;
		this.fechaegreso = fechaegreso;
		this.descripcion = descripcion;
		this.estado = estado;
		this.empresa = empresa;
		this.usuario = usuario;
		this.fecha = fecha;
		this.iplog = iplog;
	}

	@Id
	@Column(name = "idegreso", unique = true, nullable = false)
	public int getIdegreso() {
		return idegreso;
	}

	public void setIdegreso(int idegreso) {
		this.idegreso = idegreso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtipoegreso", nullable = false)
	public Tipoegreso getTipoegreso() {
		return tipoegreso;
	}

	public void setTipoegreso(Tipoegreso tipoegreso) {
		this.tipoegreso = tipoegreso;
	}

	@Column(name = "valor", nullable = false, precision = 8, scale = 8)
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaegreso", nullable = false, length = 29)
	public Date getFechaegreso() {
		return fechaegreso;
	}

	public void setFechaegreso(Date fechaegreso) {
		this.fechaegreso = fechaegreso;
	}

	@Column(name = "descripcion", length = 200)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idestado", nullable = false)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "iplog", length = 20)
	public String getIplog() {
		return iplog;
	}

	public void setIplog(String iplog) {
		this.iplog = iplog;
	}

}
