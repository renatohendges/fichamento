package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorMenu implements Serializable {
	@Getter
	@Setter
	private String pagina;

	@PostConstruct
	public void init() {
		pagina = "apresentacao";
	}
}
