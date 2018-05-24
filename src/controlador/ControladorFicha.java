package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.Ficha;
import lombok.Getter;
import lombok.Setter;
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
	private ControladorLivro controladorLivro;
	@Inject
	private ServicoLivro servicoLivro;
	@Inject
	private ServicoFicha servicoFicha;
	@Getter
	@Setter
	private Ficha ficha;
	@Getter
	@Setter
	private Ficha fichaSelecionada;
	@Getter
	@Setter
	private List<Ficha> fichas;
	@Getter
	@Setter
	private List<Ficha> fichasPorLivro;

	@PostConstruct
	public void init() {
		controladorMenu.setPagina("/cadastro/ficha");
		fichas = servicoFicha.pesquisarTodos();
	}

	public void salvarFicha() {
	}

	public void pesquisarFichaPorLivro() {
		fichasPorLivro = servicoFicha.pesquisarFichaPorLivro(controladorLivro.getLivroSelecionado().getId());
	}
}
