package br.mypetsitter.model;

import java.sql.Date;

public class Cliente {
	private String tipo, cpf, cnpj, telefone, cidade, endereco, bairro, cep, senha, nome, estado, clienteId;
	private Date dataNascimento, dataCadastro;
	

	public Cliente(String cpf, String cnpj, String telefone, String nome, String cidade, String endereco, String bairro,String cep, String clienteId, String senha, String estado, Date dataNascimento) {
		this.cpf = cpf;
		if(cep.isEmpty()) {
			this.cep = null;
		} else {
			this.cep = cep;	
		}
		if(cnpj.isEmpty()) {
			this.cnpj = null;
		} else {
			this.cnpj = cnpj;	
		}
		this.telefone = telefone;
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
		this.bairro = bairro;
		this.clienteId = clienteId;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.estado = estado;
	}
	public Cliente(String clienteId, String senha) {
		this.clienteId = clienteId;
		this.senha = senha;
	}
	
	public String getIdCliente() {
		return clienteId;
	}


	public void setIdCliente(String clienteId) {
		this.clienteId = clienteId;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
