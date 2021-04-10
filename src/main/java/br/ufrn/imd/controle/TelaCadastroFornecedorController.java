package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroFornecedorController {
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
    
    private Stage fornecedorStage;

	public void setFornecedorStage(Stage fornecedorStage) {
		this.fornecedorStage = fornecedorStage;
	}
}
