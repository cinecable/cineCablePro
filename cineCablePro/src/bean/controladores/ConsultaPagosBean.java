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

import bo.negocio.PagosBO;
import bo.negocio.TpagosBO;

import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Pagos;
import pojo.annotations.Tpagos;
import pojo.annotations.Usuario;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class ConsultaPagosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6307941361319846309L;

	//Ids por url
	private int idcuenta;
	
	//Otros
	private Date fechaDesde;
	private Date fechaHasta;
	private Pagos pagoSeleccionado;
	private List<Pagos> lisPagos;
	private LazyDataModel<Tpagos> lisTpagos;
	private boolean print;
	
	public ConsultaPagosBean() {
		fechaDesde = new Date();
		fechaHasta = new Date();
		pagoSeleccionado = new Pagos(0, new Estado(), new Usuario(), new Empresa(), 0, 0, null, 0);
		lisPagos = new ArrayList<Pagos>();
		print = false;
		
		consultarDetallePagos();
	}
	
	public void consultarPagos(){
		if(validacionOk()){
			try{
				PagosBO pagosBO = new PagosBO();
				lisPagos = pagosBO.lisPagosByFechas(idcuenta, fechaDesde, fechaHasta);
				
				if(lisPagos == null || lisPagos.size() == 0){
					pagoSeleccionado = new Pagos(0, new Estado(), new Usuario(), new Empresa(), 0, 0, null, 0);
					new MessageUtil().showInfoMessage("Sin datos", "No se han encontrado datos con los parámetros de consulta");
				}
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	@SuppressWarnings("serial")
	public void consultarDetallePagos(){
        try
        {
            lisTpagos = new LazyDataModel<Tpagos>() {
				@Override
				 public List<Tpagos> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<Tpagos> data = new ArrayList<Tpagos>();
				
				     //Si no hay pago seleccionado que no consulte
				     if(pagoSeleccionado != null && pagoSeleccionado.getIdpago() > 0){
				    	 TpagosBO tpagosBO = new TpagosBO();
				         int args[] = {0};
				         data = tpagosBO.lisTpagosByPage(pagoSeleccionado.getIdpago(), pageSize, first, args);
				         this.setRowCount(args[0]);
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

	public Pagos getPagoSeleccionado() {
		return pagoSeleccionado;
	}

	public void setPagoSeleccionado(Pagos pagoSeleccionado) {
		this.pagoSeleccionado = pagoSeleccionado;
	}

	public List<Pagos> getLisPagos() {
		return lisPagos;
	}

	public void setLisPagos(List<Pagos> lisPagos) {
		this.lisPagos = lisPagos;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public LazyDataModel<Tpagos> getLisTpagos() {
		return lisTpagos;
	}

	public void setLisTpagos(LazyDataModel<Tpagos> lisTpagos) {
		this.lisTpagos = lisTpagos;
	}

	public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}


}
