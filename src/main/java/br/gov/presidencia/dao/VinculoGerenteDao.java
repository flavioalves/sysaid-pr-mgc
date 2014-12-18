package br.gov.presidencia.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.gov.presidencia.model.VinculoGerente;

@Named
public class VinculoGerenteDao extends GenericDao<VinculoGerente> {

	public VinculoGerenteDao() {
		super(VinculoGerente.class);
	}

	private static final long serialVersionUID = 1L;
	
	public List<VinculoGerente> findVinculosByUsername(String username){	
		TypedQuery<VinculoGerente> query = this.getEntityManager().createNamedQuery("VinculoGerente.findVinculosByUsername", VinculoGerente.class);
		query.setParameter("username", username);
		query.setMaxResults(20);
		return query.getResultList();	
	}

	public List<VinculoGerente> findVinculosByUsernameAndCodLotacao(String userName,
			String codLotacao) {
		TypedQuery<VinculoGerente> query = this.getEntityManager().createNamedQuery("VinculoGerente.findVinculosByUsernameAndCodLotacao", VinculoGerente.class);
		query.setParameter("username", userName );
		query.setParameter("codLotacao", codLotacao );
		return query.getResultList();	
		
	}

}
