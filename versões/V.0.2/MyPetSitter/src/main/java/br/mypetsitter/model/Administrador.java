package br.mypetsitter.model;

public class Administrador {
	private String administradorId;
	private String nome;
	private String sobrenome;
	private String senha;
	
	public Administrador(String administradorId, String nome, String sobrenome, String senha) {
		this.administradorId = administradorId;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.senha = senha;
	}
	public Administrador(String administradorId, String senha) {
		this.administradorId = administradorId;
		this.senha = senha;
	}

	public String getAdministradorId() {
		return administradorId;
	}

	public void setAdministradorId(String administradorId) {
		this.administradorId = administradorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
