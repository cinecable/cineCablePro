package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Calleprincipal;

public class CallePrincipalDAO {
	@SuppressWarnings("unchecked")
	public List<Calleprincipal> lisCallePxSector(Session session, int idSector) throws Exception {
        List<Calleprincipal> lisCallePrincipal;

        Criteria criteria = session.createCriteria(Calleprincipal.class)
                .add( Restrictions.eq("sector.idsector", idSector))
                .add( Restrictions.eq("estado.idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisCallePrincipal = (List<Calleprincipal>)criteria.list();

        return lisCallePrincipal;
    }
  
  @SuppressWarnings("unchecked")
	public List<Calleprincipal> lisCallePxCalleP(Session session, int idCallePrincipal) throws Exception {
        List<Calleprincipal> lisCallePrincipal;

        Criteria criteria = session.createCriteria(Calleprincipal.class)
                .add( Restrictions.eq("idcalleprincipal", idCallePrincipal))
                .add( Restrictions.eq("estado.idestado", 1))
                .addOrder( Order.asc("nombre"));

        lisCallePrincipal = (List<Calleprincipal>)criteria.list();

        return lisCallePrincipal;
    }
  
	@SuppressWarnings("unchecked")
	public List<Calleprincipal> lisCallePxQuery(Session session, int idSector,String query) throws Exception {
      List<Calleprincipal> lisCallePrincipal;

      Criteria criteria = session.createCriteria(Calleprincipal.class)
              .add( Restrictions.eq("sector.idsector", idSector))
              .add( Restrictions.eq("estado.idestado", 1))
              
              .addOrder( Order.asc("nombre"));
              if(query != null && query.trim().length() > 0){
                  criteria.add( Restrictions.like("nombre","%"+query.replaceAll(" ", "%")+"%").ignoreCase());
              }
              

      lisCallePrincipal = (List<Calleprincipal>)criteria.list();

      return lisCallePrincipal;
  }

  
}
