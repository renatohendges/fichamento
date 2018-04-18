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
	private String nome;
	@Getter
	@Setter
	private String senha;

	public void acessar(String pagina) throws Exception {
		Usuario usuarioEncontrado = (Usuario) servicoAcesso.pesquisarPorNomeSenha(nome, senha);
		if (usuarioEncontrado == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos!", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			ativo = true;
			usuarioAtivo = usuarioEncontrado;
			controladorMenu.setPagina(pagina);
			Mensagem.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Acesso concedido!", "mensagem");
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/inicio.xhtml");
		}
	}

	public void sair(String pagina) {
		usuarioAtivo = null;
		ativo = false;
		controladorMenu.setPagina(pagina);
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/inicio.xhtml");
	}
}