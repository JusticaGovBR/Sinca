package br.gov.mj.sinca.mb.analise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.dao.ProcessoDAO;
import br.gov.mj.sinca.dao.SubStatusProcessoDAO;
import br.gov.mj.sinca.dao.TipoAnaliseJulgamentoDAO;
import br.gov.mj.sinca.dao.TipoPerseguicaoDAO;
import br.gov.mj.sinca.entidades.AnaliseProcesso;
import br.gov.mj.sinca.entidades.AnalistaProcesso;
import br.gov.mj.sinca.entidades.PerseguicaoAnalise;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.SubStatusProcesso;
import br.gov.mj.sinca.entidades.TipoAnaliseJulgamento;
import br.gov.mj.sinca.entidades.TipoPerseguicao;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.DateUtil;
import br.gov.mj.sinca.util.JSFUtil;


/**
 * Maneger Beam que realiza as 
 * funcionalidades de manter análise
 * do processo de anistia.
 * @author Sebastiao.Costa
 * Contrato nº:XXXXX
 */
@ManagedBean(name = "manterAnaliseMB")
@ViewScoped
public class ManterAnaliseMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4258528337106321111L;

    private String mensagem;
    private String numCpf;

    private List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento = new ArrayList<TipoAnaliseJulgamento>();
    private List<AnalistaProcesso> listarAnalistaProcesso = new ArrayList<AnalistaProcesso>();
    private List<SubStatusProcesso> listarSubStatusProcesso = new ArrayList<SubStatusProcesso>();
    private List<TipoPerseguicao> listarTipoPerseguicao = new ArrayList<TipoPerseguicao>();
    
    private List<PerseguicaoAnalise> listarPerseguicaoAnalise = new ArrayList<PerseguicaoAnalise>();
    
    private List<PessoaFisica> listarPessoa = new ArrayList<PessoaFisica>();

    private Integer idSubStatusProcesso;
    private AnaliseProcesso analiseProcesso;
    private AnalistaProcesso analistaProcesso;
    private PessoaFisica pessoaAnalista;
    private TipoPerseguicao tipoPerseguicao;
    private PerseguicaoAnalise perseguicaoAnalise;

    private boolean habilitaTabePessoa;
    
    private Usuario usuario;

    private String dataRecebimento;
    
    @ManagedProperty(value="#{loginMB}") 
    private LoginMB loginMB;

    
    @PostConstruct
    public void Init() {
	System.out.println("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	listarTipoAnaliseJulgamento = new TipoAnaliseJulgamentoDAO().lerTodos();
	analiseProcesso = new AnaliseProcesso();
	analistaProcesso = new AnalistaProcesso();
	pessoaAnalista = new PessoaFisica();
	perseguicaoAnalise = new PerseguicaoAnalise();
	perseguicaoAnalise.setTipoPerseguicao(new TipoPerseguicao());
	tipoPerseguicao = new TipoPerseguicao();
	usuario = loginMB.getUsuario();
	dataRecebimento = DateUtil.dataHoraAtual();
	if (true) {
	    Processo processo = new ProcessoDAO().lerPorId(25271l);
	    analiseProcesso.setProcesso(processo);
	}
    }

    public String salvarAnalise() {
	return null;
    }

    public List<PessoaFisica> consultarPessoas() {

	analistaProcesso = new AnalistaProcesso();
	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<PessoaFisica>();
	String nomePessoa = pessoaAnalista != null ? pessoaAnalista.getNomePessoa() : null;

	if (pessoaAnalista != null && pessoaAnalista.getIdPessoa() != null && pessoaAnalista.getIdPessoa() > 0) {
	    pessoaAnalista = new PessoaDAO().lerPorId(pessoaAnalista.getIdPessoa());
	    pessoas.add(pessoaAnalista);
	    setListarPessoa(pessoas);
	    // habilitaTabePessoa = true;
	    JSFUtil.getRequestContext().execute("PF('dlg_responsavel').show()");
	    return pessoas;

	}

	if (nomePessoa == null && (numCpf == null || numCpf.equals(""))) {
	    return pessoas;
	}

	if (numCpf != null || nomePessoa != null) {
	    if ((numCpf == null || numCpf.equals("")) && nomePessoa != null) {
		if (nomePessoa.length() < 4) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o Nome da Pessoa com mas de 4 (quatro) caracteres!");
		    return pessoas;
		}
		pessoas = new PessoaDAO().listaPessoaPorNomeLk(nomePessoa);

	    } else {
		if (numCpf != null && numCpf.length() < 7) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o CPF da Pessoa com mas de 7 (quatro) caracteres!");
		    return pessoas;
		}

		pessoas = new PessoaDAO().listaPessoaPorNomeCpf(numCpf.replace(".", "").replace("-", "").trim(),
			nomePessoa);
		if (pessoas.isEmpty()) {
		    pessoas = new PessoaDAO().listaPessoaPorNomeCpf(numCpf.trim(), nomePessoa);
		}
	    }
	    setListarPessoa(pessoas);
	}
	if (getListarPessoa().isEmpty()) {
	    JSFUtil.retornarMensagem(null, "Nenhuma pessoa Analista encontrada!");
	    JSFUtil.getRequestContext().execute("PF('dlg_responsavel').hide()");
	    return new ArrayList<PessoaFisica>();
	}

	JSFUtil.getRequestContext().execute("PF('dlg_responsavel').show()");

	return pessoas;
    }

    public void incluirNaAnalise() {
	List<AnalistaProcesso> lista = getListarAnalistaProcesso();
	for (AnalistaProcesso anlst : lista) {
	    if (anlst.getPessoa() != null && anlst.getPessoa().getIdPessoa() > 0) {
		if (pessoaAnalista.getIdPessoa() == anlst.getPessoa().getIdPessoa()) {
		    getListarAnalistaProcesso().remove(analistaProcesso);
		}
	    }
	}
	analistaProcesso.setIdSubStatusProcesso(idSubStatusProcesso);
	analistaProcesso.setPessoa(pessoaAnalista);
	getListarAnalistaProcesso().add(analistaProcesso);
    }
    

   public void editarPessoaAnalise() {
	if (JSFUtil.getRequestMap().get("pessoaLista") != null
		&& ((AnalistaProcesso) JSFUtil.getRequestMap().get("pessoaLista")).getPessoa() != null) {
	    this.analistaProcesso = ((AnalistaProcesso) JSFUtil.getRequestMap().get("pessoaLista"));
	    this.idSubStatusProcesso = analistaProcesso.getIdSubStatusProcesso();
	    this.pessoaAnalista = analistaProcesso.getPessoa();
	    JSFUtil.getRequestContext().execute("PF('dlg_responsavel').show()");
	}
    }

    public void removerPessoaAnalise() {
	if (JSFUtil.getRequestMap().get("pessoaLista") != null
		&& ((AnalistaProcesso) JSFUtil.getRequestMap().get("pessoaLista")).getPessoa() != null) {
	    AnalistaProcesso analistaProc = ((AnalistaProcesso) JSFUtil.getRequestMap().get("pessoaLista"));
	    getListarAnalistaProcesso().remove(analistaProc);
	}
    }

    public void addPerseguica(){
	this.perseguicaoAnalise = new PerseguicaoAnalise();
	this.perseguicaoAnalise.setTipoPerseguicao(new TipoPerseguicao());
	this.tipoPerseguicao = new TipoPerseguicao();
	JSFUtil.getRequestContext().execute("PF('dlg_perseguicao').show()");
    }

    public void incluirPerseguicaoLista() {
   	List<PerseguicaoAnalise> lista = new ArrayList<PerseguicaoAnalise>();
   
   	if(this.tipoPerseguicao!=null && this.tipoPerseguicao.getCodTipo()!=null && this.tipoPerseguicao.getCodTipo()>0){
   	   this.tipoPerseguicao = new TipoPerseguicaoDAO().lerPorId(tipoPerseguicao.getCodTipo());
   	}
   	perseguicaoAnalise.setTipoPerseguicao(tipoPerseguicao);

	if(perseguicaoAnalise.getTipoPerseguicao()==null || perseguicaoAnalise.getTipoPerseguicao().getCodTipo()==null 
		|| perseguicaoAnalise.getTipoPerseguicao().getCodTipo()==0){
	    JSFUtil.retornarMensagemModal("", "Tipo de Perseguição é Obrigatório!");
	    return;
	}

        for (PerseguicaoAnalise perseg : getListarPerseguicaoAnalise()) {
             if (perseguicaoAnalise.getTipoPerseguicao().getCodTipo() != perseg.getTipoPerseguicao().getCodTipo()) {
               lista.add(perseg);
   	    }
   	}
   	setListarPerseguicaoAnalise(lista);
   	getListarPerseguicaoAnalise().add(perseguicaoAnalise);
   	JSFUtil.getRequestContext().execute("PF('dlg_perseguicao').hide()");
    }
    
    public void editarPerseguicao() {
 	if (JSFUtil.getRequestMap().get("perseguicaoLista") != null
 		&& (PerseguicaoAnalise) JSFUtil.getRequestMap().get("perseguicaoLista") != null) {
 	    this.perseguicaoAnalise = ((PerseguicaoAnalise) JSFUtil.getRequestMap().get("perseguicaoLista"));
 	    this.tipoPerseguicao = perseguicaoAnalise.getTipoPerseguicao();
 	    JSFUtil.getRequestContext().execute("PF('dlg_perseguicao').show()");
 	}
     }

     public void removerPerseguicao() {
 	if (JSFUtil.getRequestMap().get("perseguicaoLista") != null
 		&& ((PerseguicaoAnalise) JSFUtil.getRequestMap().get("perseguicaoLista")) != null) {
 	   PerseguicaoAnalise perc = ((PerseguicaoAnalise) JSFUtil.getRequestMap().get("perseguicaoLista"));
 	   getListarPerseguicaoAnalise().remove(perc);
 	}
     }


    public List<PessoaFisica> listarPessoaPorNomeLike(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<PessoaFisica> pessoas = new PessoaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public AnaliseProcesso getAnaliseProcesso() {
	return analiseProcesso;
    }

    public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
	this.analiseProcesso = analiseProcesso;
    }

    public List<TipoAnaliseJulgamento> getListarTipoAnaliseJulgamento() {
	return listarTipoAnaliseJulgamento;
    }

    public void setListarTipoAnaliseJulgamento(List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento) {
	this.listarTipoAnaliseJulgamento = listarTipoAnaliseJulgamento;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }

    public List<AnalistaProcesso> getListarAnalistaProcesso() {
	return listarAnalistaProcesso;
    }

    public AnalistaProcesso getAnalistaProcesso() {
	return analistaProcesso;
    }

    public void setListarAnalistaProcesso(List<AnalistaProcesso> listarAnalistaProcesso) {
	this.listarAnalistaProcesso = listarAnalistaProcesso;
    }

    public void setAnalistaProcesso(AnalistaProcesso analistaProcesso) {
	this.analistaProcesso = analistaProcesso;
    }

    public String getNumCpf() {
	return numCpf;
    }

    public void setNumCpf(String numCpf) {
	this.numCpf = numCpf;
    }

    public PessoaFisica getPessoaAnalista() {
	return pessoaAnalista;
    }

    public void setPessoaAnalista(PessoaFisica pessoaAnalista) {
	this.pessoaAnalista = pessoaAnalista;
    }

    public List<PessoaFisica> getListarPessoa() {
	return listarPessoa;
    }

    public void setListarPessoa(List<PessoaFisica> listarPessoa) {
	this.listarPessoa = listarPessoa;
    }

    public boolean isHabilitaTabePessoa() {
	return habilitaTabePessoa;
    }

    public void setHabilitaTabePessoa(boolean habilitaTabePessoa) {
	this.habilitaTabePessoa = habilitaTabePessoa;
    }

    public List<SubStatusProcesso> getListarSubStatusProcesso() {
	if (listarSubStatusProcesso.isEmpty()) {
	    listarSubStatusProcesso = new SubStatusProcessoDAO().lerTodos();
	}
	return listarSubStatusProcesso;
    }

    public Integer getIdSubStatusProcesso() {
	return idSubStatusProcesso;
    }

    public void setListarSubStatusProcesso(List<SubStatusProcesso> listarSubStatusProcesso) {
	this.listarSubStatusProcesso = listarSubStatusProcesso;
    }

    public void setIdSubStatusProcesso(Integer idSubStatusProcesso) {
	this.idSubStatusProcesso = idSubStatusProcesso;
    }

    public List<TipoPerseguicao> getListarTipoPerseguicao() {
	if (listarTipoPerseguicao.isEmpty()) {
	    listarTipoPerseguicao = new TipoPerseguicaoDAO().lerTodos();
	}
	return listarTipoPerseguicao;
    }

    public void setListarTipoPerseguicao(List<TipoPerseguicao> listarTipoPerseguicao) {
	this.listarTipoPerseguicao = listarTipoPerseguicao;
    }

    public TipoPerseguicao getTipoPerseguicao() {
        return tipoPerseguicao;
    }

    public void setTipoPerseguicao(TipoPerseguicao tipoPerseguicao) {
        this.tipoPerseguicao = tipoPerseguicao;
    }

    public List<PerseguicaoAnalise> getListarPerseguicaoAnalise() {
        return listarPerseguicaoAnalise;
    }

    public void setListarPerseguicaoAnalise(List<PerseguicaoAnalise> listarPerseguicaoAnalise) {
        this.listarPerseguicaoAnalise = listarPerseguicaoAnalise;
    }

    public PerseguicaoAnalise getPerseguicaoAnalise() {
        return perseguicaoAnalise;
    }

    public void setPerseguicaoAnalise(PerseguicaoAnalise perseguicaoAnalise) {
        this.perseguicaoAnalise = perseguicaoAnalise;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
