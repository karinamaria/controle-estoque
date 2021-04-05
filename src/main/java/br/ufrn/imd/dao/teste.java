package br.ufrn.imd.dao;

import br.ufrn.imd.modelo.Produto;
import br.ufrn.imd.util.UsuarioUtil;

public class teste{

	public static void main(String[] args) {
		ProdutoDAO dao = new ProdutoDAO();
//		Produto p = new Produto();
//		
//		p.setCodigo("123456");
//		p.setNome("Arroz");
//		p.setPrecoCompra(3.00);
//		p.setPrecoVenda(5.50);
//		p.setQuantidadeNoEstoque(200);
//		dao.save(p);

		
		//List<Produto> prs = dao.buscarTodos();	
		UsuarioUtil usuario = new UsuarioUtil();
		System.out.println(usuario.criptografarSenha("senha"));
		
	}

}
