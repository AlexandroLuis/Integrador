package br.mypetsitter.controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXPasswordField;

import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.LoginFXML;
import br.mypetsitter.model.PetSitter;
import br.mypetsitter.model.RecuperarSenhaUsuarioFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RecuperarSenhaUsuarioController {

	@FXML
	private JFXPasswordField tfdNovaSenha;

	@FXML
	private JFXPasswordField tfdConfirmaNovaSenha;

	@FXML
	void alterarSenha(ActionEvent event) {
		String senha = tfdNovaSenha.getText();
		String confirmaSenha = tfdConfirmaNovaSenha.getText();
		if (senha.equals(confirmaSenha)) {
			PetSitter petSitter = new PetSitter(EsqueciMinhaSenhaController.getPetSitterId(), senha);
			System.out.println(LoginController.getIdUsuario());
			System.out.println(senha);
			PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
			try {
				petSitterDao.alteraSenha(petSitter);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Confirmação");
				alert.setContentText("Senha alterada com sucesso!");
				alert.showAndWait();
				fechaJanela();
				LoginFXML login = new LoginFXML();
				try {
					login.start(new Stage());
				} catch (Exception e) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("Erro");
					alert2.setContentText("Não foi possível alterar sua senha!");
					alert2.showAndWait();
				}
				if (RecuperarSenhaUsuarioFXML.getScene() != null) {
					RecuperarSenhaUsuarioFXML.getScene().setRoot(null);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Alert alert2 = new Alert(AlertType.ERROR);
			alert2.setTitle("Erro");
			alert2.setContentText("Os campos Senha e Confirmar senha não coincidem!");
			alert2.showAndWait();	
		}
	}

	private void fechaJanela() {
		RecuperarSenhaUsuarioFXML.getStage().close();
	}

}