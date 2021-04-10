package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.Produto;

public class ProdutoDAO extends DAOFactory<Produto>{
	
	public Produto save(Produto p) {
		return salvar(p);
	}
	
	public Produto buscarProdutoPorNome(String nome) {
		String query = "Select p from Produto p where p.nome='"+nome+"'";
		return buscarEntidadePorCampo(query);
	}
	
	public List<Produto> findAll(){
		return buscarTodos();
	}
	
}
