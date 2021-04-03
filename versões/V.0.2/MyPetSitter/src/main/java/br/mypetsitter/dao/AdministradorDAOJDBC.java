package br.mypetsitter.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.Administrador;

public class AdministradorDAOJDBC implements AdministradorDAO {
	private String sql;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private Connection connection;
	
	private void open() throws SQLException {
		connection = Conexao.getConexao(Conexao.url, Conexao.usuario, Conexao.senha);
	}
	private void close() throws SQLException {
		connection.close();
	}
	@Override
	public void insere(Administrador administrador) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Administrador administrador) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Administrador administrador) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscaPorCodigo(String administradorId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Administrador> listaTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean autenticarAdm(Administrador administrador) throws SQLException {
		open();
		this.sql = "SELECT * FROM Administrador WHERE (administradorId = ? AND senha = ?)";
		statement = connection.prepareStatement(sql);
		statement.setString(1, administrador.getAdministradorId());
		statement.setString(2, administrador.getSenha());
		statement.execute();
		resultSet = statement.getResultSet();
		close();
		return resultSet.next();	
	}

}
