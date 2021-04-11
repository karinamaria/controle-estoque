package br.ufrn.imd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.exception.NegocioException;
import br.ufrn.imd.modelo.Usuario;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe de valida��es da entidade Usuario
 * @author Karina e Maria Eduarda
 *
 */
public class UsuarioUtil {
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static Usuario usuarioLogado = new Usuario();
	
	/**
	 * Criptografar a senha de um usu�rio com o padr�o MD5
	 * @param senha a ser criptografada
	 * @return senha criptografada
	 * @throws NoSuchAlgorithmException
	 */
	public String criptografarSenha(String senha) throws NoSuchAlgorithmException {
		String senhaCriptograda = "";
	    
		
		MessageDigest m;
		m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0,senha.length());
		senhaCriptograda = new BigInteger(1,m.digest()).toString(16);
		
	   return senhaCriptograda;
	}
	/**
	 * Validar autentica��o do usu�rio
	 * @param usuario a ser autenticado
	 * @throws NegocioException
	 * @throws NoSuchAlgorithmException
	 */
	public Usuario validarAutenticacaoUsuario(Usuario usuario) throws NegocioException, NoSuchAlgorithmException {
		Usuario aux = usuarioDAO.buscarUsuarioPorLogin(usuario.getLogin());
		
		if(aux == null) {
			throw new NegocioException("Login inv�lido ou usu�rio n�o existe");
		}else {
			String senhaAuxiliar = criptografarSenha(usuario.getSenha());
			
			if(!aux.getSenha().equals(senhaAuxiliar)) {
				throw new NegocioException("Senha inv�lida");
			}
		}
		
		usuarioLogado = aux;
		return aux;
	}
	
	public boolean verificarLogin(TextField texto, Label labelErro) {
		boolean retorno = true;
		try {
			if(texto.getText() == null) {
				retorno = false;
			}
			validarExistenciaLogin(texto.getText());
		}catch(NegocioException ne) {
			retorno = false;
			labelErro.setText(ne.getMessage());
		}
		return retorno;
	}
	
	/**
	 * Validar se existe algum funcionario com o login informado
	 * @param login que est� sendo criado
	 * @return true se existe o usu�rio; false, caso contr�rio
	 * @throws NegocioException 
	 */
	private boolean validarExistenciaLogin(String login) throws NegocioException {
		Usuario aux = usuarioDAO.buscarUsuarioPorLogin(login);
		if(aux != null) {
			throw new NegocioException("Login j� existe");
		}
		return aux != null;
	}
	/**
	 * Ajuda a identificar o usu�rio logado
	 * @return o usu�rio logado
	 */
	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
}
