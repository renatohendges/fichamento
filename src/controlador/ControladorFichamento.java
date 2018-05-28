package controlador;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entidade.Fichamento;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoFichamento;
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorFichamento implements Serializable {
	@Inject
	private ControladorMenu controladorMenu;
	// @Inject
	// private ControladorLivro controladorLivro;
	// @Inject
	// private ServicoLivro servicoLivro;
	@Inject
	private ServicoFichamento servicoFichamento;
	@Getter
	@Setter
	private Fichamento fichamento;
	@Getter
	@Setter
	private Fichamento fichamentoSelecionado;
	@Getter
	@Setter
	private List<Fichamento> fichamentos;
	@Getter
	@Setter
	private Boolean adicionando = false;
	@Getter
	@Setter
	private Boolean editando = false;
	@PostConstruct
	public void inicializar() {
		fichamento = new Fichamento();
		fichamentoSelecionado = new Fichamento();
		fichamentos = servicoFichamento.pesquisarTodos();
		adicionando = false;
		editando = false;
		controladorMenu.setPagina("/cadastro/fichamento");
		// RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('dataTableFichamentos').unselectAllRows()");
		// context.execute("PF('dataTableFichamentos').clearFilters()");
	}
	public void salvarFichamento() {
		servicoFichamento.salvar(fichamento);
	}
}
