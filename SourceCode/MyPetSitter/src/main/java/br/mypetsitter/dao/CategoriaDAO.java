package br.mypetsitter.dao;

import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.Categoria;

public interface CategoriaDAO {
	
	public void insere(Categoria categoria) throws SQLException;
	
	public void atualiza(Categoria categoria) throws SQLException;
	
	public void remove(Categoria administrador) throws SQLException;
	
	public void buscaPorCodigo(int categoriaId) throws SQLException;
	
	public List<Categoria> listaTodos() throws SQLException;
}
