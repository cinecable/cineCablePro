package net.cinecable.model.base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import pojo.annotations.Bancos;
@Entity
@Table(name = "pardebitosbancos", schema = "public")
public class ParametrosBancos {

	private int parDebitosBancos;
	private Bancos banco;
	private List<ParametroDatosBanco> parametros;
	private ParametrosDebitosCondicion parametrosCondicion;

	@Id
	@Column(name = "idpardebitosbancos", unique = true, nullable = false)
	public int getParDebitosBancos() {
		return parDebitosBancos;
	}

	public void setParDebitosBancos(int parDebitosBancos) {
		this.parDebitosBancos = parDebitosBancos;
	}

	@ManyToOne
	@JoinColumn(name = "idbanco", nullable = false)
	public Bancos getBanco() {
		return banco;
	}

	public void setBanco(Bancos banco) {
		this.banco = banco;
	}

	@OrderBy("orden ASC")
	@OneToMany(fetch = FetchType.LAZY,mappedBy="parametrosBanco")
	public List<ParametroDatosBanco> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroDatosBanco> parametros) {
		this.parametros = parametros;
	}

	@OneToOne
	@JoinColumn(name = "idpardebitoscondicion", nullable = false)
	public ParametrosDebitosCondicion getParametrosCondicion() {
		return parametrosCondicion;
	}

	public void setParametrosCondicion(ParametrosDebitosCondicion parametrosCondicion) {
		this.parametrosCondicion = parametrosCondicion;
	}

}
