package net.cinecable.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dao.IClavesDao;
import net.cinecable.dm.PersonaDM;
import net.cinecable.exception.EntidadNoBorradaException;
import net.cinecable.model.base.Tidentidad;
import net.cinecable.service.IAreaServices;
import net.cinecable.service.IEmpresaServices;
import net.cinecable.service.IPersonaServices;
import net.cinecable.service.ITipoIdentidadServices;
import pojo.annotations.Area;
import pojo.annotations.Claves;
import pojo.annotations.Empresa;
import pojo.annotations.Persona;
import pojo.annotations.Usuario;
import pojo.annotations.custom.Genero;
import util.MessageUtil;

@ManagedBean(name="ConsPersonaController")
@ViewScoped
public class ConsPersonaController {
	@ManagedProperty(value="#{personaDM}")
	private PersonaDM personaDM;
	@EJB
	private IAreaServices iAreaServices;
	@EJB
	private IPersonaServices iPersonaServices;
	@EJB
	private IEmpresaServices iEmpresaServices;
	@EJB
	private ITipoIdentidadServices iTipoIdentidadServices;
	@EJB 
	private IClavesDao iClaveDao;
	@PostConstruct
	public void initPantalla(){
		personaDM.setPersona(new Persona());
		personaDM.setClaves(new Claves());
		personaDM.setEmpresaSeleccionada(new Empresa());
		personaDM.setUsuario(new Usuario());
		personaDM.setAreaSeleccionada(new Area());
		personaDM.setTidentidadSeleccionada(new Tidentidad());
		personaDM.setPersonaSeleccionada(new Persona());
		personaDM.getPersonaSeleccionada().setUsuario(new Usuario());
		personaDM.getPersonaSeleccionada().setTidentidad(new Tidentidad());
		personaDM.getPersonaSeleccionada().getUsuario().setClaves(new Claves());
		personaDM.getPersonaSeleccionada().getUsuario().setEmpresa(new Empresa());
		personaDM.getPersonaSeleccionada().setArea(new Area());
	}
	
	public PersonaDM getPersonaDM() {
		return personaDM;
	}

	public void setPersonaDM(PersonaDM personaDM) {
		this.personaDM = personaDM;
	}
	
	public List<Area> getAreas(){
		personaDM.setListaAreas(iAreaServices.getAreasbyEstado(1));
		return personaDM.getListaAreas();
	}
	
	public List<Tidentidad> getTidentidad(){
		personaDM.setTipoIdentidad(iTipoIdentidadServices.getIdentidad());
		return personaDM.getTipoIdentidad();
	}
	public List<Genero> getGeneros(){
		List<Genero> lisGenero= new ArrayList<Genero>();
		lisGenero.add(new Genero(1,"Masculino"));
		lisGenero.add(new Genero(2,"Femenino"));
		lisGenero.add(new Genero(3,"Otro Genero"));
		personaDM.setLisGenero(lisGenero);
		return personaDM.getLisGenero();
	}
	public List<Empresa> getEmpresas(){
		personaDM.setListaEmpresa(iEmpresaServices.getEmpresas());
		return personaDM.getListaEmpresa();
	}
	
	public List<Persona> getListaPersona(){
		personaDM.setListaPersonas(iPersonaServices.getPersonas());
		for (int i=0;i<personaDM.getListaPersonas().size();i++){
			int idUsuario=personaDM.getListaPersonas().get(i).getUsuario().getIdusuario();
			personaDM.getListaPersonas().get(i).getUsuario().setClaves(iClaveDao.getClavebyUsuario(idUsuario));
		}
		return personaDM.getListaPersonas();
	}
	
	public void onBlur$idpnombre(){
		if (personaDM.getPersonaSeleccionada().getNombre1()!=null && personaDM.getPersonaSeleccionada().getApellido1()!=null){
			if (!(personaDM.getPersonaSeleccionada().getNombre1().equals("") && personaDM.getPersonaSeleccionada().getApellido1().equals("")))
				personaDM.getPersonaSeleccionada().getUsuario().setNombre(personaDM.getPersonaSeleccionada().getNombre1().substring(0,1) + personaDM.getPersonaSeleccionada().getApellido1());
		}else{
			personaDM.getPersonaSeleccionada().getUsuario().setNombre("");
		}
	}
	public void grabar(){
		if (personaDM.getPersonaSeleccionada().getIdpersona()==0)return;
		try {
			iPersonaServices.updPersona(personaDM);
			new MessageUtil().showInfoMessage("Actualizacion de persona", "Actualizacion exitosa");
			initPantalla();
		} catch (Exception e) {
			new MessageUtil().showErrorMessage("Actualizacion de persona", e.getMessage());
			e.printStackTrace();
		}
	}
	public void eliminar(){
		try {
			if (personaDM.getPersonaSeleccionada().getIdpersona()!=0){
				iPersonaServices.delPersona(personaDM);
				personaDM.setPersonaSeleccionada(new Persona());
				new MessageUtil().showInfoMessage("Borrado de la persona", "Bodrrado exitoso");
			}
			
			
		} catch (EntidadNoBorradaException e) {
			new MessageUtil().showErrorMessage("Borrado de la persona", e.getMessage());
			e.printStackTrace();
		}
	}
}
