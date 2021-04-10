package br.ufrn.imd.controle;

import java.io.IOException;

import br.ufrn.imd.modelo.Endereco;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.util.ValidaDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaCadastroEnderecoController {
	private static Funcionario funcionario;
	
	@FXML
    private Button botaoContinuar;

    @FXML
    private Button botaoCancelar;
    
    @FXML
    private Label labelErroRua;

    @FXML
    private TextField campoRua;

    @FXML
    private Label labelErroNumero;

    @FXML
    private TextField campoNumero;

    @FXML
    private TextField campoComplemento;

    @FXML
    private Label labelErroCep;

    @FXML
    private TextField campoCep;

    @FXML
    private Label labelErroBairro;

    @FXML
    private TextField campoBairro;

    @FXML
    private Label labelErroCidade;

    @FXML
    private TextField campoCidade;

    @FXML
    private Label labelErroPais;

    @FXML
    private TextField campoPais;

    @FXML
    void abrirTelaCadastroFinal(ActionEvent event) {
    	
    	boolean rua = ValidaDados.validarTextField(campoRua, labelErroRua, "Rua inválida", FormatoRegex.NOME);
    	boolean numero = ValidaDados.validarTextField(campoNumero, labelErroNumero, "Número inválido", FormatoRegex.NUMERO);
    	boolean cep = ValidaDados.validarTextField(campoCep, labelErroCep, "CEP inválido", FormatoRegex.CEP);
    	boolean bairro = ValidaDados.validarTextField(campoBairro, labelErroBairro, "Bairro inválido", FormatoRegex.NOME);
    	boolean cidade = ValidaDados.validarTextField(campoCidade, labelErroCidade, "Cidade inválida", FormatoRegex.NOME);
    	boolean pais = ValidaDados.validarTextField(campoPais, labelErroPais, "País inválido", FormatoRegex.NOME);
    	
    	if (rua && numero && cep && bairro && cidade && pais) {
    		Endereco end = new Endereco();
    		end.setRua(campoRua.getText());
    		end.setNumero(Integer.parseInt(campoNumero.getText()));
    		end.setComplemento(campoComplemento.getText());
    		end.setBairro(campoBairro.getText());
    		end.setCidade(campoCidade.getText());
    		end.setPais(campoPais.getText());
    		
    		funcionario.setEndereco(end);
    	
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaCadastroFinal.fxml"));
		    	TelaCadastroFinalController.setFuncionario(funcionario);
				botaoContinuar.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
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
