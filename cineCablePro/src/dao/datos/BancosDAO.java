package dao.datos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojo.annotations.Bancos;

public class BancosDAO {

	public Bancos getBancosById(Session session, int idbanco) throws Exception {
		Bancos bancos = null;
		
		String hql = " from Bancos ";
		hql += " where idbanco = :idbanco ";
		
		Query query = session.createQuery(hql)
				.setInteger("idbanco", idbanco);
		
		bancos = (Bancos) query.uniqueResult();
		
		return bancos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bancos> lisBancosByTipoEntidad(Session session, int idempresa, int idtipoentidad) throws Exception {
		List<Bancos> lisBancos = null;
		
		String hql = " from Bancos ";
		hql += " where empresa.idempresa = :idempresa ";
		hql += " and estado.idestado = :idestado ";
		hql += " and tipoentidad.idtipoentidad = :idtipoentidad ";
		
		Query query = session.createQuery(hql)
				.setInteger("idempresa", idempresa)
				.setInteger("idestado", 1)
				.setInteger("idtipoentidad", idtipoentidad);
		
		lisBancos = (List<Bancos>) query.list();
		
		return lisBancos;
	}
}
