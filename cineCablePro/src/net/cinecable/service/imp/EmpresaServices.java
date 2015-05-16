package net.cinecable.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pojo.annotations.Empresa;
import net.cinecable.dao.IEmpresaDao;
import net.cinecable.service.IEmpresaServices;
@Stateless
public class EmpresaServices implements IEmpresaServices{
	@EJB
	private IEmpresaDao iEmpresaDao;
	@Override
	public List<Empresa> getEmpresas() {
		List<Empresa> listaEmpresa= new ArrayList<Empresa>();
		listaEmpresa=iEmpresaDao.obtenerTodos();
		return listaEmpresa;
	}

}
