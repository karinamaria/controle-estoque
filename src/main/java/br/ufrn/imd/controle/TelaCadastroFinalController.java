package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.DepartamentoDAO;
import br.ufrn.imd.dao.FuncionarioDAO;
import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.modelo.Papel;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.util.UsuarioUtil;
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
	
	private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	
	private static UsuarioUtil usuarioUtil = new UsuarioUtil();
	
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
	
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
    
    private List<Departamento> departamentos = new ArrayList<Departamento>();
    
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarDepartamentos();
	}
    
    public void carregarDepartamentos() {
    	departamentos = departamentoDAO.findAll();

    	obsDepartamentos = FXCollections.observableArrayList(departamentos);
    	cbDepartamentos.setItems(obsDepartamentos);
    }
    
    @FXML
    void realizarCadastro(ActionEvent event) {
    	//
    	boolean funcao = ValidaDados.validarSelecao(rbGerente, rbAuxiliarEstoque, rbChefeDepartamento, labelErroFuncao, "Selecione uma op??o");
    	boolean departamento = ValidaDados.validarDepartamento(cbDepartamentos.getValue(), labelErroDepartamento, "Selecione um departamento");
    	boolean usuario = ValidaDados.validarTextField(campoUsuario, labelErroUsuario, "Usu?rio inv?lido", FormatoRegex.USUARIO);
    	usuario = usuarioUtil.verificarLogin(campoUsuario, labelErroUsuario);
    	boolean senha = ValidaDados.validarString(campoSenha.getText(), labelErroSenha, "Digite uma senha");
    	boolean senhaRepitida = ValidaDados.compararString(campoRepitaSenha.getText(), campoSenha.getText(), labelErroRepitaSenha, "Senhas n?o correspondem");
    	
    	if (funcao && departamento && usuario && senha && senhaRepitida) {
    	
    		funcionario.setDepartamento(cbDepartamentos.getValue());
    	
			try {
				Usuario user = new Usuario();
	    		user.setLogin(campoUsuario.getText());
	    		user.setSenha(usuarioUtil.criptografarSenha(campoSenha.getText()));
	    		funcionario.setUsuario(user);
	    		
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
				
				//adicionar funcion?rio ao banco de dados
				funcionarioDAO.save(funcionario);
				
				botaoCadastrar.getScene().setRoot(root);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
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
