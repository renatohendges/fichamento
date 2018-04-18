package servico;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import entidade.Usuario;
import util.CritpoUtil;

@Named
@ApplicationScoped
public class ServicoUsuario {
	@Inject
	@Named("fichaEM")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorNomeSenha(String nome, String senha) {
		senha = CritpoUtil.stringParaMd5(senha);
		List<Usuario> usuarios = entityManager.createNamedQuery(Usuario.PESQUISAR_POR_NOME_SENHA).setParameter("nome", nome).setParameter("senha", senha).getResultList();
		if (usuarios.size() == 1) {
			return usuarios.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorEmailCodigo(String email, String codigo) {
		List<Usuario> usuarios = entityManager.createNamedQuery(Usuario.PESQUISAR_POR_EMAIL_CODIGO).setParameter("email", email).setParameter("codigo", codigo).getResultList();
		if (usuarios.size() == 1) {
			return usuarios.get(0);

		} else {
			return null;
		}
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		entityManager.persist(usuario);
		return usuario;
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		entityManager.merge(usuario);
		return usuario;
	}
}
