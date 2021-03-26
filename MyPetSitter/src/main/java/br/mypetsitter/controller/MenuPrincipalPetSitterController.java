package br.mypetsitter.controller;

import br.mypetsitter.model.LoginFXML;
import br.mypetsitter.model.PetSitterPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MenuPrincipalPetSitterController {

    @FXML
    void alteraDadosPetSitter(ActionEvent event) {

    }

    @FXML
    void excluiContaPetSitter(ActionEvent event) {

    }

    @FXML
    void mostraServicos(ActionEvent event) {

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
	    	if(PetSitterPrincipal.getScene() != null) {
	    		PetSitterPrincipal.getScene().setRoot(null);
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    private void fechaJanela() {
    	PetSitterPrincipal.getStage().close();
    }
}