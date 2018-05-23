package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import servico.ServicoFicha;
import servico.ServicoLivro;

//teste de commit
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorFicha implements Serializable {
	@Inject
	private ControladorMenu controladorMenu;
	@Inject
	private ServicoLivro servicoLivro;
	@Inject
	private ServicoFicha servicoFicha;

	@PostConstruct
	public void init() {
		controladorMenu.setPagina("/cadastro/ficha");
	}

	public void salvarFicha() {
	}
}
