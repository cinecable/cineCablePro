package net.cinecable.dm;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.cinecable.model.base.Ordenes;
import pojo.annotations.Calleprincipal;
import pojo.annotations.Callesecundaria;
import pojo.annotations.Ciudad;
import pojo.annotations.Clientes;
import pojo.annotations.Ctacliente;
import pojo.annotations.Ctasprod;
import pojo.annotations.Direccion;
import pojo.annotations.Edificio;
import pojo.annotations.Motivos;
import pojo.annotations.Nodos;
import pojo.annotations.Pais;
import pojo.annotations.Prodservicio;
import pojo.annotations.Provincia;
import pojo.annotations.Referenciadir;
import pojo.annotations.Sector;
import pojo.annotations.Tipooperacion;
import pojo.annotations.Tiposector;
import pojo.annotations.Ubicacion;

@ManagedBean(name="ingOrdenesDM")
@SessionScoped
public class ingOrdenesDM  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6294909945881750760L;
	
	private Clientes cliente;
	private String documento;
	private List<Ctacliente> listaCta;
	private List<Tipooperacion> tipoOperacion;
	private Ordenes orden;
	private List<Motivos> motivos;
	private List<Motivos> horarios;
	private Prodservicio prodServicio[];
	private List<Ctasprod>listaCtaProd;
	private List<Pais> listapais;
	private List<Provincia> listaprovincia;
	private List<Ciudad> listaciudad;
	private Pais pais;
	private Provincia provincia;
	private Ciudad ciudad;
	private Sector sector;
	private List<Sector> listaSector;
	private List<Tiposector> listaTipoSector;
	private Calleprincipal callePrincipal;
	private Callesecundaria calleSecundaria;
	private Ubicacion ubicacion;
	private Edificio edificio;
	private Nodos nodo;
	private Referenciadir referencia;	
	
	private List<Calleprincipal> listaPrincipal;
	private List<Callesecundaria> listaSecundaria;
	private List<Ubicacion> listaUbicacion;
	private List<Edificio> listaEdificio;
	private List<Nodos> listaNodos;
	
	private Direccion direccion;
	private List<Direccion> listadireccioes;
	
	public List<Motivos> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Motivos> horarios) {
		this.horarios = horarios;
	}

	public List<Direccion> getListadireccioes() {
		return listadireccioes;
	}

	public void setListadireccioes(List<Direccion> listadireccioes) {
		this.listadireccioes = listadireccioes;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Tiposector> getListaTipoSector() {
		return listaTipoSector;
	}

	public void setListaTipoSector(List<Tiposector> listaTipoSector) {
		this.listaTipoSector = listaTipoSector;
	}

	public List<Ubicacion> getListaUbicacion() {
		return listaUbicacion;
	}

	public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
		this.listaUbicacion = listaUbicacion;
	}

	public List<Calleprincipal> getListaPrincipal() {
		return listaPrincipal;
	}

	public void setListaPrincipal(List<Calleprincipal> listaPrincipal) {
		this.listaPrincipal = listaPrincipal;
	}

	public List<Callesecundaria> getListaSecundaria() {
		return listaSecundaria;
	}

	public void setListaSecundaria(List<Callesecundaria> listaSecundaria) {
		this.listaSecundaria = listaSecundaria;
	}

	public List<Edificio> getListaEdificio() {
		return listaEdificio;
	}

	public void setListaEdificio(List<Edificio> listaEdificio) {
		this.listaEdificio = listaEdificio;
	}

	public List<Nodos> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(List<Nodos> listaNodos) {
		this.listaNodos = listaNodos;
	}

	public Calleprincipal getCallePrincipal() {
		return callePrincipal;
	}

	public void setCallePrincipal(Calleprincipal callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public Callesecundaria getCalleSecundaria() {
		return calleSecundaria;
	}

	public void setCalleSecundaria(Callesecundaria calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Nodos getNodo() {
		return nodo;
	}

	public void setNodo(Nodos nodo) {
		this.nodo = nodo;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public List<Sector> getListaSector() {
		return listaSector;
	}

	public void setListaSector(List<Sector> listaSector) {
		this.listaSector = listaSector;
	}

	public List<Pais> getListapais() {
		return listapais;
	}

	public void setListapais(List<Pais> listapais) {
		this.listapais = listapais;
	}

	public List<Provincia> getListaprovincia() {
		return listaprovincia;
	}

	public void setListaprovincia(List<Provincia> listaprovincia) {
		this.listaprovincia = listaprovincia;
	}

	public List<Ciudad> getListaciudad() {
		return listaciudad;
	}

	public void setListaciudad(List<Ciudad> listaciudad) {
		this.listaciudad = listaciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Ctasprod> getListaCtaProd() {
		return listaCtaProd;
	}

	public void setListaCtaProd(List<Ctasprod> listaCtaProd) {
		this.listaCtaProd = listaCtaProd;
	}

	

	public Prodservicio[] getProdServicio() {
		return prodServicio;
	}

	public void setProdServicio(Prodservicio[] prodServicio) {
		this.prodServicio = prodServicio;
	}

	public List<Motivos> getMotivos() {
		return motivos;
	}

	public void setMotivos(List<Motivos> motivos) {
		this.motivos = motivos;
	}

	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

	public List<Tipooperacion> getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(List<Tipooperacion> tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public List<Ctacliente> getListaCta() {
		return listaCta;
	}

	public void setListaCta(List<Ctacliente> listaCta) {
		this.listaCta = listaCta;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Referenciadir getReferencia() {
		return referencia;
	}

	public void setReferencia(Referenciadir referencia) {
		this.referencia = referencia;
	}
	
	
}
