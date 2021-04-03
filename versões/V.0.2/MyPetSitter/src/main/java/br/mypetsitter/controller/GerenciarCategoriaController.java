package br.mypetsitter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.mypetsitter.dao.CategoriaDAOJDBC;
import br.mypetsitter.model.AdmPrincipalFXML;
import br.mypetsitter.model.Categoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GerenciarCategoriaController implements Initializable {
	private static int categoriaId;
	private static String nome;
	
	public static int getCategoriaId() {
		return categoriaId;
	}

	public static void setCategoriaId(int categoriaId) {
		GerenciarCategoriaController.categoriaId = categoriaId;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		GerenciarCategoriaController.nome = nome;
	}

	List<Categoria> categoriasRecuperadas = new ArrayList<>();
    @FXML
    private JFXButton btnAtualizar;

    @FXML
    private JFXButton btnRemover;
	@FXML
    private TableView<Categoria> categorias;

    @FXML
    private TableColumn<Categoria, Integer> colunaId;

    @FXML
    private TableColumn<Categoria, String> colunaNome;

    @FXML
    void alteraCategoriaSelecionada(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/AlterarCategoriaAdm.fxml"));
			BorderPane border = AdmPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void incluiNovaCategoria(ActionEvent event) {
    	try {
			VBox vbox = FXMLLoader.load(getClass().getResource("/view/IncluirCategoriaAdm.fxml"));
			BorderPane border = AdmPrincipalFXML.getRoot();
			border.setCenter(vbox);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void monitoraCategoria(MouseEvent event) {
    	btnAtualizar.setDisable(false);
    	btnRemover.setDisable(false);
       	categoriaId = categorias.getSelectionModel().getSelectedItem().getCategoriaId();
    	nome = categorias.getSelectionModel().getSelectedItem().getNome();
    }

    @FXML
    void removeCategoriaSelecionada(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Aviso");
    	alert.setContentText("Tem certeza que deseja remover a categoria selecionada?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
        	Categoria categoria = new Categoria(categoriaId);
        	CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
        	try {
            	categoriaDao.remove(categoria);
            	Alert alert2 = new Alert(AlertType.INFORMATION);
            	alert2.setTitle("Confirmação");
            	alert2.setContentText("Categoria removida com sucesso!");
            	alert2.showAndWait();
            	try {
					VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarCategoriaAdm.fxml"));
					BorderPane border = AdmPrincipalFXML.getRoot();
					border.setCenter(vbox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}catch(SQLException e) {
            	Alert alert3 = new Alert(AlertType.ERROR);
            	alert3.setTitle("Erro");
            	alert3.setContentText("Não é possível remover uma categoria que esteja sendo utilizada por um serviço!");
            	alert3.showAndWait();        		
        	}    		
    	}


    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
		try {
			categoriasRecuperadas = categoriaDao.listaTodos();
			ObservableList<Categoria> categoria = FXCollections.observableArrayList(categoriasRecuperadas);
			colunaId.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("categoriaId"));
			colunaNome.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
			categorias.setItems(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}