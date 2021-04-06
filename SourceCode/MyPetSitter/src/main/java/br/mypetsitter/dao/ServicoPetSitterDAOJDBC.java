package br.mypetsitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.mypetsitter.model.ServicoPetSitter;

public class ServicoPetSitterDAOJDBC implements ServicoPetSitterDAO {
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
	public void insere(ServicoPetSitter servicoPetSitter) throws SQLException {
		open();
		String sql = "INSERT INTO ServicoPetSitter VALUES(?, ?)";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, servicoPetSitter.getServicoId());
		statement.setString(2, servicoPetSitter.getPetSittetId());
		statement.executeUpdate();
		close();
	}
	@Override
	public void atualiza(ServicoPetSitter servicoPetSitter) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void remove(ServicoPetSitter servicoPetSitter) throws SQLException {
		open();
		String sql = "DELETE FROM ServicoPetSitter WHERE servicoId = ? AND petSitterId ~* ?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, servicoPetSitter.getServicoId());
		statement.setString(2, servicoPetSitter.getPetSittetId());
		statement.executeUpdate();
		close();
		
	}
	@Override
	public void buscaPorCodigo(int petSitterId) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ServicoPetSitter> listaTodos() throws SQLException {
		open();
		String sql = "SELECT s.servicoId, s.nome, c.nome AS categoria, s.descricao FROM Servico s JOIN Categoria c ON c.categoriaId=s.categoriaId JOIN ServicoPetSitter sp ON sp.servicoId=s.servicoId JOIN PetSitter pt ON pt.petSitterId=sp.petSitterId ORDER BY 1,2";
		List<ServicoPetSitter> servicosPetSitter = new ArrayList<>();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			ServicoPetSitter servicoPetSitter = new ServicoPetSitter();
			servicoPetSitter.setServicoId(resultSet.getInt("servicoId"));
			servicoPetSitter.setNomeServico(resultSet.getString("nome"));
			servicoPetSitter.setNomeCategoria(resultSet.getString("categoria"));
			servicoPetSitter.setDescricao(resultSet.getString("descricao"));
			servicosPetSitter.add(servicoPetSitter);
		}
		close();
		return servicosPetSitter;
	}


}
