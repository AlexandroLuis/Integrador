package br.mypetsitter.controller;

import java.io.IOException;
import java.sql.SQLException;
import com.jfoenix.controls.JFXPasswordField;
import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.PetSitter;
import br.mypetsitter.model.PetSitterPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MudarSenhaPetSitterController {

	 @FXML
	    private JFXPasswordField tfdSenhaAtual;

	    @FXML
	    private JFXPasswordField tfdNovaSenha;

	    @FXML
	    private JFXPasswordField tfdConfirmaNovaSenha;

    @FXML
    void alterarSenha(ActionEvent event) {
    	if(!(tfdSenhaAtual.getText().isEmpty()) && !(tfdNovaSenha.getText().isEmpty()) && !(tfdConfirmaNovaSenha.getText().isEmpty())) {
    		System.out.println("entra");
    		PetSitterDAOJDBC petsitterDao = new PetSitterDAOJDBC();
    		try {
    			PetSitter petSitterBd = new PetSitter();
				petSitterBd = petsitterDao.buscaPorCodigo(LoginController.getIdUsuario());
				System.out.println(petSitterBd.getCpf());
				if(petSitterBd.getSenha().equals(tfdSenhaAtual.getText())) {
					if(tfdNovaSenha.getText().equals(tfdConfirmaNovaSenha.getText())) {
						String petSitterId = LoginController.getIdUsuario();
						String senha = tfdNovaSenha.getText();
						PetSitter newPetSitter = new PetSitter(petSitterId, senha);
						try {
							petsitterDao.alteraSenha(newPetSitter);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Confirmação");
							alert.setContentText("Senha alterada com sucesso!");
							alert.showAndWait();
							try {
								VBox vbox = FXMLLoader.load(getClass().getResource("/view/PetSitterPrincipal.fxml"));
								BorderPane border = PetSitterPrincipal.getRoot();
								border.setCenter(vbox);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}catch(SQLException e) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setContentText("Não foi possível alterar a senha!");
							alert.showAndWait();
						}
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setContentText("Os campos Nova senha e Confirmar senha precisam ser iguais!");
						alert.showAndWait();
					}
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erro");
					alert.setContentText("Senha atual inválida!");
					alert.showAndWait();					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setContentText("Não  foi possível alterar a senha!");
				alert.showAndWait();
				System.out.println(e);
			}
    		
    	} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setContentText("Um ou mais campos nulos!");
			alert.showAndWait();
			//System.out.println(e);
    	}
    }

}