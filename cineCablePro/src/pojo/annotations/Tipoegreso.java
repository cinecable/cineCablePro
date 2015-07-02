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
@Table(name = "tipoegreso", schema = "public")
public class Tipoegreso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3217421212634129868L;
	private int idtipoegreso;
	private String nombre;
	private String descripcion;
	private Estado estado;
	private Empresa empresa;
	private Usuario usuario;
	private Date fecha;
	private String iplog;
	
	public Tipoegreso(int idtipoegreso,String nombre,String descripcion,Estado estado,Empresa empresa,Usuario usuario,Date fecha,String iplog) {
		this.idtipoegreso = idtipoegreso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.empresa = empresa;
		this.usuario = usuario;
		this.fecha = fecha;
		this.iplog = iplog;
	}

	@Id
	@Column(name = "idtipoegreso", unique = true, nullable = false)
	public int getIdtipoegreso() {
		return idtipoegreso;
	}

	public void setIdtipoegreso(int idtipoegreso) {
		this.idtipoegreso = idtipoegreso;
	}

	@Column(name = "nombre", nullable = false, length = 50)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 100)
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
