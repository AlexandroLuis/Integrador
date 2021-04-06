package br.mypetsitter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.mypetsitter.dao.ServicoPetSitterDAOJDBC;
import br.mypetsitter.model.PetSitterPrincipalFXML;
import br.mypetsitter.model.ServicoPetSitter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GerenciarServicoPetSitterController implements Initializable {
	private int servicoId;
	private List<ServicoPetSitter> servicosPetRecuperados = new ArrayList<>();
    @FXML
    private TableView<ServicoPetSitter> servicos;

    @FXML
    private TableColumn<ServicoPetSitter, Integer> colunaId;

    @FXML
    private TableColumn<ServicoPetSitter, String> colunaNome;

    @FXML
    private TableColumn<ServicoPetSitter, String> colunaCategoria;

    @FXML
    private TableColumn<ServicoPetSitter, String> colunaDescricao;

    @FXML
    private JFXButton btnAtualizar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    void incluiNovoServico(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/IncluirServicoPetSitter.fxml"));
			BorderPane border = PetSitterPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void monitoraServico(MouseEvent event) {
    	btnRemover.setDisable(false);
    	this.servicoId = servicos.getSelectionModel().getSelectedItem().getServicoId();
    }
    private void atualizaTela() {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarServicoPetSitter.fxml"));
			BorderPane border = PetSitterPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void removeServicoSelecionado(ActionEvent event) {
    	String petSitterId = LoginController.getIdUsuario();
    	ServicoPetSitter servicoPetSitter = new ServicoPetSitter(petSitterId, servicoId);
    	ServicoPetSitterDAOJDBC servicoPetSitterDao = new ServicoPetSitterDAOJDBC();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Aviso");
    	alert.setContentText("Tem certeza que deseja remover o serviço selecionado da sua carteira?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
        	try {
    			servicoPetSitterDao.remove(servicoPetSitter);
    			Alert alert2 = new Alert(AlertType.INFORMATION);
    	    	alert2.setTitle("Aviso");
    	    	alert2.setContentText("Serviço removido com sucesso!");
    	    	alert2.showAndWait();
    	    	atualizaTela();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			Alert alert3 = new Alert(AlertType.ERROR);
    	    	alert3.setTitle("Erro");
    	    	alert3.setContentText("Ocorreu um problema ao remover o serviço!");
    	    	alert3.show();
    	    	System.out.println(e);
    		}    		
    	}

    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ServicoPetSitterDAOJDBC servicoDao = new ServicoPetSitterDAOJDBC();
		try {
			servicosPetRecuperados = servicoDao.listaTodos();
			ObservableList<ServicoPetSitter> listaServicos = FXCollections.observableArrayList(servicosPetRecuperados);
			colunaId.setCellValueFactory(new PropertyValueFactory<ServicoPetSitter, Integer>("servicoId"));
			colunaNome.setCellValueFactory(new PropertyValueFactory<ServicoPetSitter, String>("nomeServico"));
			colunaCategoria.setCellValueFactory(new PropertyValueFactory<ServicoPetSitter, String>("nomeCategoria"));
			colunaDescricao.setCellValueFactory(new PropertyValueFactory<ServicoPetSitter, String>("descricao"));
			servicos.setItems(listaServicos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
