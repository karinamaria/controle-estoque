package br.ufrn.imd.controle;

import br.ufrn.imd.dao.DepartamentoDAO;
import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.util.DepartamentoUtil;
import br.ufrn.imd.util.ValidaDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroDepartamentoController {
	private static DepartamentoUtil departamentoUtil = new DepartamentoUtil();
	
	private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
	
	@FXML
    private Label labelErroNome;

    @FXML
    private TextField campoNome;
    
    private Stage departamentoStage;

    @FXML
    void cadastrarDepartamento(ActionEvent event) {
    	boolean nome = ValidaDados.validarTextField(campoNome, labelErroNome, "Nome inválido", FormatoRegex.NOME);
    	nome = departamentoUtil.verificarTextFieldDepartamento(campoNome, labelErroNome);
    	
    	if(nome) {
    		Departamento departamento = new Departamento();
    		departamento.setNome(campoNome.getText());
    		
    		//adicionar departamento ao banco de dados
    		departamentoDAO.save(departamento);
    		
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
