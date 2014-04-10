package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Controlador generated by hbm2java
 */
@Entity
@Table(name = "controlador", schema = "public")
public class Controlador implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582993642920758499L;
	private int idcontrolador;
	private String nombre;
	private String abreviado;
	private int idempresa;
	private int idestado;
	//private Set<?> comandoses = new HashSet<Object>(0);

	public Controlador() {
	}

	public Controlador(int idcontrolador, String nombre, int idempresa,
			int idestado) {
		this.idcontrolador = idcontrolador;
		this.nombre = nombre;
		this.idempresa = idempresa;
		this.idestado = idestado;
	}

	public Controlador(int idcontrolador, String nombre, String abreviado,
			int idempresa, int idestado/*, Set<?> comandoses*/) {
		this.idcontrolador = idcontrolador;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.idempresa = idempresa;
		this.idestado = idestado;
		//this.comandoses = comandoses;
	}

	@Id
	@Column(name = "idcontrolador", unique = true, nullable = false)
	public int getIdcontrolador() {
		return this.idcontrolador;
	}

	public void setIdcontrolador(int idcontrolador) {
		this.idcontrolador = idcontrolador;
	}

	@Column(name = "nombre", nullable = false, length = 25)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "abreviado", length = 8)
	public String getAbreviado() {
		return this.abreviado;
	}

	public void setAbreviado(String abreviado) {
		this.abreviado = abreviado;
	}

	@Column(name = "idempresa", nullable = false)
	public int getIdempresa() {
		return this.idempresa;
	}

	public void setIdempresa(int idempresa) {
		this.idempresa = idempresa;
	}

	@Column(name = "idestado", nullable = false)
	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "controlador")
	public Set<?> getComandoses() {
		return this.comandoses;
	}

	public void setComandoses(Set<?> comandoses) {
		this.comandoses = comandoses;
	}*/

}
