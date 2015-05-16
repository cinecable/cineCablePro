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

import pojo.annotations.Ctacliente;

@Entity
@Table(name = "detgeneraciondebitos")
public class GeneracionDebitosDetalle extends EntityBase {

	private Long idgenDebitosDetalle;
	private Long idGeneracion;
	private Double valorEnvio;
	private Double valorRecibido;
	private GeneracionDebitos debito;
	private Ctacliente cuentaCliente;
	private String nroFactura;

	@Id
	@Column(name = "codgendebitodet", unique = true, nullable = false)
	@SequenceGenerator(name = "sq_gen_debitosdet", sequenceName = "sec_sq_gen_debitosdet", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_gen_debitosdet")
	public Long getIdgenDebitosDetalle() {
		return idgenDebitosDetalle;
	}

	public void setIdgenDebitosDetalle(Long idgenDebitosDetalle) {
		this.idgenDebitosDetalle = idgenDebitosDetalle;
	}

	@Column(name = "idgeneracion")
	public Long getIdGeneracion() {
		return idGeneracion;
	}

	public void setIdGeneracion(Long idGeneracion) {
		this.idGeneracion = idGeneracion;
	}

	@Column(name = "valorenvio")
	public Double getValorEnvio() {
		return valorEnvio;
	}

	public void setValorEnvio(Double valorEnvio) {
		this.valorEnvio = valorEnvio;
	}

	@Column(name = "valorrecibido")
	public Double getValorRecibido() {
		return valorRecibido;
	}

	public void setValorRecibido(Double valorRecibido) {
		this.valorRecibido = valorRecibido;
	}

	@ManyToOne
	@JoinColumn(name = "idgendebito", nullable = false)
	public GeneracionDebitos getDebito() {
		return debito;
	}

	public void setDebito(GeneracionDebitos debito) {
		this.debito = debito;
	}

	@ManyToOne
	@JoinColumn(name = "idcuenta")
	public Ctacliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(Ctacliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	@Column(name = "NroFactura")
	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

}
