package net.cinecable.service.imp;

import java.util.List;

import javax.ejb.Stateless;

import net.cinecable.service.IBancoServices;

import org.hibernate.Session;

import pojo.annotations.Bancos;
import util.HibernateUtil;
import dao.datos.BancosDAO;

@Stateless
public class BancoServices implements IBancoServices {

	public Bancos getBancosById(int idbanco) throws Exception {
		Bancos bancos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		bancos = new BancosDAO().getBancosById(session, idbanco);
		return bancos;
	}

	public List<Bancos> lisBancosByTipoEntidad(int idempresa, int idtipoentidad) throws Exception {
		List<Bancos> lisBancos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		lisBancos = new BancosDAO().lisBancosByTipoEntidad(session, idempresa, idtipoentidad);
		return lisBancos;
	}

}
