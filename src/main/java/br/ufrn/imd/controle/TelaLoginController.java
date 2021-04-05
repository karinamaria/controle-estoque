package br.ufrn.imd.controle;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginController {
    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Button botaoEntrar;

    @FXML
    private Hyperlink linkCadastrar;
    
    @FXML
    private Label labelErro;

    @FXML
    void abrirTelaCadastroUsuario(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaCadastroFuncionario.fxml"));
    	linkCadastrar.getScene().setRoot(root);
    }
    
    @FXML
    void acessarSistema(ActionEvent event) {
    	//
    }
}
