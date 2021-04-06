package br.mypetsitter.dao;
import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.PetSitter;


public interface PetSitterDAO {
	public void insere(PetSitter usuario) throws SQLException;
	
	public void atualiza(PetSitter usuario) throws SQLException;
	
	public void remove(PetSitter usuario) throws SQLException;
	
	public PetSitter buscaPorCodigo(String petSitterId) throws SQLException;
	
	public List<PetSitter> listaTodos() throws SQLException;
	
	public boolean autenticarPetSitter(PetSitter autonomo) throws SQLException;
	
	public boolean existeUsuario(String petSitterId) throws SQLException;
	
	public boolean buscaUsuario(String petSitterId, String cpf) throws SQLException;
}
