package pojo.annotations;

// Generated 09-feb-2014 10:19:46 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuariomenu generated by hbm2java
 */
@Entity
@Table(name = "usuariomenu", schema = "public")
public class Usuariomenu implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idusuariomenu;
	private int idmenu;
	private int idusuario;
	private int idestado;
	private int idempresa;
	private String strruta;
	private String strproceso;

	public Usuariomenu() {
	}

	public Usuariomenu(int idusuariomenu, int idmenu, int idusuario,
			int idestado, int idempresa) {
		this.idusuariomenu = idusuariomenu;
		this.idmenu = idmenu;
		this.idusuario = idusuario;
		this.idestado = idestado;
		this.idempresa = idempresa;
	}

	public Usuariomenu(int idusuariomenu, int idmenu, int idusuario,
			int idestado, int idempresa, String strruta, String strproceso) {
		this.idusuariomenu = idusuariomenu;
		this.idmenu = idmenu;
		this.idusuario = idusuario;
		this.idestado = idestado;
		this.idempresa = idempresa;
		this.strruta = strruta;
		this.strproceso = strproceso;
	}

	@Id
	@Column(name = "idusuariomenu", unique = true, nullable = false)
	public int getIdusuariomenu() {
		return this.idusuariomenu;
	}

	public void setIdusuariomenu(int idusuariomenu) {
		this.idusuariomenu = idusuariomenu;
	}

	@Column(name = "idmenu", nullable = false)
	public int getIdmenu() {
		return this.idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	@Column(name = "idusuario", nullable = false)
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
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

	@Column(name = "strruta", length = 200)
	public String getStrruta() {
		return this.strruta;
	}

	public void setStrruta(String strruta) {
		this.strruta = strruta;
	}

	@Column(name = "strproceso", length = 200)
	public String getStrproceso() {
		return this.strproceso;
	}

	public void setStrproceso(String strproceso) {
		this.strproceso = strproceso;
	}

}
