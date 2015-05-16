package net.cinecable.model.base;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cabgeneraciondebitos", schema = "public")
public class GeneracionDebitos extends EntityBase {

	private Long idgenDebito;
	private Long idgenInicial;
	private Long idgenfinal;
	private Date fecha;
	private Integer nroImpresiones;
	private Integer receptado;
	private List<GeneracionDebitosDetalle> detalles;

	@Id
	@Column(name = "idgendebito", unique = true, nullable = false)
	@SequenceGenerator(name = "sq_gen_debitos", sequenceName = "sec_sq_gen_debitos", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_gen_debitos")
	public Long getIdgenDebito() {
		return idgenDebito;
	}

	public void setIdgenDebito(Long idgenDebito) {
		this.idgenDebito = idgenDebito;
	}

	@Column(name = "idgeninicial")
	public Long getIdgenInicial() {
		return idgenInicial;
	}

	public void setIdgenInicial(Long idgenInicial) {
		this.idgenInicial = idgenInicial;
	}

	@Column(name = "idgenfinal")
	public Long getIdgenfinal() {
		return idgenfinal;
	}

	public void setIdgenfinal(Long idgenfinal) {
		this.idgenfinal = idgenfinal;
	}

	@Column(name = "fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "nroimpresiones")
	public Integer getNroImpresiones() {
		return nroImpresiones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "debito", cascade = CascadeType.ALL)
	public List<GeneracionDebitosDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<GeneracionDebitosDetalle> detalles) {
		this.detalles = detalles;
	}

	public void setNroImpresiones(Integer nroImpresiones) {
		this.nroImpresiones = nroImpresiones;
	}

	@Column(name = "receptado")
	public Integer getReceptado() {
		return receptado;
	}

	public void setReceptado(Integer receptado) {
		this.receptado = receptado;
	}

}
