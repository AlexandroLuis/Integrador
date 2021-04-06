package br.mypetsitter.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.PetSitter;
import br.mypetsitter.model.PetSitterPrincipalFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;

public class GerenciarCadastroPetSitterController {
	private ObservableList<String> estados = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "ES",
			"GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE",
			"TO", "DF"

	);

	@FXML
	private void initialize() {
		tfdCnpj.setDisable(true);
		tfdCpf.setDisable(true);
		tfdCnpj.setText(null);
		tfdCep.setText(null);
		tfdDataNascimento.setDisable(true);
		tfdUsuario.setText(LoginController.getIdUsuario());
		PetSitter petSitterBd = null;
		PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
		try {
			petSitterBd = petSitterDao.buscaPorCodigo(LoginController.getIdUsuario());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tfdNome.setText(petSitterBd.getNome());
		tfdCpf.setText(petSitterBd.getCpf());
		tfdCpf.setText(petSitterBd.getCpf());
		tfdCnpj.setText(petSitterBd.getCnpj());
		tfdTelefone.setText(petSitterBd.getTelefone());
		tfdCidade.setText(petSitterBd.getCidade());
		cbEstado.setValue(petSitterBd.getEstado());
		tfdEndereco.setText(petSitterBd.getEndereco());
		tfdBairro.setText(petSitterBd.getBairro());
		tfdCep.setText(petSitterBd.getCep());
		Date date = petSitterBd.getDataNascimento();
		tfdDataNascimento.setValue(date.toLocalDate());

		cbEstado.setItems(estados);
	}

	@FXML
	private JFXTextField tfdUsuario;

	@FXML
	private JFXTextField tfdNome;

	@FXML
	private JFXTextField tfdCpf;

	@FXML
	private JFXTextField tfdCnpj;

	@FXML
	private JFXTextField tfdTelefone;

	@FXML
	private JFXComboBox<String> cbEstado;

	@FXML
	private JFXTextField tfdCidade;
	@FXML
	private JFXTextField tfdEndereco;

	@FXML
	private JFXTextField tfdBairro;

	@FXML
	private JFXTextField tfdCep;

	@FXML
	private DatePicker tfdDataNascimento;

	@FXML
	void alteraDadosUsuario(ActionEvent event) {
		if ((!(tfdUsuario.getText().isEmpty()) && !(tfdTelefone.getText().isEmpty()) && !(tfdNome.getText().isEmpty())
				&& !(tfdCidade.getText().isEmpty()) && !(tfdEndereco.getText().isEmpty())
				&& !(tfdBairro.getText().isEmpty()))) {
			PetSitterDAOJDBC petSitterDaoJdbc = new PetSitterDAOJDBC();
			try {
				if ((tfdUsuario.getText().equals(LoginController.getIdUsuario()))) {
					String userName = tfdUsuario.getText();
					String estado = cbEstado.getValue();
					LocalDate localDate = tfdDataNascimento.getValue();
					Date dataNascimento = Date.valueOf(localDate);
					String cpf = tfdCpf.getText();
					String cnpj = tfdCnpj.getText();
					String telefone = tfdTelefone.getText();
					String nome = tfdNome.getText();
					String cidade = tfdCidade.getText();
					String endereco = tfdEndereco.getText();
					String bairro = tfdBairro.getText();
					String cep = tfdCep.getText();

					PetSitter petSitter = new PetSitter(cpf, cnpj, telefone, nome, cidade, endereco, bairro, cep,
							userName, estado, dataNascimento);
					PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
					try {
						petSitterDao.atualiza(petSitter);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Aviso");
						alert.setContentText("Dados atualizados com sucesso!");
						alert.showAndWait();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setContentText(
								"Não foi possível realizar as alterações. Certifique-se de que os campos foram preenchidos corretamente!");
						alert.showAndWait();
						System.out.println(e);
					}
					if (!tfdUsuario.getText().equals(LoginController.getIdUsuario())) {
						LoginController.setIdUsuario(tfdUsuario.getText());
					}
				} else {
					if (!(petSitterDaoJdbc.existeUsuario(tfdUsuario.getText()))) {
						String userName = tfdUsuario.getText();
						String estado = cbEstado.getValue();
						LocalDate localDate = tfdDataNascimento.getValue();
						Date dataNascimento = Date.valueOf(localDate);
						String cpf = tfdCpf.getText();
						String cnpj = tfdCnpj.getText();
						String telefone = tfdTelefone.getText();
						String nome = tfdNome.getText();
						String cidade = tfdCidade.getText();
						String endereco = tfdEndereco.getText();
						String bairro = tfdBairro.getText();
						String cep = tfdCep.getText();

						PetSitter petSitter = new PetSitter(cpf, cnpj, telefone, nome, cidade, endereco, bairro, cep,
								userName, estado, dataNascimento);
						PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();
						try {
							petSitterDao.atualiza(petSitter);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Aviso");
							alert.setContentText("Dados atualizados com sucesso!");
							alert.showAndWait();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Erro");
							alert.setContentText(
									"Não foi possível realizar as alterações. Certifique-se de que os campos foram preenchidos corretamente!");
							alert.showAndWait();
							System.out.println(e);
						}
						if (!tfdUsuario.getText().equals(LoginController.getIdUsuario())) {
							LoginController.setIdUsuario(tfdUsuario.getText());
						}
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Erro");
						alert.setContentText(
								"Nome de usuário já está sendo utilizado. Escolha outro e tente novamente!");
						alert.showAndWait();
					}
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@FXML
	void mudarSenha(ActionEvent event) {
		VBox vbox;
		try {
			vbox = FXMLLoader.load(getClass().getResource("/view/MudarSenhaPetSitter.fxml"));
			BorderPane border = PetSitterPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}