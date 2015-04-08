package br.gov.presidencia.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.gov.presidencia.model.SysaidView;

@Named
public class ViewDao extends GenericDao<SysaidView> {

	private static final long serialVersionUID = 1L;

	public ViewDao() {
		super(SysaidView.class);
	}

	public List<Integer> findView() {		
		String sql = "SELECT DISTINCT (v.codLotacao) FROM SysaidView v"; 	
		Query query = getEntityManager().createQuery(sql);	       
		return query.getResultList();
	}
}
