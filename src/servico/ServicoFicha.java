package servico;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import entidade.Ficha;
import entidade.Livro;
import entidade.Usuario;

@Named
@ApplicationScoped
public class ServicoFicha {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Ficha> pesquisarTodos() {
		return entityManager.createNamedQuery(Ficha.PESQUISAR_TODAS).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ficha> pesquisarPorUsuario(Usuario usuario) {
		return entityManager.createNamedQuery(Ficha.PESQUISAR_POR_USUARIO).setParameter("usuario", usuario).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ficha> pesquisarPorLivro(Livro livro) {
		return entityManager.createNamedQuery(Ficha.PESQUISAR_POR_LIVRO).setParameter("livro", livro).getResultList();
	}

	@Transactional
	public void salvar(Ficha ficha) {
		entityManager.persist(ficha);
	}

	@Transactional
	public Ficha atualizar(Ficha ficha) {
		return entityManager.merge(ficha);
	}
}
