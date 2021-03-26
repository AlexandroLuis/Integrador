package br.mypetsitter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PetSitterController {
    @FXML
    void testeUsuario(ActionEvent event) {
    	System.out.println("Usuario: " + LoginController.getIdUsuario());
    }
}
