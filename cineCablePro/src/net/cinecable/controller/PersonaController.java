package net.cinecable.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.cinecable.dm.PersonaDM;
import net.cinecable.exception.EntidadNoGrabadaException;
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

@ManagedBean(name="personaController")
@ViewScoped
public class PersonaController {
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
	@PostConstruct
	public void initPantalla(){
		personaDM.setPersona(new Persona());
		personaDM.setClaves(new Claves());
		personaDM.setEmpresaSeleccionada(new Empresa());
		personaDM.setUsuario(new Usuario());
		personaDM.setAreaSeleccionada(new Area());
		personaDM.setTidentidadSeleccionada(new Tidentidad());
		
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
	
	
	
	public void onBlur$idpnombre(){
		if (personaDM.getPersona().getNombre1()!=null && personaDM.getPersona().getApellido1()!=null){
			if (!(personaDM.getPersona().getNombre1().equals("") && personaDM.getPersona().getApellido1().equals("")))
				personaDM.getUsuario().setNombre(personaDM.getPersona().getNombre1().substring(0,1) + personaDM.getPersona().getApellido1());
		}else{
			personaDM.getPersona().getUsuario().setNombre("");
		}
	}
	public void grabar(){
		try {
			iPersonaServices.insPersona(personaDM);
			new MessageUtil().showInfoMessage("Ingreso de persona", "Ingreso exitoso");
			initPantalla();
		} catch (EntidadNoGrabadaException e) {
			new MessageUtil().showErrorMessage("Ingreso de persona", e.getMessage());
			e.printStackTrace();
		}
	}
}
