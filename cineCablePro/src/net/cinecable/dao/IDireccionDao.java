package net.cinecable.dao;

import javax.ejb.Local;

import net.cinecable.enums.Estados;
import pojo.annotations.Ctacliente;
import pojo.annotations.Direccion;

@Local
public interface IDireccionDao extends IGenericDao<Direccion, Long> {

	Direccion getDireccionbyEstadoYCuenta(Ctacliente cta, Estados estado);

}
