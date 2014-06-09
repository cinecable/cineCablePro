package bo.negocio;
import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Sector;
import util.HibernateUtil;
import dao.datos.SectorDAO;;

public class SectorBO {
	private SectorDAO SectorDAO;

	   public SectorBO() {
	        SectorDAO = new SectorDAO();
	    }
    public List<Sector> SectorxCiudad(int idCiudad) throws Exception {
        List<Sector> lisSector = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisSector = SectorDAO.lisSectorByCiudad(session, idCiudad);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisSector;
    }
    
    
    public List<Sector> SectorxSector(int idsector) throws Exception {
        List<Sector> lisSector = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisSector = SectorDAO.lisSectorBySector(session, idsector);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisSector;
    }
    
    public Sector sectorById(int idsector) throws Exception {
        Sector sector = null;
        Session session = null;
        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            sector = SectorDAO.sectorById(session, idsector);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return sector;
    }
}
