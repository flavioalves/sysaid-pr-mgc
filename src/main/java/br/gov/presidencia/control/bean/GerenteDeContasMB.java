package br.gov.presidencia.control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.gov.presidencia.model.AreaAtendimento;
import br.gov.presidencia.model.Usuario;

@ViewScoped
@ManagedBean
public class GerenteDeContasMB extends AbstractBean {
	private List<Usuario> listaGerentes;
	private String nomeGerentePesquisado;
	private String nomeGerente;

	private DualListModel<AreaAtendimento> areasAtendimento;
	private boolean shouldRender;
	
	@PostConstruct
	public void init() {
		List<AreaAtendimento> areasAtendimentoSource = criarMockAreasAtendimento();
		List<AreaAtendimento> areasAtendimentoTarget = new ArrayList<AreaAtendimento>();
		areasAtendimento = new DualListModel<AreaAtendimento>(areasAtendimentoSource, areasAtendimentoTarget);
		this.shouldRender = false;
		this.listaGerentes = new ArrayList<Usuario>();
	}
	
	public void pesquisarGerente(ActionEvent actionEvent) {
		
		if(nomeGerentePesquisado != null){
			Usuario a = new Usuario();
			a.setNome("Flávio Alves");
			a.setEmail("flavio@presidencia.com.br");
			a.setUserName("flavioalves");
			a.setRamal("1351");
	
			Usuario b = new Usuario();
			b.setNome("Alvaro Silva");
			b.setEmail("alvaro@presidencia.com.br");
			b.setUserName("alvarosilva");
			b.setRamal("1551");
	
			Usuario c = new Usuario();
			c.setNome("Cristiano Maia");
			c.setEmail("cristiano@presidencia.com.br");
			c.setUserName("Cristiano Maia");
			c.setRamal("2290");
	
			this.listaGerentes.add(a);
			this.listaGerentes.add(b);
			this.listaGerentes.add(c);
			this.shouldRender = true;
		}
		
		//addMessage("Welcome to Primefaces!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
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
