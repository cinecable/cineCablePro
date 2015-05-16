package net.cinecable.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.IMaterialesDao;
import net.cinecable.dao.IMaterialesDeclaradosBodegaDao;
import net.cinecable.dao.ITipoMaterialDao;
import net.cinecable.dm.MaterialesDM;
import net.cinecable.enums.Estados;
import net.cinecable.enums.TipoEquipo;
import net.cinecable.enums.TipoMaterial;
import net.cinecable.enums.TipoPersona;
import net.cinecable.enums.TipoUnidadMedida;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.model.base.Materiales;
import net.cinecable.model.base.MaterialesDeclaradosBodega;
import net.cinecable.model.base.MaterialesEntregaTecnico;
import net.cinecable.service.IPersonaServices;

@ManagedBean(name = "materialesController")
@ViewScoped
public class MaterialesController extends BaseController {

	@ManagedProperty(value = "#{materialesdm}")
	private MaterialesDM materialesDm;

	@EJB
	ITipoMaterialDao iTipoMaterialDao;
	@EJB
	IMaterialesDao iMaterialesDao;
	@EJB
	IMaterialesDeclaradosBodegaDao iMaterialesDeclarados;
	@EJB
	IPersonaServices ipersonaService;

	@PostConstruct
	public void init() {
		materialesDm.setMaterial(new Materiales());
		materialesDm.setMaterialSeleccionado(null);
		materialesDm.setMaterialSeleccionadoDeclarado(null);
		materialesDm.setEstadoMaterial(null);
		materialesDm.setUnidadMedida(null);
		materialesDm.setCodTecnico(null);
		materialesDm.setUnidadMedida("NINGUNO");
		materialesDm.setCodOp(0);
		materialesDm.setOpDes(1);
		materialesDm.setCodTipoMaterial(1L);
		materialesDm.setMaterialesDeclarados(new ArrayList<MaterialesEntregaTecnico>());
		materialesDm.setMaterialesAgregados(new ArrayList<Materiales>());
		materialesDm.setTipoMateriales(iTipoMaterialDao.getAllTipoMaterial(Estados.ACTIVO));
		this.cnt = 1;

		materialesDm.setTecnicos(ipersonaService.getListaPersonasbyTipo(TipoPersona.TEC));

		loadParametroMaterial();
	}

	public MaterialesDM getMaterialesDm() {
		return materialesDm;
	}

	public void guardar() {
		for (Materiales material : materialesDm.getMaterialesAgregados()) {
			material.setFechaIngreso(new Date());
			Materiales mtemp = null;
			if (material.getIdUnidad() != null) {
				mtemp = iMaterialesDao.getMaterialesxSerieoMacEstado(material.getNroSerie(), Estados.ACTIVO);
				if (mtemp == null)
					mtemp = iMaterialesDao.getMaterialesxSerieoMacEstado(material.getMacAddres(), Estados.ACTIVO);
				mtemp.setEstado(Estados.INACTIVO.getDescription());

			}
			try {
				iMaterialesDao.actualizar(mtemp);
				material.setIdUnidad(null);
				iMaterialesDao.crear(material);
			} catch (EntidadNoGrabadaException e) {
				showInfo("Ingreso de Materiales", "Error al guardar los equipos/materiales");
				e.printStackTrace();
			}
		}
		showInfo("Ingreso de Materiales/Equipos", "Materiales Registrados");
		loadParametroMaterial();
		init();
	}

	public void loadParametroMaterial() {
		Materiales materialVentana = materialesDm.getMaterial();
		net.cinecable.model.base.TipoMaterial tipMaterial = iTipoMaterialDao.getTipoMaterialbyTipoEquipo(TipoEquipo.NA);
		materialVentana.setValorUnidad(tipMaterial.getNroCostoMaterialxDefecto());
		materialVentana.setValorPorLimite(tipMaterial.getNroLimiteMaterialxDefecto());
	}

	public void setMaterialesDm(MaterialesDM materialesDm) {
		this.materialesDm = materialesDm;
	}

