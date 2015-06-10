package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import pojo.annotations.Ctacliente;
import pojo.annotations.custom.ConsultaCliente;
import util.FacesUtil;
import util.MessageUtil;
import bo.negocio.CtaclienteBO;

@ManagedBean
@ViewScoped
public class ClientesBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6551797159262046706L;
	private ConsultaCliente consultaClienteItem; 
    private LazyDataModel<Ctacliente> lisCtacliente;
    private LazyDataModel<ConsultaCliente> lisConsultaCliente;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String empresa;
    private String numeroIdentificacion;
    private String vineta;
    private String ctabancaria; 
    private String idcliente;
    private int idcuenta;
    private int key;

    public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public ClientesBean() {
		consultaClienteItem = new ConsultaCliente(null, null, null, null, null, null, 0, null, null, null);
        consultarCtaClientes();
    }
    
	public ConsultaCliente getConsultaClienteItem() {
		return consultaClienteItem;
	}

	public void setConsultaClienteItem(ConsultaCliente consultaClienteItem) {
		this.consultaClienteItem = consultaClienteItem;
	}

	public LazyDataModel<Ctacliente> getLisCtacliente() {
		return lisCtacliente;
	}

	public void setLisCtacliente(LazyDataModel<Ctacliente> lisCtacliente) {
		this.lisCtacliente = lisCtacliente;
	}

	public LazyDataModel<ConsultaCliente> getLisConsultaCliente() {
		return lisConsultaCliente;
	}

	public void setLisConsultaCliente(
			LazyDataModel<ConsultaCliente> lisConsultaCliente) {
		this.lisConsultaCliente = lisConsultaCliente;
	}

	@SuppressWarnings("serial")
	public void consultarCtaClientes(){
        try
        {
            lisConsultaCliente = new LazyDataModel<ConsultaCliente>() {
				@Override
				 public List<ConsultaCliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
				     List<ConsultaCliente> data = new ArrayList<ConsultaCliente>();
				
				     //Si no hay filtros que no consulte
				     if((nombre1 != null && nombre1.trim().length() > 0) || (nombre2 != null && nombre2.trim().length() > 0) || (apellido1 != null && apellido1.trim().length() > 0) || (apellido2 != null && apellido2.trim().length() > 0) || (numeroIdentificacion != null && numeroIdentificacion.trim().length() > 0) || (empresa != null && empresa.trim().length() > 0) || (vineta != null && vineta.trim().length() > 0)  || (ctabancaria != null && ctabancaria.trim().length() > 0) || (idcliente != null && idcliente.trim().length() > 0) || idcuenta > 0){
				         CtaclienteBO ctaclienteBO = new CtaclienteBO();
				         int args[] = {0};
				         data = ctaclienteBO.lisConsultaClienteByPage(nombre1, nombre2, apellido1, apellido2, numeroIdentificacion, empresa, vineta, ctabancaria, idcliente, idcuenta, pageSize, first, args);
				         this.setRowCount(args[0]);
				     }else{
				    	 this.setRowCount(0);
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
    public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getVineta() {
		return vineta;
	}

	public void setVineta(String vineta) {
		this.vineta = vineta;
	}

	public String getCtabancaria() {
		return ctabancaria;
	}

	public void setCtabancaria(String ctabancaria) {
		this.ctabancaria = ctabancaria;
	}

	public void onRowSelect(SelectEvent event){
		FacesUtil facesUtil = new FacesUtil();
		try {
			facesUtil.redirect("cliente.jsf?faces-redirect=true&idcuenta="+consultaClienteItem.getIdcuenta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}
	
}
