package br.mypetsitter.controller;

import java.io.IOException;

import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.LoginAdmFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalAdmController {

    @FXML
    void gerenciarServicos(ActionEvent event) {
    	System.out.println("gereneciar servicos");
    	VBox vboxServico;
		try {
			vboxServico = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoAdm.fxml"));
			BorderPane borderPrincipal = AdmPrincipalFXML.getRoot();
			borderPrincipal.setCenter(vboxServico);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}

    }

    @FXML
    void gerenciarUsuarios(ActionEvent event) {
    	VBox vboxUsuario;
    	try {
			vboxUsuario = FXMLLoader.load(getClass().getResource("/view/PetSitterCliente.fxml"));
			BorderPane borderPrincipal = AdmPrincipalFXML.getRoot();
			borderPrincipal.setCenter(vboxUsuario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void gerenciarCategorias(ActionEvent event) {
    	VBox vboxCategoria;
    	try {
			vboxCategoria = FXMLLoader.load(getClass().getResource("/view/GerenciarCategoriaAdm.fxml"));
			BorderPane borderPrincipal = AdmPrincipalFXML.getRoot();
			borderPrincipal.setCenter(vboxCategoria);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void sair(ActionEvent event) {
    	LoginAdmFXML loginFXML = new LoginAdmFXML();
    	try {
			loginFXML.start(new Stage());
			fechaJanela();
	    	if(AdmPrincipalFXML.getScene() != null) {
	    		AdmPrincipalFXML.getScene().setRoot(null);
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    private void fechaJanela() {
    	AdmPrincipalFXML.getStage().close();
    }

}