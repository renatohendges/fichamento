package servico;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import entidade.Fichamento;
@Named
@ApplicationScoped
public class ServicoFichamento {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	public List<Fichamento> pesquisarTodos() {
		return entityManager.createNamedQuery(Fichamento.PESQUISAR_TODAS).getResultList();
	}
	@Transactional
	public void salvar(Fichamento fichamento) {
		entityManager.persist(fichamento);
	}
	@Transactional
	public Fichamento atualizar(Fichamento fichamento) {
		return entityManager.merge(fichamento);
	}
}
