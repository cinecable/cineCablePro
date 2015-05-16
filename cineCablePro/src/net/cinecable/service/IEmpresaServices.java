package net.cinecable.service;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Empresa;

@Local
public interface IEmpresaServices {
	List<Empresa> getEmpresas();
}
