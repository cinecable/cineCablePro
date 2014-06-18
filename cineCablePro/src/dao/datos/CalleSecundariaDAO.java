package dao.datos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Callesecundaria;

public class CalleSecundariaDAO {
	
	public Callesecundaria callesecundariaById(Session session, int idcallesecundaria) throws Exception {
		Callesecundaria callesecundaria = null;
		
		Criteria criteria = session.createCriteria(Callesecundaria.class)
                .add( Restrictions.eq("idcallesecundaria", idcallesecundaria));
		
		callesecundaria = (Callesecundaria)criteria.uniqueResult();
				
		return callesecundaria;
	}
	
	  @SuppressWarnings("unchecked")
			public List<Callesecundaria> lisCalleSxSector(Session session, int idSector) throws Exception {
		        List<Callesecundaria> lisCalleSecundaria;

		        Criteria criteria = session.createCriteria(Callesecundaria.class)
		                .add( Restrictions.eq("sector.idsector", idSector))
		                .add( Restrictions.eq("estado.idestado", 1))
		                .addOrder( Order.asc("nombre"));

		        lisCalleSecundaria = (List<Callesecundaria>)criteria.list();

		        return lisCalleSecundaria;
		    }
		  
		  @SuppressWarnings("unchecked")
			public List<Callesecundaria> lisCalleSxCalleS(Session session, int idCalleSecundaria) throws Exception {
		        List<Callesecundaria> lisCalleSecundaria;

		        Criteria criteria = session.createCriteria(Callesecundaria.class)
		                .add( Restrictions.eq("idcallesecundaria", idCalleSecundaria))
		                .add( Restrictions.eq("estado.idestado", 1))
		                .addOrder( Order.asc("nombre"));

		        lisCalleSecundaria = (List<Callesecundaria>)criteria.list();

		        return lisCalleSecundaria;
		    }
		  
		  @SuppressWarnings("unchecked")
			public List<Callesecundaria> lisCalleSxQuery(Session session, int idSector,String query) throws Exception {
		      List<Callesecundaria> lisCalleSecundaria;

		      Criteria criteria = session.createCriteria(Callesecundaria.class)
		              .add( Restrictions.eq("sector.idsector", idSector))
		              .add( Restrictions.eq("estado.idestado", 1))
		              
		              .addOrder( Order.asc("nombre"));
		              if(query != null && query.trim().length() > 0){
		                  criteria.add( Restrictions.like("nombre","%"+query.replaceAll(" ", "%")+"%").ignoreCase());
		              }
		              
		              lisCalleSecundaria = (List<Callesecundaria>)criteria.list();

		      return lisCalleSecundaria;
		  }
		  
}
