package br.gov.presidencia.control.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.gov.presidencia.facade.UsuarioFacade;
import br.gov.presidencia.model.AreaAtendimento;
import br.gov.presidencia.model.Usuario;


@Named
@SessionScoped
public class GerenteDeContasMB extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listaGerentes;
	
	private String nomeGerentePesquisado;
	private String nomeGerente;
	
	@Inject
	private UsuarioFacade usuarioFachada;
	private DualListModel<AreaAtendimento> areasAtendimento;
	private boolean shouldRender;
	
	@PostConstruct
	public void init() {
		List<AreaAtendimento> areasAtendimentoSource = criarMockAreasAtendimento();
		List<AreaAtendimento> areasAtendimentoTarget = new ArrayList<AreaAtendimento>();
		this.areasAtendimento = new DualListModel<AreaAtendimento>(areasAtendimentoSource, areasAtendimentoTarget);
		this.nomeGerentePesquisado = "";
		this.shouldRender = false;
		
	}
	
	public void pesquisarGerente(ActionEvent event) {
		if(nomeGerentePesquisado != null){
			listaGerentes = usuarioFachada.findUsuarioByNome(nomeGerentePesquisado);
			
			if(listaGerentes != null && listaGerentes.size() > 0){
				this.shouldRender = true;	
			} else { 
				addMessage("Não foram encontrados resultados para os parâmetros informados.");
			}
		}
	}
	
	public void salvarVinculo(ActionEvent event){
		addMessage("Salvar");
	}
	
	public void excluirVinculo(ActionEvent event){
		addMessage("Excluir");
	}
	
	public void limparVinculo(ActionEvent event){
		addMessage("Limpar");
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Usuario> getListaGerentes() {
		return listaGerentes;
	}

	public void setListaGerentes(List<Usuario> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}
	
   public List<String> autocomplete(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
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

	private List<AreaAtendimento> criarMockAreasAtendimento(){
		List<AreaAtendimento> mock = new ArrayList<AreaAtendimento>();
		AreaAtendimento a = new AreaAtendimento("A Nome area 1", "Sigla Area A","00.00.00.01");
		AreaAtendimento b = new AreaAtendimento("B Nome area 2", "Sigla Area B","00.00.00.02");
		AreaAtendimento c = new AreaAtendimento("C Nome area 3", "Sigla Area C","00.00.00.03");
		mock.add(a);
		mock.add(b);
		mock.add(c);
		return mock;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

}
