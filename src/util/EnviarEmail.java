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
		String gmail = "fichabiblio@gmail.com";
		String senha = "Rjhj1981";
		String servidor = "smtp.gmail.com";
		String porta = "587";
		Properties propriedades = new Properties();

		propriedades.put("mail.smtp.auth", "true");
		propriedades.put("mail.smtp.starttls.enable", "true");
		propriedades.put("mail.smtp.host", "smtp.gmail.com");
		propriedades.put("mail.smtp.port", "587");
		propriedades.put("mail.transport.protocol", "smtp");
		Session secao = Session.getDefaultInstance(propriedades, new Authenticator() {
			@SuppressWarnings("unused")
			protected PasswordAuthentication getPasswordAutentication() {
				return new PasswordAuthentication(gmail, senha);
			}
		});
		MimeMessage mineMessage = new MimeMessage(secao);
		mineMessage.addFrom(InternetAddress.parse(gmail));
		mineMessage.setRecipients(Message.RecipientType.TO, para);
		mineMessage.setSubject(assunto);
		mineMessage.setContent(mensagem, "text/html");
		Transport transport = secao.getTransport();
		try {
			transport.connect("smtp.gmail.com", 587, gmail, senha);
			transport.sendMessage(mineMessage, mineMessage.getRecipients(Message.RecipientType.TO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		transport.close();
	}
}
