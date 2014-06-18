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
 * Tiposector generated by hbm2java
 */
@Entity
@Table(name = "tiposector", schema = "public")
public class Tiposector implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5805213662024761928L;
	private int idtiposector;
	private Usuario usuario;
	private String nombre;
	private String abreviado;
	private Date fecha;
	//private Set<?> direccions = new HashSet<Object>(0);

	public Tiposector() {
	}

	public Tiposector(int idtiposector, Usuario usuario, String nombre,
			Date fecha) {
		this.idtiposector = idtiposector;
		this.usuario = usuario;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public Tiposector(int idtiposector, Usuario usuario, String nombre,
			String abreviado, Date fecha/*, Set<?> direccions*/) {
		this.idtiposector = idtiposector;
		this.usuario = usuario;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.fecha = fecha;
		//this.direccions = direccions;
	}

	@Id
	@Column(name = "idtiposector", unique = true, nullable = false)
	public int getIdtiposector() {
		return this.idtiposector;
	}

	public void setIdtiposector(int idtiposector) {
		this.idtiposector = idtiposector;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "tiposector")
	public Set<?> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(Set<?> direccions) {
		this.direccions = direccions;
	}*/
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public Tiposector clonar() throws Exception {
		return (Tiposector)clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abreviado == null) ? 0 : abreviado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idtiposector;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.getIdusuario());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tiposector other = (Tiposector) obj;
		if (abreviado == null) {
			if (other.abreviado != null)
				return false;
		} else if (!abreviado.equals(other.abreviado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idtiposector != other.idtiposector)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (usuario.getIdusuario() != other.usuario.getIdusuario())
			return false;
		return true;
	}
	
	

}
