package faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pojo.annotations.Pagos;
import util.MessageUtil;
import bo.negocio.PagosBO;

public class PagoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(submittedValue);
                Pagos pagos = new PagosBO().getPagosById(id);
                return pagos;
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Pagos){
			return String.valueOf(((Pagos) value).getIdpago());
		} else {
			return "";
		}
	}
}
