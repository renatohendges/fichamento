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
import util.Mensagem;
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorLivro implements Serializable {
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
	public void inicializar() {
		livro = new Livro();
		livroSelecionado = new Livro();
		livros = servicoLivro.pesquisarTodos();
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
		return (editandoAdicionando() ? "" : "lightgrey");
	}
	public Boolean editandoAdicionando() {
		return (adicionando || editando);
	}
}
