package br.mypetsitter.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import br.mypetsitter.dao.CategoriaDAOJDBC;
import br.mypetsitter.dao.ServicoDAOJDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import br.mypetsitter.model.Categoria;
import br.mypetsitter.model.Servico;

public class IncluiServicoAdmController {	
	private List<String> nomeCategorias = new ArrayList<>();
	private ObservableList<String> categorias = FXCollections.observableList(nomeCategorias);
	List<Categoria> aux= new ArrayList<>();
	
    @FXML
    private JFXTextField tfdNome;
    @FXML
    private JFXTextArea tfdDescricao;
    @FXML
    private JFXComboBox<String> cbCategoria;
	@FXML
	private void initialize() {
		CategoriaDAOJDBC categoriaDao = new CategoriaDAOJDBC();
		try {
			aux = categoriaDao.listaTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for(int i = 0; i < aux.size(); i++) {
			nomeCategorias.add(aux.get(i).getNome());
		}
		cbCategoria.setItems(categorias);
	}

    @FXML
    void incluirServico(ActionEvent event) {
    	String categoria = cbCategoria.getValue();
    	String nome = tfdNome.getText();
    	String descricao = tfdDescricao.getText();
    	int categoriaId=0;
  
    	for(int i = 0; i < aux.size(); i++) {
    		if(aux.get(i).getNome().equals(categoria)) {
    			categoriaId = aux.get(i).getCategoriaId();
    		}
    	}
    	Servico servico = new Servico(nome, descricao, categoriaId);
    	ServicoDAOJDBC servicoDao = new ServicoDAOJDBC();
    	try {
			servicoDao.insere(servico);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setContentText("Serviço salvo com sucesso!");
			alert.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
