package br.ufrn.imd.modelo;
/**
 * Enum que representa os tipos de usuário do sistema
 * @author Karina e Maria Eduarda
 *
 */
public enum Papel {
	AUXILIAR_ESTOQUE("AUXILIAR_ESTOQUE"), GERENTE("GERENTE"), CHEFE_DEPARTAMENTO("CHEFE_DEPARTAMENTO");

	private String descricao;

	private Papel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
