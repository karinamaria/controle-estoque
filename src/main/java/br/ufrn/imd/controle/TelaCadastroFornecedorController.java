package br.ufrn.imd.controle;

import br.ufrn.imd.dao.FornecedorDAO;
import br.ufrn.imd.modelo.Endereco;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Fornecedor;
import br.ufrn.imd.util.ValidaDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroFornecedorController {
	private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
	
	@FXML
    private Label labelErroNome;

    @FXML
    private TextField campoNome;

    @FXML
    private Label labelErroCnpj;

    @FXML
    private TextField campoCnpj;

    @FXML
    private Label labelErroArea;

    @FXML
    private TextField campoAreaAtuacao;

    @FXML
    private Label labelErroInscricao;

    @FXML
    private TextField campoInscricaoEstadual;

    @FXML
    private Label labelErroEmail;

    @FXML
    private TextField campoEmail;

    @FXML
    private Label labelErroTelefone;

    @FXML
    private TextField campoTelefone;

    @FXML
    private TextField campoSite;

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

    private Stage fornecedorStage;
    
    @FXML
    void cadastrarFornecedor(ActionEvent event) {
    	boolean nome = ValidaDados.validarTextField(campoNome, labelErroNome, "Nome inválido", FormatoRegex.NOME);
    	boolean cnpj = ValidaDados.validarTextField(campoCnpj, labelErroCnpj, "CNPJ inválido", FormatoRegex.CNPJ);
    	boolean area = ValidaDados.validarTextField(campoAreaAtuacao, labelErroArea, "Área de atuação inválida", FormatoRegex.NOME);
    	boolean inscricao = ValidaDados.validarTextField(campoInscricaoEstadual, labelErroInscricao, "Inscrição estadual inválida", FormatoRegex.INSCRICAO_ESTADUAL);
    	boolean email = ValidaDados.validarTextField(campoEmail, labelErroEmail, "Email inválido", FormatoRegex.EMAIL);
    	boolean telefone = ValidaDados.validarTextField(campoTelefone, labelErroTelefone, "Telefone inválido", FormatoRegex.TELEFONE);
    	boolean rua = ValidaDados.validarTextField(campoRua, labelErroRua, "Rua inválida", FormatoRegex.NOME);
    	boolean numero = ValidaDados.validarTextField(campoNumero, labelErroNumero, "Número inválido", FormatoRegex.NUMERO);
    	boolean cep = ValidaDados.validarTextField(campoCep, labelErroCep, "CEP inválido", FormatoRegex.CEP);
    	boolean bairro = ValidaDados.validarTextField(campoBairro, labelErroBairro, "Bairro inválido", FormatoRegex.NOME);
    	boolean cidade = ValidaDados.validarTextField(campoCidade, labelErroCidade, "Cidade inválida", FormatoRegex.NOME);
    	boolean pais = ValidaDados.validarTextField(campoPais, labelErroPais, "País inválido", FormatoRegex.NOME);
    	
    	if (nome && cnpj && area && inscricao && email && telefone && rua && numero && cep && bairro && cidade && pais) {
    		Fornecedor fornecedor = new Fornecedor();
    		fornecedor.setNome(campoNome.getText());
    		fornecedor.setCnpj(campoCnpj.getText());
    		fornecedor.setAreaAtuacao(campoAreaAtuacao.getText());
    		fornecedor.setInscricaoEstadual(campoInscricaoEstadual.getText());
    		fornecedor.setEmail(campoEmail.getText());
    		fornecedor.setTelefone(campoTelefone.getText());
    		fornecedor.setSite(campoSite.getText());
    		
    		Endereco end = new Endereco();
    		end.setRua(campoRua.getText());
    		end.setNumero(Integer.parseInt(campoNumero.getText()));
    		end.setComplemento(campoComplemento.getText());
    		end.setCep(campoCep.getText());
    		end.setBairro(campoBairro.getText());
    		end.setCidade(campoCidade.getText());
    		end.setPais(campoPais.getText());
    		fornecedor.setEndereco(end);
    	
    		//adicionar fornecedor ao banco de dados
    		fornecedorDAO.save(fornecedor);
    		
			fornecedorStage.close();
    	}
    }

    @FXML
    void cancelarCadastro(ActionEvent event) {
    	fornecedorStage.close();
    }
    
	public void setFornecedorStage(Stage fornecedorStage) {
		this.fornecedorStage = fornecedorStage;
	}
}
