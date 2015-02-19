package br.gov.mj.sinca.mb.diligencia;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.entidades.Diligencia;
import br.gov.mj.sinca.entidades.DiligenciaProcesso;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterDiligenciaMB")
@ViewScoped
public class ManterDiligencia {

    private Logger logger = null;

    private Diligencia diligencia;
    private DiligenciaProcesso diligenciaProcesso;
    private Processo processo;
    private PessoaJuridica pessoaJuridica;
    private PessoaProcesso pessoaProcesso;
    private List<PessoaFisica> listaPessoaFisicas;
    private List<DiligenciaProcesso> diligenciaProcessos;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct" + this.getClass().getName());
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	diligencia = new Diligencia();
	diligenciaProcesso = new DiligenciaProcesso();
	diligenciaProcessos = new ArrayList<DiligenciaProcesso>();
	listaPessoaFisicas = new ArrayList<PessoaFisica>();
	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("processoLista");
	if(pessoaProcesso!=null && pessoaProcesso.getIdPessoaProcesso()>0){
	    listaPessoaFisicas = new PessoaProcessoDAO().listarPessoasProcesso(pessoaProcesso.getProcesso().getIdProcesso());
	}

    }

    public DiligenciaProcesso getDiligenciaProcesso() {
	return diligenciaProcesso;
    }

    public Processo getProcesso() {
	return processo;
    }

    public List<DiligenciaProcesso> getDiligenciaProcessos() {
	return diligenciaProcessos;
    }

    public void setDiligenciaProcesso(DiligenciaProcesso diligenciaProcesso) {
	this.diligenciaProcesso = diligenciaProcesso;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public void setDiligenciaProcessos(List<DiligenciaProcesso> diligenciaProcessos) {
	this.diligenciaProcessos = diligenciaProcessos;
    }

    public Diligencia getDiligencia() {
	return diligencia;
    }

    public void setDiligencia(Diligencia diligencia) {
	this.diligencia = diligencia;
    }

    public PessoaProcesso getPessoaProcesso() {
	return pessoaProcesso;
    }

    public void setPessoaProcesso(PessoaProcesso pessoaProcesso) {
	this.pessoaProcesso = pessoaProcesso;
    }

    public List<PessoaFisica> getListaPessoaFisicas() {
	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("processoLista");
	if(pessoaProcesso!=null && pessoaProcesso.getIdPessoaProcesso()>0){
	    listaPessoaFisicas = new PessoaProcessoDAO().listarPessoasProcesso(pessoaProcesso.getProcesso().getIdProcesso());
	}
	return listaPessoaFisicas;
    }

    public PessoaJuridica getPessoaJuridica() {
	return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
	this.pessoaJuridica = pessoaJuridica;
    }

    public void setListaPessoaFisicas(List<PessoaFisica> listaPessoaFisicas) {
	this.listaPessoaFisicas = listaPessoaFisicas;
    }

}
