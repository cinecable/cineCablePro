package bo.negocio;

import java.util.List;

import org.hibernate.Session;


import pojo.annotations.Tpagos;
import util.HibernateUtil;
import dao.datos.TpagosDAO;

public class TpagosBO {

	TpagosDAO tpagosDAO;
	
	public TpagosBO() {
		tpagosDAO = new TpagosDAO();
	}
	
	/**
	 * Lista todos los abonos donde la forma de pago no sea credito o excedente
	 * @param idfactura
	 * @return List Tpagos
	 * @throws Exception
	 */
	public List<Tpagos> lisTpagosAbonosByFactura(String idfactura) throws Exception {
		List<Tpagos> lisTpagos = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            lisTpagos = tpagosDAO.lisTpagosAbonosByFactura(session, idfactura);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
		
		return lisTpagos;
	}
	
	public List<Tpagos> lisTpagosByPage(int idpago, int pageSize, int pageNumber, int[] args  ) throws RuntimeException {
		List<Tpagos> lisTpagos = null;
		Session session = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			lisTpagos = tpagosDAO.lisTpagos(session, idpago, pageSize, pageNumber, args);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
            session.close();
        }
		
		return lisTpagos;
	}
}
