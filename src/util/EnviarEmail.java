package util;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public abstract class EnviarEmail {
	public static void enviarEmail(String destinatario, String corpoMensagem, String assunto) throws Exception {
		String remetente = "fichabiblio@gmail.com";
		String senha = "Rjhj1981";
		String servidor = "smtp.gmail.com";
		Integer porta = 587;
		Properties propriedades = new Properties();
		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.host", servidor);
		propriedades.put("mail.smtp.port", porta);
		propriedades.put("mail.transport.protocol", "smtp");
		Session secao = Session.getDefaultInstance(propriedades, new Authenticator() {
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAutentication() {
				return new PasswordAuthentication(remetente, senha);
			}
		});
		MimeMessage mensagem = new MimeMessage(secao);
		mensagem.addFrom(InternetAddress.parse(remetente));
		mensagem.setSubject("[NÃO RESPONDA A ESTE EMAIL] " + assunto);
		mensagem.setContent(corpoMensagem, "text/html");
		mensagem.setRecipients(Message.RecipientType.TO, destinatario);
		Transport transport = secao.getTransport();
		try {
			transport.connect(servidor, porta, remetente, senha);
			transport.sendMessage(mensagem, mensagem.getRecipients(Message.RecipientType.TO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		transport.close();
	}
}
