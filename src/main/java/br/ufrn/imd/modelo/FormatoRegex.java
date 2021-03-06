package br.ufrn.imd.modelo;
/**
 * Enum que representa os formatos regex para Strings usadas no sistema
 * @author Karina e Maria Eduarda
 *
 */
public enum FormatoRegex {
	NOME("[a-z A-Z ?-?]+"), CPF("\\b[0-9]{11}\\b"), EMAIL("[a-zA-Z0-9._%+-]+@gmail.com"), 
	TELEFONE("\\b[0-9]{11}\\b"), CEP("\\b[0-9]{8}\\b"), NUMERO("\\b[0-9]+\\b"), 
	USUARIO("[a-zA-Z0-9._%+-]+"), PRECO("^(\\d+)(\\,\\d+)?$"), CNPJ("\\b[0-9]{14}\\b"), 
	INSCRICAO_ESTADUAL("\\b[0-9]{9}\\b");
	
	private String formato;

	private FormatoRegex(String formato) {
		this.formato = formato;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
}
