package net.cinecable.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.cinecable.dao.IOrdenesDao;
import net.cinecable.exception.EntidadNoEncontradaException;
import net.cinecable.model.base.Ordenes;

@FacesConverter("OrdenesConverter")
public class OrdenesConverter implements Converter {

	IOrdenesDao iordenes;

	{
		InitialContext ic = null;
		try {
			ic = new InitialContext();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		try {
			iordenes = (IOrdenesDao) ic.lookup("java:module/OrdenesDao!net.cinecable.dao.IOrdenesDao");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object ord = null;
		try {
			ord = iordenes.recuperar(Long.valueOf(arg2));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ord;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Ordenes) arg2).getIdOrdenes() + "";
	}

}
