package br.ufrn.imd.util;

import java.util.List;
import java.util.Random;

import br.ufrn.imd.dao.PedidoDAO;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Fornecedor;
import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Produto;
import javafx.scene.control.Label;

/**
 * Classe de validações das entidades Pedido e ItemPedido
 * 
 * @author Karina e Maria Eduarda
 *
 */
public class PedidoUtil {
	private static PedidoDAO pedidoDAO = new PedidoDAO();
	public static boolean validarQuantidade(String qtd, Label labelErro) {
		boolean ehValida = true;
		String labelMensagem = null;
		
		if(qtd.isEmpty()) {
			labelMensagem = "Digite uma quantidade";
			ehValida = false;
		}
		else if(!qtd.matches(FormatoRegex.NUMERO.getFormato()) || Integer.parseInt(qtd) <= 0) {
			labelMensagem = "Quantidade inválida";
			ehValida = false;
		}
		
		labelErro.setText(labelMensagem);
		return ehValida;
	}
	
	public static boolean validarProduto(Produto p, Label labelErro) {
		boolean naoNulo = true;
		String labelMensagem = null;
		
		if(p == null) {
			labelMensagem = "Selecione um produto";
			naoNulo = false;
		}
		
		labelErro.setText(labelMensagem);
		return naoNulo;
	}

	public static boolean validarItens(List<ItemPedido> itens, Label labelErro) {
		boolean naoNulo = true;
		String labelMensagem = null;
		
		if(itens.isEmpty()) {
			labelMensagem = "Adicione pelo menos um item";
			naoNulo = false;
		}
		
		labelErro.setText(labelMensagem);
		return naoNulo;
	}

	public static boolean validarFornecedor(Fornecedor f, Label labelErro) {
		boolean naoNulo = true;
		String labelMensagem = null;
		
		if(f == null) {
			labelMensagem = "Selecione um fornecedor";
			naoNulo = false;
		}
		
		labelErro.setText(labelMensagem);
		return naoNulo;
	}

	public static boolean validarTexto(String texto, Label labelErro, String mensagemErro) {
		boolean naoNulo = true;
		String labelMensagem = null;
		
		if(texto.isEmpty()) {
			labelMensagem = mensagemErro;
			naoNulo = false;
		}
		
		labelErro.setText(labelMensagem);
		return naoNulo;
	}
	
	public static int gerarNumeroPedido() {
		Random gerador = new Random();
		int gerarNumero = 0;
		
		do {
			gerarNumero = gerador.nextInt();
		}while(pedidoDAO.FindByNumero(gerarNumero) != null && gerarNumero < 0);
		
		return gerarNumero;
	}
}
