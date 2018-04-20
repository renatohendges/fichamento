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
	public static void enviarEmail(String para, String codigo) throws Exception {
		String gmail = "fichabiblio@gmail.com";
		Properties propriedades = new Properties();

		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.host", "smtp.gmail.com");
		propriedades.put("mail.smtp.port", "587");
		propriedades.put("mail.transport.protocol", "smtp");
		Session secao = Session.getDefaultInstance(propriedades, new Authenticator() {
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAutentication() {
				return new PasswordAuthentication(gmail, "Rjhj1981");
			}
		});
		MimeMessage mensagem = new MimeMessage(secao);
		mensagem.addFrom(InternetAddress.parse(gmail));
		mensagem.setRecipients(Message.RecipientType.TO, para);
		mensagem.setSubject("Validação de email");
		mensagem.setContent(codigo, "text/html");
		Transport transport = secao.getTransport();
		try {
			transport.connect("smtp.gmail.com", 587, gmail, "Rjhj1981");
			transport.sendMessage(mensagem, mensagem.getRecipients(Message.RecipientType.TO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		transport.close();
	}
}
