package br.mypetsitter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import br.mypetsitter.dao.ServicoDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class GerenciarServicoAdmController implements Initializable {
	private static int servicoId;
	private static String nome;
	private static String categoria;
	private static String descricao;
	List<Servico> servicosRecuperados = new ArrayList<>();
	
	public static int getServicoId() {
		return servicoId;
	}

	public static void setServicoId(int servicoId) {
		GerenciarServicoAdmController.servicoId = servicoId;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		GerenciarServicoAdmController.nome = nome;
	}

	public static String getCategoria() {
		return categoria;
	}

	public static void setCategoria(String categoria) {
		GerenciarServicoAdmController.categoria = categoria;
	}

	public static String getDescricao() {
		return descricao;
	}

	public static void setDescricao(String descricao) {
		GerenciarServicoAdmController.descricao = descricao;
	}

	@FXML
	private void initialize() {
		ServicoDAOJDBC servicoDao = new ServicoDAOJDBC();
		try {
			servicosRecuperados = servicoDao.listaServicosCategorias();
			System.out.println(servicosRecuperados);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    private JFXButton btnAtualizar;

    @FXML
    private JFXButton btnRemover;
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
	void alteraServicoSelecionado(ActionEvent event) {
		try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/AlterarServicoAdm.fxml"));
			BorderPane border = AdmPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	@FXML
	void incluiNovoServico(ActionEvent event) {
		try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/CadastroServicoAdm.fxml"));
			BorderPane border = AdmPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void monitoraServico(MouseEvent event) {
		btnAtualizar.setDisable(false);
		btnRemover.setDisable(false);
		servicoId = this.servicos.getSelectionModel().getSelectedItem().getServicoId();
		nome = this.servicos.getSelectionModel().getSelectedItem().getNome();
		categoria = this.servicos.getSelectionModel().getSelectedItem().getNomeCategoria();
		descricao = this.servicos.getSelectionModel().getSelectedItem().getDescricao();
	}

	@FXML
	void removeServicoSelecionado(ActionEvent event) {
		Servico servico = new Servico(servicoId);
		ServicoDAOJDBC servicoDao = new ServicoDAOJDBC();
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
    	alert1.setTitle("Aviso");
    	alert1.setContentText("Tem certeza que deseja remover o serviço selecionado?");
    	Optional<ButtonType> result = alert1.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		try {
    			servicoDao.remove(servico);
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Confirmação");
    			alert.setContentText("Serviço removido com sucesso!");
    			alert.showAndWait();
    			try {
    				VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoAdm.fxml"));
    				BorderPane border = AdmPrincipalFXML.getRoot();
    				border.setCenter(vbox);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Erro");
    			alert.setContentText("Não é possível remover serviços que estão sendo utilizados por PetSitters!");
    			System.out.println(e);
    			alert.showAndWait();
    		}    		
    	}

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
