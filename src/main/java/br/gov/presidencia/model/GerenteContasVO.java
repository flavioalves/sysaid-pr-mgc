package br.gov.presidencia.model;

import java.util.ArrayList;
import java.util.List;

public class GerenteContasVO {
	private String nomeGerente;
	private String email;
	private String telefone;
	private String username;
	private List<AreaAtendimento> contas;
	
	public GerenteContasVO(String nome, String email, String phone, String username) {
		this.nomeGerente = nome;
		this.email = email;
		this.telefone = phone;
		this.username = username;
		this.contas = new ArrayList<AreaAtendimento>();
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNomeGerente() {
		return nomeGerente;
	}
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<AreaAtendimento> getContas() {
		return contas;
	}
	public void setContas(List<AreaAtendimento> contas) {
		this.contas = contas;
	}
}
