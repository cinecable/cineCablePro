package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Producto generated by hbm2java
 */
@Entity
@Table(name = "producto", schema = "public")
public class Producto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5730089651665308297L;
	private int idproducto;
	private Estado estado;
	private Usuario usuario;
	private Empresa empresa;
	private String nombre;
	private String abreviado;
	private Date fecha;
	/*private Set<?> prodservicios = new HashSet<Object>(0);
	private Set<?> pagosants = new HashSet<Object>(0);
	private Set<?> ctasprods = new HashSet<Object>(0);*/
	private Costoproducto costoproducto;
	//private Set<?> promocioneses = new HashSet<Object>(0);
	private int jerarquia;
	private String tipojerarquia;

	public Producto() {
	}

	public Producto(int idproducto, Estado estado, Usuario usuario,
			Empresa empresa, String nombre, Date fecha) {
		this.idproducto = idproducto;
		this.estado = estado;
		this.usuario = usuario;
		this.empresa = empresa;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public Producto(int idproducto, Estado estado, Usuario usuario,
			Empresa empresa, String nombre, String abreviado, Date fecha,
			/*Set<?> prodservicios, Set<?> pagosants, Set<?> ctasprods,*/
			Costoproducto costoproducto/*, Set<?> promocioneses*/) {
		this.idproducto = idproducto;
		this.estado = estado;
		this.usuario = usuario;
		this.empresa = empresa;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.fecha = fecha;
		/*this.prodservicios = prodservicios;
		this.pagosants = pagosants;
		this.ctasprods = ctasprods;*/
		this.costoproducto = costoproducto;
		//this.promocioneses = promocioneses;
	}

	@Id
	@Column(name = "idproducto", unique = true, nullable = false)
	public int getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idestado", nullable = false)
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "nombre", nullable = false, length = 25)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "abreviado", length = 8)
	public String getAbreviado() {
		return this.abreviado;
	}

	public void setAbreviado(String abreviado) {
		this.abreviado = abreviado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<?> getProdservicios() {
		return this.prodservicios;
	}

	public void setProdservicios(Set<?> prodservicios) {
		this.prodservicios = prodservicios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<?> getPagosants() {
		return this.pagosants;
	}

	public void setPagosants(Set<?> pagosants) {
		this.pagosants = pagosants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<?> getCtasprods() {
		return this.ctasprods;
	}

	public void setCtasprods(Set<?> ctasprods) {
		this.ctasprods = ctasprods;
	}*/

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "producto")
	public Costoproducto getCostoproducto() {
		return this.costoproducto;
	}

	public void setCostoproducto(Costoproducto costoproducto) {
		this.costoproducto = costoproducto;
	}

	@Column(name = "jerarquia")
	public int getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(int jerarquia) {
		this.jerarquia = jerarquia;
	}

	@Column(name = "tipojerarquia", length = 50)
	public String getTipojerarquia() {
		return tipojerarquia;
	}

	public void setTipojerarquia(String tipojerarquia) {
		this.tipojerarquia = tipojerarquia;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<?> getPromocioneses() {
		return this.promocioneses;
	}

	public void setPromocioneses(Set<?> promocioneses) {
		this.promocioneses = promocioneses;
	}*/

}
