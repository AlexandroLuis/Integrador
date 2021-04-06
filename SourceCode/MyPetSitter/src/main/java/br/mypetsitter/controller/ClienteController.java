package br.mypetsitter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClienteController {

    @FXML
    void verificaUsuario(ActionEvent event) {
    	
    	System.out.println(LoginController.getIdUsuario());
    }

}