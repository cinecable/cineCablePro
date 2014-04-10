package pojo.annotations;

// Generated 09-feb-2014 10:19:46 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Nodos generated by hbm2java
 */
@Entity
@Table(name = "nodos", schema = "public")
public class Nodos implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idnodo;
	private String nombre;
	private String abreviado;
	private int idestado;
	private Sector sector;
	private int idusuario;
	private Date fecha;
	private Set<?> direccions = new HashSet<Object>(0);

	public Nodos() {
	}

	public Nodos(int idnodo, String nombre, int idestado, Sector sector,
			int idusuario, Date fecha) {
		this.idnodo = idnodo;
		this.nombre = nombre;
		this.idestado = idestado;
		this.sector = sector;
		this.idusuario = idusuario;
		this.fecha = fecha;
	}

	public Nodos(int idnodo, String nombre, String abreviado, int idestado,
			Sector sector, int idusuario, Date fecha, Set<?> direccions) {
		this.idnodo = idnodo;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.idestado = idestado;
		this.sector = sector;
		this.idusuario = idusuario;
		this.fecha = fecha;
		this.direccions = direccions;
	}

	@Id
	@Column(name = "idnodo", unique = true, nullable = false)
	public int getIdnodo() {
		return this.idnodo;
	}

	public void setIdnodo(int idnodo) {
		this.idnodo = idnodo;
	}

	@Column(name = "nombre", nullable = false, length = 25)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "abreviado", length = 6)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsector", nullable = false)
	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nodos",targetEntity=Direccion.class)
	public Set<?> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(Set<?> direccions) {
		this.direccions = direccions;
	}

}
