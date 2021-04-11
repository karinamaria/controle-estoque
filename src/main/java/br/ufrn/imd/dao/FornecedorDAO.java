package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.Fornecedor;

public class FornecedorDAO extends DAOFactory<Fornecedor> {
	public Fornecedor save(Fornecedor f) {
		return salvar(f);
	}
	
	public List<Fornecedor> findAll() {
		return buscarTodos();
	}
}
