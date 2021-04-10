package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.util.ValidaDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroDepartamentoController {
	@FXML
    private Label labelErroNome;

    @FXML
    private TextField campoNome;
    
    private Stage departamentoStage;

    @FXML
    void cadastrarDepartamento(ActionEvent event) {
    	boolean nome = ValidaDados.validarTextField(campoNome, labelErroNome, "Nome inválido", FormatoRegex.NOME);
    	
    	if(nome) {
    		Departamento departamento = new Departamento();
    		departamento.setNome(campoNome.getText());
    		
    		//adicionar departamento ao banco de dados
    		
    		departamentoStage.close();
    	}
    }

    @FXML
    void cancelarCadastro(ActionEvent event) {
    	departamentoStage.close();
    }

	public void setDepartamentoStage(Stage departamentoStage) {
		this.departamentoStage = departamentoStage;
	}
}
