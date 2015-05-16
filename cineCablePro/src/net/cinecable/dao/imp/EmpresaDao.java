package net.cinecable.dao.imp;

import javax.ejb.Stateless;

import net.cinecable.dao.IEmpresaDao;
import pojo.annotations.Empresa;
@Stateless
public class EmpresaDao extends GenericDao<Empresa, Long> implements IEmpresaDao{

	public EmpresaDao() {
		super(Empresa.class);
		// TODO Auto-generated constructor stub
	}

}
