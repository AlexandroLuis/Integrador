package br.mypetsitter.model;

public class Categoria {
	private int categoriaId;
	private String nome;
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	public Categoria(int categoriaId, String nome) {
		this.categoriaId = categoriaId;
		this.nome = nome;
	}
	public Categoria(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Categoria() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public int getCategoriaId() {
		return this.categoriaId;
	}
	
}
