package br.gov.presidencia.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.presidencia.dao.AreaAtendimentoDao;
import br.gov.presidencia.dao.UsuarioDao;
import br.gov.presidencia.dao.ViewDao;
import br.gov.presidencia.dao.VinculoGerenteDao;
import br.gov.presidencia.model.AreaAtendimento;
import br.gov.presidencia.model.GerenteContasVO;
import br.gov.presidencia.model.SysaidView;
import br.gov.presidencia.model.Usuario;
import br.gov.presidencia.model.VinculoGerente;

@Named
public class ServiceFacade implements Serializable {

	private static final long serialVersionUID = 7419336992415458594L;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private AreaAtendimentoDao areaDao;
	
	@Inject
	private VinculoGerenteDao vinculoGerenteDao;
	
	@Inject
	private ViewDao viewDao;

	public List<SysaidView> listViewItens(){
		return viewDao.findAll();
	}
	
	public List<AreaAtendimento> listAreas() {
		//return areaDao.findAll();
		return areaDao.listAreas(); 
	}

	public void salvarVinculoGerente(VinculoGerente vinculo) throws Exception {
		List<VinculoGerente> vinculos = vinculoGerenteDao.findVinculosByUsernameAndCodLotacao(vinculo.getUserName(), vinculo.getCodLotacao());
		if(vinculos != null && vinculos.size() == 0){
			vinculoGerenteDao.save(vinculo);	
		}		
	}

	public List<VinculoGerente> listVinculosDeArearPorGerente(String username) {
		return vinculoGerenteDao.findVinculosByUsername(username);
	}

	public List<GerenteContasVO> listarGerentesDeConta() {
		return vinculoGerenteDao.listarGerentesDeConta();
	}
	
	public List<Usuario> listAllPorGrupoRelatorioPerido(String grupoId, Date inicio, Date fim) {
		List<Usuario> lista = this.getUsuarioDao() .listAllPorGrupoRelatorioPerido(grupoId, inicio, fim);

		return lista;
	}

	public List<VinculoGerente> findVinculosByUsernameAndCodLotacao(String userName, String codLotacao){
		return vinculoGerenteDao.findVinculosByUsernameAndCodLotacao(userName, codLotacao);
	}
	
	public List<Usuario> findUsuarioByNome(String nome) {
		return this.getUsuarioDao().findUsuarioByNome(nome);
	}

	public Usuario find(String userName) {
		return usuarioDao.find(userName);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	 public List<AreaAtendimento> listAreasByCodLotacao(List<String> codLotacoes) {	
		 return areaDao.listAreasByCodLotacao(codLotacoes);
	 }

	public List<Usuario> listAllUsers() {
		return usuarioDao.findAll();
	}

	public void excluirVinculo(VinculoGerente vinculoGerente) throws Exception {
		vinculoGerenteDao.delete(vinculoGerente);	
	}

	public void excluirVinculo(String userName, List<AreaAtendimento> itensRemovidos) throws Exception {
		for (AreaAtendimento areaAtendimento : itensRemovidos) {
			List<VinculoGerente> vinculo = findVinculosByUsernameAndCodLotacao(userName, areaAtendimento.getCodUnidade().toString());
			
			for (VinculoGerente vinculoGerente : vinculo) {
				excluirVinculo(vinculoGerente);
			}
		}
	}

	public List<Usuario> findUsuarioTipoGerente() {
		return this.getUsuarioDao().findUsuarioTipoGerente();
	}

}
