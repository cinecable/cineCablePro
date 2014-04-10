package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Empresa;

public class EmpresaDAO {
	 @SuppressWarnings("unchecked")
		public List<Empresa> lisEmpresa(Session session) throws Exception {
	        List<Empresa> lisEmpresa;

	        Criteria criteria = session.createCriteria(Empresa.class)
	        		.add( Restrictions.eq("idestado", (int)1))
	        		.addOrder( Order.asc("nombre"));

	        lisEmpresa = (List<Empresa>)criteria.list();

	        return lisEmpresa;
	    }
		@SuppressWarnings("unchecked")
		public List<Empresa> lisIdEmpresa(Session session,int idEmpresa) throws Exception {
	        List<Empresa> lisEmpresa;

	        Criteria criteria = session.createCriteria(Empresa.class)
	        		.add( Restrictions.eq("idestado", (int)1))
	        		.add( Restrictions.eq("idempresa",idEmpresa))
	        		.addOrder( Order.asc("nombre"));

	        lisEmpresa = (List<Empresa>)criteria.list();

	        return lisEmpresa;
	    }
}
