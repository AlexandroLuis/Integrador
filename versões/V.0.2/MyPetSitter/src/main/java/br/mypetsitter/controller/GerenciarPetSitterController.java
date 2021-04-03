package br.mypetsitter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.mypetsitter.dao.PetSitterDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.PetSitter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GerenciarPetSitterController implements Initializable {
	private List<PetSitter> petSittersRecuperados = new ArrayList<>();
	private String petSitterId;
	@FXML
	private TableView<PetSitter> petsitters;

	@FXML
	private TableColumn<PetSitter, String> colunaId;

	@FXML
	private TableColumn<PetSitter, String> colunaNome;

	@FXML
	private TableColumn<PetSitter, String> colunaCpf;

	@FXML
	private TableColumn<PetSitter, String> colunaCnpj;

	@FXML
	private TableColumn<PetSitter, String> colunaTelefone;

	@FXML
	private TableColumn<PetSitter, String> colunaCidade;

	@FXML
	private TableColumn<PetSitter, String> colunaEstado;
	@FXML
	private TableColumn<PetSitter, String> colunaEndereco;

	@FXML
	private TableColumn<PetSitter, String> colunaBairro;

	@FXML
	private TableColumn<PetSitter, String> colunaCep;

	@FXML
	private TableColumn<PetSitter, Date> colunaDataNascimento;

	@FXML
	private TableColumn<PetSitter, Date> colunaDataCadastro;

	@FXML
	private JFXButton btnRemover;

	@FXML
	void monitorarPetSitter(MouseEvent event) {
		btnRemover.setDisable(false);
		this.petSitterId = petsitters.getSelectionModel().getSelectedItem().getIdPetSitter();
	}

	@FXML
	void removePetSitterSelecionado(ActionEvent event) {
		PetSitter petSitter = new PetSitter(this.petSitterId);
		PetSitterDAOJDBC petSitterDao = new PetSitterDAOJDBC();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Aviso");
		alert.setContentText("Tem certeza que deseja remover o PetSitter selecionado?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				petSitterDao.remove(petSitter);
				Alert alert1 = new Alert(AlertType.CONFIRMATION);
				alert1.setTitle("Aviso");
				alert1.setContentText("PetSitter removido com sucesso!");
				alert1.showAndWait();
				try {
					VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarPetSitter.fxml"));
					BorderPane border = AdmPrincipalFXML.getRoot();
					border.setCenter(vbox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Alert alert2 = new Alert(AlertType.ERROR);
				System.out.println(e);
				alert2.setTitle("Aviso");
				alert2.setContentText("Ocorreu um problema ao remover o PetSitter!");
				alert2.show();
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PetSitterDAOJDBC petsitterDao = new PetSitterDAOJDBC();
		try {
			petSittersRecuperados = petsitterDao.listaTodos();
			ObservableList<PetSitter> listPetSitters = FXCollections.observableArrayList(petSittersRecuperados);
			colunaId.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("idPetSitter"));
			colunaNome.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("nome"));
			colunaCpf.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("cpf"));
			colunaCnpj.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("cnpj"));
			colunaTelefone.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("telefone"));
			colunaCidade.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("cidade"));
			colunaEstado.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("estado"));
			colunaEndereco.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("endereco"));
			colunaBairro.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("bairro"));
			colunaCep.setCellValueFactory(new PropertyValueFactory<PetSitter, String>("cep"));
			colunaDataNascimento.setCellValueFactory(new PropertyValueFactory<PetSitter, Date>("dataNascimento"));
			colunaDataCadastro.setCellValueFactory(new PropertyValueFactory<PetSitter, Date>("dataCadastro"));
			petsitters.setItems(listPetSitters);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}