package br.gov.presidencia.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "SYSAID_USER")
@NamedQueries({ @NamedQuery(name = "Usuario.findUserByName", query = "SELECT u FROM Usuario u where UPPER(u.userNameCalculado) LIKE '%UPPER(:nome)%' ORDER BY u.nome ASC"), })
public class Usuario extends GenericModel implements Serializable {

	@Transient
	private Integer id;

	public static int All = 1;
	public static int AssignedOnly = 2;
	public static int AssigendGroupAndAssignedOnly = 3;

	@Id
	@Column(name = "USER_NAME", updatable = false, insertable = false)
	private String userName;

	@Column(name = "FIRST_NAME", updatable = false, insertable = false)
	private String nome;

	@Column(name = "LAST_NAME", updatable = false, insertable = false)
	private String sobrenome;

	@Column(name = "CALCULATED_USER_NAME", updatable = false, insertable = false)
	private String userNameCalculado;

	@Column(name = "EMAIL_ADDRESS", updatable = false, insertable = false)
	private String email;

	@Column(name = "ADMINISTRATOR")
	@Type(type = "yes_no")
	private Boolean administrador;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user2group", joinColumns = @JoinColumn(name = "USER_NAME"), inverseJoinColumns = @JoinColumn(name = "group_name"))
	private List<Grupo> grupos;

	@Transient
	private Boolean indisponivelNoPeriodo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserNameCalculado() {
		return userNameCalculado;
	}

	public void setUserNameCalculado(String userNameCalculado) {
		this.userNameCalculado = userNameCalculado;
	}

	public Boolean getIndisponivelNoPeriodo() {
		return indisponivelNoPeriodo;
	}

	public void setIndisponivelNoPeriodo(Boolean indisponivelNoPeriodo) {
		this.indisponivelNoPeriodo = indisponivelNoPeriodo;
	}

	public String getNomeCompleto() {
		return (nome + " " + sobrenome);
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
