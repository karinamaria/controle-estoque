package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Funcionario;

public class FuncionarioDAO extends DAOFactory<Funcionario> {
	public Funcionario save(Funcionario f) {
		return salvar(f);
	}
	
	public Funcionario buscarFuncionarioPorLogin(String login) {
		String query = "Select f from Funcionario f where f.usuario.login='"+login+"'";
		return buscarEntidadePorCampo(query);
	}
	
	public Funcionario buscarFuncionarioPorEmail(String email) {
		String query = "Select f from Funcionario f where f.email='"+email+"'";
		return buscarEntidadePorCampo(query);
	}
}
