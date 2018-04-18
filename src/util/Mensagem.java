package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Mensagem {
	public static void adicionarMensagem(FacesMessage.Severity serveridade, String sumario, String mensagem) {
		FacesContext.getCurrentInstance().addMessage("Teste1111", new FacesMessage(serveridade, sumario, mensagem));
	}

}
