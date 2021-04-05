package br.ufrn.imd.modelo;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CommonsMail {
	public CommonsMail() throws EmailException {
		
	}
	
	//Antes de chamar esse m�todo � preciso que o usu�rio do funcionario j� tenha sido gerado.
	public void enviaEmailUsuarioSenha(Funcionario funcionario) {
		SimpleEmail email = new SimpleEmail();
		try {
			email.setHostName("smtp.gmail.com");
			email.addTo(funcionario.getEmail(), funcionario.getNome());
			email.setFrom("scestoquev1.0@gmail.com", "Sistema de Controle de Estoque v1.0");
			email.setSubject("Seja bem-vindo(a) ao Sistema de Controle de Estoque");
			email.setMsg("Ol� " + funcionario.getNome() + ","
					+ "\n\nSegue abaixo seus dados de acesso ao Sistema de Controle de Estoque v1.0"
					+ "\n\nUsu�rio: " + funcionario.getUsuario().getLogin()
					+ "\nSenha: " + funcionario.getUsuario().getSenha()
					+ "\n\nSistema de Controle de Estoque v1.0"
					+ "\n\nNota: Este e-mail foi gerado automaticamente. Por favor n�o responda esta mensagem.");
			email.setAuthentication("scestoquev1.0@gmail.com", "0.1v-ecs");
			email.setSmtpPort(465);
			//email.setSSL(true);
			//email.setTLS(true);
			email.send();
		} catch (EmailException e) {
			System.out.println(e.getMessage());
		}
	}
}