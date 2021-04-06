package br.mypetsitter.dao;

import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.Cliente;

public interface ClienteDAO {
	
	public void insere(Cliente usuario) throws SQLException;
	
	public void atualiza(Cliente usuario) throws SQLException;
	
	public void remove(Cliente usuario) throws SQLException;
	
	public void buscaPorCodigo(int idUsuario) throws SQLException;
	
	public List<Cliente> listaTodos() throws SQLException;
	
	public boolean autenticarCliente(Cliente cliente) throws SQLException;
}
