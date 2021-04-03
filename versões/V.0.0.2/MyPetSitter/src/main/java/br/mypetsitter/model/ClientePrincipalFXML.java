package br.mypetsitter.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientePrincipalFXML extends Application {
	private static Stage stage;
	
	public static Stage getStage() {
		return stage;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientePrincipalFXML.fxml"));
			BorderPane root = loader.load();
			Scene scene = new Scene(root);
			//primaryStage.setTitle("MyWork");
			primaryStage.setScene(scene);
			//primaryStage.setResizable(false);
			primaryStage.show();			
		}catch(Exception e) {
			System.out.println("ERRO: " + e);
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
