package br.mypetsitter.dao;

import java.sql.SQLException;
import java.util.List;
import br.mypetsitter.model.Servico;

public interface ServicoDAO {
	public void insere(Servico servico) throws SQLException;
	
	public void atualiza(Servico servico) throws SQLException;
	
	public void remove(Servico servico) throws SQLException;
	
	public void buscaPorCodigo(int servicoId) throws SQLException;
	
	public List<Servico> listaTodos() throws SQLException;
	
	public List<Servico> listaServicosCategorias() throws SQLException;
}
