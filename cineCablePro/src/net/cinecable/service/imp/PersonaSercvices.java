package net.cinecable.service.imp;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.cinecable.dao.IClavesDao;
import net.cinecable.dao.IPersonaDao;
import net.cinecable.dao.IUsuarioDao;
import net.cinecable.dm.PersonaDM;
import net.cinecable.enums.TipoPersona;
import net.cinecable.exception.EntidadNoBorradaException;
import net.cinecable.exception.EntidadNoGrabadaException;
import net.cinecable.service.IPersonaServices;
import pojo.annotations.Claves;
import pojo.annotations.Empresa;
import pojo.annotations.Estado;
import pojo.annotations.Persona;
import pojo.annotations.Usuario;
import util.MessageUtil;

@Stateless
public class PersonaSercvices implements IPersonaServices {
	@EJB
	private IPersonaDao iPersonaDao;
	@EJB
	private IUsuarioDao iUsuarioDao;
	@EJB
	private IClavesDao iClavesDao;

	@Override
	public void insPersona(PersonaDM personaDM) throws EntidadNoGrabadaException {
		if (personaDM.getPersona().getNombre1() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el primer nombre");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getPersona().getApellido1() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el primer apellido");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getPersona().getFnacimiento() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese la fecha de nacimiento");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getAreaSeleccionada().getIdarea() == 0) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el area");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getPersona().getIdgenero() == 0) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el genero");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getClaves().getClave() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese la clave");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getClaves().getClave().equals("")) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese la clave");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getEmpresaSeleccionada().getIdempresa() == 0) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese la empresa");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getUsuario().getPerfil() == 0) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el perfil");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getUsuario().getPtovta() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese el punto de venta");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getUsuario().getNombre().length() > 6) {
			new MessageUtil().showInfoMessage("Persona", "El maximo de caracteres del nombre de usuario es de 6");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getTidentidadSeleccionada().getIdtidentidad() == 0) {
			new MessageUtil().showInfoMessage("Persona", "Seleccione el tipo de identidad");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		if (personaDM.getPersona().getIdentidad() == null) {
			new MessageUtil().showInfoMessage("Persona", "Ingrese la el no. de identidad");
			throw new EntidadNoGrabadaException("Faltan datos");
		}
		personaDM.getUsuario().setAbreviado(personaDM.getUsuario().getNombre());
		personaDM.getUsuario().setIdestado(1);
		personaDM.getUsuario().setEmpresa(new Empresa());
		personaDM.getPersona().setArea(personaDM.getAreaSeleccionada());
		personaDM.getPersona().setTidentidad(personaDM.getTidentidadSeleccionada());
		personaDM.getUsuario().getEmpresa().setIdempresa(personaDM.getEmpresaSeleccionada().getIdempresa());
		iUsuarioDao.crear(personaDM.getUsuario());
		personaDM.getClaves().setIdusuario(personaDM.getUsuario().getIdusuario());
		personaDM.getClaves().setUsuario(new Usuario());
		personaDM.getClaves().getUsuario().setIdusuario(personaDM.getUsuario().getIdusuario());
		iClavesDao.crear(personaDM.getClaves());
		personaDM.getPersona().setUsuario(new Usuario());
		personaDM.getPersona().getUsuario().setIdusuario(personaDM.getUsuario().getIdusuario());
		Estado estado = new Estado();
		estado.setIdestado(1);
		personaDM.getPersona().setEstado(estado);
		iPersonaDao.crear(personaDM.getPersona());
	}

	@Override
	public List<Persona> getPersonas() {
		List<Persona> listaPersonas = new ArrayList<Persona>();
		listaPersonas = iPersonaDao.obtenerTodos();
		return listaPersonas;
	}

	@Override
	public void delPersona(PersonaDM personaDM) throws EntidadNoBorradaException {

		Claves clave = iClavesDao.getClavebyUsuario(personaDM.getPersonaSeleccionada().getUsuario().getIdusuario());
		if (clave == null) {
			new MessageUtil().showInfoMessage("Persona", "Error al buscar la clave del usuario");
			throw new EntidadNoBorradaException("Error al consultar");
		}
		personaDM.getPersonaSeleccionada().getUsuario().setClaves(clave);
		iClavesDao.eliminar(personaDM.getPersonaSeleccionada().getUsuario().getClaves());
		iPersonaDao.eliminar(personaDM.getPersonaSeleccionada());
		iUsuarioDao.eliminar(personaDM.getPersonaSeleccionada().getUsuario());

	}

	@Override
	public List<Persona> getListaPersonasbyTipo(TipoPersona tipo) {
		List<Persona> listaPersonas;
		listaPersonas = iPersonaDao.getListaPersonasbyTipo(tipo);
		return listaPersonas;
	}
	@Override
	public void updPersona(PersonaDM personaDM) throws Exception {
		if (personaDM.getPersonaSeleccionada().getNombre1()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese el primer nombre");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getApellido1()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese el primer apellido");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getFnacimiento()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese la fecha de nacimiento");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getArea().getIdarea()==0){
			new MessageUtil().showInfoMessage("Persona", "Ingrese el area");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getIdgenero()==0){
			new MessageUtil().showInfoMessage("Persona", "Ingrese el genero");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getUsuario().getClaves().getClave()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese la clave");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getUsuario().getClaves().getClave().equals("")){
			new MessageUtil().showInfoMessage("Persona", "Ingrese la clave");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getUsuario().getEmpresa().getIdempresa()==0){
			new MessageUtil().showInfoMessage("Persona", "Ingrese la empresa");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getUsuario().getPtovta()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese el punto de venta");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getUsuario().getNombre().length()>15){
			new MessageUtil().showInfoMessage("Persona", "El maximo de caracteres del nombre de usuario es de 6");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getTidentidad().getIdtidentidad()==0){
			new MessageUtil().showInfoMessage("Persona", "Seleccione el tipo de identidad");
			throw new Exception("Faltan datos");
		}
		if (personaDM.getPersonaSeleccionada().getIdentidad()==null){
			new MessageUtil().showInfoMessage("Persona", "Ingrese la el no. de identidad");
			throw new Exception("Faltan datos");
		}
		String clave=encriptar(personaDM.getPersonaSeleccionada().getUsuario().getClaves().getClave());
		personaDM.getPersonaSeleccionada().getUsuario().getClaves().setClave(clave);
		iUsuarioDao.actualizar(personaDM.getPersonaSeleccionada().getUsuario());
		iClavesDao.actualizar(personaDM.getPersonaSeleccionada().getUsuario().getClaves());
		iPersonaDao.actualizar(personaDM.getPersonaSeleccionada());
		
	}
	private String encriptar(String clear) throws Exception { 
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		byte[] b = md.digest(clear.getBytes()); 
		int size = b.length; 
		StringBuffer h = new StringBuffer(size); 
		for (int i = 0; i < size; i++) { 
			int u = b [ i ] & 255 ;
			if (u<16){
				h.append("0"+Integer.toHexString(u)); 
			}else { 
				h.append(Integer.toHexString(u)); 
			} 
		} 
		return h.toString(); 
		}
	
	
}
