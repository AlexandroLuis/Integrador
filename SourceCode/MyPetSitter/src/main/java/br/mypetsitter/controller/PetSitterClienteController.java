package br.mypetsitter.controller;

import java.io.IOException;

import br.mypetsitter.model.AdmPrincipalFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PetSitterClienteController {

    @FXML
    void gerenciarCliente(ActionEvent event) {

    }

    @FXML
    void gerenciarPetSitter(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarPetSitter.fxml"));
			BorderPane border = AdmPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}