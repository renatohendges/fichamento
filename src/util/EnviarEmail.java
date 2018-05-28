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
	public static void enviarEmail(String para, String mensagem, String assunto) throws Exception {
		String email = "naoresponder@ficha.tk";
		String usuario = "fichabiblio";
		String senha = "Rjhj1981";
		String servidor = "mail.smtp2go.com";
		Integer porta = 2525;
		Properties propriedades = new Properties();
		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.host", servidor);
		propriedades.put("mail.smtp.port", porta);
		propriedades.put("mail.transport.protocol", "smtp");
		Session secao = Session.getDefaultInstance(propriedades, new Authenticator() {
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAutentication() {
				return new PasswordAuthentication(usuario, senha);
			}
		});
		MimeMessage mineMessage = new MimeMessage(secao);
		mineMessage.addFrom(InternetAddress.parse(email));
		mineMessage.setRecipients(Message.RecipientType.TO, para);
		mineMessage.setSubject("[NÃO RESPONDA A ESTE EMAIL] " + assunto);
		mineMessage.setContent(mensagem, "text/html");
		Transport transport = secao.getTransport();
		try {
			transport.connect(servidor, porta, usuario, senha);
			transport.sendMessage(mineMessage, mineMessage.getRecipients(Message.RecipientType.TO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		transport.close();
	}
}
