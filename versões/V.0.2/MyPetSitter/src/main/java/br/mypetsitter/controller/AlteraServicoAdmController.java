package br.mypetsitter.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.mypetsitter.dao.CategoriaDAOJDBC;
import br.mypetsitter.dao.ServicoDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.Categoria;
import br.mypetsitter.model.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AlteraServicoAdmController {
	private List<String> nomeCategorias = new ArrayList<>();
	private ObservableList<String> categorias = FXCollections.observableList(nomeCategorias);
	List<Categoria> aux = new ArrayList<>();
	@FXML
	private JFXTextField tfdNome;

	@FXML
	private JFXComboBox<String> cbCategoria;

	@FXML
	private JFXTextArea txtDescricao;

	@FXML
	private void initialize() {
		CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
		try {
			aux = categoriaDao.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < aux.size(); i++) {
			nomeCategorias.add(aux.get(i).getNome());
		}
		cbCategoria.setItems(categorias);
		cbCategoria.setValue(GerenciarServicoAdmController.getCategoria());
		tfdNome.setText(GerenciarServicoAdmController.getNome());
		txtDescricao.setText(GerenciarServicoAdmController.getDescricao());
	}
	private void mudaTela(VBox vbox) {
		BorderPane border = AdmPrincipalFXML.getRoot();
		border.setCenter(vbox);
	}
	@FXML
	void alterarServico(ActionEvent event) {
		int servicoId = GerenciarServicoAdmController.getServicoId();
		String nome = tfdNome.getText();
		int categoriaId = 0;
		String categoria = cbCategoria.getValue();
		String descricao = txtDescricao.getText();

		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).getNome().equals(categoria)) {
				categoriaId = aux.get(i).getCategoriaId();
			}
		}
		Servico servico = new Servico(servicoId, nome, categoriaId, descricao);
		ServicoDAOJDBC servicoDao = new ServicoDAOJDBC();
		try {
			servicoDao.atualiza(servico);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setContentText("Serviço atualizado com sucesso!");
			alert.showAndWait();
			VBox vbox;
			try {
				vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoAdm.fxml"));
				mudaTela(vbox);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ocorreu um erro ao atualizar o serviço");
			alert.setContentText("Certifique-se de que os campos estão preenchidos corretamente!");
			alert.showAndWait();
		}

	}

}