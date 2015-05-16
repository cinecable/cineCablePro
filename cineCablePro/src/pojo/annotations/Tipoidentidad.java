package pojo.annotations;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tipoidentidad", schema = "public")
public class Tipoidentidad  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3251658315497662037L;
	
	private int idtidentidad;
	private Estado estado;
	private String descripcion;

	public Tipoidentidad() {
	}

	public Tipoidentidad(int idtidentidad, Estado estado, String descripcion) {
		this.idtidentidad = idtidentidad;
		this.estado = estado;
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
	
	@Id
	@Column(name = "idtidentidad", unique = true, nullable = false, length = 30)
	public int getIdtidentidad() {
		return idtidentidad;
	}
	public void setIdtidentidad(int idtidentidad) {
		this.idtidentidad = idtidentidad;
	}
	
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
