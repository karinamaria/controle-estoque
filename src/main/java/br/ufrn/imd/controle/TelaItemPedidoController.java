package br.ufrn.imd.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Produto;
import br.ufrn.imd.util.PedidoUtil;
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
    
    private ItemPedido itemPedido;

    @FXML
    void adicionarItem(ActionEvent event) {
    	boolean quantidade = PedidoUtil.validarQuantidade(campoQuantidade.getText(), labelErroQuantidade);
    	boolean produto = PedidoUtil.validarProduto(cbProdutos.getValue(), labelErroProduto);
    	
    	if(quantidade && produto) {
    		itemPedido = new ItemPedido();
    		itemPedido.setProduto(cbProdutos.getValue());
    		itemPedido.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
    		
    		itemPedidoStage.close();
    	}
    }
    
    public ItemPedido getItemPedido() {
    	return itemPedido;
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
    	produto1.setPrecoCompra(7);
    	produtos.add(produto1);  	
    	//

    	obsProdutos = FXCollections.observableArrayList(produtos);
    	cbProdutos.setItems(obsProdutos);
    }
}
