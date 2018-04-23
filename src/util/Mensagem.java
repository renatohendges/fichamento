package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Mensagem {
	public static final FacesMessage.Severity INFORMACAO = FacesMessage.SEVERITY_INFO;
	public static final FacesMessage.Severity ERRO = FacesMessage.SEVERITY_ERROR;
	public static final FacesMessage.Severity FATAL = FacesMessage.SEVERITY_FATAL;
	public static final FacesMessage.Severity AVISO = FacesMessage.SEVERITY_WARN;

	public static void adicionarMensagem(FacesMessage.Severity serveridade, String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage("Teste1111", new FacesMessage(serveridade, titulo, mensagem));
	}

}
