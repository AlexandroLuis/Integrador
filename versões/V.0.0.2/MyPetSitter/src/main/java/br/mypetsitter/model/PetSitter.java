package br.mypetsitter.model;

import java.sql.Date;

public class PetSitter {
	private String tipo, cpf, cnpj, telefone, cidade, endereco, bairro, cep, senha, nome, estado, idPetSitter;
	private Date dataNascimento, dataCadastro;
	private boolean status;
	

	public PetSitter(String cpf, String cnpj, String telefone, String nome, String cidade, String endereco, String bairro,String cep, String petSitterId, String senha, String estado, Date dataNascimento) {
		//this.tipo = tipo;
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
		this.idPetSitter = petSitterId;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.estado = estado;
		//this.status = false;
		//this.dataCadastro = dataCadastro;
	}
	public PetSitter(String cpf, String cnpj, String telefone, String nome, String cidade, String endereco, String bairro,String cep, String petSitterId, String estado, Date dataNascimento) {
		//this.tipo = tipo;
		this.cpf = cpf;
		//if(cep.isEmpty()) {
			//this.cep = null;
		//} else {
			this.cep = cep;	
		//}
		//if(cnpj.isEmpty()) {
		//	this.cnpj = null;
		//} else {
			this.cnpj = cnpj;	
		//}
		this.telefone = telefone;
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
		this.bairro = bairro;
		this.idPetSitter = petSitterId;
		this.dataNascimento = dataNascimento;
		this.estado = estado;
		//this.status = false;
		//this.dataCadastro = dataCadastro;
	}
	public PetSitter(String petSitterId, String senha) {	
		this.idPetSitter = petSitterId;
		this.senha = senha;
	}
	public PetSitter() {
		
	}
	public PetSitter(String idPetSitter) {
		this.idPetSitter = idPetSitter;
	}
	public String getIdPetSitter() {
		return idPetSitter;
	}

	public void setIdPetSitter(String petSitterId) {
		this.idPetSitter = petSitterId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
