package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Costoservicio generated by hbm2java
 */
@Entity
@Table(name = "costoservicio", schema = "public")
public class Costoservicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -622098153114633473L;
	//private int idcostoservicio;
	private int idservicio;
	private Servicio servicio;
	private float costo;
	private String ip;

	public Costoservicio() {
	}

	public Costoservicio(Servicio servicio, float costo) {
		this.servicio = servicio;
		this.costo = costo;
	}

	public Costoservicio(Servicio servicio, float costo, String ip) {
		this.servicio = servicio;
		this.costo = costo;
		this.ip = ip;
	}

	//@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "servicio"))
	//@Id
	//@GeneratedValue(generator = "generator")
	
	/*@Column(name = "idcostoservicio")
	public int getIdcostoservicio() {
		return idcostoservicio;
	}

	public void setIdcostoservicio(int idcostoservicio) {
		this.idcostoservicio = idcostoservicio;
	}*/
	
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "servicio"))
	@Id
	@GeneratedValue(generator = "generator")
	
	@Column(name = "idservicio", unique = true, nullable = false)
	public int getIdservicio() {
		return this.idservicio;
	}

	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}

	@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "idservicio")
	@PrimaryKeyJoinColumn
	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Column(name = "costo", nullable = false, precision = 8, scale = 8)
	public float getCosto() {
		return this.costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	@Column(name = "ip", length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
