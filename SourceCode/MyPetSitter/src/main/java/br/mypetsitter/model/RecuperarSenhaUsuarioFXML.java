package br.mypetsitter.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecuperarSenhaUsuarioFXML extends Application {
	private static VBox root;
	private static Stage stage;
	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecuperarSenhaUsuario.fxml"));
			VBox root = loader.load();
			setRoot(root);
			Scene scene = new Scene(root);
			setScene(scene);
			primaryStage.setScene(scene);
			setStage(primaryStage);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
		}
	}

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
}
