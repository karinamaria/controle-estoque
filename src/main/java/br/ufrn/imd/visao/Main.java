package br.ufrn.imd.visao;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(carregarTelaLogin());
		stage.setScene(scene);
		stage.setTitle("Sistema de Controle de Estoque v1.0");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Parent carregarTelaLogin() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
		return root;
	}
}
