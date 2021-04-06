package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.modelo.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class TelaCadastroEnderecoController {
	private static Funcionario funcionario;
	
	@FXML
    private Button botaoContinuar;

    @FXML
    private Button botaoCancelar;

    @FXML
    void abrirTelaCadastroFinal(ActionEvent event) {
    	//
    }

    @FXML
    void cancelarCadastro(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaLogin.fxml"));
    	botaoCancelar.getScene().setRoot(root);
    }

	public static void setFuncionario(Funcionario f) {
		funcionario = f;
	}
}
