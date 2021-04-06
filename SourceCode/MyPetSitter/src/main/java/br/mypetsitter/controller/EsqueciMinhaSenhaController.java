package br.mypetsitter.controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.EsqueciMinhaSenhaFXML;
import br.mypetsitter.model.LoginFXML;
import br.mypetsitter.model.RecuperarSenhaUsuarioFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EsqueciMinhaSenhaController {
	private static String petSitterId;
	@FXML
	private JFXTextField tfdCpf;

	@FXML
	private JFXTextField tfdUsuario;

	@FXML
	void verificarCredenciais(ActionEvent event) {
		String cpf = tfdCpf.getText();
		String petSitterId = tfdUsuario.getText();

		PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
		try {
			boolean existe = petSitterDao.buscaUsuario(petSitterId, cpf);
			if (existe) {
				setPetSitterId(petSitterId);
				try {
					fechaJanela();
					RecuperarSenhaUsuarioFXML recuperarSenha = new RecuperarSenhaUsuarioFXML();
					recuperarSenha.start(new Stage());
					if (EsqueciMinhaSenhaFXML.getScene() != null) {
						EsqueciMinhaSenhaFXML.getScene().setRoot(null);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setContentText("Cpf ou Usuário não encontrado!");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setContentText("Cpf ou Usuário não encontrado!");
			alert.showAndWait();
		}

	}
	

	public static String getPetSitterId() {
		return petSitterId;
	}


	public static void setPetSitterId(String petSitterId) {
		EsqueciMinhaSenhaController.petSitterId = petSitterId;
	}


	private void fechaJanela() {
		EsqueciMinhaSenhaFXML.getStage().close();
	}

}