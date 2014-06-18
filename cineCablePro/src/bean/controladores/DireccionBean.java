package bean.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
import util.MessageUtil;
import bo.negocio.CallePrincipalBO;
import bo.negocio.CallesecundariaBO;
import bo.negocio.CiudadBO;
import bo.negocio.DireccionBO;
import bo.negocio.EmpresaBO;
import bo.negocio.NodosBO;
import bo.negocio.PaisBO;
import bo.negocio.ProvinciaBO;
import bo.negocio.ReferenciadirBO;
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
    private Direccion direccionClon;
    private Referenciadir referenciadir;
    private Referenciadir referenciadirClon;
     
    private boolean habilitaTipoSector;
    private int idcuenta;
    private String tipo;
   
	public DireccionBean() {
		lisPais = new ArrayList<Pais>();
		lisProvincia = new ArrayList<Provincia>();
		lisCiudad = new ArrayList<Ciudad>();
		lisSector = new ArrayList<Sector>();
		lisTipoSector = new ArrayList<Tiposector>();
		lisEdificio = new ArrayList<Edificio>();
		lisCallePrincipal = new ArrayList<Calleprincipal>();
		lisCalleSecundaria = new ArrayList<Callesecundaria>();
		lisNodos = new ArrayList<Nodos>();
		lisUbicacion = new ArrayList<Ubicacion>();
		
        paisSelected = new Pais();
        provinciaSelected = new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null);
        ciudadSelected = new Ciudad((int)0, new Estado(), new Usuario(), new Provincia(0, new Pais(), new Usuario(), null, (int) 0, null), null, null);
        direccion = new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
        direccionClon = new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
        referenciadir = new Referenciadir();
        referenciadirClon = new Referenciadir();
        
        habilitaTipoSector = false;
        tipo = "";
        
        llenarxDefectoCombos();
    }
	
	@PostConstruct
	public void initDireccionBean() {
		FacesUtil facesUtil = new FacesUtil();
		idcuenta = Integer
				.parseInt(facesUtil.getParametroUrl("idcuenta") != null ? facesUtil
						.getParametroUrl("idcuenta").toString() : "0");
		
		if(idcuenta > 0){
		}
	}
	
	//consultar data de la tabla de direcciones
	//List<Direccion> lisDireccion = direccionBO.consultarDireccionesPorCuenta(idcuenta);
	//llenar combo pais y setear el selected
	//llenar el combo provincia segun pais y setear el selected
	//llenar el combo ciudad segun provincia y setear el selected
	//llenar el combo sector segun ciudad y setear el selected
	//llenar el combo tipo sector y setear el seleccionado
	//calle principal setear el consultado
	//calle secundaria setear el consultado
	//ubicacion consultar
	//edificio consultar
	//referenciadir consultar
	public void consultarDireccionPorTipo(int idcuenta, String tipo){
		try{
			DireccionBO direccionBO = new DireccionBO();
			direccion = direccionBO.consultarDireccionPorCuentaTipo(idcuenta, tipo);
			
			if(direccion == null){
				direccion = new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
				direccionClon = new Direccion(0, new Edificio(), new Ctacliente(), new Calleprincipal(), new Tiposector(0, new Usuario(), null, null, null), new Callesecundaria(), new Ubicacion(), new Nodos(), new Sector(), 0, 0, 0, null, null, null, null, null);
			}else{
				direccionClon = direccion.clonar();
			}
			
			if(direccion.getCalleprincipal() == null){
				direccion.setCalleprincipal(new Calleprincipal());
				direccionClon.setCalleprincipal(new Calleprincipal());
			}else{
				direccionClon.setCalleprincipal(direccion.getCalleprincipal().clonar());
			}
			
			if(direccion.getCallesecundaria() == null){
				direccion.setCallesecundaria(new Callesecundaria());
				direccionClon.setCallesecundaria(new Callesecundaria());
			}else{
				direccionClon.setCallesecundaria(direccion.getCallesecundaria().clonar());
			}
			
			if(direccion.getUbicacion() == null){
				direccion.setUbicacion(new Ubicacion());
				direccionClon.setUbicacion(new Ubicacion());
			}else{
				direccionClon.setUbicacion(direccion.getUbicacion().clonar());
			}
			
			if(direccion.getEdificio() == null){
				direccion.setEdificio(new Edificio());
				direccionClon.setEdificio(new Edificio());
			}else{
				direccionClon.setEdificio(direccion.getEdificio().clonar());
			}
			
			if(direccion.getCtacliente() == null){
				direccion.setCtacliente(new Ctacliente());
				direccionClon.setCtacliente(new Ctacliente());
			}else{
				direccionClon.setCtacliente(direccion.getCtacliente().clonar());
			}
			
			if(direccion.getTiposector() == null){
				direccion.setTiposector(new Tiposector());
				direccionClon.setTiposector(new Tiposector());
			}else{
				direccionClon.setTiposector(direccion.getTiposector().clonar());
			}
			
			if(direccion.getSector() == null){
				direccion.setSector(new Sector());
				direccionClon.setSector(new Sector());
			}else{
				direccionClon.setSector(direccion.getSector());
			}
			
			ReferenciadirBO referenciadirBO = new ReferenciadirBO();
			referenciadir = referenciadirBO.getReferenciadirByIdDireccion(direccion.getIddireccion());
			
			if(referenciadir == null){
				referenciadir = new Referenciadir();
				referenciadirClon = new Referenciadir();
			}else{
				referenciadirClon = referenciadir.clonar();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
		}
	}
	
	public void consultarSectoresCiudadesProvinciasPaises(){
		if(direccion != null && direccion.getIddireccion() > 0){
			try{
				//Sectores
				Sector sector = new Sector();
		        sector.setIdsector(0);
		        sector.setNombre("Seleccione Sector...");
		         
		        lisSector = new ArrayList<Sector>();
		        lisSector.add(sector);
		       
	            SectorBO sectorBO = new SectorBO();
	            Sector sectorTmp = sectorBO.sectorById(direccion.getSector().getIdsector());
	            
	            List<Sector> lisSectorTmp = sectorBO.SectorxCiudad(sectorTmp.getCiudad().getIdciudad());
	            if(lisSectorTmp != null && lisSectorTmp.size() > 0){
	                lisSector.addAll(lisSectorTmp);
	            }
	            
	            //Ciudades
	            Ciudad ciudad = new Ciudad();
	            ciudad.setIdciudad(0);
	            ciudad.setNombre("Seleccione Ciudad...");
	            
	            lisCiudad = new ArrayList<Ciudad>();
	            lisCiudad.add(ciudad);
		        
	            CiudadBO ciudadBO = new CiudadBO();
	            Ciudad ciudadTmp = ciudadBO.ciudadById(sectorTmp.getCiudad().getIdciudad());
	            
	            List<Ciudad> lisCiudadTmp = ciudadBO.consultarCiudadPorProvincia(ciudadTmp.getProvincia().getIdprovincia());
	            if(lisCiudadTmp != null && lisCiudadTmp.size() > 0){
	            	lisCiudad.addAll(lisCiudadTmp);
	            }
	            
	            ciudadSelected = ciudadTmp;
	            
	            //Provincias
	            Provincia provincia = new Provincia();
	            provincia.setIdprovincia(0);
	            provincia.setNombre("Selecciones Provincia...");
	            
	            lisProvincia = new ArrayList<Provincia>();
	            lisProvincia.add(provincia);
	            
	            ProvinciaBO provinciaBO = new ProvinciaBO();
	            Provincia provinciaTmp = provinciaBO.provinciaPorId(ciudadTmp.getProvincia().getIdprovincia());
	            
	            List<Provincia> lisProvinciaTmp = provinciaBO.consultarProvinciaPorPais(provinciaTmp.getPais().getIdpais());
	            if(lisProvinciaTmp != null && lisProvinciaTmp.size() > 0){
	            	lisProvincia.addAll(lisProvinciaTmp);
	            }
	            
	            provinciaSelected = provinciaTmp;
	            
	            //Paises
	            Pais pais = new Pais();
	            pais.setIdpais(0);
	            pais.setNombre("Seleccione Pais...");
	            
	            lisPais = new ArrayList<Pais>();
	            lisPais.add(pais);
	            
	            PaisBO paisBO = new PaisBO();
	            Pais paisTmp = paisBO.consultaPaisPorId(provinciaTmp.getPais().getIdpais());
	            
	            List<Pais> lisPaisTmp = paisBO.consultarPaises();
	            if(lisPaisTmp != null && lisPaisTmp.size() > 0){
	            	lisPais.addAll(lisPaisTmp);
	            }
	            
	            paisSelected = paisTmp;
	            
	            //Ubicacion
	            Ubicacion ubicacion = new Ubicacion();
	            ubicacion.setIdubicacion(0);
	            ubicacion.setNombre("Seleccione Ubicacion...");
	            
	            lisUbicacion = new ArrayList<Ubicacion>();
	            lisUbicacion.add(ubicacion);
	            
	            UbicacionBO ubicacionBO = new UbicacionBO();
	            
	            List<Ubicacion> lisUbicacionTmp = ubicacionBO.ConsultarUbicacionxSector(sectorTmp.getIdsector());
	            if(lisUbicacionTmp != null && lisUbicacionTmp.size() > 0){
	            	lisUbicacion.addAll(lisUbicacionTmp);
	            }
	            
	            //Edificio
	            Edificio edificio = new Edificio();
	            edificio.setIdedificio(0);
	            edificio.setNombre("Seleccione Edificio...");
	            
	            lisEdificio = new ArrayList<Edificio>();
	            lisEdificio.add(edificio);
	            
	            EdificiosBO edificiosBO = new EdificiosBO();
	            
	            List<Edificio> lisEdificioTmp = edificiosBO.EdificioxSector(sectorTmp.getIdsector());
	            if(lisEdificioTmp != null && lisEdificioTmp.size() > 0){
	            	lisEdificio.addAll(lisEdificioTmp);
	            }
	            
	            //Nodo
	            Nodos nodos = new Nodos();
	            nodos.setIdnodo(0);
	            nodos.setNombre("Seleccione Nodos...");
	            
	            lisNodos = new ArrayList<Nodos>();
	            lisNodos.add(nodos);
	            
	            NodosBO nodosBO = new NodosBO();
	            
	            List<Nodos> lisNodosTmp = nodosBO.ConsultaNodosxSector(sectorTmp.getIdsector());
	            if(lisNodosTmp != null && lisNodosTmp.size() > 0){
	            	lisNodos.addAll(lisNodosTmp);
	            }
	            
			}catch(Exception e){
				e.printStackTrace();
				new MessageUtil().showFatalMessage("Ha ocurrido un error inesperado. Comunicar al Webmaster!", "");
			}
		}
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
				 //agregado
				 Pais pais = new Pais();
				 pais.setIdpais(0);
				 pais.setNombre("Seleccione País...");
		         lisPais.add(pais);
		            
	            PaisBO paisBO = new PaisBO();
	            List<Pais>lisTmpPais = paisBO.consultarPaises();
	            
	            if(lisTmpPais != null && lisTmpPais.size() > 0){
	                lisPais.addAll(lisTmpPais);
	            }
	            paisSelected.setIdpais(idpais);
	            
	            //agregado
	            Provincia provincia = new Provincia();
	            provincia.setIdprovincia(0);
	            provincia.setNombre("Seleccione Provincia...");
	            lisProvincia = new ArrayList<Provincia>();
	            lisProvincia.add(provincia);
	            
	            List<Provincia>lisTmpPro = provinciaBO.consultarProvinciaPorPais(idpais);
	            
	            if(lisTmpPro != null && lisTmpPro.size() > 0){
	                lisProvincia.addAll(lisTmpPro);
	            }
	            provinciaSelected.setIdprovincia(idprovincia);
	            
	            
	            //agregado
	            Ciudad ciudad = new Ciudad();
	            ciudad.setIdciudad((int)0);
	            ciudad.setNombre("Seleccione Ciudad...");
	            lisCiudad = new ArrayList<Ciudad>();
	            lisCiudad.add(ciudad);
	            
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
	
	public void llenarDependientes() {
		try{
			direccion.setSector(new Sector(0,new Estado(),new Ciudad(),new Usuario(), null, null));
			
			//agregado
			Sector sector = new Sector();
	        sector.setIdsector((int)0);
	        sector.setNombre("Seleccione Sector...");
	        lisSector = new ArrayList<Sector>();
	        lisSector.add(sector);
	        
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
					 //agregado
					 Tiposector tiposector = new Tiposector();
					 tiposector.setIdtiposector(0);
					 tiposector.setNombre("Seleccione Tipo Sector...");
					 lisTipoSector.add(tiposector);
					 
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
				  //if (codigo == 2) { //entra en caso de ser sector el clickeado
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
	            					
	            //}// si codigo == 2 				  
			  
				     
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

	public int getIdcuenta() {
		return idcuenta;
	}

	public void setIdcuenta(int idcuenta) {
		this.idcuenta = idcuenta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Referenciadir getReferenciadir() {
		return referenciadir;
	}

	public void setReferenciadir(Referenciadir referenciadir) {
		this.referenciadir = referenciadir;
	}

	public Direccion getDireccionClon() {
		return direccionClon;
	}

	public void setDireccionClon(Direccion direccionClon) {
		this.direccionClon = direccionClon;
	}

	public Referenciadir getReferenciadirClon() {
		return referenciadirClon;
	}

	public void setReferenciadirClon(Referenciadir referenciadirClon) {
		this.referenciadirClon = referenciadirClon;
	}

}
