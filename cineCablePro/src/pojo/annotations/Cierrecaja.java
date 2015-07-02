package pojo.annotations;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cierrecaja", schema = "public")
public class Cierrecaja implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7065372722815220910L;
	private int idcierrecaja;
	private Usuario usuariocaja;
	private float saldoinicial;
	private float saldofinal;
	private Date fechacierre;
	private Date fechadesde;
	private Date fechahasta;
    private Estado estado;
	private Empresa empresa;
	private Usuario usuario;
	private Date fecha;
	private String iplog;
	
	public Cierrecaja(int idcierrecaja,Usuario usuariocaja,float saldoinicial,float saldofinal,
			Date fechacierre,Date fechadesde,Date fechahasta,Estado estado,Empresa empresa,
			Usuario usuario,Date fecha,String iplog) {
		this.idcierrecaja = idcierrecaja;
		this.usuariocaja = usuariocaja;
		this.saldoinicial = saldoinicial;
		this.saldofinal = saldofinal;
		this.fechacierre = fechacierre;
		this.fechadesde = fechadesde;
		this.fechahasta = fechahasta;
		this.estado = estado;
		this.empresa = empresa;
		this.usuario = usuario;
		this.fecha = fecha;
		this.iplog = iplog;
	}

	@Id
	@Column(name = "idcierrecaja", unique = true, nullable = false)
	public int getIdcierrecaja() {
		return idcierrecaja;
	}

	public void setIdcierrecaja(int idcierrecaja) {
		this.idcierrecaja = idcierrecaja;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuariocaja", nullable = false)
	public Usuario getUsuariocaja() {
		return usuariocaja;
	}

	public void setUsuariocaja(Usuario usuariocaja) {
		this.usuariocaja = usuariocaja;
	}

	@Column(name = "saldoinicial", nullable = false, precision = 8, scale = 8)
	public float getSaldoinicial() {
		return saldoinicial;
	}

	public void setSaldoinicial(float saldoinicial) {
		this.saldoinicial = saldoinicial;
	}

	@Column(name = "saldofinal", nullable = false, precision = 8, scale = 8)
	public float getSaldofinal() {
		return saldofinal;
	}

	public void setSaldofinal(float saldofinal) {
		this.saldofinal = saldofinal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechacierre", nullable = false, length = 29)
	public Date getFechacierre() {
		return fechacierre;
	}

	public void setFechacierre(Date fechacierre) {
		this.fechacierre = fechacierre;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechadesde", nullable = false, length = 29)
	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechahasta", nullable = false, length = 29)
	public Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idestado", nullable = false)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "iplog", length = 20)
	public String getIplog() {
		return iplog;
	}

	public void setIplog(String iplog) {
		this.iplog = iplog;
	}
	
	
}
