package br.mypetsitter.dao;

import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.Administrador;

public interface AdministradorDAO {
	public void insere(Administrador administrador) throws SQLException;
	
	public void atualiza(Administrador administrador) throws SQLException;
	
	public void remove(Administrador administrador) throws SQLException;
	
	public void buscaPorCodigo(String administradorId) throws SQLException;
	
	public List<Administrador> listaTodos() throws SQLException;
	
	public boolean autenticarAdm(Administrador administrador) throws SQLException;
}
