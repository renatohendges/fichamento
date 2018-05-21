package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
	@Getter
	@Setter
	private Livro livroSelecionado;
	@Getter
	@Setter
	private List<Livro> livros;
	@Getter
	@Setter
	private List<Livro> livrosFiltrados;
	@Getter
	@Setter
	private Boolean adicionando = false;

	@PostConstruct
	public void init() {
		this.livro = new Livro();
		this.livros = servicoLivro.pesquisarTodos();
		controladorMenu.setPagina("/cadastro/livro");
	}

	public void salvarLivro() {
		livro = servicoLivro.salvar(livro);
		this.livros = servicoLivro.pesquisarTodos();
		adicionando = false;
	}

	public void cancelar() {
		livro = new Livro();
		adicionando = false;
	}

	public void adicionarLivro() {
		livro = new Livro();
		adicionando = true;
	}

	public void verLivro() {
		livro = livroSelecionado;
	}
}
