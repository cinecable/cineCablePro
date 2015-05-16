package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import pojo.annotations.Costoservicio;

@Local
public interface ICostoServicioDao extends IGenericDao<Costoservicio, Long> {

	List<Costoservicio> consultarServiciosCostoPorIdTipoOperacionYTipoServicioAbrev(String tipServAbrev);

}
