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

public class IncluiCategoriaAdmController {

    @FXML
    private JFXTextField tfdCategoria;

    @FXML
    void incluirCategoria(ActionEvent event) {
    	String nome = tfdCategoria.getText();
    	if(!nome.isEmpty()) {
    		Categoria categoria = new Categoria(nome);
			CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
    		try {
				categoriaDao.insere(categoria);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Confirmação");
				alert.setContentText("Categoria salva com sucesso!");
				alert.showAndWait();
				VBox vbox;
				try {
					vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarCategoriaAdm.fxml"));
					BorderPane border = AdmPrincipalFXML.getRoot();
					border.setCenter(vbox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
    	}
    }

}