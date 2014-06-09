package faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pojo.annotations.Clientes;
import util.MessageUtil;
import bo.negocio.ClienteBO;

public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                Clientes clientes = new ClienteBO().getClientesById(submittedValue);
                return clientes;
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Clientes){
			return String.valueOf(((Clientes) value).getIdcliente());
		} else {
			return "";
		}
	}

}
