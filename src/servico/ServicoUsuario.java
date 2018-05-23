package servico;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import entidade.Usuario;

@Named
@ApplicationScoped
public class ServicoUsuario {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorEmailSenha(String email, String senha) {
		List<Usuario> usuarios = entityManager.createNamedQuery(Usuario.PESQUISAR_POR_EMAIL_SENHA).setParameter("email", email).setParameter("senha", senha).getResultList();
		if (usuarios.size() >= 1) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorNome(String nome) {
		List<Usuario> usuarios = entityManager.createNamedQuery(Usuario.PESQUISAR_POR_NOME).setParameter("nome", nome).getResultList();
		if (usuarios.size() >= 1) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorEmail(String email) {
		List<Usuario> usuarios = entityManager.createNamedQuery(Usuario.PESQUISAR_POR_EMAIL).setParameter("email", email).getResultList();
		if (usuarios.size() >= 1) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	public void salvar(Usuario usuario) {
		entityManager.persist(usuario);
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		entityManager.merge(usuario);
		return usuario;
	}
}
