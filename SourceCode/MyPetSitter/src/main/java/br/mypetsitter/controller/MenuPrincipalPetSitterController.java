package br.mypetsitter.controller;

import java.io.IOException;

import br.mypetsitter.model.LoginFXML;
import br.mypetsitter.model.PetSitterPrincipalFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalPetSitterController {

    @FXML
    void alteraDadosPetSitter(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarCadastroPetSitter.fxml"));
			BorderPane border = PetSitterPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void excluiContaPetSitter(ActionEvent event) {

    }

    @FXML
    void mostraServicos(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoPetSitter.fxml"));
			BorderPane border = PetSitterPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void mostraServicosEmAndamento(ActionEvent event) {

    }

    @FXML
    void mostraServicosPendentes(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
    	LoginFXML loginFXML = new LoginFXML();
    	try {
			loginFXML.start(new Stage());
			fechaJanela();
	    	if(PetSitterPrincipalFXML.getScene() != null) {
	    		PetSitterPrincipalFXML.getScene().setRoot(null);
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    private void fechaJanela() {
    	PetSitterPrincipalFXML.getStage().close();
    }
}