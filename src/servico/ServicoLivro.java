package servico;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import entidade.Livro;
@Named
@ApplicationScoped
public class ServicoLivro {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarTodos() {
		return entityManager.createNamedQuery(Livro.PESQUISAR_TODOS).getResultList();
	}
	@Transactional
	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}
	@Transactional
	public Livro atualizar(Livro livro) {
		entityManager.merge(livro);
		return livro;
	}
}
