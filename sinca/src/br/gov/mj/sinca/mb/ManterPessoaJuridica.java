package br.gov.mj.sinca.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.PessoaJuridicaDAO;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.entidades.PessoaProcesso;


@ManagedBean(name = "manterPessoaJuridicaMB")
@ViewScoped
public class ManterPessoaJuridica implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2241L;

    private Logger logger = null;
    
    private PessoaJuridica pessoaJuridicaCadastro;
    private PessoaJuridica pessoaJuridica;    
    private String numCNPJ;
    private PessoaProcesso pessoaProcesso;
    private List<PessoaFisica> listaPessoaFisicas;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct"+this.getClass().getName());
	instanciaAtributos();
    }
    private void instanciaAtributos() {
	pessoaJuridicaCadastro = new PessoaJuridica();
	
    }
    
    public void salvar(){
	
    }
    
    
    public List<PessoaJuridica> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    logger.info("Nome Pessoa PESQUISA " + nome);
	List<PessoaJuridica> pessoas = new PessoaJuridicaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }
    
    
    public List<PessoaJuridica> consultarPessoasJuridica() {
	List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
	
	
	return lista;
    }
    
    public PessoaProcesso getPessoaProcesso() {
        return pessoaProcesso;
    }
    public List<PessoaFisica> getListaPessoaFisicas() {
        return listaPessoaFisicas;
    }
    public void setPessoaProcesso(PessoaProcesso pessoaProcesso) {
        this.pessoaProcesso = pessoaProcesso;
    }
    public void setListaPessoaFisicas(List<PessoaFisica> listaPessoaFisicas) {
        this.listaPessoaFisicas = listaPessoaFisicas;
    }
    public PessoaJuridica getPessoaJuridicaCadastro() {
        return pessoaJuridicaCadastro;
    }
    public void setPessoaJuridicaCadastro(PessoaJuridica pessoaJuridicaCadastro) {
        this.pessoaJuridicaCadastro = pessoaJuridicaCadastro;
    }
    public String getNumCNPJ() {
        return numCNPJ;
    }
    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }
    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }
    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    
    
}
