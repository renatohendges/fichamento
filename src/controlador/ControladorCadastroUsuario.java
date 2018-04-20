package controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Email;

import entidade.Usuario;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoUsuario;
import util.CritpoUtil;
import util.EnviarEmail;
import util.Mensagem;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorCadastroUsuario implements Serializable {
	@Inject
	private ServicoUsuario servicoAcesso;
	@Inject
	private ControladorMenu controladorMenu;
	@Getter
	@Setter
	private String nome;
	@Getter
	@Setter
	private String senha;
	@Getter
	@Setter
	private String confirmarSenha;
	@Getter
	@Setter
	@Email(message = "Precisa ser um email válido!")
	private String email;
	@Getter
	@Setter
	private Boolean ativado;
	@Getter
	@Setter
	private String codigo;

	@PostConstruct
	public void iniciar() {
		nome = null;
		senha = null;
		confirmarSenha = null;
		email = null;
		ativado = false;
		codigo = null;
	}

	public void salvar(String pagina) {
		if (emailExiste()) {
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_FATAL, "Email já cadastrado no sistema", "Email já cadastrado no sistema");
			email = null;
		} else {
			Usuario usuario = new Usuario(nome, CritpoUtil.stringParaMd5(senha), email, CritpoUtil.stringParaMd5(nome + email + senha));
			servicoAcesso.salvar(usuario);
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Usuário " + nome + " salvo com sucesso!", "Usuário " + nome + " salvo com sucesso!");
			try {
				EnviarEmail.enviarEmail(email, CritpoUtil.stringParaMd5(nome + email + senha));
			} catch (Exception e) {
				e.printStackTrace();
			}
			controladorMenu.setPagina(pagina);
			iniciar();
		}
	}

	public Boolean emailExiste() {
		if (servicoAcesso.pesquisarPorEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void verificarCodigo() {
		Usuario usuario = servicoAcesso.pesquisarPorEmailCodigo(email, codigo);
		if (usuario == null) {
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Código não confere com usuário", "Código não confere com usuário");
		} else {
			usuario.setAtivo(true);
			servicoAcesso.atualizar(usuario);
			controladorMenu.setPagina("/acesso");
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Código aceito! Clique em acessar.", "Código aceito! Clique em acessar.");
			iniciar();
		}
	}

	public void reenviarEmail() {
		Usuario usuario = servicoAcesso.pesquisarPorEmail(email);
		if (usuario == null) {
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Email não está cadastrado no sistema!", "Email não está cadastrado no sistema!");
		} else {
			try {
				EnviarEmail.enviarEmail(email, usuario.getCodigo());
				iniciar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}