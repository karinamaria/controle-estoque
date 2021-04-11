package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.ProdutoDAO;
import br.ufrn.imd.modelo.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAuxiliarEstoqueController implements Initializable {
	private static ProdutoDAO produtoDAO = new ProdutoDAO();
	
	@FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, String> colunaCodigo;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Integer> colunaQtdEstoque;

    @FXML
    private TableColumn<Produto, Double> colunaPrecoCompra;
    
    private ObservableList<Produto> obsProdutos;
    
    private List<Produto> produtos = new ArrayList<Produto>();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	colunaNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
    	colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
    	colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidadeNoEstoque"));
    	colunaPrecoCompra.setCellValueFactory(new PropertyValueFactory<Produto, Double>("precoCompra"));
    	
		carregarProdutos();
	}
    
    public void carregarProdutos() {
    	produtos = produtoDAO.findAll();
    	obsProdutos = FXCollections.observableArrayList(produtos);
    	tabelaProdutos.setItems(obsProdutos);
    }
    
    @FXML
    void atualizarProdutos(ActionEvent event) {
    	carregarProdutos();
    }

    @FXML
    void abrirTelaCadastroProduto(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaCadastroProdutoController.class.getResource("/br/ufrn/imd/visao/TelaCadastroProduto.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage produtoStage = new Stage();
    	produtoStage.setTitle("Cadastro de produto");
    	produtoStage.setResizable(false);
    	produtoStage.setScene(new Scene(page));
    	
    	TelaCadastroProdutoController controller = loader.getController();
    	controller.setProdutoStage(produtoStage);
    	produtoStage.showAndWait();
    }

    @FXML
    void registrarEntradaProdutos(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaRegistroEntradaController.class.getResource("/br/ufrn/imd/visao/TelaRegistroEntrada.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage registroEntradaStage = new Stage();
    	registroEntradaStage.setTitle("Registro de entrada de produtos");
    	registroEntradaStage.setResizable(false);
    	registroEntradaStage.setScene(new Scene(page));
    	
    	TelaRegistroEntradaController controller = loader.getController();
    	controller.setRegistroEntradaStage(registroEntradaStage);
    	registroEntradaStage.showAndWait();
    }
    
    @FXML
    void visualizarPedidos(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaVisualizacaoPedidosController.class.getResource("/br/ufrn/imd/visao/TelaVisualizacaoPedidos.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage visualizarPedidoStage = new Stage();
    	visualizarPedidoStage.setTitle("Histórico de pedidos");
    	visualizarPedidoStage.setResizable(false);
    	visualizarPedidoStage.setScene(new Scene(page));
    	
    	TelaVisualizacaoPedidosController controller = loader.getController();
    	controller.setVisualizarPedidosStage(visualizarPedidoStage);
    	visualizarPedidoStage.showAndWait();
    }

    @FXML
    void sairDoSistema(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaLogin.fxml"));
    	tabelaProdutos.getScene().setRoot(root);
    }
}
