package net.cinecable.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pojo.annotations.Empresa;

/**
 * 
 * @author Rafael
 * 
 */

@Entity
@Table(name = "pardebitos", schema = "public")
public class ParametroDatosBanco {

	private int idpardebitos;
	private ParametrosBancos parametrosBanco;
	private int lineaDesde;
	private int columna;
	private int longitudDesde;
	private int longitudHasta;
	private int estado = 1;
	private Date fecha;
	private Empresa empresa;
	private int tipoParametro;
	private int orden;
	private String titulocolumna;

	public static final transient int VALORDEBITO = 1;
	public static final transient int REFERENCIA = 2;
	public static final transient int ESTADO = 3;
	public static final transient int TIPO_CUENTA = 4;
	public static final transient int OBSERVACION_TRANSACCION = 5;
	public static final transient int CLIENTE_NOMBRE = 6;
	public static final transient int CUENTA_CLIENTE = 7;
	public static final transient int CUENTA_SEGURIDAD = 8;
	public static final transient int ID_UNICO = 9;
	public static final transient int FECHA_VENCIMIENTO = 10;
	public static final transient int LOCALIDAD =11;
	public static final transient int OCP=12;
	public static final transient int CODSERV =13;
	
	
	

	@Id
	@Column(name = "idpardebitos", unique = true, nullable = false)
	public int getIdpardebitos() {
		return idpardebitos;
	}

	public void setIdpardebitos(int idpardebitos) {
		this.idpardebitos = idpardebitos;
	}

	@Column(name = "filadesde")
	public int getLineaDesde() {
		return lineaDesde;
	}

	public void setLineaDesde(int lineaDesde) {
		this.lineaDesde = lineaDesde;
	}

	@Column(name = "estado", nullable = false)
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

	@Column(name = "columna", nullable = false)
	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Column(name = "longitud_desde")
	public int getLongitudDesde() {
		return longitudDesde;
	}

	public void setLongitudDesde(int longitudDesde) {
		this.longitudDesde = longitudDesde;
	}

	@Column(name = "longitud_hasta")
	public int getLongitudHasta() {
		return longitudHasta;
	}

	public void setLongitudHasta(int longitudHasta) {
		this.longitudHasta = longitudHasta;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idempresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "tipoparametro")
	public int getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(int tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	@ManyToOne
	@JoinColumn(name = "idpardebitosbancos", nullable = false)
	public ParametrosBancos getParametrosBanco() {
		return parametrosBanco;
	}

	public void setParametrosBanco(ParametrosBancos parametrosBanco) {
		this.parametrosBanco = parametrosBanco;
	}

	@Column(name = "orden")
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Column(name = "titulocolumna", length = 150)
	public String getTitulocolumna() {
		return titulocolumna;
	}

	public void setTitulocolumna(String titulocolumna) {
		this.titulocolumna = titulocolumna;
	}

}
