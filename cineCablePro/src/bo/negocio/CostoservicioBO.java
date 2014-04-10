package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import pojo.annotations.Costoservicio;
import util.HibernateUtil;
import dao.datos.CostoservicioDAO;

public class CostoservicioBO {

	CostoservicioDAO costoservicioDAO;
	
	public CostoservicioBO() {
		costoservicioDAO = new CostoservicioDAO();
	}
	
	public List<Costoservicio> lisCostoservicioByIdCuenta(int idcuenta) throws Exception {
		List<Costoservicio> lisCostoservicio = null;
		Session session = null;
	
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisCostoservicio = costoservicioDAO.lisCostoservicioByIdCuenta(session, idcuenta);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
        
        return lisCostoservicio;
	}
} 
