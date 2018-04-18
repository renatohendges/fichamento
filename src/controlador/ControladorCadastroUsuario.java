package controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	public void salvar() {
		Usuario usuario = new Usuario(nome, CritpoUtil.stringParaMd5(senha), email, CritpoUtil.stringParaMd5(nome + email + senha));
		servicoAcesso.salvar(usuario);
		Mensagem.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Usuário " + nome + " salvo com sucesso!", "Usuário " + nome + " Salvo com sucesso!");
		try {
			EnviarEmail.enviarEmail(email, CritpoUtil.stringParaMd5(nome + email + senha));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verificarCodigo() {
		String email = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("email");
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		if (!email.equals(null) && !codigo.equals(null)) {
			controladorMenu.setPagina("codigo");
		} else {
			Usuario usuario = servicoAcesso.pesquisarPorEmailCodigo(email, codigo);
			if (usuario == null) {
			} else {
				usuario.setAtivo(true);
				servicoAcesso.atualizar(usuario);
				System.out.println("Teste");
				System.out.println("Teste");
			}
		}
	}

}