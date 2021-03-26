package br.mypetsitter.dao;
import java.sql.SQLException;
import java.util.List;

import br.mypetsitter.model.PetSitter;


public interface PetSitterDAO {
	public void insere(PetSitter usuario) throws SQLException;
	
	public void atualiza(PetSitter usuario) throws SQLException;
	
	public void remove(PetSitter usuario) throws SQLException;
	
	public void buscaPorCodigo(int idAutonomo) throws SQLException;
	
	public List<PetSitter> listaTodos() throws SQLException;
	
	public boolean autenticarPetSitter(PetSitter autonomo) throws SQLException;
	
}
