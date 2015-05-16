package bean.controladores;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import util.FacesUtil;
import util.MessageUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Calleprincipal;
import pojo.annotations.Callesecundaria;
import pojo.annotations.Ciudad;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;
import pojo.annotations.Edificio;
import pojo.annotations.Nodos;
import pojo.annotations.Pais;
import pojo.annotations.Provincia;
import pojo.annotations.Referenciadir;
import pojo.annotations.Sector;
import pojo.annotations.Tiposector;
import pojo.annotations.Ubicacion;
import pojo.annotations.Usuario;

import bo.negocio.CiudadBO;
import bo.negocio.DireccionBO;
import bo.negocio.PaisBO;
import bo.negocio.ProvinciaBO;
import bo.negocio.ReferenciadirBO;


@ManagedBean
@ViewScoped
public class ConsultaDirBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2741538934987793724L;
    private Direccion direccionI;
    private Direccion direccionC;
    private Direccion direccionB;
    private Direccion varDireccion;
    private Referenciadir referenciadir;
    private Referenciadir referenciadirI;
    private Referenciadir referenciadirB;
    private Referenciadir referenciadirC;
    private Ciudad ciudadI;
    private Ciudad ciudadC;
    private Ciudad ciudadB;
    private Provincia provinciaI;
    private Provincia provinciaC;
    public Ciudad getCiudadC() {
		return ciudadC;
	}

	public void setCiudadC(Ciudad ciudadC) {
		this.ciudadC = ciudadC;
	}

	public Ciudad getCiudadB() {
		return ciudadB;
	}

	public void setCiudadB(Ciudad ciudadB) {
		this.ciudadB = ciudadB;
	}

	public Provincia getProvinciaI() {
		return provinciaI;
	}

	public void setProvinciaI(Provincia provinciaI) {
		this.provinciaI = provinciaI;
	}

	public Provincia getProvinciaB() {
		return provinciaB;
	}

	public void setProvinciaB(Provincia provinciaB) {
		this.provinciaB = provinciaB;
	}

	public Pais getPaisI() {
		return paisI;
	}

	public void setPaisI(Pais paisI) {
		this.paisI = paisI;
	}

	public Pais getPaisC() {
		return paisC;
	}

	public void setPaisC(Pais paisC) {
		this.paisC = paisC;
	}

	public Pais getPaisB() {
		return paisB;
	}

	public void setPaisB(Pais paisB) {
		this.paisB = paisB;
	}

	private Provincia provinciaB;
    private Pais paisI;
    private Pais paisC;
    private Pais paisB;
    
	private int idcuenta;
	
	public ConsultaDirBean() {
		// TODO Auto-generated constructor stub
	  /*  setDireccionI(new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null));
	    setDireccionC(new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null));
	    setDireccionB(new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null));
*/
	    referenciadir = new Referenciadir();
	}
	
	@PostConstruct
	public void initConsultaDirBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");
		
		if(idcuenta > 0){
			 cargar_Direcciones(idcuenta,"I");
			 cargar_Direcciones(idcuenta,"C");
			 cargar_Direcciones(idcuenta,"B");
			 //direccionI.setIddireccion(9999);
		}
	}
	
	private void cargar_Direcciones(int idcuenta,String tipo) {
		// TODO Auto-generated method stub
		try{
			//Direccion  varDireccion = new Direccion();
			
			DireccionBO direccionBO = new DireccionBO();
			varDireccion = direccionBO.consultarDireccionPorCuentaTipo(idcuenta, tipo);
			
			if(varDireccion == null){
				varDireccion = new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
			}else{
				
			}
			
			if(varDireccion.getCalleprincipal() == null){
				varDireccion.setCalleprincipal(new Calleprincipal());					
			}
			
			if(varDireccion.getCallesecundaria() == null){
				varDireccion.setCallesecundaria(new Callesecundaria());
			}
			
			if(varDireccion.getUbicacion() == null){
				varDireccion.setUbicacion(new Ubicacion());
			}
			
			if(varDireccion.getEdificio() == null){
				varDireccion.setEdificio(new Edificio());
			}
			
			if(varDireccion.getCtacliente() == null){
				varDireccion.setCtacliente(new Ctacliente());
			}
			
			if(varDireccion.getTiposector() == null){
				varDireccion.setTiposector(new Tiposector());
			}
			
			if(varDireccion.getSector() == null){
				varDireccion.setSector(new Sector());
			}
			
			
			 CiudadBO ciudadBO = new CiudadBO();
			 ProvinciaBO provinciaBO = new ProvinciaBO();
			 PaisBO paisBO = new PaisBO();
			 
			 
			 ReferenciadirBO referenciadirBO = new ReferenciadirBO();
			
				
				
			if (tipo =="I") {
				direccionI = varDireccion.clonar();
				if (direccionI.getSector().getIdsector() >0) { 
					setCiudadI(ciudadBO.ciudadById(direccionI.getSector().getIdsector()));
				} else {
					setCiudadI(null);
				}
				if (ciudadI != null && ciudadI.getIdciudad() > 0) {
					setProvinciaI(provinciaBO.provinciaPorId(ciudadI.getIdciudad()));
				} else {
					setProvinciaI(null);
				}
				if (provinciaI != null && provinciaI.getIdprovincia()>0) {
					setPaisI(paisBO.consultaPaisPorId(provinciaI.getIdprovincia()));
				} else {
					setPaisI(null);
				}
				if (varDireccion.getIddireccion()>0) {
					setReferenciadirI(referenciadirBO.getReferenciadirByIdDireccion(varDireccion.getIddireccion()));
					if(referenciadirI == null){
						referenciadirI = new Referenciadir();		
					} 					
				} else {
					setReferenciadirI(null);
				}
			}
			if (tipo =="C") {
				direccionC = varDireccion.clonar();
				if (direccionC.getSector().getIdsector() >0) { 
					setCiudadC(ciudadBO.ciudadById(direccionC.getSector().getIdsector()));
				} else {
					setCiudadC(null);
				}
				if (ciudadC != null && ciudadC.getIdciudad() > 0) {
					setProvinciaC(provinciaBO.provinciaPorId(ciudadC.getIdciudad()));
				} else {
					setProvinciaC(null);
				}
				if (provinciaC != null && provinciaC.getIdprovincia()>0) {
					setPaisC(paisBO.consultaPaisPorId(provinciaC.getIdprovincia()));
				} else {
					setPaisC(null);
				}
				if (varDireccion.getIddireccion()>0) {
					setReferenciadirC(referenciadirBO.getReferenciadirByIdDireccion(varDireccion.getIddireccion()));
					if(referenciadirC == null){
						referenciadirC = new Referenciadir();		
					} 					
				} else {
					setReferenciadirC(null);
				}
			}
			if (tipo =="B") {
				direccionB = varDireccion.clonar();
				if (direccionB.getSector().getIdsector() >0) { 
					setCiudadB(ciudadBO.ciudadById(direccionI.getSector().getIdsector()));
				} else {
					setCiudadB(null);
				}
				if (ciudadB != null && ciudadB.getIdciudad() > 0) {
					setProvinciaB(provinciaBO.provinciaPorId(ciudadB.getIdciudad()));
				} else {
					setProvinciaB(null);
				}
				if (provinciaB != null && provinciaB.getIdprovincia()>0) {
					setPaisB(paisBO.consultaPaisPorId(provinciaI.getIdprovincia()));
				} else {
					setPaisB(null);
				}
				if (varDireccion.getIddireccion()>0) {
					setReferenciadirB(referenciadirBO.getReferenciadirByIdDireccion(varDireccion.getIddireccion()));
					if(referenciadirB == null){
						referenciadirB = new Referenciadir();		
					} 					
				} else {
					setReferenciadirB(null);
				}
			
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado, al consultar Direccion de cliente!", "");
		}
	}
	
	
	/**
	 * @return the direccion
	 */
	public Direccion getDireccionI() {
		return direccionI;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccionI(Direccion direccionI) {
		this.direccionI = direccionI;
	}
	/**
	 * @return the direccionC
	 */
	public Direccion getDireccionC() {
		return direccionC;
	}
	/**
	 * @param direccionC the direccionC to set
	 */
	public void setDireccionC(Direccion direccionC) {
		this.direccionC = direccionC;
	}
	/**
	 * @return the direccionB
	 */
	public Direccion getDireccionB() {
		return direccionB;
	}
	/**
	 * @param direccionB the direccionB to set
	 */
	public void setDireccionB(Direccion direccionB) {
		this.direccionB = direccionB;
	}
	
	 public Direccion getVarDireccion() {
			return varDireccion;
		}

		public void setVarDireccion(Direccion varDireccion) {
			this.varDireccion = varDireccion;
		}

		public Referenciadir getReferenciadir() {
			return referenciadir;
		}

		public void setReferenciadir(Referenciadir referenciadir) {
			this.referenciadir = referenciadir;
		}

		public int getIdcuenta() {
			return idcuenta;
		}

		public void setIdcuenta(int idcuenta) {
			this.idcuenta = idcuenta;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		/**
		 * @return the ciudadI
		 */
		public Ciudad getCiudadI() {
			return ciudadI;
		}

		/**
		 * @param ciudadI the ciudadI to set
		 */
		public void setCiudadI(Ciudad ciudadI) {
			this.ciudadI = ciudadI;
		}

		/**
		 * @return the provinciaC
		 */
		public Provincia getProvinciaC() {
			return provinciaC;
		}

		/**
		 * @param provinciaC the provinciaC to set
		 */
		public void setProvinciaC(Provincia provinciaC) {
			this.provinciaC = provinciaC;
		}

		public Referenciadir getReferenciadirI() {
			return referenciadirI;
		}

		public void setReferenciadirI(Referenciadir referenciadirI) {
			this.referenciadirI = referenciadirI;
		}

		public Referenciadir getReferenciadirB() {
			return referenciadirB;
		}

		public void setReferenciadirB(Referenciadir referenciadirB) {
			this.referenciadirB = referenciadirB;
		}

		public Referenciadir getReferenciadirC() {
			return referenciadirC;
		}

		public void setReferenciadirC(Referenciadir referenciadirC) {
			this.referenciadirC = referenciadirC;
		}
		
		
}
