package br.mypetsitter.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginAdmFXML extends Application {
	private static Stage stage;
	private static VBox root;
	private static Scene scene;
	public static void setRoot(VBox newRoot) {
		root = newRoot;
	}
	public static VBox getRoot() {
		return root;
	}
	public static Stage getStage() {
		return stage;
	}
	private void setStage(Stage newStage) {
		stage = newStage;
	}
	public static Scene getScene() {
		return scene;
	}
	public static void setScene(Scene newScene) {
		scene = newScene;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginAdm.fxml"));
			VBox root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("MyWork");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			setStage(primaryStage);
			stage.show();			
		}catch(Exception e) {
			System.out.println("ERRO: " + e);
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
