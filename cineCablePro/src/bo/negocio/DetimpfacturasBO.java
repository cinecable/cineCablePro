package bo.negocio;

import java.util.List;

import org.hibernate.Session;

import bean.controladores.UsuarioBean;

import pojo.annotations.Detimpfacturas;
import util.FacesUtil;
import util.HibernateUtil;
import dao.datos.DetimpfacturasDAO;

public class DetimpfacturasBO {
	
	private DetimpfacturasDAO detimpfacturasDAO;

	public DetimpfacturasBO() {
		detimpfacturasDAO = new DetimpfacturasDAO();
	}
	
	public List<Detimpfacturas> lisDetimpfacturasByPage(String idfactura, int pageSize, int pageNumber, int[] args  ) throws RuntimeException {
        List<Detimpfacturas> lisDetimpfacturas = null;
        Session session = null;
     
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisDetimpfacturas = detimpfacturasDAO.lisDetimpfacturasByPage(session, idfactura, idempresa, pageSize, pageNumber, args);
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            session.close();
        }
        
        return lisDetimpfacturas;
	}

}
