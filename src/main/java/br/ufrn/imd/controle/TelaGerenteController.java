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
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaGerenteController implements Initializable {
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

    @FXML
    private TableColumn<Produto, Double> colunaPrecoVenda;
    
    private ObservableList<Produto> obsProdutos;
    
    private List<Produto> produtos = new ArrayList<Produto>(); //apenas para teste
    
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	colunaNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
    	colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produto, String>("codigo"));
    	colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidadeNoEstoque"));
    	colunaPrecoCompra.setCellValueFactory(new PropertyValueFactory<Produto, Double>("precoCompra"));
    	colunaPrecoVenda.setCellValueFactory(new PropertyValueFactory<Produto, Double>("precoVenda"));
    	
		carregarProdutos();
	}
    
    public void carregarProdutos() {
//    	//Teste
//    	Produto produto1 = new Produto();
//    	produto1.setNome("Produto 1");
//    	produto1.setCodigo("COD01");
//    	produto1.setQuantidadeNoEstoque(10);
//    	produto1.setPrecoCompra(6.13);
//    	produto1.setPrecoVenda(7);
//    	produtos.add(produto1);
//    	Produto produto2 = new Produto();
//    	produto2.setNome("Produto 2");
//    	produto2.setCodigo("COD02");
//    	produto2.setQuantidadeNoEstoque(17);
//    	produto2.setPrecoCompra(3.93);
//    	produto2.setPrecoVenda(9);
//    	produtos.add(produto2);
//    	//
    	produtos = produtoDAO.findAll();
    	obsProdutos = FXCollections.observableArrayList(produtos);
    	tabelaProdutos.setItems(obsProdutos);
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
    void abrirTelaCadastroDepartamento(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaCadastroDepartamentoController.class.getResource("/br/ufrn/imd/visao/TelaCadastroDepartamento.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage departamentoStage = new Stage();
    	departamentoStage.setTitle("Cadastro de departamento");
    	departamentoStage.setResizable(false);
    	departamentoStage.setScene(new Scene(page));
    	
    	TelaCadastroDepartamentoController controller = loader.getController();
    	controller.setDepartamentoStage(departamentoStage);
    	departamentoStage.showAndWait();
    }
	
	@FXML
    void abrirTelaCadastroFornecedor(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaCadastroFornecedorController.class.getResource("/br/ufrn/imd/visao/TelaCadastroFornecedor.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage fornecedorStage = new Stage();
    	fornecedorStage.setTitle("Cadastro de fornecedor");
    	fornecedorStage.setResizable(false);
    	fornecedorStage.setScene(new Scene(page));
    	
    	TelaCadastroFornecedorController controller = loader.getController();
    	controller.setFornecedorStage(fornecedorStage);
    	fornecedorStage.showAndWait();
    }
}
