package br.mypetsitter.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class CadastroUsuarioFXML extends Application {
	private static Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroUsuario.fxml"));
			ScrollPane root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			setStage(primaryStage);
			stage.setResizable(false);
			stage.show();
		}catch(Exception e) {
			System.out.println("ERRO: " + e);
		}
	}
	private void setStage(Stage stage) {
		CadastroUsuarioFXML.stage = stage;
	}
	public static Stage getStage() {
		return stage;
	}
	
	
}
