package br.gov.presidencia.model;

public class AreaAtendimento {
	private String nome;
	private String sigla;
	private String codEstruturado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getCodEstruturado() {
		return codEstruturado;
	}
	public void setCodEstruturado(String codEstruturado) {
		this.codEstruturado = codEstruturado;
	}
	
	public AreaAtendimento() {
		// TODO Auto-generated constructor stub
	}
	
	public AreaAtendimento(String nome, String sigla, String codEstruturado) {
		this.nome = nome;
		this.sigla = sigla;
		this.codEstruturado = codEstruturado;
	}
}