	public void cambioSeleccion() {
		if (materialesDm.getMaterial().getTipoMaterial().getTipEquipoMaterial().equals(TipoEquipo.NA)) {
			materialesDm.setOpDes(1);
			materialesDm.getMaterial().setCantidad(0D);
			materialesDm.setUnidadMedida(TipoUnidadMedida.NA.getValue());
		} else {
			materialesDm.setOpDes(0);
			materialesDm.getMaterial().setCantidad(1D);
		}
		Materiales materialVentana = materialesDm.getMaterial();
		net.cinecable.model.base.TipoMaterial tipMaterial = iTipoMaterialDao.getTipoMaterialbyTipoEquipo(materialesDm.getMaterial().getTipoMaterial().getTipEquipoMaterial());
		materialVentana.setValorUnidad(tipMaterial.getNroCostoMaterialxDefecto());
		materialVentana.setValorPorLimite(tipMaterial.getNroLimiteMaterialxDefecto());
	}

	private int cnt = 1;

	public void agregar() {
		Materiales materialtmp = new Materiales();
		Materiales material = (Materiales) materialesDm.getMaterial();

		Materiales matTemp = iMaterialesDao.getMaterialesxSerieoMacEstado(material.getNroSerie(), Estados.ACTIVO);
		if (matTemp == null && material.getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.EQ))
			matTemp = iMaterialesDao.getMaterialesxSerieoMacEstado(material.getMacAddres(), Estados.ACTIVO);

		if (matTemp == null)
			matTemp = material;

		if (material.getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.EQ)) {
			materialtmp.setCantidad(1D);
			materialesDm.setUnidadMedida("NINGUNO");
		} else {
			matTemp.setCantidad(matTemp.getCantidad() + material.getCantidad());
			matTemp.setCantidadIngresada(material.getCantidad());
			material = matTemp;
		}
		material.setKey(cnt);
		cnt++;
		this.materialesDm.getMaterialesAgregados().add(material);
		materialesDm.setMaterial(materialtmp);
		loadParametroMaterial();
	}

	public void remover() {
		Iterator<Materiales> it = materialesDm.getMaterialesAgregados().iterator();
		while (it.hasNext()) {
			if (it.next() == materialesDm.getMaterialSeleccionado()) {
				it.remove();
			}
		}
	}

	public void buscarDeclarado() {
		Materiales mat = iMaterialesDao.getMaterialesxSerieoMacEstado(materialesDm.getSerieOmac(), Estados.ACTIVO);
		if (mat == null)
			mat = iMaterialesDao.getMaterialesxSerieoMacEstado(materialesDm.getSerieOmac().toUpperCase(), Estados.ACTIVO);
		if (mat != null && mat.getTipoMaterial().getTipMaterialGen().equals(TipoMaterial.UT)) {
			MaterialesEntregaTecnico matEntrega = new MaterialesEntregaTecnico();
			matEntrega.setMaterial(mat);
			matEntrega.setCantidad(0);
			matEntrega.setKey(cnt);
			materialesDm.getMaterialesDeclarados().add(matEntrega);
		} else {
			MaterialesEntregaTecnico matEntrega = new MaterialesEntregaTecnico();
			matEntrega.setMaterial(mat);
			matEntrega.setCantidad(1);
			matEntrega.setKey(cnt);
			materialesDm.getMaterialesDeclarados().add(matEntrega);
		}
		cnt++;
		materialesDm.setSerieOmac("");
	}

	public void removerDeclarado() {
		Iterator<MaterialesEntregaTecnico> it = materialesDm.getMaterialesDeclarados().iterator();
		while (it.hasNext()) {
			if (it.next() == materialesDm.getMaterialSeleccionadoDeclarado()) {
				it.remove();
			}
		}
	}

	public void guardarDeclarados() {
		MaterialesDeclaradosBodega matDecBod = new MaterialesDeclaradosBodega();
		matDecBod.setTecnico(materialesDm.getTecnicoSeleccionado());
		matDecBod.setMaterialesEntregados(new ArrayList<MaterialesEntregaTecnico>());
		if (materialesDm.getTecnicoSeleccionado() == null)
			showInfo("Ingreso de Materiales", "Debe seleccionar un tecnico para la asiganción");
		for (MaterialesEntregaTecnico materiales : materialesDm.getMaterialesDeclarados()) {
			materiales.setMaterialBodega(matDecBod);
			materiales.setFechaDeclaracion(new Date());
			matDecBod.getMaterialesEntregados().add(materiales);
		}
		try {
			iMaterialesDeclarados.crear(matDecBod);
		} catch (EntidadNoGrabadaException e) {
			showInfo("Ingreso de Materiales", "Error al guardar la declaración");
			e.printStackTrace();
		}
		showInfo("Ingreso de Materiales/Equipos", "Materiales Declarados exitosamente");
		init();
	}
}
