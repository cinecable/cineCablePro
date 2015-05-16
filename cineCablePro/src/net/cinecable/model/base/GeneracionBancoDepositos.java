package net.cinecable.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.annotations.Bancos;

@Entity
@Table(name = "generacionbancodepositos", schema = "public")
public class GeneracionBancoDepositos extends EntityBase {

	private Long idGeneracionBancos;
	private Bancos banco;
	private Long idgeneracionUnico;
	private Double valorDebito;
	private String referencia;
	private String TipoCuenta;
	private String observacion;
	private String nombreCliente;
	private String estadoResp;

	@Id
	@Column(name = "idgeneracionbanco")
	@SequenceGenerator(name = "sq_generacion_banco", sequenceName = "sec_generacion_banco", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_generacion_banco")
	public Long getIdGeneracionBancos() {
		return idGeneracionBancos;
	}

	public void setIdGeneracionBancos(Long idGeneracionBancos) {
		this.idGeneracionBancos = idGeneracionBancos;
	}

	@Column(name = "valordebito")
	public Double getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(Double valorDebito) {
		this.valorDebito = valorDebito;
	}

	@Column(name = "nombrecliente")
	public String getNombreCliente() {
		return nombreCliente;
	}

	@Column(name = "tipocuenta")
	public String getTipoCuenta() {
		return TipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Column(name = "referencia")
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Column(name = "observacion")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "estadorespuesta")
	public String getEstadoResp() {
		return estadoResp;
	}

	public void setEstadoResp(String estadoResp) {
		this.estadoResp = estadoResp;
	}

	@ManyToOne
	@JoinColumn(name = "idbanco")
	public Bancos getBanco() {
		return banco;
	}

	public void setBanco(Bancos banco) {
		this.banco = banco;
	}

	@Column(name = "idunico")
	public Long getIdgeneracionUnico() {
		return idgeneracionUnico;
	}

	public void setIdgeneracionUnico(Long idgeneracionUnico) {
		this.idgeneracionUnico = idgeneracionUnico;
	}

}