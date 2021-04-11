package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.FornecedorDAO;
import br.ufrn.imd.modelo.Fornecedor;
import br.ufrn.imd.modelo.HistoricoDeEntrada;
import br.ufrn.imd.modelo.ItemPedido;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaRegistroEntradaController implements Initializable {
	private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
	
	private static HistoricoEntradaObserver historicoObserver = new HistoricoEntradaObserver();
	
	@FXML
    private Label labelErroItens;

    @FXML
    private TableView<ItemPedido> tabelaItens;

    @FXML
    private TableColumn<ItemPedido, Integer> colunaQuantidade;

    @FXML
    private TableColumn<ItemPedido, Produto> colunaProduto;

    @FXML
    private Label labelErroFornecedor;

    @FXML
    private ComboBox<Fornecedor> cbFornecedores;
    
    private Stage registroEntradaStage;
    
    private ObservableList<Fornecedor> obsFornecedores;
    
    private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    
    private ObservableList<ItemPedido> obsItens;
    
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

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
    		carregarItens();
    	}
    }

    @FXML
    void cancelarRegistro(ActionEvent event) {
    	registroEntradaStage.close();
    }

    @FXML
    void registrarEntrada(ActionEvent event) {
    	boolean fornecedor = PedidoUtil.validarFornecedor(cbFornecedores.getValue(), labelErroFornecedor);
    	boolean listaItens = PedidoUtil.validarItens(itens, labelErroItens);
    	
    	if(fornecedor && listaItens) {
    		HistoricoDeEntrada entradaProdutos = new HistoricoDeEntrada();
    		entradaProdutos.setFornecedor(cbFornecedores.getValue());
    		entradaProdutos.setItensPedido(itens);
    		entradaProdutos.setDataOperacao(new Date());
    		
    		//adicionar `entradaProdutos` ao banco de dados
    		historicoObserver.update(entradaProdutos);
    		
    		registroEntradaStage.close();
    	}
    }
    
    public void setRegistroEntradaStage(Stage registroEntradaStage) {
    	this.registroEntradaStage = registroEntradaStage;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("quantidade"));
		colunaProduto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Produto>("produto"));
		
		carregarFornecedores();
	}

	private void carregarFornecedores() {
		fornecedores = fornecedorDAO.findAll();
		obsFornecedores = FXCollections.observableArrayList(fornecedores);
		cbFornecedores.setItems(obsFornecedores);
	}
	
	public void carregarItens() {
		obsItens = FXCollections.observableArrayList(itens);
		tabelaItens.setItems(obsItens);
	}
}
