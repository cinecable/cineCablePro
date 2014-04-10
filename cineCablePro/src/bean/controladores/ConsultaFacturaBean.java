package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import bo.negocio.DetimpfacturasBO;
import bo.negocio.FacturaBO;

import pojo.annotations.Detimpfacturas;
import pojo.annotations.Factura;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ConsultaFacturaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3021593751960029911L;

	//Ids por url
	private int idcuenta;
	
	//Otros
	private Date fechaDesde;
	private Date fechaHasta;
	private Factura facturaSeleccionada;
	private List<Factura> lisFacturaCliente;
	private LazyDataModel<Detimpfacturas> lisDetimpfacturas;
	
	public ConsultaFacturaBean() {
		fechaDesde = new Date();
		fechaHasta = new Date();
		facturaSeleccionada = new Factura();
		lisFacturaCliente = new ArrayList<Factura>(); 
		
		consultarDetalleFactura();
	}
	
	public void consultarFacturas(){
		if(validacionOk()){
			try{
				FacturaBO facturaBO = new FacturaBO();
				lisFacturaCliente = facturaBO.lisFacturaByFechas(idcuenta, fechaDesde, fechaHasta);
				
				if(lisFacturaCliente == null || lisFacturaCliente.size() == 0){
					facturaSeleccionada = new Factura();
					new MessageUtil().showInfoMessage("Sin datos", "No se han encontrado datos con los parámetros de consulta");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	@SuppressWarnings("serial")
	public void consultarDetalleFactura(){
        try
        {
        	lisDetimpfacturas = new LazyDataModel<Detimpfacturas>() {
				@Override
				 public List<Detimpfacturas> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<Detimpfacturas> data = new ArrayList<Detimpfacturas>();
				
				     try{
					     //Si no hay pago seleccionado que no consulte
					     if(facturaSeleccionada != null && facturaSeleccionada.getIdsecuencia() > 0){
					    	 DetimpfacturasBO detimpfacturasBO = new DetimpfacturasBO();
					         int args[] = {0};
					         data = detimpfacturasBO.lisDetimpfacturasByPage(facturaSeleccionada.getIdfactura(), pageSize, first, args);
					         this.setRowCount(args[0]);
					     }
				     }catch(Exception e){
				    	 e.printStackTrace();
				     }
				
				     return data;
				 }
                
                @Override
                public void setRowIndex(int rowIndex) {
                    /*
                     * The following is in ancestor (LazyDataModel):
                     * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                     */
                    if (rowIndex == -1 || getPageSize() == 0) {
                        super.setRowIndex(-1);
                    }
                    else {
                        super.setRowIndex(rowIndex % getPageSize());
                    }      
                }
             };
        }catch(Exception re){
            re.printStackTrace();
            new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
        }
        
    }
	
	private boolean validacionOk(){
		boolean ok = false;
		
		if(fechaDesde != null){
			if(fechaHasta != null){
				ok = true;
			}else{
				new MessageUtil().showInfoMessage("Campo requerido", "Favor ingrese la fecha hasta");
			}
		}else{
			new MessageUtil().showInfoMessage("Campo requerido", "Favor ingrese la fecha desde");
		}
		
		if(ok){
			if(fechaDesde.compareTo(fechaHasta) > 0){
				ok = false;
				new MessageUtil().showInfoMessage("Corregir", "La fecha desde no puede ser mayor que la fecha hasta");
			}
		}
		
		return ok;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public List<Factura> getLisFacturaCliente() {
		return lisFacturaCliente;
	}

	public void setLisFacturaCliente(List<Factura> lisFacturaCliente) {
		this.lisFacturaCliente = lisFacturaCliente;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public LazyDataModel<Detimpfacturas> getLisDetimpfacturas() {
		return lisDetimpfacturas;
	}

	public void setLisDetimpfacturas(LazyDataModel<Detimpfacturas> lisDetimpfacturas) {
		this.lisDetimpfacturas = lisDetimpfacturas;
	}
	
}
