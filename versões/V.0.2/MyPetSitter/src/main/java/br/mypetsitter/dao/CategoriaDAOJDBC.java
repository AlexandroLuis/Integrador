package br.mypetsitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mypetsitter.model.Categoria;

public class CategoriaDAOJDBC implements CategoriaDAO {
	ResultSet resultSet;
	Connection connection;
	PreparedStatement statement;

	private void open() throws SQLException {
		connection = Conexao.getConexao(Conexao.url, Conexao.usuario, Conexao.senha);
	}

	private void close() throws SQLException {
		connection.close();
	}

	@Override
	public void insere(Categoria categoria) throws SQLException {
		open();
		String sql = "INSERT INTO Categoria VALUES(DEFAULT, ?)";
		statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNome());
		statement.executeUpdate();
		close();

	}

	@Override
	public void atualiza(Categoria categoria) throws SQLException {
		open();
		String sql = "UPDATE Categoria SET nome = ? WHERE categoriaId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNome());
		statement.setInt(2, categoria.getCategoriaId());
		statement.executeUpdate();
		close();

	}

	@Override
	public void remove(Categoria categoria) throws SQLException {
		open();
		String sql = "DELETE FROM Categoria WHERE categoriaId = ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, categoria.getCategoriaId());
		statement.executeUpdate();
		close();

	}

	@Override
	public void buscaPorCodigo(int categoriaId) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Categoria> listaTodos() throws SQLException {
		open();
		String sql = "SELECT * FROM Categoria ORDER BY 1,2";
		ArrayList<Categoria> categorias = new ArrayList<>();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Categoria categoria = new Categoria();
			categoria.setCategoriaId(resultSet.getInt("categoriaId"));
			categoria.setNome(resultSet.getString("nome"));
			categorias.add(categoria);
		}
		close();
		return categorias;
	}

}
