package net.cinecable.dm;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.cinecable.enums.FileType;
import net.cinecable.model.base.GeneracionDebitos;
import net.cinecable.model.extension.DebitosDetalle;
import net.cinecable.model.extension.FacturasDebito;

import org.primefaces.model.StreamedContent;

import pojo.annotations.Bancos;
import pojo.annotations.Pagos;
import pojo.annotations.custom.DatosImport;
import util.MessageUtil;

@ManagedBean(name = "debitoBancarioDM")
@ViewScoped
public class DebitoBancarioDM implements Serializable {

	private static final long serialVersionUID = 0xAC;
	private InputStream rawFile = null;
	private boolean seleccionTodos = true;
	private StreamedContent fileDown;
	private Date fechaActual = Calendar.getInstance().getTime();
	private FileType formatting = FileType.TEXT;
	private Bancos banco;
	private Bancos bancoGeneracion;
	private String archivoSubido;
	private String archivoDescarga;
	private List<FacturasDebito> debitosClientes;
	private GeneracionDebitos debitos;
	private List<Pagos> pagos;
	private boolean descargaTodo=true;
	
	private FacturasDebito facturasDebitoSelected;
	private Float totalGeneralExport;
	private Float totalGeneralImport;
	private List<DebitosDetalle> lisDebitosDetalle;
	private String tituloImport1;
	private String tituloImport2;
	private String tituloImport3;
	private String tituloImport4;
	private String tituloImport5;
	private List<DatosImport> lisDatosImport;
	private boolean tablaActiva;

	public DebitoBancarioDM() {
		debitosClientes = new ArrayList<FacturasDebito>();
		pagos = new ArrayList<Pagos>();
		totalGeneralExport = 0f;
		totalGeneralImport = 0f;
		tituloImport1 = "Título 1";
		tituloImport2 = "Título 2";
		tituloImport3 = "Título 3";
		tituloImport4 = "Título 4";
		tituloImport5 = "Título 5";
		tablaActiva = true;
	}
	
	public void quitarPago(){
		try {
			debitosClientes.remove(facturasDebitoSelected);
			
			calcularTotalGeneralExport();
			
			new MessageUtil().showInfoMessage("Listo!", "Registro excluido!");
		} catch(Exception re) {
			re.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!","");
		}
	}
	
	public void calcularTotalGeneralExport() {
		Float total = 0f;
		
		for(FacturasDebito facturasDebito : debitosClientes){
			if(facturasDebito.getFactura() != null && facturasDebito.getFactura().getValpendiente() != null){
				total += facturasDebito.getFactura().getValpendiente();
			}
		}
		
		totalGeneralExport = total;
	}
	
	public boolean isDescargaTodo() {
		return descargaTodo;
	}

	public void setDescargaTodo(boolean descargaTodo) {
		this.descargaTodo = descargaTodo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Pagos> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pagos> pagos) {
		this.pagos = pagos;
	}

	public GeneracionDebitos getDebitos() {
		return debitos;
	}

	public void setDebitos(GeneracionDebitos debitos) {
		this.debitos = debitos;
	}

	public Bancos getBancoGeneracion() {
		return bancoGeneracion;
	}

	public void setBancoGeneracion(Bancos bancoGeneracion) {
		this.bancoGeneracion = bancoGeneracion;
	}

	public List<FacturasDebito> getDebitosClientes() {
		return debitosClientes;
	}

	public void setDebitosClientes(List<FacturasDebito> debitosClientes) {
		this.debitosClientes = debitosClientes;
	}

	public String getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(String archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public Bancos getBanco() {
		return banco;
	}

	public void setBanco(Bancos banco) {
		this.banco = banco;
	}

	public boolean isSeleccionTodos() {
		this.banco = null;
		return seleccionTodos;
	}

	public void setSeleccionTodos(boolean seleccionTodos) {
		this.seleccionTodos = seleccionTodos;
	}

	public StreamedContent getFileDown() {
		return fileDown;
	}

	public FileType getFormatting() {
		return formatting;
	}

	public void setFormatting(FileType formatting) {
		this.formatting = formatting;
	}

	public InputStream getRawFile() {
		return rawFile;
	}

	public void setRawFile(InputStream rawFile) {
		this.rawFile = rawFile;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public void setFileDown(StreamedContent fileDown) {
		this.fileDown = fileDown;
	}

	public String getArchivoDescarga() {
		return archivoDescarga;
	}

	public void setArchivoDescarga(String archivoDescarga) {
		this.archivoDescarga = archivoDescarga;
	}

	public FacturasDebito getFacturasDebitoSelected() {
		return facturasDebitoSelected;
	}

	public void setFacturasDebitoSelected(FacturasDebito facturasDebitoSelected) {
		this.facturasDebitoSelected = facturasDebitoSelected;
	}
	
	public Float getTotalGeneralExport() {
		return totalGeneralExport;
	}

	public void setTotalGeneralExport(Float totalGeneralExport) {
		this.totalGeneralExport = totalGeneralExport;
	}

	public Float getTotalGeneralImport() {
		return totalGeneralImport;
	}

	public void setTotalGeneralImport(Float totalGeneral) {
		this.totalGeneralImport = totalGeneral;
	}

	public List<DebitosDetalle> getLisDebitosDetalle() {
		return lisDebitosDetalle;
	}

	public void setLisDebitosDetalle(List<DebitosDetalle> lisDebitosDetalle) {
		this.lisDebitosDetalle = lisDebitosDetalle;
	}

	public String getTituloImport1() {
		return tituloImport1;
	}

	public void setTituloImport1(String headerImport1) {
		this.tituloImport1 = headerImport1;
	}

	public String getTituloImport2() {
		return tituloImport2;
	}

	public void setTituloImport2(String headerImport2) {
		this.tituloImport2 = headerImport2;
	}

	public String getTituloImport3() {
		return tituloImport3;
	}

	public void setTituloImport3(String headerImport3) {
		this.tituloImport3 = headerImport3;
	}

	public String getTituloImport4() {
		return tituloImport4;
	}

	public void setTituloImport4(String headerImport4) {
		this.tituloImport4 = headerImport4;
	}

	public String getTituloImport5() {
		return tituloImport5;
	}

	public void setTituloImport5(String headerImport5) {
		this.tituloImport5 = headerImport5;
	}

	public List<DatosImport> getLisDatosImport() {
		return lisDatosImport;
	}

	public void setLisDatosImport(List<DatosImport> lisDatosImport) {
		this.lisDatosImport = lisDatosImport;
	}

	public boolean isTablaActiva() {
		return tablaActiva;
	}

	public void setTablaActiva(boolean tablaActiva) {
		this.tablaActiva = tablaActiva;
	}

}
