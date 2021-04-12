package br.ufrn.imd.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.PedidoDAO;
import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaVisualizacaoPedidosController implements Initializable {
	private static PedidoDAO pedidoDAO = new PedidoDAO();
	
	@FXML
    private TableView<Pedido> tabelaPedidos;

    @FXML
    private TableColumn<Pedido, Date> colunaData;

    @FXML
    private TableColumn<Pedido, Integer> colunaNumero;

    @FXML
    private TableColumn<Pedido, Departamento> colunaDepartamento;

    @FXML
    private TableColumn<Pedido, String> colunaDescricao;

    @FXML
    private TableColumn<Pedido, Boolean> colunaFinalizado;
    
    private ObservableList<Pedido> obsPedidos;
    
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    
    private Stage visualizarPedidosStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colunaData.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("dataPedido"));
		colunaNumero.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("numeroPedido"));
		colunaDepartamento.setCellValueFactory(new PropertyValueFactory<Pedido, Departamento>("departamento"));
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Pedido, String>("descricao"));
		colunaFinalizado.setCellValueFactory(new PropertyValueFactory<Pedido, Boolean>("pedidoFinalizado"));
		
		carregarPedidos();
	}

	private void carregarPedidos() {
		pedidos = pedidoDAO.findAll();
		obsPedidos = FXCollections.observableArrayList(pedidos);
		tabelaPedidos.setItems(obsPedidos);
	}
	
	@FXML
    void fecharHistorico(ActionEvent event) {
		visualizarPedidosStage.close();
    }

	public void setVisualizarPedidosStage(Stage visualizarPedidosStage) {
		this.visualizarPedidosStage = visualizarPedidosStage;
	}
}
