package dao.datos;

import java.util.List;

import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoPersona;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pojo.annotations.Persona;

public class PersonaDAO {

	public Persona getPersonaById(Session session, int idpersona) throws Exception {
		Persona persona = null;
		
		String hql = " from Persona ";
		hql += " where idpersona = :idpersona ";
		
		Query query = session.createQuery(hql)
				.setInteger("idpersona", idpersona);
		
		persona = (Persona) query.uniqueResult();
		
		return persona;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> lisPersonaByPage(Session session, String[] nombres, int idarea, int pageSize, int pageNumber, int[] args) throws Exception {
		List<Persona> lisPersona = null;
		
		Criteria criteria = session.createCriteria(Persona.class)
		.add( Restrictions.eq("area.idarea", idarea));
		
		if(nombres != null && nombres.length > 0){
			String query = "(";
			for(int i=0;i<nombres.length;i++)
			{
				query += "(lower({alias}.apellido1) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.apellido2) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.nombre1) like lower('%"+nombres[i]+"%') or ";
				query += "lower({alias}.nombre2) like lower('%"+nombres[i]+"%')) ";
				if(i<nombres.length-1){
					query += "and ";
				}
			}
			query += ")";
			
			criteria.add(Restrictions.sqlRestriction(query));
		}
		
		criteria.addOrder(Order.asc("apellido1"))
		.addOrder(Order.asc("apellido2"))
		.addOrder(Order.asc("nombre1"))
		.addOrder(Order.asc("nombre2"))
		.setMaxResults(pageSize)
		.setFirstResult(pageNumber);
		
		lisPersona = (List<Persona>) criteria.list();
		
		if(lisPersona != null && lisPersona.size() > 0){
			Criteria criteriaCount = session.createCriteria(Persona.class)
			.setProjection( Projections.rowCount())
			.add( Restrictions.eq("area.idarea", idarea));
			
			if(nombres != null && nombres.length > 0){
				String query = "(";
				for(int i=0;i<nombres.length;i++)
				{
					query += "(lower({alias}.apellido1) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.apellido2) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.nombre1) like lower('%"+nombres[i]+"%') or ";
					query += "lower({alias}.nombre2) like lower('%"+nombres[i]+"%')) ";
					if(i<nombres.length-1){
						query += "and ";
					}
				}
				query += ")";
				
				criteriaCount.add(Restrictions.sqlRestriction(query));
			}
			
			Object object = criteriaCount.uniqueResult();
			int count = (object==null?0:Integer.parseInt(object.toString()));
			args[0] = count;
		} else {
			args[0] = 0;
		}
		
		return lisPersona;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonaCargo(Session session, TipoPersona tipoPersona) throws Exception {
		List<Persona> lisPersona = null;
		
		Criteria criteria = session.createCriteria(Persona.class)
            
                .add( Restrictions.eq("estado.idestado", Estados.ACTIVO.getDescription()));
		
        
        if(tipoPersona != null){
            criteria.add( Restrictions.like("cargo", tipoPersona+"%").ignoreCase());
        }
				
        lisPersona = (List<Persona>) criteria.list();
							
		return lisPersona;
	}}
