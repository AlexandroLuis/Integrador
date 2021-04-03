package br.mypetsitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.mypetsitter.controller.LoginController;
import br.mypetsitter.model.PetSitter;

public class PetSitterDAOJDBC implements PetSitterDAO {
	private String sql;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	private void open() throws SQLException {
		connection = Conexao.getConexao(Conexao.url, Conexao.usuario, Conexao.senha);
	}
	private void close() throws SQLException {
		connection.close();
	}
	public void insere(PetSitter usuario) throws SQLException {
		open();
		this.sql = ""
				+ "INSERT INTO PetSitter"
				+ "(cpf, cnpj, telefone, nome, cidade, endereco,cep, bairro, senha, dataNascimento, dataCadastro, estado, petSitterId)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,current_date, ?,?)";
		
		statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getCpf());
		statement.setString(2, usuario.getCnpj());
		statement.setString(3, usuario.getTelefone());
		statement.setString(4, usuario.getNome());
		statement.setString(5, usuario.getCidade());
		statement.setString(6, usuario.getEndereco());
		statement.setString(7, usuario.getCep());
		statement.setString(8, usuario.getBairro());
		statement.setString(9, usuario.getSenha());
		statement.setDate(10, usuario.getDataNascimento());
		statement.setString(11, usuario.getEstado());
		statement.setString(12, usuario.getIdPetSitter());
		statement.executeUpdate();
		
		close();		
	}
	public void atualiza(PetSitter usuario) throws SQLException {
		open();
		String sql = "UPDATE PetSitter SET petSitterId= ?, nome = ?, cpf = ?, cnpj = ?, cidade = ?, estado = ?, endereco = ?, bairro = ?, cep = ?, dataNascimento = ?, telefone = ? WHERE petSitterId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getIdPetSitter());
		statement.setString(2, usuario.getNome());
		statement.setString(3, usuario.getCpf());
		statement.setString(4, usuario.getCnpj());
		statement.setString(5, usuario.getCidade());
		statement.setString(6, usuario.getEstado());
		statement.setString(7, usuario.getEndereco());
		statement.setString(8, usuario.getBairro());
		statement.setString(9, usuario.getCep());
		statement.setDate(10, usuario.getDataNascimento());
		statement.setString(11, usuario.getTelefone());
		statement.setString(12, LoginController.getIdUsuario());
		statement.executeUpdate();
		close();
		
	}
	public void remove(PetSitter usuario) throws SQLException {
		open();
		String sql = "DELETE FROM PetSitter WHERE petSitterId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getIdPetSitter());
		statement.executeUpdate();
		close();
		
	}
	public void alteraSenha(PetSitter petSitter) throws SQLException {
		open();
		String sql = "UPDATE PetSitter SET senha = ? WHERE petSitterId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, petSitter.getSenha());
		statement.setString(2, petSitter.getIdPetSitter());
		statement.executeUpdate();
		close();
		
	}
	public PetSitter buscaPorCodigo(String petSitterId) throws SQLException {
		open();
		String sql = "SELECT * FROM PetSitter WHERE petSitterId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, petSitterId);
		resultSet = statement.executeQuery();
		
		PetSitter petsitter = new PetSitter();
		while(resultSet.next()) {
			petsitter.setIdPetSitter(resultSet.getString("petSitterId"));
			petsitter.setNome(resultSet.getString("nome"));
			petsitter.setSenha(resultSet.getString("senha"));
			petsitter.setCpf(resultSet.getString("cpf"));
			petsitter.setCnpj(resultSet.getString("cnpj"));
			petsitter.setTelefone(resultSet.getString("telefone"));
			petsitter.setCidade(resultSet.getString("cidade"));
			petsitter.setEstado(resultSet.getString("estado"));
			petsitter.setEndereco(resultSet.getString("endereco"));
			petsitter.setBairro(resultSet.getString("bairro"));
			petsitter.setCep(resultSet.getString("cep"));
			petsitter.setDataNascimento(resultSet.getDate("dataNascimento"));			
		}

		close();
		return petsitter;
		
	}
	public List<PetSitter> listaTodos() throws SQLException {
		open();
		String sql = "SELECT * FROM PetSitter";
		ArrayList<PetSitter> listPetSitter = new ArrayList<>();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			PetSitter petsitter = new PetSitter();
			petsitter.setIdPetSitter(resultSet.getString("petSitterId"));
			petsitter.setNome(resultSet.getString("nome"));
			petsitter.setCpf(resultSet.getString("cpf"));
			petsitter.setCnpj(resultSet.getString("cnpj"));
			petsitter.setTelefone(resultSet.getString("telefone"));
			petsitter.setCidade(resultSet.getString("cidade"));
			petsitter.setEstado(resultSet.getString("estado"));
			petsitter.setEndereco(resultSet.getString("endereco"));
			petsitter.setBairro(resultSet.getString("bairro"));
			petsitter.setCep(resultSet.getString("cep"));
			petsitter.setDataNascimento(resultSet.getDate("dataNascimento"));
			petsitter.setDataCadastro(resultSet.getDate("dataCadastro"));
			listPetSitter.add(petsitter);
			
		}
		close();
		return listPetSitter;
	}
	public boolean autenticarPetSitter(PetSitter autonomo) throws SQLException {
		open();
		this.sql = "SELECT * FROM PetSitter WHERE (petSitterId = ? AND senha = ?)";
		statement = connection.prepareStatement(sql);
		statement.setString(1, autonomo.getIdPetSitter());
		statement.setString(2, autonomo.getSenha());
		statement.execute();
		resultSet = statement.getResultSet();
		close();
		return resultSet.next();
		
	}
	@Override
	public boolean existeUsuario(String petSitterId) throws SQLException {
		open();
		this.sql = "SELECT * FROM PetSitter WHERE petSitterId = ?";
		statement = connection.prepareStatement(sql);
		statement.setString(1, petSitterId);
		statement.execute();
		resultSet = statement.getResultSet();
		close();
		return resultSet.next();
	}
	


}

	