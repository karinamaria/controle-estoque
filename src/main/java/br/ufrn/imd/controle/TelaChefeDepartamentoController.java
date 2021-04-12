package br.ufrn.imd.controle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.FuncionarioDAO;
import br.ufrn.imd.dao.PedidoDAO;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.modelo.Pedido;
import br.ufrn.imd.util.UsuarioUtil;
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

public class TelaChefeDepartamentoController implements Initializable {
	private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private static PedidoDAO pedidoDAO = new PedidoDAO();
	
	@FXML
    private TableView<Pedido> tabelaHistorico;

    @FXML
    private TableColumn<Pedido, Date> colunaData;

    @FXML
    private TableColumn<Pedido, Integer> colunaNumero;

    @FXML
    private TableColumn<Pedido, String> colunaDescricao;

    @FXML
    private TableColumn<Pedido, Boolean> colunaFinalizado;
	
    private ObservableList<Pedido> obsPedidos;
    
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    
	@FXML
    void realizarPedido(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(TelaPedidoController.class.getResource("/br/ufrn/imd/visao/TelaPedido.fxml"));
    	AnchorPane page = (AnchorPane) loader.load();
    	
    	Stage pedidoStage = new Stage();
    	pedidoStage.setTitle("Realizar pedido");
    	pedidoStage.setResizable(false);
    	pedidoStage.setScene(new Scene(page));
    	
    	TelaPedidoController controller = loader.getController();
    	controller.setPedidoStage(pedidoStage);
    	pedidoStage.showAndWait();
    	
    	carregarPedidos();
    	tabelaHistorico.refresh();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colunaData.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("dataPedido"));
		colunaNumero.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("numeroPedido"));
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Pedido, String>("descricao"));
		colunaFinalizado.setCellValueFactory(new PropertyValueFactory<Pedido, Boolean>("pedidoFinalizado"));
		
		carregarPedidos();
		tabelaHistorico.refresh();
	}

	private void carregarPedidos() {		
		//carregar pedidos associados ao departamento do usuario
		Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorLogin(UsuarioUtil.getUsuarioLogado().getLogin());
		
		pedidos = pedidoDAO.findPedidoByDepartamento(funcionario.getDepartamento().getId());
		
		obsPedidos = FXCollections.observableArrayList(pedidos);
		tabelaHistorico.setItems(obsPedidos);
	}
	
	@FXML
    void sairDoSistema(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/visao/TelaLogin.fxml"));
    	tabelaHistorico.getScene().setRoot(root);
    }
}
