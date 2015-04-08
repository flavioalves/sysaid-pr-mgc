package br.gov.presidencia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "viw0001pes_sysaid", schema="ORADADOS")
public class SysaidView {

	@Id
	@Column(name = "IDN_SERVIDOR", updatable = false, insertable = false)
	private Integer id;
	
	@Column(name = "COD_LOTACAO")
	private Integer codLotacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodLotacao() {
		return codLotacao;
	}

	public void setCodLotacao(Integer codLotacao) {
		this.codLotacao = codLotacao;
	}
}
