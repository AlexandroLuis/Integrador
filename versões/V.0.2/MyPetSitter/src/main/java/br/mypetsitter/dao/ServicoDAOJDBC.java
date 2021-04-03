package br.mypetsitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.mypetsitter.model.Servico;

public class ServicoDAOJDBC implements ServicoDAO {
	Connection connection;
	PreparedStatement statement;
	ResultSet resultSet;
	private void open() throws SQLException {
		connection = Conexao.getConexao(Conexao.url, Conexao.usuario, Conexao.senha);
	}
	private void close() throws SQLException {
		connection.close();
	}
	@Override
	public void insere(Servico servico) throws SQLException {
		open();
		String sql = "INSERT INTO SERVICO VALUES(DEFAULT, ?, ?, ?)";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, servico.getCategoriaId());
		statement.setString(2, servico.getNome());
		statement.setString(3, servico.getDescricao());
		statement.executeUpdate();
		close();
		
	}

	@Override
	public void atualiza(Servico servico) throws SQLException {
		open();
		String sql = "UPDATE Servico SET nome = ?, categoriaId = ?, descricao = ? WHERE servicoId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, servico.getNome());
		statement.setInt(2, servico.getCategoriaId());
		statement.setString(3, servico.getDescricao());
		statement.setInt(4, servico.getServicoId());
		statement.executeUpdate();
		close();
		
	}

	@Override
	public void remove(Servico servico) throws SQLException {
		open();
		String sql = "DELETE FROM Servico WHERE servicoId = ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, servico.getServicoId());
		statement.executeUpdate();
		close();
		
	}

	@Override
	public void buscaPorCodigo(int servicoId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Servico> listaTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Servico> listaServicosCategorias() throws SQLException {
		open();
		String sql = "SELECT s.servicoId, s.nome, c.nome AS categoria, s.descricao FROM Servico s JOIN Categoria c ON s.categoriaId=c.categoriaId ORDER BY 1,2";
		ArrayList<Servico> servicos = new ArrayList<>();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			Servico servico = new Servico();
			servico.setServicoId(resultSet.getInt("servicoId"));
			servico.setNome(resultSet.getString("nome"));
			servico.setNomeCategoria(resultSet.getString("categoria"));
			servico.setDescricao(resultSet.getString("descricao"));
			servicos.add(servico);
		}
		close();
		return servicos;
	}

}
