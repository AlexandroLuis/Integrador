package br.mypetsitter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.mypetsitter.dao.ServicoDAOJDBC;
import br.mypetsitter.dao.ServicoPetSitterDAOJDBC;
import br.mypetsitter.model.PetSitterPrincipal;
import br.mypetsitter.model.Servico;
import br.mypetsitter.model.ServicoPetSitter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class IncluiServicoPetSitterController implements Initializable {
	private List<Servico> servicosRecuperados = new ArrayList<>();
	private int idServicoSelecionado;

	@FXML
	private JFXButton btnVoltarTela;

	@FXML
	private JFXButton btnIncluirServico;
	@FXML
	private TableView<Servico> servicos;

	@FXML
	private TableColumn<Servico, Integer> colunaId;

	@FXML
	private TableColumn<Servico, String> colunaNome;

	@FXML
	private TableColumn<Servico, String> colunaCategoria;

	@FXML
	private TableColumn<Servico, String> colunaDescricao;
	@FXML
	private void voltarTela(ActionEvent event) {
		try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoPetSitter.fxml"));
			BorderPane border = PetSitterPrincipal.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void incluiNovoServico(ActionEvent event) {
		ServicoPetSitter servicoPetSitter = new ServicoPetSitter(LoginController.getIdUsuario(), idServicoSelecionado);
		ServicoPetSitterDAOJDBC servicoDao = new ServicoPetSitterDAOJDBC();
		try {
			servicoDao.insere(servicoPetSitter);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Confirmaçao");
			alert.setContentText("O serviço adicionado com sucesso a sua carteira!");
			alert.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro ao incluir serviço");
			alert.setContentText("O serviço selecionado já consta em sua carteira!");
			alert.showAndWait();
			System.out.println(e);
		}
	}

	@FXML
	void monitoraServico(MouseEvent event) {
		btnIncluirServico.setDisable(false);
		;
		this.idServicoSelecionado = servicos.getSelectionModel().getSelectedItem().getServicoId();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ServicoDAOJDBC servicoDao = new ServicoDAOJDBC();
		try {
			servicosRecuperados = servicoDao.listaServicosCategorias();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Servico> listServicos = FXCollections.observableArrayList(servicosRecuperados);
		colunaId.setCellValueFactory(new PropertyValueFactory<Servico, Integer>("servicoId"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<Servico, String>("nome"));
		colunaCategoria.setCellValueFactory(new PropertyValueFactory<Servico, String>("nomeCategoria"));
		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Servico, String>("descricao"));
		servicos.setItems(listServicos);

	}

}
