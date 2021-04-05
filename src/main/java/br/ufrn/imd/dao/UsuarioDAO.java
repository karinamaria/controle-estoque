package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Usuario;

public class UsuarioDAO extends DAOFactory<Usuario> {
	public Usuario buscarUsuarioPorLogin(String login) {
		String query = "Select u from Usuario u where p.login='"+login+"'";
		return buscarEntidadePorCampo(query);
	}
	public Usuario buscarUsuarioPorLoginESenha(String login, String senha) {
		String query = "Select u from Usuario u where p.login='"+login+"' and p.senha='"+senha+"'";
		return buscarEntidadePorCampo(query);
	}
}
