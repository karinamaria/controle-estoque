package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.modelo.Fornecedor;
import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Pedido;
import br.ufrn.imd.modelo.Produto;
import br.ufrn.imd.util.PedidoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaPedidoEstoqueController implements Initializable {
	@FXML
    private Label labelErroFornecedor;

    @FXML
    private ComboBox<Fornecedor> cbFornecedores;

    @FXML
    private Label labelErroItens;

    @FXML
    private TableView<ItemPedido> tabelaItens;

    @FXML
    private TableColumn<ItemPedido, Integer> colunaQuantidade;

    @FXML
    private TableColumn<ItemPedido, Produto> colunaProduto;

    @FXML
    private Label labelTotalPedido;

    @FXML
    private Label labelErroDescricao;

    @FXML
    private TextField campoDescricao;

    @FXML
    private Label labelErroMotivo;

    @FXML
    private TextField campoMotivo;

	private Stage pedidoEstoqueStage;
	
	private ObservableList<Fornecedor> obsFornecedores;
    
    private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    
    private ObservableList<ItemPedido> obsItens;
    
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();
    
    private double totalPedido = 0;

    @FXML
    void adicionarItemPedido(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaItemPedidoController.class.getResource("/br/ufrn/imd/visao/TelaItemPedido.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage itemPedidoStage = new Stage();
    	itemPedidoStage.setTitle("Adicionar item");
    	itemPedidoStage.setResizable(false);
    	itemPedidoStage.setScene(new Scene(page));
    	
    	TelaItemPedidoController controller = loader.getController();
    	controller.setItemPedidoStage(itemPedidoStage);
    	itemPedidoStage.showAndWait();
    	
    	ItemPedido itemAux = controller.getItemPedido();
    	if(itemAux != null) {
    		itens.add(itemAux);
    		
    		totalPedido += itemAux.getQuantidade()*itemAux.getProduto().getPrecoCompra();
    		labelTotalPedido.setText(String.valueOf(totalPedido));
    		
    		carregarItens();
    	}
    }

    @FXML
    void cancelarPedido(ActionEvent event) {
    	pedidoEstoqueStage.close();
    }

    @FXML
    void realizarPedido(ActionEvent event) {
    	boolean fornecedor = PedidoUtil.validarFornecedor(cbFornecedores.getValue(), labelErroFornecedor);
    	boolean listaItens = PedidoUtil.validarItens(itens, labelErroItens);
    	boolean descricao = PedidoUtil.validarTexto(campoDescricao.getText(), labelErroDescricao, "Digite uma descrição");
    	boolean motivo = PedidoUtil.validarTexto(campoMotivo.getText(), labelErroMotivo, "Digite um motivo");
    	
    	if(fornecedor && listaItens && descricao && motivo) {
    		Pedido pedido = new Pedido();
    		Date dataAtual = new Date();
    		
    		//falta definir número do pedido
    		pedido.setDataPedido(dataAtual);
    		pedido.setItensPedido(itens);
    		pedido.setDescricao(campoDescricao.getText());
    		pedido.setMotivo(campoMotivo.getText());
    		pedido.setValorTotal(totalPedido);
    		
    		//passar pedido e fornecedor, cbFornecedores.getValue(), para historico de entrada (?)
    		
    		pedido.setPedidoRealizado(true);
    		pedidoEstoqueStage.close();
    	}
    }

	public void setPedidoEstoqueStage(Stage pedidoEstoqueStage) {
		// TODO Auto-generated method stub
		this.pedidoEstoqueStage = pedidoEstoqueStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("quantidade"));
		colunaProduto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Produto>("produto"));
		
		carregarFornecedores();
	}
	
	public void carregarFornecedores() {
		//Teste
    	Fornecedor fornecedor1 = new Fornecedor();
    	fornecedor1.setNome("Fornecedor Teste");
    	fornecedores.add(fornecedor1);  	
    	//

    	obsFornecedores = FXCollections.observableArrayList(fornecedores);
    	cbFornecedores.setItems(obsFornecedores);
	}
	
	public void carregarItens() {
		obsItens = FXCollections.observableArrayList(itens);
		tabelaItens.setItems(obsItens);
	}
}
