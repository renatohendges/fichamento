package servico;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import entidade.Ficha;
import entidade.Livro;

@Named
@ApplicationScoped
public class ServicoFicha {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarTodos() {
		return entityManager.createNamedQuery(Ficha.PESQUISAR_TODAS).getResultList();
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
