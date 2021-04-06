package br.mypetsitter.model;

public class Servico {
	private int servicoId;
	private int categoriaId;
	private String nomeCategoria;
	private String descricao;
	private String nome;
	
	public Servico(String nome, String descricao, int categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}
	public Servico(int servicoId, String nome, int categoriaId, String descricao) {
		this.servicoId = servicoId;
		this.nome = nome;
		this.categoriaId = categoriaId;
		this.descricao = descricao;
	}
	public Servico(int servicoId) {
		this.servicoId = servicoId;
	}
	public Servico() {
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getServicoId() {
		return servicoId;
	}

	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	
}
