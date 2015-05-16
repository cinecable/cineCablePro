package net.cinecable.faces.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import net.cinecable.model.base.Ordenes;

@FacesConverter("dualOrdenesConverter")
public class DualListOrdenesConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getObjectFromUIPickListComponent(arg1, arg2);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String string;
		if (arg2 == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((Ordenes) arg2).getIdOrdenes());
			} catch (ClassCastException cce) {
				throw new ConverterException();
			}
		}
		return string;
	}

	@SuppressWarnings("unchecked")
	private Ordenes getObjectFromUIPickListComponent(UIComponent component, String value) {
		final DualListModel<Ordenes> dualList;
		try {
			dualList = (DualListModel<Ordenes>) ((PickList) component).getValue();
			Ordenes team = getObjectFromList(dualList.getSource(), Long.valueOf(value));
			if (team == null) {
				team = getObjectFromList(dualList.getTarget(), Long.valueOf(value));
			}

			return team;
		} catch (ClassCastException cce) {
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			throw new ConverterException();
		}
	}

	private Ordenes getObjectFromList(final List<?> list, final Long identifier) {
		for (final Object object : list) {
			final Ordenes ord = (Ordenes) object;
			if (ord.getIdOrdenes().equals(identifier)) {
				return ord;
			}
		}
		return null;
	}

}
