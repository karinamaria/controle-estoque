package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.Produto;

public class ProdutoDAO extends DAOFactory<Produto>{
	
	public Produto save(Produto p) {
		return salvar(p);
	}
	
	public List<Produto> findAll(){
		return buscarTodos();
	}
	
	public Produto findById(Integer id) {
		return buscarPorId(id);
	}
	
	public Produto findByField(Object campo, Object valor) {
		return buscarPorCampo(campo, valor);
	}
}
