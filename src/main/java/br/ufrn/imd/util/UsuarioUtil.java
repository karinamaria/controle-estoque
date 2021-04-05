package br.ufrn.imd.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioUtil {
	public String criptografarSenha(String senha) {
		String senhaCriptograda = "";
	    
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(),0,senha.length());
			senhaCriptograda = new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	   return senhaCriptograda;
	}
}
