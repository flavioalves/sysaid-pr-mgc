package br.gov.presidencia.control.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.gov.presidencia.facade.ServiceFacade;
import br.gov.presidencia.model.AreaAtendimento;
import br.gov.presidencia.model.Usuario;
import br.gov.presidencia.model.VinculoGerente;


@Named
@SessionScoped
public class GerenteDeContasMB extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listaGerentes;
	private Usuario selectedGerente;
	private String nomeGerentePesquisado;
	private String nomeGerente;
	
	@Inject
	private ServiceFacade serviceFachada;
	private DualListModel<AreaAtendimento> areasAtendimento;
	private boolean shouldRender;
	private List<AreaAtendimento> areasAtendimentoSource;
	List<AreaAtendimento> areasAtendimentoTarget;
	
	@PostConstruct
	public void init() {
		areasAtendimentoSource = serviceFachada.listAreas();
		areasAtendimentoTarget = new ArrayList<AreaAtendimento>();
		this.areasAtendimento = new DualListModel<AreaAtendimento>(areasAtendimentoSource, areasAtendimentoTarget);
		this.nomeGerentePesquisado = "";
		this.shouldRender = false;
	}
	
	public void pesquisarGerente(ActionEvent event) {
		if(nomeGerentePesquisado != null){
			listaGerentes = serviceFachada.findUsuarioByNome(nomeGerentePesquisado);
			
			if(listaGerentes != null && listaGerentes.size() > 0){
				this.shouldRender = true;	
			} else { 
				addMessage("Não foram encontrados resultados para os parâmetros informados.");
			}
		}
	}
	
	public void salvarVinculo(ActionEvent ev){				
		if(selectedGerente != null){
			for (AreaAtendimento area : areasAtendimento.getTarget()) {
				VinculoGerente vinculo = new VinculoGerente();
				vinculo.setCodLotacao(area.getCodUnidade().toString());
				vinculo.setUserName(selectedGerente.getUserName());
				vinculo.setNome(selectedGerente.getNome());
				vinculo.setUserNameCadastro(getUsuarioLogadoCookie().getNome() != null ? getUsuarioLogadoCookie().getNome() : "sysaid"); 
				vinculo.setDtCadastro(new Date());	
				try {
					serviceFachada.salvarVinculoGerente(vinculo);
				} catch (Exception e) {
					errorMessage("Ocorreu um erro ao salvar o vínculo.");
				}
			}
			addMessage("Área(s) vinculas ao gerente de contas");
		} else {
			addMessage("Selecione um gerente para vincular a alguma área.");
		}		
	}
	
	public void editarVinculo(ActionEvent ev){ 
		selectedGerente = (Usuario) ev.getComponent().getAttributes().get("gerente");			
		carregaVinculosExistentes(selectedGerente.getUserName());		
	}
	
	private void carregaVinculosExistentes(String userNameGerente){
		List<VinculoGerente> vinculosGerenteAtual = serviceFachada.listVinculosDeArearPorGerente(userNameGerente);
		if(vinculosGerenteAtual != null && vinculosGerenteAtual.size() > 0){
			List<String> codLotacoes = new ArrayList<String>();
			for (VinculoGerente vinculoGerente : vinculosGerenteAtual) {
				codLotacoes.add(vinculoGerente.getCodLotacao());
			}
			
			List<AreaAtendimento> areasVinculadas = serviceFachada.listAreasByCodLotacao(codLotacoes);
			//get codigo lotacao end find areas		
			areasAtendimento.getTarget().clear();
			areasAtendimento.getTarget().addAll(areasVinculadas);			
		}else {
			//limpa vinculos
			areasAtendimentoTarget = new ArrayList<AreaAtendimento>();
			this.areasAtendimento = new DualListModel<AreaAtendimento>(areasAtendimentoSource, areasAtendimentoTarget);
		}
	}
	
	public void excluirVinculo(ActionEvent ev){
		selectedGerente = (Usuario) ev.getComponent().getAttributes().get("gerente");
		List<VinculoGerente> vinculosGerenteAtual = serviceFachada.listVinculosDeArearPorGerente(selectedGerente.getUserName());
		for (VinculoGerente vinculoGerente : vinculosGerenteAtual) {
			try {
				serviceFachada.excluirVinculo(vinculoGerente);
			} catch (Exception e) {
				errorMessage("Erro ao excluir vínculos.");
			}
		}
		addMessage("Vinculos excluidos com sucesso!");
	}
	
	
	public void limparVinculo(ActionEvent ev){
		//addMessage("Limpar");
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void errorMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Usuario> getListaGerentes() {
		return listaGerentes;
	}

	public void setListaGerentes(List<Usuario> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}
	
   public List<Usuario> autocomplete(String query) {
		listaGerentes = serviceFachada.findUsuarioByNome(query);
		
		if(listaGerentes != null && listaGerentes.size() > 0){
			this.shouldRender = true;	
		} 
		return listaGerentes;
		
    }

	public boolean isShouldRender() {
		shouldRender = listaGerentes != null && this.listaGerentes.size() > 0 ? true : false;
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
	}

	public String getNomeGerentePesquisado() {
		return nomeGerentePesquisado;
	}

	public void setNomeGerentePesquisado(String nomeGerentePesquisado) {
		this.nomeGerentePesquisado = nomeGerentePesquisado;
	}

	public DualListModel<AreaAtendimento> getAreasAtendimento() {
		return areasAtendimento;
	}
	
	public void setAreasAtendimento(DualListModel<AreaAtendimento> areasAtendimento) {
		this.areasAtendimento = areasAtendimento;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public Usuario getSelectedGerente() {
		return selectedGerente;
	}

	public void setSelectedGerente(Usuario selectedGerente) {
		this.selectedGerente = selectedGerente;
	}

}
