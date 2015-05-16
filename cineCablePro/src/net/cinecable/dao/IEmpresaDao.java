package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Empresa;
@Local
public interface IEmpresaDao extends IGenericDao<Empresa, Long>{

}
