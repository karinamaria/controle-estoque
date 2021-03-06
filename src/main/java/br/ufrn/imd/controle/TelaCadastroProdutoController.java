package br.ufrn.imd.controle;

import br.ufrn.imd.dao.ProdutoDAO;
import br.ufrn.imd.modelo.Produto;
import br.ufrn.imd.util.ProdutoUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroProdutoController {
	private static ProdutoDAO produtoDAO = new ProdutoDAO();
	
	@FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoCancelar;

    @FXML
    private Label labelErroNome;

    @FXML
    private TextField campoNome;

    @FXML
    private Label labelErroCodigo;

    @FXML
    private TextField campoCodigo;

    @FXML
    private Label labelErroQtdEstoque;

    @FXML
    private TextField campoQtdEstoque;

    @FXML
    private Label labelErroPrecoCompra;

    @FXML
    private TextField campoPrecoCompra;

	private Stage produtoStage;

    @FXML
    void cadastrarProduto(ActionEvent event) {
    	boolean nome = ProdutoUtil.validarNomeProduto(campoNome.getText(), labelErroNome);
    	boolean codigo = ProdutoUtil.validarCodigo(campoCodigo.getText(), labelErroCodigo);
    	boolean qtdEstoque = ProdutoUtil.validarQuantidade(campoQtdEstoque.getText(), labelErroQtdEstoque);
    	boolean precoCompra = ProdutoUtil.validarPreco(campoPrecoCompra.getText(), labelErroPrecoCompra);
    	
    	if (nome && codigo && qtdEstoque && precoCompra) {
    		Produto produto = new Produto();
    		produto.setNome(campoNome.getText());
    		produto.setCodigo(campoCodigo.getText());
    		produto.setQuantidadeNoEstoque(Integer.parseInt(campoQtdEstoque.getText()));
    		
    		String preco = campoPrecoCompra.getText().replaceAll( "," , "." );
    		produto.setPrecoCompra(Double.parseDouble(preco));
    		
    		//adicionar produto ao banco de dados
    		produtoDAO.save(produto);
    		
    		produtoStage.close();
    	}
    }

    @FXML
    void cancelarCadastro(ActionEvent event) {
    	produtoStage.close();
    }

	public void setProdutoStage(Stage produtoStage) {
		this.produtoStage = produtoStage;
	}
}
