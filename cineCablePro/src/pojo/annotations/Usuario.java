package pojo.annotations;

// Generated 09/02/2014 10:20:13 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269189417225365369L;
	private int idusuario;
	private Empresa empresa;
	private String nombre;
	private String abreviado;
	private int idestado;
	private Short perfil;
	private String ip;
	private String ptovta;
	/*private Set<?> ciudads = new HashSet<Object>(0);
	private Set<?> modcronogramas = new HashSet<Object>(0);
	private Set<?> productos = new HashSet<Object>(0);
	private Set<?> pagoses = new HashSet<Object>(0);
	private Set<?> creditoses = new HashSet<Object>(0);
	private Set<?> edificios = new HashSet<Object>(0);
	private Set<?> cargoses = new HashSet<Object>(0);
	private Set<?> provincias = new HashSet<Object>(0);*/
	private Claves claves;
	/*private Set<?> tipoclientes = new HashSet<Object>(0);
	private Set<?> sectors = new HashSet<Object>(0);
	private Set<?> clienteses = new HashSet<Object>(0);
	private Set<?> calleprincipals = new HashSet<Object>(0);
	private Set<?> tiposectors = new HashSet<Object>(0);
	private Set<?> areas = new HashSet<Object>(0);
	private Set<?> callesecundarias = new HashSet<Object>(0);
	private Set<?> motivoses = new HashSet<Object>(0);*/

	public Usuario() {
	}

	public Usuario(int idusuario, Empresa empresa, String nombre, int idestado,
			String ptovta) {
		this.idusuario = idusuario;
		this.empresa = empresa;
		this.nombre = nombre;
		this.idestado = idestado;
		this.ptovta = ptovta;
	}

	public Usuario(int idusuario, Empresa empresa, String nombre,
			String abreviado, int idestado, Short perfil, String ip,
			String ptovta, /*Set<?> ciudads, Set<?> modcronogramas, Set<?> productos,
			Set<?> pagoses, Set<?> creditoses, Set<?> edificios, Set<?> cargoses,
			Set<?> provincias,*/ Claves claves/*, Set<?> tipoclientes, Set<?> sectors,
			Set<?> clienteses, Set<?> calleprincipals, Set<?> tiposectors, Set<?> areas,
			Set<?> callesecundarias, Set<?> motivoses*/) {
		this.idusuario = idusuario;
		this.empresa = empresa;
		this.nombre = nombre;
		this.abreviado = abreviado;
		this.idestado = idestado;
		this.perfil = perfil;
		this.ip = ip;
		this.ptovta = ptovta;
		/*this.ciudads = ciudads;
		this.modcronogramas = modcronogramas;
		this.productos = productos;
		this.pagoses = pagoses;
		this.creditoses = creditoses;
		this.edificios = edificios;
		this.cargoses = cargoses;
		this.provincias = provincias;*/
		this.claves = claves;
		/*this.tipoclientes = tipoclientes;
		this.sectors = sectors;
		this.clienteses = clienteses;
		this.calleprincipals = calleprincipals;
		this.tiposectors = tiposectors;
		this.areas = areas;
		this.callesecundarias = callesecundarias;
		this.motivoses = motivoses;*/
	}

	@Id
	@Column(name = "idusuario", unique = true, nullable = false)
	public int getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "nombre", nullable = false, length = 15)
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

	@Column(name = "idestado", nullable = false)
	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	@Column(name = "perfil")
	public Short getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Short perfil) {
		this.perfil = perfil;
	}

	@Column(name = "ip", length = 100)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "ptovta", nullable = false, length = 100)
	public String getPtovta() {
		return this.ptovta;
	}

	public void setPtovta(String ptovta) {
		this.ptovta = ptovta;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(Set<?> ciudads) {
		this.ciudads = ciudads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getModcronogramas() {
		return this.modcronogramas;
	}

	public void setModcronogramas(Set<?> modcronogramas) {
		this.modcronogramas = modcronogramas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<?> productos) {
		this.productos = productos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getPagoses() {
		return this.pagoses;
	}

	public void setPagoses(Set<?> pagoses) {
		this.pagoses = pagoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getCreditoses() {
		return this.creditoses;
	}

	public void setCreditoses(Set<?> creditoses) {
		this.creditoses = creditoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getEdificios() {
		return this.edificios;
	}

	public void setEdificios(Set<?> edificios) {
		this.edificios = edificios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getCargoses() {
		return this.cargoses;
	}

	public void setCargoses(Set<?> cargoses) {
		this.cargoses = cargoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Set<?> provincias) {
		this.provincias = provincias;
	}*/

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Claves getClaves() {
		return this.claves;
	}

	public void setClaves(Claves claves) {
		this.claves = claves;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getTipoclientes() {
		return this.tipoclientes;
	}

	public void setTipoclientes(Set<?> tipoclientes) {
		this.tipoclientes = tipoclientes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getSectors() {
		return this.sectors;
	}

	public void setSectors(Set<?> sectors) {
		this.sectors = sectors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getClienteses() {
		return this.clienteses;
	}

	public void setClienteses(Set<?> clienteses) {
		this.clienteses = clienteses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getCalleprincipals() {
		return this.calleprincipals;
	}

	public void setCalleprincipals(Set<?> calleprincipals) {
		this.calleprincipals = calleprincipals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getTiposectors() {
		return this.tiposectors;
	}

	public void setTiposectors(Set<?> tiposectors) {
		this.tiposectors = tiposectors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getAreas() {
		return this.areas;
	}

	public void setAreas(Set<?> areas) {
		this.areas = areas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getCallesecundarias() {
		return this.callesecundarias;
	}

	public void setCallesecundarias(Set<?> callesecundarias) {
		this.callesecundarias = callesecundarias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<?> getMotivoses() {
		return this.motivoses;
	}

	public void setMotivoses(Set<?> motivoses) {
		this.motivoses = motivoses;
	}*/

}
