package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tiposervicio generated by hbm2java
 */
@Entity
@Table(name = "tiposervicio", schema = "public")
public class Tiposervicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6572787551022779339L;
	private int idtiposervicio;
	private String nombre;
	private String abreviado;
	private int idestado;
	private int idempresa;
	private int idusuario;
	private Date fecha;
	//private Set<?> servicios = new HashSet<Object>(0);

	public Tiposervicio() {
	}

	public Tiposervicio(int idtiposervicio, String nombre, int idestado,
			int idempresa, int idusuario, Date fecha) {
		this.idtiposervicio = idtiposervicio;
		this.nombre = nombre;
		this.idestado = idestado;
		this.idempresa = idempresa;
		this.idusuario = idusuario;
		this.fecha = fecha;
	}

	public Tiposervicio(int idtiposervicio, String nombre, String abreviado,
			int idestado, int idempresa, int idusuario, Date fecha/*,
			Set<?> servicios*/) {
		this.idtiposervicio = idtiposervicio;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.idestado = idestado;
		this.idempresa = idempresa;
		this.idusuario = idusuario;
		this.fecha = fecha;
		//this.servicios = servicios;
	}

	@Id
	@Column(name = "idtiposervicio", unique = true, nullable = false)
	public int getIdtiposervicio() {
		return this.idtiposervicio;
	}

	public void setIdtiposervicio(int idtiposervicio) {
		this.idtiposervicio = idtiposervicio;
	}

	@Column(name = "nombre", nullable = false, length = 10)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "abreviado", length = 3)
	public String getAbreviado() {
		return this.abreviado;
	}

	public void setAbreviado(String abreviado) {
		this.abreviado = abreviado;
	}

	@Column(name = "idestado", nullable = false)
	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	@Column(name = "idempresa", nullable = false)
	public int getIdempresa() {
		return this.idempresa;
	}

	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

	@Column(name = "idusuario", nullable = false)
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposervicio")
	public Set<?> getServicios() {
		return this.servicios;
	}

	public void setServicios(Set<?> servicios) {
		this.servicios = servicios;
	}*/

}
