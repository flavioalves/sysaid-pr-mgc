package br.gov.presidencia.control.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.presidencia.facade.ServiceFacade;
import br.gov.presidencia.model.GerenteContasVO;
import br.gov.presidencia.model.VinculoGerente;

@Named
@SessionScoped
public class RelatorioGerenteDeContaMB extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private List<GerenteContasVO> listaGerentesContas;
	
	@Inject
	private ServiceFacade serviceFachada;
	
	@PostConstruct	
	public void init(){
		//carrega Lista Gerentes de Contas
		listaGerentesContas = serviceFachada.listarGerentesDeConta();
		
		for (GerenteContasVO vo : listaGerentesContas) {
			List<VinculoGerente> vinculosGerenteAtual = serviceFachada.listVinculosDeArearPorGerente(vo.getUsername());
			if(vinculosGerenteAtual != null && vinculosGerenteAtual.size() > 0){
				List<String> codLotacoes = new ArrayList<String>();
				for (VinculoGerente vinculoGerente : vinculosGerenteAtual) {
					codLotacoes.add(vinculoGerente.getCodLotacao());
				}
				
				vo.setContas(serviceFachada.listAreasByCodLotacao(codLotacoes));
			}
		}		
	}
	
	public List<GerenteContasVO> getListaGerentesContas() {			
		return listaGerentesContas;
	}
	

	public void setListaGerentesContas(List<GerenteContasVO> listaGerentesContas) {
		this.listaGerentesContas = listaGerentesContas;
	}

}
