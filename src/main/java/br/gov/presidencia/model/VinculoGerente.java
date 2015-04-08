package br.gov.presidencia.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "IPI_SYSAID_MGC_SYSAID")
@NamedQueries({ 
	@NamedQuery(name = "VinculoGerente.findVinculosByUsername", query = "SELECT v FROM VinculoGerente v where v.userName = :username ORDER BY v.codLotacao ASC"),
	@NamedQuery(name = "VinculoGerente.findVinculosByUsernameAndCodLotacao", query = "SELECT v FROM VinculoGerente v where v.userName = :username AND v.codLotacao = :codLotacao ")})
public class VinculoGerente {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "NOME_GERENTE")
	private String nome;
	
	@Column(name = "COD_LOTACAO")
	private String codLotacao;

	@Column(name = "USER_NAME_CADASTRO")
	private String userNameCadastro;
	
	@Column(name = "DT_CADASTRO")
	private Date dtCadastro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCodLotacao() {
		return codLotacao;
	}

	public void setCodLotacao(String codLotacao) {
		this.codLotacao = codLotacao;
	}

	public String getUserNameCadastro() {
		return userNameCadastro;
	}

	public void setUserNameCadastro(String userNameCadastro) {
		this.userNameCadastro = userNameCadastro;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
