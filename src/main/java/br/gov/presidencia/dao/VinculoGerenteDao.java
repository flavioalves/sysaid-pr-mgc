package br.gov.presidencia.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.presidencia.model.GerenteContasVO;
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
	
	public List<GerenteContasVO> listarGerentesDeConta() {
		String jpql = "SELECT NEW br.gov.presidencia.model.GerenteContasVO(u.nome, u.email, u.telefone, u.userName) "
					+ "FROM VinculoGerente v, Usuario u WHERE v.userName = u.userName GROUP BY v.userName";		
        Query query = getEntityManager().createQuery(jpql);

       return query.getResultList();
	}

}
