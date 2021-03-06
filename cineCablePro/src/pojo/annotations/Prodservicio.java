package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Prodservicio generated by hbm2java
 */
@Entity
@Table(name = "prodservicio", schema = "public")
public class Prodservicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -583132301567303011L;
	private int idprodservicio;
	private Servicio servicio;
	private Producto producto;

	public Prodservicio() {
	}

	public Prodservicio(int idprodservicio, Servicio servicio, Producto producto) {
		this.idprodservicio = idprodservicio;
		this.servicio = servicio;
		this.producto = producto;
	}

	@Id
	@Column(name = "idprodservicio", unique = true, nullable = false)
	public int getIdprodservicio() {
		return this.idprodservicio;
	}

	public void setIdprodservicio(int idprodservicio) {
		this.idprodservicio = idprodservicio;
	}
//(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "idservicio", nullable = false)
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
//(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "idproducto", nullable = false)
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
