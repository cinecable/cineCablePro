package net.cinecable.dao;

import javax.ejb.Local;

import pojo.annotations.Pagos;

@Local
public interface IPagosDao extends IGenericDao<Pagos, Long> {

}
