package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import entidade.Livro;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoLivro;
import util.Mensagem;

//teste de commit
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
	@Getter
	@Setter
	private Boolean editando = false;

	@PostConstruct
	public void init() {
		livro = new Livro();
		livroSelecionado = null;
		livros = servicoLivro.pesquisarTodos();
		adicionando = false;
		editando = false;
		controladorMenu.setPagina("/cadastro/livro");
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dataTableLivros').unselectAllRows()");
		context.execute("PF('dataTableLivros').clearFilters()");
	}

	public void salvarLivro() {
		if (adicionando) {
			servicoLivro.salvar(livro);
			this.livros = servicoLivro.pesquisarTodos();
			Mensagem.adicionarMensagem(Mensagem.INFORMACAO, "Livro salvo com sucesso!", "Livro salvo com sucesso!");
			adicionando = false;
		}
		if (editando) {
			livro = servicoLivro.atualizar(livro);
			this.livros = servicoLivro.pesquisarTodos();
			editando = false;
		}
	}

	public void cancelar() {
		livro = new Livro();
		adicionando = false;
		editando = false;
	}

	public void adicionarLivro() {
		livro = new Livro();
		adicionando = true;
	}

	public void verLivro() {
		livro = livroSelecionado;
	}

	public void editar() {
		editando = true;
	}

	public String alterarFundoInputText() {
		return (editandoAdicionando() ? "none" : "lightgrey");
	}

	public Boolean editandoAdicionando() {
		return (adicionando || editando);
	}

	public Integer quantidadeFichasPorLivro() {
		return servicoLivro.quantidadeFichasPorLivro(livro.getId());
	}
}
