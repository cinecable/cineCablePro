package bean.controladores;

import global.Parametro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import bo.negocio.EgresoBO;
import bo.negocio.PagosBO;
import bo.negocio.UsuarioBO;

import pojo.annotations.Usuario;
import pojo.annotations.custom.IngresosEgresosCierreCaja;
import util.FacesUtil;
import util.MessageUtil;

@ManagedBean
@ViewScoped
public class CierreCajaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8611511872812574728L;
	private List<Usuario> lisUsuario;
	private int idusuario;
	private Date fechaDesde;
	private Date fechaHasta;
	private boolean isconsultaejecutada;
	private LazyDataModel<IngresosEgresosCierreCaja> lisIngresosCierreCaja;
	private IngresosEgresosCierreCaja ingresosCierreCajaSelected;
	private LazyDataModel<IngresosEgresosCierreCaja> lisEgresosCierreCaja;
	private IngresosEgresosCierreCaja egresosCierreCajaSelected;
	
	public CierreCajaBean() {
		consultarIngresos();
	}

	@PostConstruct
	public void PostCierreCajaBean() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
			UsuarioBO usuarioBO = new UsuarioBO();
			if(usuarioBean.getUsuario().getPerfil() == Parametro.PERFIL_ADMINISTRADOR){
				lisUsuario = usuarioBO.lisUsuario();
			}else{
				lisUsuario = new ArrayList<Usuario>();
				lisUsuario.add(usuarioBean.getUsuario());
			}
		}catch(Exception e){
			e.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
		}
	}

	@SuppressWarnings("serial")
	public void consultarIngresos(){
        try
        {
            lisIngresosCierreCaja = new LazyDataModel<IngresosEgresosCierreCaja>() {
				@Override
				 public List<IngresosEgresosCierreCaja> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<IngresosEgresosCierreCaja> data = new ArrayList<IngresosEgresosCierreCaja>();
				
				     //Si no hay filtros que no consulte
				     if(isconsultaejecutada || fechaDesde != null || fechaHasta != null){
				         PagosBO pagosBO = new PagosBO();
				         //int args[] = {0};
				         data = pagosBO.lisIngresosCierreCaja(idusuario, fechaDesde, fechaHasta);
				         if(data != null && data.size() > 0){
				        	 this.setRowCount(data.size());
				         }
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
            new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
        }
        
    }
	
	@SuppressWarnings("serial")
	public void consultarEgresos(){
        try
        {
            lisEgresosCierreCaja = new LazyDataModel<IngresosEgresosCierreCaja>() {
				@Override
				 public List<IngresosEgresosCierreCaja> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<IngresosEgresosCierreCaja> data = new ArrayList<IngresosEgresosCierreCaja>();
				
				     //Si no hay filtros que no consulte
				     if(isconsultaejecutada || fechaDesde != null || fechaHasta != null){
				         EgresoBO egresoBO = new EgresoBO();
				         //int args[] = {0};
				         data = egresoBO.lisEgresosCierreCaja(idusuario, fechaDesde, fechaHasta);
				         if(data != null && data.size() > 0){
				        	 this.setRowCount(data.size());
				         }
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
            new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
        }
        
    }
	
	public List<Usuario> getLisUsuario() {
		return lisUsuario;
	}


	public void setLisUsuario(List<Usuario> lisUsuario) {
		this.lisUsuario = lisUsuario;
	}

	public int getIdusuario() {
		return idusuario;
	}



	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
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

	public boolean isIsconsultaejecutada() {
		return isconsultaejecutada;
	}

	public void setIsconsultaejecutada(boolean isconsultaejecutada) {
		this.isconsultaejecutada = isconsultaejecutada;
	}

	public LazyDataModel<IngresosEgresosCierreCaja> getLisIngresosCierreCaja() {
		return lisIngresosCierreCaja;
	}

	public void setLisIngresosCierreCaja(
			LazyDataModel<IngresosEgresosCierreCaja> lisIngresosCierreCaja) {
		this.lisIngresosCierreCaja = lisIngresosCierreCaja;
	}

	public IngresosEgresosCierreCaja getIngresosCierreCajaSelected() {
		return ingresosCierreCajaSelected;
	}

	public void setIngresosCierreCajaSelected(
			IngresosEgresosCierreCaja ingresosCierreCajaSelected) {
		this.ingresosCierreCajaSelected = ingresosCierreCajaSelected;
	}

	public LazyDataModel<IngresosEgresosCierreCaja> getLisEgresosCierreCaja() {
		return lisEgresosCierreCaja;
	}

	public void setLisEgresosCierreCaja(
			LazyDataModel<IngresosEgresosCierreCaja> lisEgresosCierreCaja) {
		this.lisEgresosCierreCaja = lisEgresosCierreCaja;
	}

	public IngresosEgresosCierreCaja getEgresosCierreCajaSelected() {
		return egresosCierreCajaSelected;
	}

	public void setEgresosCierreCajaSelected(
			IngresosEgresosCierreCaja egresosCierreCajaSelected) {
		this.egresosCierreCajaSelected = egresosCierreCajaSelected;
	}
}
