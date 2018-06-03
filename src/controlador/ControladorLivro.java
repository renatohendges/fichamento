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
	private ControladorPagina controladorPagina;
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
	private RequestContext context;
	@PostConstruct
	public void inicializar() {
		context = RequestContext.getCurrentInstance();
		livro = new Livro();
		livroSelecionado = null;
		livros = servicoLivro.pesquisarTodos();
		adicionando = false;
		editando = false;
		controladorPagina.setPagina("/cadastro/livro");
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
		context.execute("PF('dataTableLivros').clearFilters()");
	}
	public void adicionarLivro() {
		livro = new Livro();
		adicionando = true;
		context.execute("PF('dataTableLivros').clearFilters()");
	}
	public void verLivro() {
		livro = livroSelecionado;
		System.out.println(livro.getTitulo());
		System.out.println(livroSelecionado.getTitulo());
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
