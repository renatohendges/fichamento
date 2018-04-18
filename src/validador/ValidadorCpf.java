package validador;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import util.ValidarCpf;

@Named
@FacesValidator("cpfValidator")
@RequestScoped
public class ValidadorCpf implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		if (!ValidarCpf.isCpfValido(String.valueOf(String.format("%011d", value)))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido!", "");
			throw new ValidatorException(message);
		}
	}

}
