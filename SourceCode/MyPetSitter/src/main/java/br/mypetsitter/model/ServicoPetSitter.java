package br.mypetsitter.model;

public class ServicoPetSitter {
	private String petSittetId;
	private int servicoId;
	private String nomeCategoria;
	private String descricao;
	private String nomeServico;
	
	public ServicoPetSitter(String petSitterId, int servicoId) {
		this.petSittetId = petSitterId;
		this.servicoId = servicoId;
	}
	public ServicoPetSitter() {}

	public String getPetSittetId() {
		return petSittetId;
	}


	public void setPetSittetId(String petSittetId) {
		this.petSittetId = petSittetId;
	}


	public int getServicoId() {
		return servicoId;
	}


	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}


	public String getNomeCategoria() {
		return nomeCategoria;
	}


	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	
}
