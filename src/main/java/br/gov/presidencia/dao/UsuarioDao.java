package br.gov.presidencia.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.gov.presidencia.model.Usuario;

@Named
public class UsuarioDao extends GenericDao<Usuario> {
	
	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public List<Usuario> listAll(){
		
		List<Usuario> lista = new ArrayList<Usuario>();
		return lista;
		
	}
	
	
	public List<Usuario> listAllPorGrupoRelatorioPerido(String grupoId,
			Date inicio, Date fim) {
		
		
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql;
		
		if(inicio == null || fim == null){
			 sql = "SELECT DISTINCT(g.user_name), i.ativo FROM user2group g left join mgd_indisponibilidade i on g.user_name = i.user_name and "
					+ "  :dataHoje  between i.dt_inicio and i.dt_fim and i.ativo = 'Y' "
					+ " where  g.group_name =:groupId";
		}else{
			 sql = "SELECT DISTINCT(g.user_name), i.ativo FROM user2group g left join mgd_indisponibilidade i on g.user_name = i.user_name and "
				+ "  ((:dt_inicio between i.dt_inicio and i.dt_fim) or (:dt_fim between i.dt_inicio and i.dt_fim) or (i.dt_inicio >= :dt_inicio and i.dt_fim <= :dt_fim)) and i.ativo = 'Y' "
				+ " where  g.group_name =:groupId";
		}
		
		Query query = getEntityManager().createNativeQuery(sql);
		if(inicio == null || fim == null){
			query.setParameter("dataHoje", new Date());
		};
		
		query.setParameter("groupId", grupoId);
		
		if(inicio != null && fim != null){
		query.setParameter("dt_inicio", inicio);
		query.setParameter("dt_fim", fim);
		}
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> retorno = query.getResultList();
		for(Object[] obj: retorno){
			Usuario user = this.find(obj[0]);
			Character ativo = ((Character)obj[1]);
			if(ativo != null && ativo.charValue() == 'Y' ){
				user.setIndisponivelNoPeriodo(true);
			}else{
				user.setIndisponivelNoPeriodo(false);
			}
			
			this.refresh(user);
			
			//user.getOsHoje();
			//.user.getOsTotal();
			lista.add(user);
		}
		
		
		return lista;
	}
	
	public List<Usuario> findUsuarioByNome(String nome){		
		TypedQuery<Usuario> query = this.getEntityManager().createNamedQuery("Usuario.findUserByName", Usuario.class);
		query.setParameter("nome", "%" +nome +"%");
		query.setMaxResults(20);
		return query.getResultList();
		
	}
	

	
	public Usuario find(String userName) {
	       Query query = getEntityManager().createQuery("From Usuario user WHERE UPPER(user.userName) = UPPER(:userName)");
	       query.setParameter("userName", userName);
	       return (Usuario) query.getSingleResult();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501828520309506308L;



}
