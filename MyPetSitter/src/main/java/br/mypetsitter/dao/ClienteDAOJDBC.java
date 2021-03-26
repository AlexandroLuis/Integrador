package br.mypetsitter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.Cliente;

public class ClienteDAOJDBC implements ClienteDAO {
	private String sql;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private int idUsuario;
	
	private void open() throws SQLException {
		connection = Conexao.getConexao(Conexao.url, Conexao.usuario, Conexao.senha);
	}
	private void close() throws SQLException {
		connection.close();
	}
	public void insere(Cliente usuario) throws SQLException {
		open();
		this.sql = ""
				+ "INSERT INTO Cliente"
				+ "(cpf, cnpj, telefone, nome, cidade, endereco,cep, bairro, senha, dataNascimento, dataCadastro, estado, clienteId)"
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
		statement.setString(12, usuario.getIdCliente());
		statement.executeUpdate();
		
		close();
		
		
	}
	public void atualiza(Cliente usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public void remove(Cliente usuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public void buscaPorCodigo(int idUsuario) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	public List<Cliente> listaTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean autenticarCliente(Cliente cliente) throws SQLException {
		open();
		this.sql = "SELECT * FROM Cliente WHERE (clienteId = ? AND senha = ?)";
		statement = connection.prepareStatement(sql);
		statement.setString(1, cliente.getIdCliente());
		statement.setString(2, cliente.getSenha());
		statement.execute();
		resultSet = statement.getResultSet();
		close();
		return resultSet.next();
	}

}
