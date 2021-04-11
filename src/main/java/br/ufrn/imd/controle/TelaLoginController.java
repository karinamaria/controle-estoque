package br.ufrn.imd.controle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import br.ufrn.imd.exception.NegocioException;
import br.ufrn.imd.modelo.Papel;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.util.UsuarioUtil;
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
	private Usuario usuario = new Usuario();
	
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
    
    private static UsuarioUtil usuarioUtil = new UsuarioUtil();
   
    
    @FXML
    void abrirTelaCadastroUsuario(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaCadastroFuncionario.fxml"));
    	linkCadastrar.getScene().setRoot(root);
    }
    
    @FXML
    void acessarSistema(ActionEvent event) {
    	try {
    		usuario.setLogin(campoUsuario.getText());
    		usuario.setSenha(campoSenha.getText());
    		
			usuario = usuarioUtil.validarAutenticacaoUsuario(usuario);
			//redirecionar para tela de acordo com login
			Parent root;
			if(usuario.getPermissao() == Papel.GERENTE) {			
				root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaGerente.fxml"));
			}
			else if(usuario.getPermissao() == Papel.AUXILIAR_ESTOQUE) {
				root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaAuxiliarEstoque.fxml"));
			}
			else {
				root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaChefeDepartamento.fxml"));
			}
			botaoEntrar.getScene().setRoot(root);
			
		} catch (NoSuchAlgorithmException e) {
			labelErro.setText("Erro ao fazer login. Tente novamente mais tarde.");
			e.printStackTrace();
		} catch (NegocioException e) {
			labelErro.setText(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
