package br.ufrn.imd.util;

import br.ufrn.imd.modelo.FormatoRegex;
import javafx.scene.control.Label;

/**
 * Classe de valida��es da entidade Produto
 * 
 * @author Karina e Maria Eduarda
 *
 */
public class ProdutoUtil {
	//private static ProdutoDAO produtoDAO;
	
	public static boolean validarNomeProduto(String nome, Label labelErro) {
		boolean ehValido = true;
		String labelMensagem = null;
		
		if(nome.isEmpty()) {
			labelMensagem = "Digite um nome";
			ehValido = false;
		}
		else if(!nome.matches(FormatoRegex.NOME.getFormato())) {
			labelMensagem = "Nome inv�lido";
			ehValido = false;
		}
		//verificar se j� existe produto com esse nome no banco de dados
		
		labelErro.setText(labelMensagem);
		return ehValido;
	}
	
	public static boolean validarCodigo(String codigo, Label labelErro) {
		boolean ehValido = true;
		String labelMensagem = null;
		
		if(codigo.isEmpty()) {
			labelMensagem = "Digite um c�digo";
			ehValido = false;
		}	
		//verificar se j� existe produto com esse c�digo no banco de dados
		
		labelErro.setText(labelMensagem);
		return ehValido;
	}
	
	public static boolean validarQuantidade(String qtd, Label labelErro) {
		boolean ehValida = true;
		String labelMensagem = null;
		
		if(qtd.isEmpty()) {
			labelMensagem = "Digite uma quantidade";
			ehValida = false;
		}
		else if(!qtd.matches(FormatoRegex.NUMERO.getFormato())) {
			labelMensagem = "Quantidade inv�lida";
			ehValida = false;
		}
		
		labelErro.setText(labelMensagem);
		return ehValida;
	}
	
	public static boolean validarPreco(String preco, Label labelErro) {
		boolean ehValido = true;
		String labelMensagem = null;
		
		if(preco.isEmpty()) {
			labelMensagem = "Digite um pre�o";
			ehValido = false;
		}
		else if(!preco.matches(FormatoRegex.PRECO.getFormato())) {
			labelMensagem = "Pre�o inv�lido";
			ehValido = false;
		}
		
		labelErro.setText(labelMensagem);
		return ehValido;
	}
}
