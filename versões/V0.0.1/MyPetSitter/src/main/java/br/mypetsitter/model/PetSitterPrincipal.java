package br.mypetsitter.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PetSitterPrincipal extends Application {
	private static Stage stage;
	private static Scene scene;
	private static BorderPane root = new BorderPane();
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage newStage) {
		stage = newStage;
	}
	public static Scene getScene() {
		return scene;
	}
	public static void setScene(Scene newScene) {
		scene = newScene;
	}
	public static void setRoot(BorderPane newRoot) {
		root = newRoot;
	}
	public static BorderPane getRoot() {
		return root;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//VBox vBox = FXMLLoader.load(getClass().getResource("/view/PetSitterPrincipal.fxml"));
			MenuBar menuBar = FXMLLoader.load(getClass().getResource("/view/MenuPrincipalPetSitter.fxml"));
			root.setTop(menuBar);
			//root.setCenter(vBox);
			Scene scene = new Scene(root, 600,400);
			setScene(scene);
			primaryStage.setTitle("MyWork");
			primaryStage.setScene(scene);
			setStage(primaryStage);
			//primaryStage.setResizable(false);
			stage.show();			
		}catch(Exception e) {
			System.out.println("ERRO: " + e);
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
