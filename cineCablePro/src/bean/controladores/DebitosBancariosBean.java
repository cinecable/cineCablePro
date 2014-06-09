package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bo.negocio.BancosBO;
import bo.negocio.DebitosbcoBO;
import bo.negocio.TiposDebitoBO;

import pojo.annotations.Bancos;
import pojo.annotations.Debitobco;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Tipodebito;
import pojo.annotations.Tipoentidad;
import pojo.annotations.custom.TipoCuenta;
import pojo.annotations.custom.TipoIdDoc;
import util.FacesUtil;
import util.MessageUtil;


@ManagedBean
@ViewScoped
public class DebitosBancariosBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5305115173781059059L;
	
	private List<Tipodebito> lisTipodebito;	
	private List<Bancos> lisBancos;	
	private List<Bancos> lisTarjetas;	
	private List<TipoCuenta> lisTipoCuenta;
	private List<TipoIdDoc> lisTipoIddoc;

	
	//private Tipodebito tipoDebitoSelected;	
	//private Bancos bancosSelected;	
	//private Bancos tarjetasSelected;	
	//private TipoCuenta tipoCuentaSelected;
	//private String codSeguridad;
	//private String propietario;
	//private String nroDocumento;
	private boolean reqBanco;
	
	private Debitobco debitobco;
	private int idcuenta;
	
	public DebitosBancariosBean() {
		lisTipodebito = new ArrayList<Tipodebito>();
		lisBancos = new ArrayList<Bancos>();
		lisTarjetas = new ArrayList<Bancos>();
		lisTipoCuenta = new ArrayList<TipoCuenta>();
		lisTipoIddoc = new ArrayList<TipoIdDoc>();
		
		debitobco = new Debitobco(0, new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null), 0, null, null, null, 0, null, 0, 0, null);
		
		//tipoDebitoSelected=new Tipodebito(0, null, 0, 0, null);
		
		//bancosSelected=new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null);
		
		//tarjetasSelected=new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null);
		
		//tipoCuentaSelected=new TipoCuenta(0, null);
		
		cargaTiposdb();
		cargaBcoTar();
		CargaTDoc();
	}
	
	@PostConstruct
	public void initDebitosBancariosBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");

		if (idcuenta > 0) {
			//consultarDebitoBancario();
		}
	}
	
	public void consultarDebitoBancario(){
		if(this.idcuenta > 0){
			try {
				DebitosbcoBO debitosbcoBO = new DebitosbcoBO();
				debitobco = debitosbcoBO.getDebitobcoByIdcuenta(idcuenta);
			} catch(Exception e) {
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Error!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
			}
		}
	}
	
	public void cargaTiposdb() {
		
		try{
			lisTipodebito = new ArrayList<Tipodebito>();
			
			// Inicializando Debitos Bancarios
			Tipodebito lisTDebb = new Tipodebito();
			lisTDebb.setIdtipodebito(0);
			lisTDebb.setNombre("Sel. Debito Bancario ...");
			
	        lisTipodebito = new ArrayList<Tipodebito>();
	        lisTipodebito.add(lisTDebb);  
		    						
           TiposDebitoBO tiposDebitoBO = new TiposDebitoBO();
           List<Tipodebito> lisTDeb = tiposDebitoBO.Tipodebitos();
          
           if(lisTDeb != null && lisTDeb.size() > 0){
        	   lisTipodebito.addAll(lisTDeb);
           }
       
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
	}
	
public void cargaBcoTar() {
	
	lisBancos = new ArrayList<Bancos>();
	lisTarjetas = new ArrayList<Bancos>();
	lisTipoCuenta=new ArrayList<TipoCuenta>();
	
	boolean continuar=false;
	String texto ="No habilitado" ;
	int idtipoentidad=0;
	reqBanco=false;
	

	
	/*if (!tipoDebitoSelected.equals(null)) {
		idtipoentidad=tipoDebitoSelected.getIdtipodebito();
		reqBanco=true;
	}*/
	idtipoentidad = debitobco.getIdtipodebito();
	reqBanco=true;
	
	if (idtipoentidad == 1) {
		//bancosSelected=new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null);
		debitobco.setBancos(new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null));
		continuar=true;
		reqBanco=true;
		
		texto="Sel.Banco ... ";	
	
		lisTipoCuenta.add(new TipoCuenta(0, "Sel.Tipo Cuenta"));
		lisTipoCuenta.add(new TipoCuenta(1, "Ahorros"));
		lisTipoCuenta.add(new TipoCuenta(2, "Corriente"));
	
		
	
		
	} else if (idtipoentidad == 2) {
		//tarjetasSelected=new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null);
		debitobco.setIdbancotar(0);
		continuar=true;
		texto="Sel.Tarjeta Credito ...";
	}
		try{
			
			
			// Inicializando Bancos
			Bancos lisTDebb = new Bancos();
			lisTDebb.setIdbanco(0);
			 
					if (continuar) {
						
						lisTDebb.setNombre("Sel.Banco");
						lisBancos.add(lisTDebb);  	
						
						BancosBO bancosBO = new BancosBO();
			           List<Bancos> lisTDeb = bancosBO.lisBancosByTipoEntidad(idtipoentidad);
			          
			           if(lisTDeb != null && lisTDeb.size() > 0){
			        	   lisBancos.addAll(lisTDeb);
			           }
				           
			        			           
				           if (idtipoentidad == 2) {
					       		//tarjetasSelected=new Bancos(0, new Estado(), new Tipoentidad(), new Empresa(), null, 0, null);
				        	   debitobco.setIdbancotar(0);
					       		texto="Sel.Emisor Tarjeta ...";
					       									
								// Inicializando Nodos
								Bancos lisTDebt = new Bancos();
								lisTDebt.setIdbanco(0);
								lisTDebt.setNombre(texto);
							
								lisTarjetas.add(lisTDebt);  
											
					           List<Bancos> lisTDebtr = bancosBO.lisBancosByTipoEntidad(1);
					          
					           if(lisTDebtr != null && lisTDebtr.size() > 0){
					        	   lisTarjetas.addAll(lisTDebtr);
					           }
					           lisTipoCuenta.add(new TipoCuenta(0, "No habilitado"));
				           } else{
				        	    Bancos lisTDebt2 = new Bancos();
								lisTDebt2.setIdbanco(0);
								lisTDebt2.setNombre(texto);
				        	    lisTDebt2.setNombre("No habilitado");
								lisTarjetas.add(lisTDebt2);  
								
					       }
					} else {
						
						lisTDebb.setNombre(texto);					
						lisBancos.add(lisTDebb); 
						
						lisTDebb.setNombre(texto);
						lisTarjetas.add(lisTDebb);  
						
						lisTipoCuenta.add(new TipoCuenta(0, "No habilitado"));
						
					}// fin de if (continuar)
					
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
	}	
	
	private void CargaTDoc() {
		lisTipoIddoc=new ArrayList<TipoIdDoc>();
		lisTipoIddoc.add(new TipoIdDoc(0, "Sel.Tipo Identidad"));
		lisTipoIddoc.add(new TipoIdDoc(1, "Cedula"));
		lisTipoIddoc.add(new TipoIdDoc(2, "RUC"));
		lisTipoIddoc.add(new TipoIdDoc(3, "Pasaporte"));
		lisTipoIddoc.add(new TipoIdDoc(4, "Otro Documento"));
	}
	
	public List<Tipodebito> getLisTipodebito() {
		return lisTipodebito;
	}
	public void setLisTipodebito(List<Tipodebito> lisTipodebito) {
		this.lisTipodebito = lisTipodebito;
	}
	/*public Tipodebito getTipoDebitoSelected() {
		return tipoDebitoSelected;
	}
	public void setTipoDebitoSelected(Tipodebito tipoDebitoSelected) {
		this.tipoDebitoSelected = tipoDebitoSelected;
	}*/
	public List<Bancos> getLisBancos() {
		return lisBancos;
	}
	public void setLisBancos(List<Bancos> lisBancos) {
		this.lisBancos = lisBancos;
	}
	public List<Bancos> getLisTarjetas() {
		return lisTarjetas;
	}
	public void setLisTarjetas(List<Bancos> lisTarjetas) {
		this.lisTarjetas = lisTarjetas;
	}
	/*public Bancos getBancosSelected() {
		return bancosSelected;
	}
	public void setBancosSelected(Bancos bancosSelected) {
		this.bancosSelected = bancosSelected;
	}
	public Bancos getTarjetasSelected() {
		return tarjetasSelected;
	}
	public void setTarjetasSelected(Bancos tarjetasSelected) {
		this.tarjetasSelected = tarjetasSelected;
	}*/

	public List<TipoCuenta> getLisTipoCuenta() {
		return lisTipoCuenta;
	}
	public void setLisTipoCuenta(List<TipoCuenta> lisTipoCuenta) {
		this.lisTipoCuenta = lisTipoCuenta;
	}
	
	/*public TipoCuenta getTipoCuentaSelected() {
		return tipoCuentaSelected;
	}
	public void setTipoCuentaSelected(TipoCuenta tipoCuentaSelected) {
		this.tipoCuentaSelected = tipoCuentaSelected;
	}
	public String getCodSeguridad() {
		return codSeguridad;
	}
	public void setCodSeguridad(String codSeguridad) {
		this.codSeguridad = codSeguridad;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}*/
	public boolean isReqBanco() {
		return reqBanco;
	}
	public void setReqBanco(boolean reqBanco) {
		this.reqBanco = reqBanco;
	}
	public List<TipoIdDoc> getLisTipoIddoc() {
		return lisTipoIddoc;
	}
	public void setLisTipoIddoc(List<TipoIdDoc> lisTipoIddoc) {
		this.lisTipoIddoc = lisTipoIddoc;
	}
	public Debitobco getDebitobco() {
		return debitobco;
	}
	public void setDebitobco(Debitobco debitobco) {
		this.debitobco = debitobco;
	}

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}	
}
