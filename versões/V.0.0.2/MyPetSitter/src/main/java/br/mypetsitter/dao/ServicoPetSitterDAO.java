package br.mypetsitter.dao;

import java.sql.SQLException;
import java.util.List;
import br.mypetsitter.model.ServicoPetSitter;

public interface ServicoPetSitterDAO {
	public void insere(ServicoPetSitter servicoPetSitter) throws SQLException;
	
	public void atualiza(ServicoPetSitter servicoPetSitter) throws SQLException;
	
	public void remove(ServicoPetSitter servicoPetSitter) throws SQLException;
	
	public void buscaPorCodigo(int petSitterId) throws SQLException;
	
	public List<ServicoPetSitter> listaTodos() throws SQLException;

}
