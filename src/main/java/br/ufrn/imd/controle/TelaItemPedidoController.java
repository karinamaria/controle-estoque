package br.ufrn.imd.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.modelo.FormatoRegex;
import br.ufrn.imd.modelo.Produto;
import br.ufrn.imd.util.ValidaDados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaItemPedidoController implements Initializable {
	@FXML
    private ComboBox<Produto> cbProdutos;
	
	@FXML
    private Label labelErroProduto;

    @FXML
    private Label labelErroQuantidade;

    @FXML
    private TextField campoQuantidade;
    
    private ObservableList<Produto> obsProdutos;
    
    private Stage itemPedidoStage;
    
    private List<Produto> produtos = new ArrayList<Produto>();

    @FXML
    void adicionarItem(ActionEvent event) {
    	boolean quantidade = ValidaDados.validarTextField(campoQuantidade, labelErroQuantidade, "Quantidade inválida", FormatoRegex.NUMERO);
    	boolean produto = ValidaDados.validarProduto(cbProdutos.getValue(), labelErroProduto, "Selecione um produto");
    	
    	if(quantidade && produto) {
    		
    		//adicionar produto a lista de itens do pedido
    		
    		itemPedidoStage.close();
    	}
    }

    @FXML
    void cancelarAdicao(ActionEvent event) {
    	itemPedidoStage.close();
    }
    
    public void setItemPedidoStage(Stage itemPedidoStage) {
    	this.itemPedidoStage = itemPedidoStage;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarProdutos();
	}
	
	public void carregarProdutos() {
    	//Teste
    	Produto produto1 = new Produto();
    	produto1.setNome("Produto Teste");
    	produtos.add(produto1);  	
    	//

    	obsProdutos = FXCollections.observableArrayList(produtos);
    	cbProdutos.setItems(obsProdutos);
    }
}
