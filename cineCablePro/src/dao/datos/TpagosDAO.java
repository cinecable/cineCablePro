package dao.datos;

import global.Parametro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import pojo.annotations.Tpagos;

public class TpagosDAO {

	public int maxIdTpagos(Session session) throws Exception {
		int max=0;
		
		Object object = session.createQuery("select max(idtpago) as max from Tpagos").uniqueResult();
		max = (object==null?0:Integer.parseInt(object.toString()));
		
		return max;
	}
	
	public void actualizarTpago(Session session, Tpagos tpagos) throws Exception {
		session.update(tpagos);
	}
	
	public void ingresarTpago(Session session, Tpagos tpagos) throws Exception {
		session.save(tpagos);
	}
	
	/**
	 * Lista todos los abonos donde la forma de pago no sea credito o excedente
	 * @param session
	 * @param idfactura
	 * @return List Tpagos
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tpagos> lisTpagosAbonosByFactura(Session session, String idfactura) throws Exception {
		List<Tpagos> lisTpagos = null;
		
		String hql = " select t from Tpagos t, Pagos p ";
		hql += " where p.idfactura = :idfactura ";
		hql += " and p.estado.idestado = :idestadop ";
		hql += " and t.pagos.idpago = p.idpago ";
		hql += " and t.idestado = :idestadot ";
		hql += " and t.fpago.idfpago not in(:lisIdfpago) ";
		
		Query query = session.createQuery(hql)
				.setString("idfactura", idfactura)
				.setInteger("idestadop", 1)
				.setInteger("idestadot", 4)
				.setParameterList("lisIdfpago", new Integer[] {Parametro.TIPO_FORMA_PAGO_CREDITO, Parametro.TIPO_FORMA_PAGO_EXCEDENTE});
		
		lisTpagos = (List<Tpagos>) query.list();
		
		return lisTpagos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tpagos> lisTpagos(Session session, int idpago, int pageSize, int pageNumber, int[] args  ) throws Exception {
            List<Tpagos> lisTpagos;

            Criteria criteria = session.createCriteria(Tpagos.class)
            		.add( Restrictions.eq("pagos.idpago", idpago))
                    .createAlias("fpago", "formapago", Criteria.LEFT_JOIN)
                    .createAlias("bancosByIdbcoemisor", "bcoemisor", Criteria.LEFT_JOIN)
                    .createAlias("pagos", "pag", Criteria.LEFT_JOIN);
            
            
            criteria.addOrder(Order.asc("idtpago"))
            .setMaxResults(pageSize)
            .setFirstResult(pageNumber);
            
            lisTpagos = (List<Tpagos>) criteria.list();
            
            if(lisTpagos != null && lisTpagos.size() > 0)
            {
				Criteria criteriaCount = session.createCriteria(Tpagos.class)
	            		.add( Restrictions.eq("pagos.idpago", idpago))
	                    .createAlias("fpago", "formapago", Criteria.LEFT_JOIN)
	                    .createAlias("bancosByIdbcoemisor", "bcoemisor", Criteria.LEFT_JOIN)
	                    .createAlias("pagos", "pag", Criteria.LEFT_JOIN)
                        .setProjection( Projections.rowCount());

				Object object = criteriaCount.uniqueResult();
				int count = (object==null?0:Integer.parseInt(object.toString()));
				args[0] = count;
			}
			else
			{
				args[0] = 0;
			}

            return lisTpagos;
	}
}
