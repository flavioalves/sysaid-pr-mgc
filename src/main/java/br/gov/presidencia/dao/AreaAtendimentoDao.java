package br.gov.presidencia.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import br.gov.presidencia.model.AreaAtendimento;

@Named
public class AreaAtendimentoDao extends GenericDao<AreaAtendimento> {

	public AreaAtendimentoDao() {
		super(AreaAtendimento.class);
	}

	public List<AreaAtendimento> listAreas() {
		List<AreaAtendimento> areas = new ArrayList<AreaAtendimento>();
		String sql = "FROM AreaAtendimento area WHERE area.seqHierarquia = 999 ORDER BY area.sigla";
		//String sql = "FROM AreaAtendimento area WHERE area.codUnidade IN (SELECT DISTINCT (v.codLotacao) FROM SysaidView v) ORDER BY area.sigla";
		//String sql = "FROM AreaAtendimento area WHERE area.codUnidade IN (SELECT DISTINCT (v.codLotacao) FROM SysaidView v)";
		Query query = getEntityManager().createQuery(sql);	       
		return query.getResultList();
	}
	
	public List<AreaAtendimento> listAreasByCodLotacao(List<String> codLotacoes) {
		List<AreaAtendimento> areas = new ArrayList<AreaAtendimento>();
		List<Integer> cods = new ArrayList<Integer>();		
		
		if(codLotacoes != null){
			
			for (String codLotacao : codLotacoes) {
				cods.add(new Integer(codLotacao));
			}
			
		   String hql = "From AreaAtendimento area WHERE area.codUnidade IN :codLotacoes "; 	
	       Query query = getEntityManager().createQuery(hql);	       
	       query.setParameter("codLotacoes", cods);
	       
	       query.setMaxResults(20);
	       return query.getResultList();
		}
		return areas;
	}
	
	
	
}
