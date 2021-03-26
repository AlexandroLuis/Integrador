package br.mypetsitter.controller;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class IncluiServicoAutonomoController {
    @FXML
    private JFXButton btnIncluirServico;
    
    @FXML
    void pesquisar(ActionEvent event) {
    	btnIncluirServico.setDisable(false);;
    }


}
