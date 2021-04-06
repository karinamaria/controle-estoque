package br.ufrn.imd.util;

import br.ufrn.imd.modelo.FormatoRegex;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ValidaDados {
	public static boolean validarDataPicker(DatePicker campoDataNascimento, Label labelErro, String mensagemErro) {
		boolean ehValida = true;
		String labelMensagem = null;
		
		if(campoDataNascimento.getValue() == null) {
			labelMensagem = mensagemErro;
			ehValida = false;
		}
		
		labelErro.setText(labelMensagem);
		return ehValida;
	}
	
	public static boolean validarTextField(TextField texto, Label labelErro, String mensagemErro, FormatoRegex formatacao) {
		boolean ehValido = true;
		String labelMensagem = null;
		
		if(!texto.getText().matches(formatacao.getFormato())) {
			labelMensagem = mensagemErro;
			ehValido = false;
		}
		
		labelErro.setText(labelMensagem);
		return ehValido;
	}
	
	public static boolean validarSelecao(RadioButton opcao1, RadioButton opcao2, Label labelErro, String mensagemErro) {
		boolean umaOpcaoSelecionada = true;
		String labelMensagem = null;
		
		if(!(opcao1.isSelected() || opcao2.isSelected())) {
			labelMensagem = mensagemErro;
			umaOpcaoSelecionada = false;
		}
		
		labelErro.setText(labelMensagem);
		return umaOpcaoSelecionada;
	}
}
