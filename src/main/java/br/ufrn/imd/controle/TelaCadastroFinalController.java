package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.modelo.Papel;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.util.ValidaDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class TelaCadastroFinalController implements Initializable {
	private static Funcionario funcionario;
	
	private ObservableList<Departamento> obsDepartamentos;
	
	@FXML
    private Button botaoCancelar;
	
	@FXML
    private Button botaoCadastrar;
	
	@FXML
    private Label labelErroFuncao;

    @FXML
    private RadioButton rbGerente;

    @FXML
    private RadioButton rbAuxiliarEstoque;

    @FXML
    private RadioButton rbChefeDepartamento;
    
    @FXML
    private Label labelErroDepartamento;
    
    @FXML
    private ComboBox<Departamento> cbDepartamentos;

    @FXML
    private Label labelErroUsuario;

    @FXML
    private TextField campoUsuario;

    @FXML
    private Label labelErroSenha;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label labelErroRepitaSenha;

    @FXML
    private PasswordField campoRepitaSenha;
    
    private List<Departamento> departamentos = new ArrayList<Departamento>(); //apenas para testes
    
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarDepartamentos();
	}
    
    public void carregarDepartamentos() {
    	//Teste
    	Departamento departamento1 = new Departamento();
    	departamento1.setNome("Departamento Teste");
    	departamentos.add(departamento1);  	
    	//

    	obsDepartamentos = FXCollections.observableArrayList(departamentos);
    	cbDepartamentos.setItems(obsDepartamentos);
    }
    
    @FXML
    void realizarCadastro(ActionEvent event) {
    	//
    	boolean funcao = ValidaDados.validarSelecao(rbGerente, rbAuxiliarEstoque, rbChefeDepartamento, labelErroFuncao, "Selecione uma opção");
    	boolean departamento = ValidaDados.validarDepartamento(cbDepartamentos.getValue(), labelErroDepartamento, "Selecione um departamento");
    	boolean usuario = ValidaDados.validarTextField(campoUsuario, labelErroUsuario, "Usuário inválido", FormatoRegex.USUARIO);
    	//Falta verificar se usuário já existe
    	boolean senha = ValidaDados.validarString(campoSenha.getText(), labelErroSenha, "Digite uma senha");
    	boolean senhaRepitida = ValidaDados.compararString(campoRepitaSenha.getText(), campoSenha.getText(), labelErroRepitaSenha, "Senhas não correspondem");
    	
    	if (funcao && departamento && usuario && senha && senhaRepitida) {
    		Usuario user = new Usuario();
    		user.setLogin(campoUsuario.getText());
    		user.setSenha(campoSenha.getText());
    		funcionario.setUsuario(user);
    		
    		funcionario.setDepartamento(cbDepartamentos.getValue());
    	
			try {
				Parent root;
		    	
				if(rbGerente.isSelected()) {
					funcionario.getUsuario().setPermissao(Papel.GERENTE);
					root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaGerente.fxml"));
				}
				else if(rbAuxiliarEstoque.isSelected()) {
					funcionario.getUsuario().setPermissao(Papel.AUXILIAR_ESTOQUE);
					root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaAuxiliarEstoque.fxml"));
				}
				else {
					funcionario.getUsuario().setPermissao(Papel.CHEFE_DEPARTAMENTO);
					root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaChefeDepartamento.fxml"));
				}
				
				//adicionar funcionário ao banco de dados
				
				botaoCadastrar.getScene().setRoot(root);
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
    void rbAuxiliarEstoqueSelecionado(ActionEvent event) {
    	if(rbAuxiliarEstoque.isSelected()) {
    		if(rbGerente.isSelected()) {
    			rbGerente.setSelected(false);
    		}
    		else if(rbChefeDepartamento.isSelected()) {
    			rbChefeDepartamento.setSelected(false);
    		}
    	}
    }

    @FXML
    void rbChefeDepartamentoSelecionado(ActionEvent event) {
    	if(rbChefeDepartamento.isSelected()) {
    		if(rbGerente.isSelected()) {
    			rbGerente.setSelected(false);
    		}
    		else if(rbAuxiliarEstoque.isSelected()) {
    			rbAuxiliarEstoque.setSelected(false);
    		}
    	}
    }

    @FXML
    void rbGerenteSelecionado(ActionEvent event) {
    	if(rbGerente.isSelected()) {
    		if(rbAuxiliarEstoque.isSelected()) {
    			rbAuxiliarEstoque.setSelected(false);
    		}
    		else if(rbChefeDepartamento.isSelected()) {
    			rbChefeDepartamento.setSelected(false);
    		}
    	}
    }
    
    public static void setFuncionario(Funcionario f) {
		funcionario = f;
	}
}
