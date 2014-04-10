package faces.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import bo.negocio.ProductoBO;

import pojo.annotations.Producto;

import util.MessageUtil;


public class ProductosServConverter  implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String submittedValue) {
		if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int id = Integer.parseInt(submittedValue);
                Producto producto = null;
                if(id > 0){
                	List<Producto> lisProducto = new ProductoBO().getProductosById(id);
                	if(lisProducto != null && lisProducto.size() > 0){
                		producto = lisProducto.get(0);
                	}
                }
                return producto;
            } catch(Exception ex) {
            	ex.printStackTrace();
            	new MessageUtil().showFatalMessage("Esto es Vergonzoso!", "Ha ocurrido un error inesperado. Comunicar al Webmaster!");
            }
        }

        return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if (value instanceof Producto){
			return String.valueOf(((Producto) value).getIdproducto());
		} else {
			return "";
		}
	}
}
