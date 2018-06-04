package controlador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorPagina implements Serializable {
	private String pagina;
	@PostConstruct
	public void init() {
		pagina = "apresentacao";
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
}
