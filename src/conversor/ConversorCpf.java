package conversor;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter("conversorCpf")
@RequestScoped
public class ConversorCpf implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		Long cpfNum = null;
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			value = value.replaceAll("\\.", "").replaceAll("\\-", "");
			cpfNum = Long.parseLong(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpfNum;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (!(value instanceof Long)) {
			return null;
		}
		return String.format("%011d", value).substring(0, 3) + "." + String.format("%011d", value).substring(3, 6) + "." + String.format("%011d", value).substring(6, 9) + "-" + String.format("%011d", value).substring(9, 11);
	}
}
