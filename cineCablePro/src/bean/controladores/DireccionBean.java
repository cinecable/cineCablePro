package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pojo.annotations.Calleprincipal;
import pojo.annotations.Callesecundaria;
import pojo.annotations.Ciudad;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;
import pojo.annotations.Edificio;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Nodos;
import pojo.annotations.Pais;
import pojo.annotations.Provincia;
import pojo.annotations.Referenciadir;
import pojo.annotations.Sector;
import pojo.annotations.Tiposector;
import pojo.annotations.Ubicacion;
import pojo.annotations.Usuario;
import util.FacesUtil;
import bo.negocio.CallePrincipalBO;
import bo.negocio.CallesecundariaBO;
import bo.negocio.CiudadBO;
import bo.negocio.EmpresaBO;
import bo.negocio.NodosBO;
import bo.negocio.PaisBO;
import bo.negocio.ProvinciaBO;
import bo.negocio.SectorBO;
import bo.negocio.EdificiosBO;
import bo.negocio.TipoSectorBO;
import bo.negocio.UbicacionBO;


@ManagedBean
@ViewScoped
public class DireccionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2139586034899293730L;
	
	private List<Pais> lisPais;
    private List<Provincia> lisProvincia;
    private List<Ciudad> lisCiudad;
	private List<Sector> lisSector;
    private List<Tiposector> lisTipoSector;
    private List<Edificio> lisEdificio;   
	private List<Calleprincipal> lisCallePrincipal;
    private List<Callesecundaria> lisCalleSecundaria;
    private List<Nodos> lisNodos;
    private List<Ubicacion> lisUbicacion;
    
    private Pais paisSelected;
    private Provincia provinciaSelected;
    private Ciudad ciudadSelected;
    private Direccion direccion;
     
    private boolean habilitaTipoSector;
   
	public DireccionBean() {
        paisSelected = new Pais();
        provinciaSelected = new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null);
        ciudadSelected = new Ciudad((int)0, new Estado(), new Usuario(), new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null), null, null);
        
        direccion = new Direccion(0, new Edificio(), new Referenciadir(), new Ctacliente(), new Calleprincipal(), new Tiposector(), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
        
        lisTipoSector = new ArrayList<Tiposector>();
        habilitaTipoSector = false;
        
        llenarxDefectoCombos();
    }
	
	public void llenarDependientes() {
		try{
			direccion.setSector(new Sector(0,new Estado(),new Ciudad(),new Usuario(), null, null));
			
            SectorBO sectorBO = new SectorBO();
            List<Sector> lisTSec = sectorBO.SectorxCiudad(ciudadSelected.getIdciudad());
            
            if(lisTSec != null && lisTSec.size() > 0){
                lisSector.addAll(lisTSec);
            }
                       
            SeteaTiposSectores(1);
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
	}
	
	public void llenarDependientesSector() {
		 SeteaTiposSectores(2);
		 CompletarCallesPrincipales("");
	}
	
	public void llenarDependientesTipoSector() {
		
		SeteaTiposSectores(3);
	}
	
	  public List<Calleprincipal> CompletarCallesPrincipales(String query) {  
		  
		  lisCallePrincipal = new ArrayList<Calleprincipal>();
	   
		  CallePrincipalBO calleprincipalBO = new CallePrincipalBO();
          List<Calleprincipal> lisCallP;
			try {				
						            
						         
				lisCallP = calleprincipalBO.ConsultarCPxQuery(direccion.getSector().getIdsector(),query);
				
				   if(lisCallP != null && lisCallP.size() > 0){
		            	lisCallePrincipal.addAll(lisCallP);
		            }
		            
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		       
		         return lisCallePrincipal;  
		      }  

 public List<Callesecundaria> CompletarCallesSecundarias(String query) {  
		  
		  lisCalleSecundaria = new ArrayList<Callesecundaria>();
	   
		  CallesecundariaBO CallesecundariaBO = new CallesecundariaBO();
          List<Callesecundaria> lisCallP;
			try {				
						            
						         
				lisCallP = CallesecundariaBO.ConsultarCSxQuery(direccion.getSector().getIdsector(),query);
				
				   if(lisCallP != null && lisCallP.size() > 0){
		            	lisCalleSecundaria.addAll(lisCallP);
		            }
		            
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		       
		         return lisCalleSecundaria;  
		      }  
 
	  
	private void SeteaTiposSectores(int codigo) {
		 //calleprincipalSelected = new Calleprincipal(0, new Estado(),new Usuario(), new Sector(), null, null);
		direccion.setCalleprincipal(new Calleprincipal(0, new Estado(),new Usuario(), new Sector(), null, null));
	     //callesecundariaSelected = new Callesecundaria(0,new  Estado(), new Usuario(), new Empresa(),null, null, null, new Sector());
		direccion.setCallesecundaria(new Callesecundaria(0,new  Estado(), new Usuario(), new Empresa(),null, null, null, new Sector()));
	     //ubicacionSelected = new Ubicacion(0, null,0, new Sector(),0,null);
		direccion.setUbicacion(new Ubicacion(0, null,0, new Sector(),0,null));
	     //edificioSelected = new Edificio(0,new Estado(),new Sector(), new Usuario(), null,null);
		direccion.setEdificio(new Edificio(0,new Estado(),new Sector(), new Usuario(), null,null));
	     //nodosSelected = new Nodos(0,null, 0,new Sector(), 0, null);
		direccion.setNodos(new Nodos(0,null, 0,new Sector(), 0, null));
	     
	   
			  // Inicializando Nodos
				Nodos nodos = new Nodos();
		        nodos.setIdnodo((int)0);
		        nodos.setNombre("Seleccione Nodo...");
		            
		        lisNodos = new ArrayList<Nodos>();
		        lisNodos.add(nodos);  
			    	
		  	 		// Inicializando Edificios
				Edificio edificio = new Edificio();
		        edificio.setIdedificio((int)0);
		        edificio.setNombre("Seleccione Edificio...");
		            
		        lisEdificio = new ArrayList<Edificio>();
		        lisEdificio.add(edificio);  
		        
		    				
				
				// Inicializando Ubicaciones
				Ubicacion ubicacion  = new Ubicacion();
				ubicacion.setIdubicacion((int)0);
				ubicacion.setNombre("Seleccione Urbanizacion...");			     
				lisUbicacion = new ArrayList<Ubicacion>();
			    lisUbicacion.add(ubicacion);  
			    
			    if (codigo==4) {
				     Sector sector = new Sector();
			         sector.setIdsector((int)0);
			         sector.setNombre("Seleccione Sector...");
			         
			         lisSector = new ArrayList<Sector>();
			         lisSector.add(sector);
		       }
				if (codigo !=3) {		
				 // tipo de sector
					 lisTipoSector = new ArrayList<Tiposector>();
					TipoSectorBO tiposectorBO = new TipoSectorBO();
		            List<Tiposector> lisTpoS;
		            
		           try {
						lisTpoS = tiposectorBO.ConsultarTsXTs(1);
						 if(lisTpoS != null && lisTpoS.size() > 0){
							 lisTipoSector.addAll(lisTpoS);
				            }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		          // tiposectorSelected.setIdtiposector(1);		           		     
				}//si codigo !=3
				
			  if (codigo==2 || codigo ==3)  {
				  if (codigo == 2) { //entra en caso de ser sector el clickeado
				   NodosBO nodosBO = new NodosBO();
			         List<Nodos> lisTNodos;
					try {
						//lisTNodos = nodosBO.ConsultaNodosxSector(sectorSelected.getIdsector());
						lisTNodos = nodosBO.ConsultaNodosxSector(direccion.getSector().getIdsector());
						
						if(lisTNodos != null && lisTNodos.size() > 0){
							lisNodos.addAll(lisTNodos);
			            }
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					EdificiosBO edificiosBO = new EdificiosBO();
		            List<Edificio> lisTmp;
		            
		          
					try {
						//lisTmp = edificiosBO.EdificioxSector(sectorSelected.getIdsector());
						lisTmp = edificiosBO.EdificioxSector(direccion.getSector().getIdsector());
						 if(lisTmp != null && lisTmp.size() > 0){
				                lisEdificio.addAll(lisTmp);
				            }
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	            					
	            }// si codigo == 2 				  
			  
				     
				    	 UbicacionBO ubicacionBO = new UbicacionBO();
				            List<Ubicacion> lisTUbicacion;
							try {
								lisTUbicacion = ubicacionBO.ConsultarUbicacionxSector(direccion.getSector().getIdsector());
								
								if(lisTUbicacion != null && lisTUbicacion.size() > 0){
									lisUbicacion.addAll(lisTUbicacion);
					            }
							} catch (Exception e) {
								e.printStackTrace();
							}
				   				     
			  } // si codigo == 2 || codigo == 3	        	        
	}
    private void llenarxDefectoCombos() {
    	
         UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
         int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
                  
		try {
					     
				int idCiudad=0;
				EmpresaBO EmpresaBO = new EmpresaBO();
	            List<Empresa> lisTmp = EmpresaBO.consultarEmpresasId(idempresa);
	            for( Empresa empresaReg : lisTmp){
	            	 idCiudad = empresaReg.getIdciudad();
	            }	           
            
	            int idprovincia=0;
	                     
	            lisCiudad = new ArrayList<Ciudad>();
	          //  lisCiudad.add(ciudad);
				 CiudadBO ciudadBO = new CiudadBO();
				 List<Ciudad> lisciu =  ciudadBO.consultarCiudadXCiudad(idCiudad);
				 for( Ciudad empresaReg : lisciu){
	            	 idprovincia = empresaReg.getProvincia().getIdprovincia();
	            }   
			 
			    int idpais=0;			  
	            
	            lisProvincia= new ArrayList<Provincia>();
	            //lisProvincia.add(provincia);
				 ProvinciaBO provinciaBO = new ProvinciaBO();
				 List<Provincia> lispro =  provinciaBO.consultarProvinciaPorProvincia(idprovincia);
				 for( Provincia empresaReg : lispro){
	            	  idpais = empresaReg.getPais().getIdpais();
	            }   
				 
			 
				 lisPais = new ArrayList<Pais>();
		           // lisPais.add(pais);
		            
	            PaisBO paisBO = new PaisBO();
	            List<Pais>lisTmpPais = paisBO.consultarPaises();
	            
	            if(lisTmpPais != null && lisTmpPais.size() > 0){
	                lisPais.addAll(lisTmpPais);
	            }
	            paisSelected.setIdpais(idpais);
	            
            
	           
               List<Provincia>lisTmpPro = provinciaBO.consultarProvinciaPorPais(idpais);
	            
	            if(lisTmpPro != null && lisTmpPro.size() > 0){
	                lisProvincia.addAll(lisTmpPro);
	            }
	            provinciaSelected.setIdprovincia(idprovincia);
	            
	            
	            List<Ciudad>lisTmpCiu = ciudadBO.consultarCiudadPorProvincia(idprovincia);
	            
	            if(lisTmpCiu != null && lisTmpCiu.size() > 0){
	                lisCiudad.addAll(lisTmpCiu);
	            }
	            ciudadSelected.setIdciudad(idCiudad);
	            	            
	            Sector sector = new Sector();
		        sector.setIdsector((int)0);
		        sector.setNombre("Seleccione Sector...");
		         
		        lisSector = new ArrayList<Sector>();
		        lisSector.add(sector);
		       
	            SectorBO sectorBO = new SectorBO();
	            List<Sector>lisTSector = sectorBO.SectorxCiudad(idCiudad);
	            
	            if(lisTSector != null && lisTSector.size() > 0){
	                lisSector.addAll(lisTSector);
	            }	           
	            
	            SeteaTiposSectores(1);
			            	            
		} catch (Exception e) {
			e.printStackTrace();
		}
         
    }
    
    public void llenarLisProvincia(){
        try{
            provinciaSelected = new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null);
            ciudadSelected = new Ciudad((int)0, new Estado(), new Usuario(), new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null), null, null);
            
            Provincia provincia = new Provincia();
            provincia.setIdprovincia(0);
            provincia.setNombre("Seleccione Provincia...");
            lisProvincia = new ArrayList<Provincia>();
            lisProvincia.add(provincia);
            
            Ciudad ciudad = new Ciudad();
            ciudad.setIdciudad((int)0);
            ciudad.setNombre("Seleccione Ciudad...");
            lisCiudad = new ArrayList<Ciudad>();
            lisCiudad.add(ciudad);
            
            ProvinciaBO provinciaBO = new ProvinciaBO();
            List<Provincia> lisTmp = provinciaBO.consultarProvinciaPorPais(paisSelected.getIdpais());
            
            if(lisTmp != null && lisTmp.size() > 0){
                lisProvincia.addAll(lisTmp);
            }
            
            SeteaTiposSectores(4);
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
   
    
    public void llenarLisCiudad(){
        try{
            ciudadSelected = new Ciudad((int)0, new Estado(), new Usuario(), new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null), null, null);
            
            Ciudad ciudad = new Ciudad();
            ciudad.setIdciudad((int)0);
            ciudad.setNombre("Seleccione Ciudad...");
            
            lisCiudad = new ArrayList<Ciudad>();
            lisCiudad.add(ciudad);
            
            CiudadBO ciudadBO = new CiudadBO();
            List<Ciudad> lisTmp = ciudadBO.consultarCiudadPorProvincia(provinciaSelected.getIdprovincia());
            
            if(lisTmp != null && lisTmp.size() > 0){
                lisCiudad.addAll(lisTmp);
                
                
                SeteaTiposSectores(4);    
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * @return the lisPais
     */
    public List<Pais> getLisPais() {
        return lisPais;
    }

    /**
     * @param lisPais the lisPais to set
     */
    public void setLisPais(List<Pais> lisPais) {
        this.lisPais = lisPais;
    }

    /**
     * @return the lisProvincia
     */
    public List<Provincia> getLisProvincia() {
        return lisProvincia;
    }

    /**
     * @param lisProvincia the lisProvincia to set
     */
    public void setLisProvincia(List<Provincia> lisProvincia) {
        this.lisProvincia = lisProvincia;
    }

    /**
     * @return the lisCiudad
     */
    public List<Ciudad> getLisCiudad() {
        return lisCiudad;
    }

    /**
     * @param lisCiudad the lisCiudad to set
     */
    public void setLisCiudad(List<Ciudad> lisCiudad) {
        this.lisCiudad = lisCiudad;
    }

    /**
     * @return the paisSelected
     */
    public Pais getPaisSelected() {
        return paisSelected;
    }

    /**
     * @param paisSelected the paisSelected to set
     */
    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    /**
     * @return the provinciaSelected
     */
    public Provincia getProvinciaSelected() {
        return provinciaSelected;
    }

    /**
     * @param provinciaSelected the provinciaSelected to set
     */
    public void setProvinciaSelected(Provincia provinciaSelected) {
        this.provinciaSelected = provinciaSelected;
    }

    /**
     * @return the ciudadSelected
     */
    public Ciudad getCiudadSelected() {
        return ciudadSelected;
    }

    /**
     * @param ciudadSelected the ciudadSelected to set
     */
    public void setCiudadSelected(Ciudad ciudadSelected) {
        this.ciudadSelected = ciudadSelected;
    }
    
    
    
    
    public List<Sector> getLisSector() {
		return lisSector;
	}

	public void setLisSector(List<Sector> lisSector) {
		this.lisSector = lisSector;
	}

	public List<Tiposector> getLisTipoSector() {
		return lisTipoSector;
	}

	public void setLisTipoSector(List<Tiposector> lisTipoSector) {
		this.lisTipoSector = lisTipoSector;
	}

	public List<Edificio> getLisEdificio() {
		return lisEdificio;
	}

	public void setLisEdificio(List<Edificio> lisEdificio) {
		this.lisEdificio = lisEdificio;
	}

	public List<Calleprincipal> getLisCallePrincipal() {
		return lisCallePrincipal;
	}

	public void setLisCallePrincipal(List<Calleprincipal> lisCallePrincipal) {
		this.lisCallePrincipal = lisCallePrincipal;
	}

	public List<Callesecundaria> getLisCalleSecundaria() {
		return lisCalleSecundaria;
	}

	public void setLisCalleSecundaria(List<Callesecundaria> lisCalleSecundaria) {
		this.lisCalleSecundaria = lisCalleSecundaria;
	}

	public List<Nodos> getLisNodos() {
		return lisNodos;
	}

	public void setLisNodos(List<Nodos> lisNodos) {
		this.lisNodos = lisNodos;
	}

	public List<Ubicacion> getLisUbicacion() {
		return lisUbicacion;
	}

	public void setLisUbicacion(List<Ubicacion> lisUbicacion) {
		this.lisUbicacion = lisUbicacion;
	}
	
	public boolean isHabilitaTipoSector() {
		return habilitaTipoSector;
	}

	public void setHabilitaTipoSector(boolean habilitaTipoSector) {
		this.habilitaTipoSector = habilitaTipoSector;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}
