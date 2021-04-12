package br.ufrn.imd.controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrn.imd.dao.HistoricoEntradaDAO;
import br.ufrn.imd.modelo.ItemPedido;
import br.ufrn.imd.modelo.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaVisualizarRegistroEntradaController implements Initializable {
	private static HistoricoEntradaDAO historicoEntradaDAO = new HistoricoEntradaDAO();
	
	@FXML
    private ComboBox<Integer> cbNumerosHistorico;

    @FXML
    private TableView<ItemPedido> tabelaItens;

    @FXML
    private TableColumn<ItemPedido, Integer> colunaQuantidade;

    @FXML
    private TableColumn<ItemPedido, Produto> colunaProduto;

    @FXML
    private Label labelFornecedor;

    @FXML
    private Label labelData;
    
    private Stage vrEntradaStage;
    
    private ObservableList<Integer> obsNumHistorico;
    
    private List<Integer> numHistorico = new ArrayList<Integer>();
    
    private ObservableList<ItemPedido> obsItens;
    
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		colunaQuantidade.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("quantidade"));
		colunaProduto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Produto>("produto"));
		
		carregarNumHistorico();
	}
	
	private void carregarNumHistorico() {
		//numHistorico.add(1);
		numHistorico = historicoEntradaDAO.findNumerosHistorico();
		obsNumHistorico = FXCollections.observableArrayList(numHistorico);
    	cbNumerosHistorico.setItems(obsNumHistorico);
	}

	@FXML
    void fecharRegistro(ActionEvent event) {
		vrEntradaStage.close();
    }

	@FXML
    void cbSelecionado(ActionEvent event) { 	
    	//pegar HistoricoDeEntrada que possui o número de cbNumerosHistorico.getValue()
    	
    	//definir
    	//labelFornecedor.setText();
    	//labelData.setText();
    	//setar itens
    	itens = historicoEntradaDAO.FindByNumero(cbNumerosHistorico.getValue()).getItensPedido();
    	
		obsItens = FXCollections.observableArrayList(itens);
		tabelaItens.setItems(obsItens);
    }
    
    public void setVREntradaStage(Stage vrEntradaStage) {
    	this.vrEntradaStage = vrEntradaStage;
    }
}
