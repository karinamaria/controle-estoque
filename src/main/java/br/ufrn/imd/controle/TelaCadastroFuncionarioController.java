package br.ufrn.imd.controle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TelaCadastroFuncionarioController {
	@FXML
    private Button botaoContinuar;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label labelErroNome;

    @FXML
    private TextField campoNome;

    @FXML
    private Label labelErroDataNascimento;

    @FXML
    private DatePicker campoDataNascimento;

    @FXML
    private Label labelErroSexo;

    @FXML
    private RadioButton rbFeminino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private Label labelErroCpf;

    @FXML
    private TextField campoCpf;

    @FXML
    private Label labelErroEmail;

    @FXML
    private TextField campoEmail;

    @FXML
    private Label labelErroTelefone;

    @FXML
    private TextField campoTelefone;

    @FXML
    void abrirTelaCadastroEndereco(ActionEvent event) {
    	
    }

    @FXML
    void cancelarCadastro(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaLogin.fxml"));
    	botaoCancelar.getScene().setRoot(root);
    }

    @FXML
    void rbFemininoSelecionado(ActionEvent event) {
    	if(rbFeminino.isSelected()) {
    		rbMasculino.setSelected(false);
    	}
    }

    @FXML
    void rbMasculinoSelecionado(ActionEvent event) {
    	if(rbMasculino.isSelected()) {
    		rbFeminino.setSelected(false);
    	}
    }
}
