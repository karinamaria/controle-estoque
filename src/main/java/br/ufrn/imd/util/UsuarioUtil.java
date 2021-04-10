package br.ufrn.imd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufrn.imd.dao.UsuarioDAO;
import br.ufrn.imd.exception.NegocioException;
import br.ufrn.imd.modelo.Usuario;

/**
 * Classe de valida��es da entidade Usuario
 * @author Karina e Maria Eduarda
 *
 */
public class UsuarioUtil {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
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
	public void validarAutenticacaoUsuario(Usuario usuario) throws NegocioException, NoSuchAlgorithmException {
		Usuario aux = usuarioDAO.buscarUsuarioPorLogin(usuario.getLogin());
		
		if(aux == null) {
			throw new NegocioException("Login inv�lido ou usu�rio n�o existe");
		}else {
			String senhaAuxiliar = criptografarSenha(usuario.getSenha());
			
			if(!aux.getSenha().equals(senhaAuxiliar)) {
				throw new NegocioException("Senha inv�lida");
			}
		}
	}
}
