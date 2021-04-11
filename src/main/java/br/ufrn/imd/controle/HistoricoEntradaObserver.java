package br.ufrn.imd.controle;

import br.ufrn.imd.dao.HistoricoEntradaDAO;
import br.ufrn.imd.dao.ProdutoDAO;
import br.ufrn.imd.modelo.HistoricoDeEntrada;
import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Observer;
import br.ufrn.imd.modelo.Produto;

public class HistoricoEntradaObserver implements Observer<HistoricoDeEntrada>{
	private static HistoricoEntradaDAO dao = new HistoricoEntradaDAO();
	private static ProdutoDAO produtoDAO = new ProdutoDAO();
	private static PedidoObserver pedidoObserver = new PedidoObserver();
	
	@Override
	public void update(HistoricoDeEntrada entidade) {
		for(ItemPedido item : entidade.getItensPedido()) {
			Produto produto = produtoDAO.buscarProdutoPorNome(item.getProduto().getNome());
			produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque()+item.getQuantidade());
			produtoDAO.save(produto);
		}
		dao.save(entidade);
		pedidoObserver.analisarPedidos();
	}

}
