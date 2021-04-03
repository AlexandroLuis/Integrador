package br.mypetsitter.controller;

import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import br.mypetsitter.dao.AdministradorDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.Administrador;
import br.mypetsitter.model.LoginAdmFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginAdmController {

	@FXML
	private TextField tfdUsuario;

	@FXML
	private PasswordField tfdSenha;

	@FXML
	private JFXButton btnEntrar;

	public void autenticar() throws SQLException {
		String administradorId = tfdUsuario.getText();
		String senha = tfdSenha.getText();

		Administrador adm = new Administrador(administradorId, senha);
		AdministradorDAOJDBC admDao = new AdministradorDAOJDBC();
		boolean existe = admDao.autenticarAdm(adm);
		if (existe) {
			System.out.println("Logou");
			AdmPrincipalFXML administradorPrincipal = new AdmPrincipalFXML();
			try {
				administradorPrincipal.start(new Stage());
				fechaJanela();
				if (LoginAdmFXML.getScene() != null) {
					LoginAdmFXML.getScene().setRoot(null);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um problema");
			alert.setContentText("Usuário e/ou senha incorretos ou inexistentes.");
			alert.show();
		}
	}

	@FXML
	private void logar(ActionEvent event) throws SQLException {
		autenticar();
	}

	private void fechaJanela() {
		LoginAdmFXML.getStage().close();
	}
}
