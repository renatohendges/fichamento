package controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.Livro;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoLivro;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorLivro implements Serializable {
	@Inject
	private ControladorMenu controladorMenu;
	@Inject
	private ServicoLivro servicoLivro;
	@Getter
	@Setter
	private Livro livro;

	public void salvarLivro(String pagina) {
		livro = servicoLivro.salvar(livro);
		controladorMenu.setPagina(pagina);
	}
}
