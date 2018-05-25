package controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import entidade.Ficha;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoFicha;
import servico.ServicoLivro;

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
	@Getter
	@Setter
	private Boolean adicionando = false;
	@Getter
	@Setter
	private Boolean editando = false;

	@PostConstruct
	public void inicializar() {
		ficha = new Ficha();
		fichaSelecionada = new Ficha();
		fichas = servicoFicha.pesquisarTodos();
		adicionando = false;
		editando = false;
		controladorMenu.setPagina("/cadastro/ficha");
		RequestContext context = RequestContext.getCurrentInstance();
		// context.execute("PF('dataTableFichas').unselectAllRows()");
		// context.execute("PF('dataTableFichas').clearFilters()");
	}

	public void salvarFicha() {
		servicoFicha.salvar(ficha);
	}
}
