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
	public List<Livro> pesquisarPorTitulo(String titulo) {
		return entityManager.createNamedQuery(Livro.PESQUISAR_POR_TITULO).setParameter("titulo", titulo).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarPorIsbn(Integer isbn) {
		return entityManager.createNamedQuery(Livro.PESQUISAR_POR_ISBN).setParameter("isbn", isbn).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarPorAutor(String autor) {
		return entityManager.createNamedQuery(Livro.PESQUISAR_POR_AUTOR).setParameter("autor", autor).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarTodos() {
		return entityManager.createNamedQuery(Livro.PESQUISAR_TODOS).getResultList();
	}

	@Transactional
	public Livro salvar(Livro livro) {
		entityManager.persist(livro);
		return livro;
	}

	@Transactional
	public Livro atualizar(Livro livro) {
		entityManager.merge(livro);
		return livro;
	}
}
