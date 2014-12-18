package br.gov.presidencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MF_TAB0025")
public class AreaAtendimento {

	@Id
	@Column(name = "COD_UNID_PR", updatable = false, insertable = false)
	private Integer codUnidade;

	@Column(name = "NOM_UNID_PR")
	private String nome;

	@Column(name = "SIG_UNID_PR")
	private String sigla;

	@Column(name = "COD_ESTRUTURAL")
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

	public Integer getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(Integer codUnidade) {
		this.codUnidade = codUnidade;
	}

	public AreaAtendimento() {
		// TODO Auto-generated constructor stub
	}

	public AreaAtendimento(String nome, String sigla, String codEstruturado) {
		this.nome = nome;
		this.sigla = sigla;
		this.codEstruturado = codEstruturado;
	}

	@Override
	public String toString() {
		return sigla + " - " + nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUnidade == null) ? 0 : codUnidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return (obj instanceof AreaAtendimento) ? (this.getCodUnidade() == null ? this == obj
				: this.getCodUnidade().equals(((AreaAtendimento) obj).getCodUnidade()))
				: false;
	}

}
