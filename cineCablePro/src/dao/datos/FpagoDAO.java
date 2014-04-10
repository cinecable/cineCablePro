package dao.datos;

import global.Parametro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Fpago;

public class FpagoDAO {

	public Fpago getFpagoById(Session session, int idfpago) throws Exception {
		Fpago fpago = null;
		
		String hql = " from Fpago ";
		hql += " where idfpago = :idfpago ";
		
		Query query = session.createQuery(hql)
				.setInteger("idfpago", idfpago);
		
		fpago = (Fpago) query.uniqueResult();
		
		return fpago;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fpago> lisFpago(Session session, int idempresa) throws Exception {
		List<Fpago> lisFpago = null;
		
		String hql = " from Fpago ";
		hql += " where idempresa = :idempresa ";
		hql += " and idestado = :idestado ";
		hql += " and idfpago not in(:lisIdfpago) ";
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1)
				.setParameterList("lisIdfpago", new Integer[] {Parametro.TIPO_FORMA_PAGO_CREDITO, Parametro.TIPO_FORMA_PAGO_EXCEDENTE});
		
		lisFpago = (List<Fpago>) query.list();
		
		return lisFpago;
	}
}
