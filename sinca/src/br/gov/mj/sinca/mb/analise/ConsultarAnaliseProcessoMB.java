package br.gov.mj.sinca.mb.analise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.entidades.Pessoa;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "consultarAnaliseProcessoMB")
@ViewScoped
public class ConsultarAnaliseProcessoMB implements Serializable {

    private static final String LISTA_SESSAO = "LISTA_SESSAO";

    private static final long serialVersionUID = 1L;

    private String numCpf;
    private String mensagem;

    private Integer numProcessoCA;
    private String numProcessoMJ;
    private Date dataProtocoloMJ;
    private Date dataProtocoloCA;

    private List<Pessoa> listarPessoa;
    private List<Processo> listarProcessos;
    private List<PessoaProcesso> listarPessoaProcesso;
    private Pessoa pessoa;
    private Processo processo;
    private PessoaProcesso pessoaProcesso;

    @PostConstruct
    public void Init() {
	System.out.println("Chamada Init <>  PosConstruct");
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	listarPessoa = new ArrayList<Pessoa>();
	listarPessoaProcesso = new ArrayList<PessoaProcesso>();
	JSFUtil.getSessionMap().put(LISTA_SESSAO, listarPessoaProcesso);
	listarProcessos = new ArrayList<Processo>();
	pessoa = new Pessoa();
	pessoaProcesso = new PessoaProcesso();
	processo = new Processo();
	numProcessoMJ = null;
	numProcessoCA = null;
	this.mensagem = "Pesquisando....";
    }

    public String detalharProcesso() {
	JSFUtil.getSessionMap().put("processoLista", pessoaProcesso);
	return "/pages/processo/manterProcesso"+"?faces-redirect=true";
    }

    public List<Pessoa> consultarPessoas() {

	JSFUtil.retornarMensagemModal("", "Consultando pessoas!");
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<Pessoa>();
	String nomePessoa = pessoa != null ? pessoa.getNomePessoa() : null;

	if (pessoa != null && pessoa.getIdPessoa() != null && pessoa.getIdPessoa() > 0) {
	    pessoa = new PessoaDAO().lerPorId(pessoa.getIdPessoa());
	    pessoas.add(pessoa);
	    setListarPessoa(pessoas);
	    return pessoas;
	}

	if (nomePessoa == null && (numCpf == null || numCpf.equals(""))) {
	    return pessoas;
	}

	// if (numCpf != null || nomePessoa != null) {
	// if ((numCpf == null || numCpf.equals("")) && nomePessoa != null) {
	// if (nomePessoa.length() <= 4) {
	// JSFUtil.retornarMensagem(null,
	// "Para Consulta a Pessoa Favor Informar o Nome da Pessoa com mas de 4 (quatro) caracteres!");
	// return pessoas;
	// }
	// pessoas = new PessoaDAO().listaPessoaPorNomeLk(nomePessoa);
	// JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	// } else {
	// pessoas = new
	// PessoaDAO().listaPessoaPorNomeCpfCnpj(numCpf.replaceAll(".",
	// "").replaceAll("-", "")
	// .trim(), nomePessoa);
	// JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	// }
	// setListarPessoa(pessoas);
	// }
	return pessoas;
    }

    public String consultarProcesso() {
	try {
	    JSFUtil.getSessionMap().put(LISTA_SESSAO, null);
	    List<PessoaProcesso> lista = null;
	    numProcessoCA = (numProcessoCA == 0 ? null : numProcessoCA);
	    numProcessoMJ = (numProcessoMJ == null ? numProcessoMJ : (numProcessoMJ.equals("") ? null : numProcessoMJ));

	    if (dataProtocoloCA != null || dataProtocoloMJ != null) {
		lista = new PessoaProcessoDAO().listarProcessoPorDataProtocolo(dataProtocoloCA, dataProtocoloMJ);
		processo = new Processo();
		setListarPessoaProcesso(lista);
		JSFUtil.getSessionMap().put(LISTA_SESSAO, getListarPessoaProcesso());
		JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
		return null;
	    }
	    if (pessoa != null && pessoa.getIdPessoa() > 0) {
		lista = new PessoaProcessoDAO().listarProcessoPorIdPessoa(pessoa.getIdPessoa());
		setListarPessoaProcesso(lista);
	    }

	    if (numProcessoCA != null || numProcessoMJ != null) {
		lista = new PessoaProcessoDAO().ListarProcesso(numProcessoCA, numProcessoMJ);
		setListarPessoaProcesso(lista);
	    }
	    if (lista == null || lista.isEmpty()) {
		JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_WARN,
			"N�o foi localizando o processo com os par�mentros de pesquisa");
		processo = new Processo();
		setListarPessoaProcesso(new ArrayList<PessoaProcesso>());
		return null;
	    }
	    setListarPessoaProcesso(lista);
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	} catch (Exception e) {
	    JSFUtil.retornarMensagem(null, "N�o foi poss�vel consultar o processo na Comiss�o de Anistia");
	    processo = new Processo();
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	    e.printStackTrace();
	}
	return null;
    }

    public List<Pessoa> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<Pessoa> pessoas = new PessoaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public String getNumProcessoMJ() {
	return numProcessoMJ;
    }

    public void setNumProcessoMJ(String numProcessoMJ) {
	this.numProcessoMJ = numProcessoMJ;
    }

    public List<Pessoa> getListarPessoa() {
	return listarPessoa;
    }

    public void setListarPessoa(List<Pessoa> listarPessoa) {
	this.listarPessoa = listarPessoa;
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

    public Processo getProcesso() {
	return processo;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public PessoaProcesso getPessoaProcesso() {
	return pessoaProcesso;
    }

    public void setPessoaProcesso(PessoaProcesso pessoaProcesso) {
	this.pessoaProcesso = pessoaProcesso;
    }

    public String getNumCpf() {
	return numCpf;
    }

    public void setNumCpf(String numCpf) {
	this.numCpf = numCpf;
    }

    public Integer getNumProcessoCA() {
	return numProcessoCA;
    }

    public void setNumProcessoCA(Integer numProcessoCA) {
	this.numProcessoCA = numProcessoCA;
    }

    public List<Processo> getListarProcessos() {
	return listarProcessos;
    }

    public void setListarProcessos(List<Processo> listarProcessos) {
	this.listarProcessos = listarProcessos;
    }

    public List<PessoaProcesso> getListarPessoaProcesso() {
	@SuppressWarnings("unchecked")
	List<PessoaProcesso> listaSessao = (List<PessoaProcesso>) JSFUtil.getSessionMap().get(LISTA_SESSAO);
	if ((listarPessoaProcesso == null || listarPessoaProcesso.isEmpty()) && listaSessao != null) {
	    listarPessoaProcesso = listaSessao;
	}
	return listarPessoaProcesso;
    }

    public void setListarPessoaProcesso(List<PessoaProcesso> listarPessoaProcesso) {
	this.listarPessoaProcesso = listarPessoaProcesso;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }

    public Date getDataProtocoloMJ() {
	return dataProtocoloMJ;
    }

    public void setDataProtocoloMJ(Date dataProtocoloMJ) {
	this.dataProtocoloMJ = dataProtocoloMJ;
    }

    public Date getDataProtocoloCA() {
	return dataProtocoloCA;
    }

    public void setDataProtocoloCA(Date dataProtocoloCA) {
	this.dataProtocoloCA = dataProtocoloCA;
    }

}
