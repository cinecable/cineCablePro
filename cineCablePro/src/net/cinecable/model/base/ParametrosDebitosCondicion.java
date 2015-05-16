package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pojo.annotations.Empresa;

@Entity
@Table(name = "pardebitoscondicion", schema = "public")
public class ParametrosDebitosCondicion {
	private String tipoGeneracion;
	private String token;
	private String tipoArchivo;
	private int parDebitosCondicion;
	private String estadoExitoso;
	private int estado = 1;
	private Date fecha;
	private Empresa empresa;

	@Id
	@Column(name = "idpardebitoscondicion", unique = true, nullable = false)
	public int getParDebitosCondicion() {
		return parDebitosCondicion;
	}

	public void setParDebitosCondicion(int parDebitosCondicion) {
		this.parDebitosCondicion = parDebitosCondicion;
	}

	@Column(name = "tipogeneracion")
	public String getTipoGeneracion() {
		return tipoGeneracion;
	}

	public void setTipoGeneracion(String tipoGeneracion) {
		this.tipoGeneracion = tipoGeneracion;
	}

	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "tipoarchivo")
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	@Column(name = "estado")
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@OneToOne
	@JoinColumn(name = "empresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "estadoexitoso")
	public String getEstadoExitoso() {
		return estadoExitoso;
	}

	public void setEstadoExitoso(String estadoExitoso) {
		this.estadoExitoso = estadoExitoso;
	}

}
