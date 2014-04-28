package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

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

/**
 * Ciudad generated by hbm2java
 */
@Entity
@Table(name = "ciudad", schema = "public")
public class Ciudad implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8808587850538125426L;
	private int idciudad;
	private Estado estado;
	private Usuario usuario;
	private Provincia provincia;
	private String nombre;
	private String abreviado;
	private Date fecha;
	//private Set<?> edificios = new HashSet<Object>(0);

	public Ciudad() {
	}

	public Ciudad(int idciudad, Estado estado, Usuario usuario,
			Provincia provincia, String nombre, Date fecha) {
		this.idciudad = idciudad;
		this.estado = estado;
		this.usuario = usuario;
		this.provincia = provincia;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public Ciudad(int idciudad, Estado estado, Usuario usuario,
			Provincia provincia, String nombre, String abreviado, Date fecha/*,
			Set<?> edificios*/) {
		this.idciudad = idciudad;
		this.estado = estado;
		this.usuario = usuario;
		this.provincia = provincia;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.fecha = fecha;
		//this.edificios = edificios;
	}

	@Id
	@Column(name = "idciudad", unique = true, nullable = false)
	public int getIdciudad() {
		return this.idciudad;
	}

	public void setIdciudad(int idciudad) {
		this.idciudad = idciudad;
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
	@JoinColumn(name = "idprovincia", nullable = false)
	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciudad")
	public Set<?> getEdificios() {
		return this.edificios;
	}

	public void setEdificios(Set<?> edificios) {
		this.edificios = edificios;
	}*/

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Ciudad clonar() throws Exception {
		return (Ciudad)this.clone();
	}
}
