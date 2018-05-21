package controlador;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.Usuario;
import lombok.Getter;
import lombok.Setter;
import servico.ServicoUsuario;
import util.EnviarEmail;
import util.GerarSenha;
import util.Mensagem;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ControladorAcesso implements Serializable {
	@Inject
	private ServicoUsuario servicoAcesso;
	@Inject
	private ControladorMenu controladorMenu;
	@Getter
	@Setter
	private Usuario usuario;
	@Getter
	@Setter
	private String nome;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String senha;
	@Getter
	@Setter
	private Boolean ativo = false;
	@Getter
	@Setter
	private Boolean editando = false;

	public void acessar(String pagina) throws Exception {
		if (emailExiste()) {
			usuario = servicoAcesso.pesquisarPorEmailSenha(email, senha);
			if (usuario == null) {
				Mensagem.adicionarMensagem(Mensagem.FATAL, "", "Senha incorreta!");
			} else {
				controladorMenu.setPagina(pagina);
				ativo = true;
				Mensagem.adicionarMensagem(Mensagem.INFORMACAO, "Acesso concedido!", "Seja bem vindo " + usuario.getNome() + "!");
			}
		} else {
			Mensagem.adicionarMensagem(Mensagem.FATAL, email, "Email não cadastrado no sistema!");
		}
	}

	public void sair(String pagina) {
		ativo = false;
		controladorMenu.setPagina(pagina);
	}

	public void salvarUsuario(String pagina) {
		if (emailExiste()) {
			Mensagem.adicionarMensagem(Mensagem.FATAL, "", "Email já cadastrado no sistema");
			email = "";
		} else {
			usuario = servicoAcesso.salvar(new Usuario(nome, email, GerarSenha.gerarSenha()));
			Mensagem.adicionarMensagem(Mensagem.INFORMACAO, "Usuário " + usuario.getNome() + " salvo com sucesso!", "A senha foi enviada ao seu email.");
			try {
				EnviarEmail.enviarEmail(usuario.getEmail(), "Esta é a sua senha para acesssar o sistema de Fichamento Bibliográfico.<br/><h1>" + usuario.getSenha() + "</h1>", "Cadastro Fichamento Bibliográfico");
			} catch (Exception e) {
				e.printStackTrace();
			}
			controladorMenu.setPagina(pagina);
		}
	}

	public void alterar(String pagina) {
		usuario = servicoAcesso.atualizar(usuario);
		try {
			EnviarEmail.enviarEmail(usuario.getEmail(), "Seus dados foram editados.<br/>Nome: " + usuario.getNome() + "<br/>Senha: " + usuario.getSenha(), "Alteração de Cadastro Fichamento Bibliográfico");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Mensagem.adicionarMensagem(Mensagem.INFORMACAO, "Editado", "Usuário " + usuario.getNome() + " editado com sucesso!");
		editando = false;
	}

	public void reenviarSenha(String pagina) {
		if (emailExiste()) {
			usuario = servicoAcesso.pesquisarPorEmail(email);
			try {
				EnviarEmail.enviarEmail(usuario.getEmail(), "Esta é a sua senha.<br/><h1>" + usuario.getSenha() + "</h1>", "Reenvio de senha Fichamento Bibliográfico");
				controladorMenu.setPagina(pagina);
				Mensagem.adicionarMensagem(Mensagem.INFORMACAO, "Senha enviada!", "Senha enviada ao email " + usuario.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Mensagem.adicionarMensagem(Mensagem.FATAL, email, "Email não cadastrado no sistema!");
		}
	}

	private Boolean emailExiste() {
		if (servicoAcesso.pesquisarPorEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}
}