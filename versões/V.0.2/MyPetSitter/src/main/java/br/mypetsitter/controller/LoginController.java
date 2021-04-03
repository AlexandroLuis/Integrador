package br.mypetsitter.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.PetSitter;
import br.mypetsitter.model.PetSitterPrincipal;
import br.mypetsitter.model.CadastroUsuarioFXML;
import br.mypetsitter.model.LoginFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private static String idUsuario;
	private static String senha;
	// @FXML
	// private JFXToggleButton ehAutonomo;

	@FXML
	private void initialize() {
		tfdUsuario.setText("TESTE");
	}

	@FXML
	private Hyperlink hypRecuperarSenha;
	@FXML
	private TextField tfdUsuario;
	@FXML
	private Hyperlink hypCadastro;
	@FXML
	private PasswordField tfdSenha;
	@FXML
	private Button btnEntrar;

	@FXML
	void cadastrarUsuario(ActionEvent event) {
		CadastroUsuarioFXML cadastroUsuario = new CadastroUsuarioFXML();
		try {
			cadastroUsuario.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getIdUsuario() {
		return idUsuario;
	}

	public static void setIdUsuario(String idUsuario) {
		LoginController.idUsuario = idUsuario;
	}

	public void autenticar() throws SQLException {

		if (tfdUsuario.getText().contains(tfdUsuario.getText().toUpperCase())) {
			idUsuario = tfdUsuario.getText().toLowerCase();
		} else {
			idUsuario = tfdUsuario.getText();
		}
		senha = tfdSenha.getText();
		/*
		 * if(ehAutonomo.getAccessibleText() == "C") { Cliente cliente = new
		 * Cliente(idUsuario, senha); ClienteDAOJDBC clienteDao = new ClienteDAOJDBC();
		 * boolean existe = clienteDao.autenticarCliente(cliente); if(existe) {
		 * ClientePrincipalFXML clientePrincipal = new ClientePrincipalFXML(); try {
		 * clientePrincipal.start(new Stage()); fechaJanela(); } catch (Exception e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } } else { Alert
		 * alert = new Alert(AlertType.ERROR); alert.setTitle("Ocorreu um problema");
		 * alert.setContentText("Usuário e/ou senha incorretos ou inexistentes.");
		 * alert.show(); } } else {
		 */
		// if(ehAutonomo.getAccessibleText() == "A") {
		PetSitter autonomo = new PetSitter(idUsuario, senha);
		PetSitterDAOJDBC autonomoDao = new PetSitterDAOJDBC();
		boolean existe = autonomoDao.autenticarPetSitter(autonomo);
		if (existe) {
			System.out.println("Logou");
			PetSitterPrincipal petSitterPrincipal = new PetSitterPrincipal();
			try {
				petSitterPrincipal.start(new Stage());
				fechaJanela();
				if (LoginFXML.getScene() != null) {
					LoginFXML.getScene().setRoot(null);
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
		// }
		// }
	}

	@FXML
	void logar(ActionEvent event) throws SQLException {
		autenticar();

	}

	@FXML
	void recuperarSenha(ActionEvent event) {
		System.out.println("Recuperar Senha");
	}

	private void fechaJanela() {
		LoginFXML.getStage().close();
	}

	public void initialize(URL location, ResourceBundle resources) {
		/*
		 * ehAutonomo.setAccessibleText("C");
		 * ehAutonomo.selectedProperty().addListener(new ChangeListener<Boolean>() {
		 * //@Override public void changed(ObservableValue<? extends Boolean>
		 * observable, Boolean oldValue, Boolean newValue) { if(ehAutonomo.isSelected())
		 * { ehAutonomo.setAccessibleText("A"); } else {
		 * ehAutonomo.setAccessibleText("C"); } } });
		 */
	}

}