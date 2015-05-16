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

import bo.negocio.PagosBO;
import bo.negocio.UsuarioBO;

import pojo.annotations.Usuario;
import pojo.annotations.custom.IngresosCierreCaja;
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
	private LazyDataModel<IngresosCierreCaja> lisIngresosCierreCaja;
	private IngresosCierreCaja ingresosCierreCajaSelected;
	
	public CierreCajaBean() {
		consultarPagos();
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
	public void consultarPagos(){
        try
        {
            lisIngresosCierreCaja = new LazyDataModel<IngresosCierreCaja>() {
				@Override
				 public List<IngresosCierreCaja> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<IngresosCierreCaja> data = new ArrayList<IngresosCierreCaja>();
				
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

	public LazyDataModel<IngresosCierreCaja> getLisIngresosCierreCaja() {
		return lisIngresosCierreCaja;
	}

	public void setLisIngresosCierreCaja(
			LazyDataModel<IngresosCierreCaja> lisIngresosCierreCaja) {
		this.lisIngresosCierreCaja = lisIngresosCierreCaja;
	}

	public IngresosCierreCaja getIngresosCierreCajaSelected() {
		return ingresosCierreCajaSelected;
	}

	public void setIngresosCierreCajaSelected(
			IngresosCierreCaja ingresosCierreCajaSelected) {
		this.ingresosCierreCajaSelected = ingresosCierreCajaSelected;
	}
}
