package br.ufrn.imd.controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.util.FuncionarioUtil;
import br.ufrn.imd.util.ValidaDados;
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
	private static FuncionarioUtil funcionarioUtil = new FuncionarioUtil();
	
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
    	
    	boolean nome = ValidaDados.validarTextField(campoNome, labelErroNome, "Nome inválido", FormatoRegex.NOME);
    	boolean dataNascimento = ValidaDados.validarDataPicker(campoDataNascimento, labelErroDataNascimento, "Selecione uma data");
    	boolean sexo = ValidaDados.validarSelecao(rbFeminino, rbMasculino, labelErroSexo, "Selecione uma opção");
    	boolean cpf = ValidaDados.validarTextField(campoCpf, labelErroCpf, "CPF inválido", FormatoRegex.CPF);
    	boolean email = ValidaDados.validarTextField(campoEmail, labelErroEmail, "Email inválido", FormatoRegex.EMAIL);
    	boolean telefone = ValidaDados.validarTextField(campoTelefone, labelErroTelefone, "Telefone inválido", FormatoRegex.TELEFONE);
    	cpf = funcionarioUtil.verificarTextFieldCpf(campoCpf, labelErroCpf);
    	email = funcionarioUtil.verificarTextFieldEmail(campoEmail, labelErroEmail);
    	if (nome && dataNascimento && sexo && cpf && email && telefone) {
    		Funcionario f = new Funcionario();
    		
    		f.setNome(campoNome.getText());
    		Date data = new Date(campoDataNascimento.getValue().toEpochDay());
    	
    		if(rbFeminino.isSelected()) {
    			f.setSexo("Feminino");
    		}
    		else {
    			f.setSexo("Masculino");
    		}
    		f.setDataNascimento(data);
    		f.setCpf(campoCpf.getText());
    		f.setEmail(campoEmail.getText());
    		f.setTelefone(campoTelefone.getText());
    	
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaCadastroEndereco.fxml"));
		    	TelaCadastroEnderecoController.setFuncionario(f);
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
