package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Produto;

public class ProdutoDAO extends DAOFactory<Produto>{
	
	public Produto buscarProdutoPorNome(String nome) {
		String query = "Select p from Produto p where p.nome='"+nome+"'";
		return buscarEntidadePorCampo(query);
	}
	
}
