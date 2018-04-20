package controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.Usuario;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoUsuario;
import util.Mensagem;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorUsuario implements Serializable {

	@Inject
	private ServicoUsuario servicoAcesso;

	@Inject
	private ControladorMenu controladorMenu;

	@Getter
	@Setter
	private Boolean ativo = false;
	@Getter
	@Setter
	private Usuario usuarioAtivo;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String senha;

	public void acessar(String pagina) throws Exception {
		Usuario usuarioEncontrado = (Usuario) servicoAcesso.pesquisarPorEmailSenha(email, senha);
		if (usuarioEncontrado == null) {
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", "Usuário ou senha inválidos");
		} else if (!usuarioEncontrado.getAtivo()) {
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Usuário não está ativo no sistema", "Usuário não está ativo no sistema");
			controladorMenu.setPagina("/reenviarcodigo");
		} else {
			ativo = true;
			usuarioAtivo = usuarioEncontrado;
			controladorMenu.setPagina(pagina);
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Acesso concedido!", "mensagem");
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
		}
	}

	public void sair(String pagina) {
		usuarioAtivo = null;
		ativo = false;
		controladorMenu.setPagina(pagina);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
	}
}