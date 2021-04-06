package br.mypetsitter.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import br.mypetsitter.dao.CategoriaDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.Categoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AlteraCategoriaAdmController {

    @FXML
    private JFXTextField tfdCategoria;
    @FXML
    private void initialize() {
    	tfdCategoria.setText(GerenciarCategoriaController.getNome());
    }
    @FXML
    void alterarCategoria(ActionEvent event) {
    	String nome = tfdCategoria.getText();
    	Categoria categoria = new Categoria(GerenciarCategoriaController.getCategoriaId(), nome);
    	CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
    	try {
			categoriaDao.atualiza(categoria);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setContentText("Categoria alterada com sucesso!");
			alert.showAndWait();
			try {
				VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarCategoriaAdm.fxml"));
				BorderPane border = AdmPrincipalFXML.getRoot();
				border.setCenter(vbox);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um erro ao atualizar a categoria");
			alert.setContentText("Certifique-se de que o campo foi preenchido corretamente!");
			alert.showAndWait();
		}
    }

}