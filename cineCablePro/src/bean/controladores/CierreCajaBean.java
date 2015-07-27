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

import bo.negocio.CierreCajaBO;
import bo.negocio.EgresoBO;
import bo.negocio.PagosBO;
import bo.negocio.SaldoscierreBO;
import bo.negocio.UsuarioBO;

import pojo.annotations.Saldoscierre;
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
	private List<IngresosEgresosCierreCaja> lisIngresosCierreCajaTmp;
	private List<IngresosEgresosCierreCaja> lisEgresosCierreCajaTmp;
	private float totalIngresos;
	private float totalEgresos;
	private float saldoInicial;
	boolean tienesaldoinicial;
	Saldoscierre saldoscierre;
	private boolean haydata;
	
	public CierreCajaBean() {
		totalIngresos = 0;
		totalEgresos = 0;
		saldoInicial = 0;
		
		saldoscierre = new Saldoscierre();
		
		lisIngresosCierreCajaTmp = new ArrayList<IngresosEgresosCierreCaja>();
		lisEgresosCierreCajaTmp = new ArrayList<IngresosEgresosCierreCaja>();
		
		consultarIngresos();
		consultarEgresos();
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
					lisIngresosCierreCajaTmp = new ArrayList<IngresosEgresosCierreCaja>();
				
				     //Si no hay filtros que no consulte
				     if(isconsultaejecutada && (fechaDesde != null && fechaHasta != null) ){
				         PagosBO pagosBO = new PagosBO();
				         //int args[] = {0};
				         lisIngresosCierreCajaTmp = pagosBO.lisIngresosCierreCaja(idusuario, fechaDesde, fechaHasta);
				         if(lisIngresosCierreCajaTmp != null && lisIngresosCierreCajaTmp.size() > 0){
				        	 this.setRowCount(lisIngresosCierreCajaTmp.size());
				        	 
				        	 haydata = true;
				        	 totalIngresos = obtenerTotal(lisIngresosCierreCajaTmp);
				         }
				     }
				
				     return lisIngresosCierreCajaTmp;
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
					lisEgresosCierreCajaTmp = new ArrayList<IngresosEgresosCierreCaja>();
				
				     //Si no hay filtros que no consulte
					 if(isconsultaejecutada && (fechaDesde != null && fechaHasta != null) ){
				         EgresoBO egresoBO = new EgresoBO();
				         //int args[] = {0};
				         lisEgresosCierreCajaTmp = egresoBO.lisEgresosCierreCaja(idusuario, fechaDesde, fechaHasta);
				         if(lisEgresosCierreCajaTmp != null && lisEgresosCierreCajaTmp.size() > 0){
				        	 this.setRowCount(lisEgresosCierreCajaTmp.size());
				        	 
				        	 haydata = true;
				        	 totalEgresos = obtenerTotal(lisEgresosCierreCajaTmp);
				         }
				     }
				
				     return lisEgresosCierreCajaTmp;
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
	
	protected float obtenerTotal(List<IngresosEgresosCierreCaja> data) {
		float total = 0;
		
		for(IngresosEgresosCierreCaja ingresoEgreso : data){
			total += ingresoEgreso.getValpago();
		}
		
		return total;
	}
	
	public void consultarSaldoInicial(){
		try
        {
			//obtener el saldo inicial/anterior
			SaldoscierreBO saldoscierreBO = new SaldoscierreBO();
			saldoscierre = saldoscierreBO.getByIdUsuario(idusuario);
			
			if(saldoscierre != null){
				saldoInicial = saldoscierre.getSaldo();
				tienesaldoinicial = true;
			}else{
				saldoInicial = 0f;
				tienesaldoinicial = false;
			}
        }catch(Exception re){
            re.printStackTrace();
            new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
        }
	}

	public void grabar(){
		if(validacionOk()){
			try{
				CierreCajaBO cierreCajaBO = new CierreCajaBO();
				
				boolean ok = false;
				
				ok = cierreCajaBO.procesoCierreCaja(idusuario, fechaDesde, fechaHasta, tienesaldoinicial, saldoscierre, totalIngresos, totalEgresos, lisIngresosCierreCajaTmp, lisEgresosCierreCajaTmp);
				
				if(ok){
					new MessageUtil().showInfoMessage("Cierre de Caja efecutado con Exito!", "");
					/*FacesUtil facesUtil = new FacesUtil();
					try {
						facesUtil.redirect("cierre.jsf?faces-redirect=true");
					} catch (Exception e) {
						e.printStackTrace();
					}*/
				}else{
					new MessageUtil().showWarnMessage("No se ha efectuado el cierre", "");
				}
			} catch(Exception re) {
				re.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
	}
	
	private boolean validacionOk(){
		boolean ok = false;
		
		if(this.idusuario > 0){
			if(this.fechaDesde != null){
				if(this.fechaHasta != null){
					ok = true;
				}else{
					new MessageUtil().showWarnMessage("Ingrese Fecha Desde", null);
				}
			}
			else{
				new MessageUtil().showWarnMessage("Ingrese Fecha Hasta", null);
			}
		}else{
			new MessageUtil().showWarnMessage("Seleccionar Cajero", null);
		}
		
		return ok;
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

	public float getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(float totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public float getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(float totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public float getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(float saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public boolean isHaydata() {
		return haydata;
	}

	public void setHaydata(boolean haydata) {
		this.haydata = haydata;
	}
}
