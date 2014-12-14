package br.gov.presidencia.control.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import br.gov.presidencia.model.Usuario;

@Named
@ViewScoped
public class GerenteDeContasMB extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Usuario> listaGerentes;

	private boolean shouldRender;
	
	@PostConstruct
	public void init() {
		this.listaGerentes = new ArrayList<Usuario>();

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
	}
	
	public void pesquisarGerente(ActionEvent actionEvent) {
		addMessage("Welcome to Primefaces!!");
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

	public boolean isShouldRender() {
		shouldRender = listaGerentes != null && this.listaGerentes.size() > 0 ? true : false;
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
	}

	
}
