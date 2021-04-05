package br.ufrn.imd.exception;

/**
 * Responsável por capturar exceções relacionadas às regras de negócio
 * @author Karina e Maria Eduarda
 *
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
}
