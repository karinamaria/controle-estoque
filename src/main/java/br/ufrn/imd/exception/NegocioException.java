package br.ufrn.imd.exception;

/**
 * Respons�vel por capturar exce��es relacionadas �s regras de neg�cio
 * @author Karina e Maria Eduarda
 *
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
}
