package br.ufrn.imd.controle;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.dao.PedidoDAO;
import br.ufrn.imd.dao.ProdutoDAO;
import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Observer;
import br.ufrn.imd.modelo.Pedido;
import br.ufrn.imd.modelo.Produto;

public class PedidoObserver implements Observer<Pedido> {
	private static PedidoDAO pedidoDAO = new PedidoDAO();
	private static ProdutoDAO produtoDAO = new ProdutoDAO();

	public void analisarPedidos(Pedido p) {
		boolean ehPossivelRealizarPedido = true;
		for (ItemPedido item : p.getItensPedido()) {
			if (ehPossivelRealizarPedido) {
				if (item.getQuantidade() <= item.getProduto().getQuantidadeNoEstoque()) {
					ehPossivelRealizarPedido = true;
				} else {
					ehPossivelRealizarPedido = false;
				}
			}
		}

		if (ehPossivelRealizarPedido) {
			finalizarPedido(p);
		}
	}

	public void analisarPedidos() {
		List<Pedido> pedidos = pedidoDAO.findAllPedidosNaoFinalizados();
		List<Pedido> pedidosACumprir = new ArrayList<Pedido>();

		boolean ehPossivelRealizarPedido = true;
		if (pedidos != null) {
			for (Pedido pedido : pedidos) {
				for (ItemPedido item : pedido.getItensPedido()) {
					if (ehPossivelRealizarPedido) {
						if (item.getQuantidade() <= item.getProduto().getQuantidadeNoEstoque()) {
							item.getProduto().setQuantidadeNoEstoque(item.getProduto().getQuantidadeNoEstoque() - item.getQuantidade());
							ehPossivelRealizarPedido = true;
						} else {
							ehPossivelRealizarPedido = false;
						}
					} else {
						continue;
					}
				}
				if (ehPossivelRealizarPedido) {
					pedidosACumprir.add(pedido);
				}
			}
		}

		if (ehPossivelRealizarPedido) {
			for (Pedido p : pedidosACumprir) {
				finalizarPedido(p);
			}
		}
	}

	public void finalizarPedido(Pedido p) {

		for (ItemPedido item : p.getItensPedido()) {
			if (item.getQuantidade() <= item.getProduto().getQuantidadeNoEstoque()) {
				Produto produto = produtoDAO.buscarProdutoPorNome(item.getProduto().getNome());
				produto.setQuantidadeNoEstoque(produto.getQuantidadeNoEstoque() - item.getQuantidade());
				produtoDAO.save(produto);
			}
		}
		p.setPedidoFinalizado(true);

	}

	@Override
	public void update(Pedido entidade) {
		pedidoDAO.save(entidade);
	}

}
