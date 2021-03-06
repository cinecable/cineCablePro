package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tipocargo generated by hbm2java
 */
@Entity
@Table(name = "tipocargo", schema = "public")
public class Tipocargo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8085486512842518402L;
	private int idtipocargo;
	private Motivos motivos;
	private String nombre;
	private String abreviado;
	private int idempresa;
	private int idestado;
	private int idusuario;
	private short nivel;

	public Tipocargo() {
	}

	public Tipocargo(int idtipocargo, Motivos motivos, String nombre,
			int idempresa, int idestado, int idusuario, short nivel) {
		this.idtipocargo = idtipocargo;
		this.motivos = motivos;
		this.nombre = nombre;
		this.idempresa = idempresa;
		this.idestado = idestado;
		this.idusuario = idusuario;
		this.nivel = nivel;
	}

	public Tipocargo(int idtipocargo, Motivos motivos, String nombre,
			String abreviado, int idempresa, int idestado, int idusuario,
			short nivel) {
		this.idtipocargo = idtipocargo;
		this.motivos = motivos;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.idempresa = idempresa;
		this.idestado = idestado;
		this.idusuario = idusuario;
		this.nivel = nivel;
	}

	@Id
	@Column(name = "idtipocargo", unique = true, nullable = false)
	public int getIdtipocargo() {
		return this.idtipocargo;
	}

	public void setIdtipocargo(int idtipocargo) {
		this.idtipocargo = idtipocargo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idmotivo", nullable = false)
	public Motivos getMotivos() {
		return this.motivos;
	}

	public void setMotivos(Motivos motivos) {
		this.motivos = motivos;
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

	@Column(name = "idusuario", nullable = false)
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@Column(name = "nivel", nullable = false)
	public short getNivel() {
		return this.nivel;
	}

	public void setNivel(short nivel) {
		this.nivel = nivel;
	}

}
