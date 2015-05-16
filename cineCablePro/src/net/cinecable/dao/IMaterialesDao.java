package net.cinecable.dao;

import java.util.List;

import javax.ejb.Local;

import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoEquipo;
import net.cinecable.enums.TipoMaterial;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.Ordenes;
import net.cinecable.model.base.ReservacionesBodegaMateriales;

@Local
public interface IMaterialesDao extends IGenericDao<Materiales, Long> {

	List<Materiales> getAllbyTipoEquipo(TipoEquipo tipEquipo);

	List<Materiales> getAllbyTipoMaterialNoOcupadoInventario(String tipMaterial);

	List<Materiales> getMaterialTecnicoControl(Long idTecnico, Ordenes orden, TipoMaterial... tipoMaterial);

	List<Materiales> getMaterialPersonaCuentaControl(Long idCuenta, TipoMaterial... tipoMaterial);

	List<ReservacionesBodegaMateriales> getMaterialTecnicoBaja(Long idTecnico, Ordenes orden);

	List<ReservacionesBodegaMateriales> getMaterialPersonaCuentaBaja(Long idCuenta, Ordenes orden);

	List<Materiales> getMaterialProductoCuenta(Long idCuenta);

	Materiales getMaterialesxSerieoMacEstado(String serieMac, Estados estado);
}
