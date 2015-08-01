package bo.negocio;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import dao.datos.EgresoDAO;

import pojo.annotations.custom.IngresosEgresosCierreCaja;
import util.FacesUtil;
import util.HibernateUtil;
import bean.controladores.UsuarioBean;

public class EgresoBO {

	public EgresoBO() {
	}
	
	public List<IngresosEgresosCierreCaja> lisEgresosCierreCaja(int idusuario, Date fechaDesde, Date fechaHasta) throws RuntimeException {
		List<IngresosEgresosCierreCaja> lisIngresosEgresosCierreCaja = null;
		Session session = null;
		
		try{
            session = HibernateUtil.getSessionFactory().openSession();
            EgresoDAO egresoDAO = new EgresoDAO();
            
            UsuarioBean usuarioBean = (UsuarioBean)new FacesUtil().getSessionBean("usuarioBean");
            int idempresa = usuarioBean.getUsuario().getEmpresa().getIdempresa();
            lisIngresosEgresosCierreCaja = egresoDAO.lisSumEgresosByFechas(session, idusuario, idempresa, fechaDesde, fechaHasta);
            
            for(IngresosEgresosCierreCaja reg:lisIngresosEgresosCierreCaja){
        		//reg.setLisVegresos(vegresosDAO.lisVegresos(session, idusuario, reg.getIdfpago(), fechaDesde, fechaHasta));
        	}
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
        finally{
            session.close();
        }
		
		return lisIngresosEgresosCierreCaja;
	}
}
