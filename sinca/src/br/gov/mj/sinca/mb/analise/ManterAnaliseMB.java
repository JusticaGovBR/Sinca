package br.gov.mj.sinca.mb.analise;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.AnaliseProcessoDAO;
import br.gov.mj.sinca.dao.DiligenciaDAO;
import br.gov.mj.sinca.dao.HistoricoRequerimentoDAO;
import br.gov.mj.sinca.dao.PerseguicaoAnaliseDAO;
import br.gov.mj.sinca.dao.PessoaFisicaDAO;
import br.gov.mj.sinca.dao.ProvasAnaliseDAO;
import br.gov.mj.sinca.dao.RecomendacaoAnaliseDAO;
import br.gov.mj.sinca.dao.ReparacaoDireitoAnaliseDAO;
import br.gov.mj.sinca.dao.ReparacaoEconomicaAnaliseDAO;
import br.gov.mj.sinca.dao.ReparacaoMoralAnaliseDAO;
import br.gov.mj.sinca.dao.SubStatusProcessoDAO;
import br.gov.mj.sinca.dao.TipoAnaliseJulgamentoDAO;
import br.gov.mj.sinca.dao.TipoPerseguicaoDAO;
import br.gov.mj.sinca.entidades.AnaliseProcesso;
import br.gov.mj.sinca.entidades.Diligencia;
import br.gov.mj.sinca.entidades.HitoricoRequerimento;
import br.gov.mj.sinca.entidades.PerseguicaoAnalise;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.ProvasAnalise;
import br.gov.mj.sinca.entidades.RecomendacaoAnalise;
import br.gov.mj.sinca.entidades.ReparacaoDireitoAnalise;
import br.gov.mj.sinca.entidades.ReparacaoEconomicaAnalise;
import br.gov.mj.sinca.entidades.ReparacaoMoralAnalise;
import br.gov.mj.sinca.entidades.SubStatusProcesso;
import br.gov.mj.sinca.entidades.TipoAnaliseJulgamento;
import br.gov.mj.sinca.entidades.TipoPerseguicao;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.DateUtil;
import br.gov.mj.sinca.util.JSFUtil;

/**
 * Maneger Beam que realiza as funcionalidades de manter análise do processo de
 * anistia.
 * 
 * @author Sebastiao.Costa Contrato nº:XXXXX
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

    private List<SubStatusProcesso> listarSubStatusProcesso = new ArrayList<SubStatusProcesso>();
    private List<TipoPerseguicao> listarTipoPerseguicao = new ArrayList<TipoPerseguicao>();

    private List<PerseguicaoAnalise> listarPerseguicaoAnalise = new ArrayList<PerseguicaoAnalise>();
    private List<ProvasAnalise> listarProvasAnalise = new ArrayList<ProvasAnalise>();

    private List<Diligencia> listarDiligencia = new ArrayList<Diligencia>();

    private List<PessoaFisica> listarPessoa = new ArrayList<PessoaFisica>();

    private List<HitoricoRequerimento> listarHitorico = new ArrayList<HitoricoRequerimento>();

    private HitoricoRequerimento hitoricoRequerimento;

    private Integer idTipoAnaliseJulgamento;
    private Integer idSubStatusProcesso;
    private AnaliseProcesso analiseProcesso;
    private PessoaFisica pessoaAnalista;
    private TipoPerseguicao tipoPerseguicao;
    private PerseguicaoAnalise perseguicaoAnalise;
    private ProvasAnalise provasAnalise;
    private RecomendacaoAnalise recomendacaoAnalise;

    private ReparacaoEconomicaAnalise reparacaoEconomicaAnalise;
    private ReparacaoMoralAnalise reparacaoMoralAnalise;
    private ReparacaoDireitoAnalise reparacaoDireitoAnalise;

    private boolean bolReparacaoEconomica = false;
    private boolean bolReparacaoMoral = false;
    private boolean bolReparacaoDireito = false;

    private boolean habilitaTabePessoa;

    private boolean habilitaAnailiseIniciada;

    private Usuario usuario;

    private String dataRecebimento;

    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;

    @PostConstruct
    public void Init() {
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	System.out.println("Chamada :" + this.getClass().getName() + " Init Iniciando Atributos");
	reparacaoEconomicaAnalise = new ReparacaoEconomicaAnalise();
	reparacaoMoralAnalise = new ReparacaoMoralAnalise();
	reparacaoDireitoAnalise = new ReparacaoDireitoAnalise();

	listarTipoAnaliseJulgamento = new TipoAnaliseJulgamentoDAO().lerTodos();
	analiseProcesso = new AnaliseProcesso();
	pessoaAnalista = new PessoaFisica();
	perseguicaoAnalise = new PerseguicaoAnalise();
	perseguicaoAnalise.setTipoPerseguicao(new TipoPerseguicao());
	tipoPerseguicao = new TipoPerseguicao();
	usuario = loginMB.getUsuario();
	dataRecebimento = DateUtil.dataHoraAtual();
	provasAnalise = new ProvasAnalise();
	recomendacaoAnalise = new RecomendacaoAnalise();

	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcesso");

	if (pessoaProcesso != null && pessoaProcesso.getProcesso().getIdProcesso() > 0) {
	    analiseProcesso.setProcesso(pessoaProcesso.getProcesso());
	    List<Diligencia> listarDiligenciaBD = new DiligenciaDAO().listarDiligenciaPorProcesso(pessoaProcesso
		    .getProcesso().getIdProcesso());
	    if (listarDiligenciaBD != null && !listarDiligenciaBD.isEmpty()) {
		listarDiligencia = listarDiligenciaBD;
	    }
	}
	hitoricoRequerimento = (HitoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (hitoricoRequerimento != null && hitoricoRequerimento.getIdHistorico() != null
		&& hitoricoRequerimento.getIdHistorico().longValue() > 0) {
	    hitoricoRequerimento = new HistoricoRequerimentoDAO().lerPorId(hitoricoRequerimento.getIdHistorico());
	    this.analiseProcesso = hitoricoRequerimento.getAnaliseProcesso1();

	    bolReparacaoEconomica = this.analiseProcesso.getBolReparacaoEconomica() == 1 ? true : false;
	    bolReparacaoMoral = this.analiseProcesso.getBolReparacaoMoral() == 1 ? true : false;
	    bolReparacaoDireito = this.analiseProcesso.getBolRestituicaoDireitos() == 1 ? true : false;

	    listarPerseguicaoAnalise = new PerseguicaoAnaliseDAO().listarPerseguicaoPorAnalise(analiseProcesso
		    .getIdAnalise());
	    listarProvasAnalise = new ProvasAnaliseDAO().listarProvaPorAnalise(analiseProcesso.getIdAnalise());

	    RecomendacaoAnalise recomendacao = new RecomendacaoAnaliseDAO().buscarRecomendacaoAnalise(analiseProcesso
		    .getIdAnalise());

	    if (recomendacao != null && recomendacao.getIdRecomendacao().longValue() > 0) {
		this.recomendacaoAnalise = recomendacao;
		this.recomendacaoAnalise.getReparacaoEconomicaAnalises().size();
		this.recomendacaoAnalise.getReparacaoMoralAnalises().size();
		this.recomendacaoAnalise.getReparacaoDireitoAnalises().size();
		
		if(recomendacaoAnalise.getTipoAnaliseJulgamento()!=null){
		    idTipoAnaliseJulgamento = recomendacaoAnalise.getTipoAnaliseJulgamento().getCodTipo();
		}

		if (!this.recomendacaoAnalise.getReparacaoEconomicaAnalises().isEmpty()) {
		    this.reparacaoEconomicaAnalise = this.recomendacaoAnalise.getReparacaoEconomicaAnalises().get(0);
		}

		if (!this.recomendacaoAnalise.getReparacaoMoralAnalises().isEmpty()) {
		    this.reparacaoMoralAnalise = this.recomendacaoAnalise.getReparacaoMoralAnalises().get(0);
		}

		if (!this.recomendacaoAnalise.getReparacaoDireitoAnalises().isEmpty()) {
		    this.reparacaoDireitoAnalise = this.recomendacaoAnalise.getReparacaoDireitoAnalises().get(0);
		}

	    }
	}

    }

    public void iniciarAnalise() {
	if (!habilitaAnailiseIniciada) {
	    habilitaAnailiseIniciada = true;
	    instanciaAtributos();
	} else {
	    habilitaAnailiseIniciada = false;
	}
    }

    public String salvarAnalise() {
	try {
	    this.mensagem = "Salvando Dados da Análise";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_analise').show()");
	    if (analiseProcesso.getIdAnalise() == null) {
		analiseProcesso.setDataCadastro(new Date());
	    }
	    analiseProcesso.setIdUsuario(usuario.getIdUsuario());

	    analiseProcesso.setBolReparacaoEconomica(bolReparacaoEconomica ? Byte.parseByte("1") : Byte.parseByte("0"));
	    analiseProcesso.setBolReparacaoMoral(bolReparacaoMoral ? Byte.parseByte("1") : Byte.parseByte("0"));
	    analiseProcesso.setBolRestituicaoDireitos(bolReparacaoDireito ? Byte.parseByte("1") : Byte.parseByte("0"));

	    AnaliseProcesso analiseSalva = new AnaliseProcessoDAO().salvar(this.analiseProcesso);

	    List<PerseguicaoAnalise> listaPerseg = new PerseguicaoAnaliseDAO().listarPerseguicaoPorAnalise(analiseSalva
		    .getIdAnalise());
	    for (PerseguicaoAnalise perseguicao : listaPerseg) {
		new PerseguicaoAnaliseDAO().excluir(perseguicao);
	    }

	    for (PerseguicaoAnalise perseguicaoAnalise : getListarPerseguicaoAnalise()) {
		perseguicaoAnalise.setAnaliseProcesso(analiseSalva);
		new PerseguicaoAnaliseDAO().salvar(perseguicaoAnalise);
	    }

	    List<ProvasAnalise> listaProvaBD = new ProvasAnaliseDAO()
		    .listarProvaPorAnalise(analiseSalva.getIdAnalise());
	    for (ProvasAnalise prova : listaProvaBD) {
		new ProvasAnaliseDAO().excluir(prova);
	    }
	    for (ProvasAnalise provas : getListarProvasAnalise()) {
		provas.setAnaliseProcesso(analiseSalva);
		new ProvasAnaliseDAO().salvar(provas);
	    }
	    salvarHistoricoRequerimento(analiseSalva);
	    this.analiseProcesso = analiseSalva;
	    salvarRecomendacao();
	    JSFUtil.retornarMensagemModal("Anaslise", "Analise Salva....");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_analise').hide()");
	} catch (Exception e) {
	    this.mensagem = "";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_analise').hide()");
	    e.printStackTrace();
	    return null;
	}
	return null;
    }

    public String salvarRecomendacao() {
	try {
	    this.mensagem = "Salvando Dados da Análise";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_recomendacao').show()");

	    if (analiseProcesso == null || analiseProcesso.getIdAnalise() == null
		    || analiseProcesso.getIdAnalise().longValue() == 0) {
		JSFUtil.retornarMensagemModal("Análise não Atualizada", "Favor Salvar a Análise!");
	    }

	    this.recomendacaoAnalise.setBolReparacaoEconomica(new BigInteger(bolReparacaoEconomica ? "1" : "0"));
	    this.recomendacaoAnalise.setBolReparacaoMoral(new BigInteger(bolReparacaoMoral ? "1" : "0"));
	    this.recomendacaoAnalise.setBolRestituicaoDireitos(new BigInteger(bolReparacaoDireito ? "1" : "0"));

	    if (idTipoAnaliseJulgamento != null && idTipoAnaliseJulgamento.intValue() > 0) {
		recomendacaoAnalise.setTipoAnaliseJulgamento(new TipoAnaliseJulgamentoDAO().lerPorId(idTipoAnaliseJulgamento.intValue()));
	    }
	    recomendacaoAnalise.setAnaliseProcesso(analiseProcesso);
	    RecomendacaoAnalise recomendacaoSalva = new RecomendacaoAnaliseDAO().salvar(recomendacaoAnalise);
	    reparacaoDireitoAnalise.setRecomendacaoAnalise(recomendacaoSalva);
	    reparacaoEconomicaAnalise.setRecomendacaoAnalise(recomendacaoSalva);
	    reparacaoMoralAnalise.setRecomendacaoAnalise(recomendacaoSalva);

	    if (bolReparacaoDireito) {
		reparacaoDireitoAnalise = new ReparacaoDireitoAnaliseDAO().salvar(reparacaoDireitoAnalise);
	    }
	    if (bolReparacaoEconomica) {
		reparacaoEconomicaAnalise = new ReparacaoEconomicaAnaliseDAO().salvar(reparacaoEconomicaAnalise);
	    }
	    if (bolReparacaoMoral) {
		reparacaoMoralAnalise = new ReparacaoMoralAnaliseDAO().salvar(reparacaoMoralAnalise);
	    }
	    JSFUtil.retornarMensagemModal("Recomendações", "Recomendações Salva....");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_recomendacao').hide()");
	} catch (Exception e) {
	    this.mensagem = "";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_recomendacao').hide()");
	    e.printStackTrace();
	    return null;
	}
	return null;
    }

    private void salvarHistoricoRequerimento(AnaliseProcesso analiseProcesso) {
	hitoricoRequerimento = (HitoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (hitoricoRequerimento != null && analiseProcesso != null && analiseProcesso.getIdAnalise().longValue() > 0) {
	    if (hitoricoRequerimento.getIdHistorico() == null || hitoricoRequerimento.getIdHistorico().intValue() == 0) {
		hitoricoRequerimento.setDataCadastro(new Date());
		hitoricoRequerimento.setUsuario(usuario);
	    }
	    hitoricoRequerimento.setProcesso(analiseProcesso.getProcesso());
	    hitoricoRequerimento.setAnaliseProcesso1(analiseProcesso);
	    hitoricoRequerimento.setUsuarioAnalista(usuario);
	    hitoricoRequerimento.setDataRecebimento(DateUtil.parseDate(this.dataRecebimento,
		    DateUtil.DD_MM_YYYY_HH_MM_SS));
	    hitoricoRequerimento.setDataAtualizacao(new Date());
	    recomendacaoAnalise = new RecomendacaoAnaliseDAO()
		    .buscarRecomendacaoAnalise(analiseProcesso.getIdAnalise());
	    if (recomendacaoAnalise != null && recomendacaoAnalise.getTipoAnaliseJulgamento() != null) {
		hitoricoRequerimento.setIdTipoJulgamentoPreposto(recomendacaoAnalise.getTipoAnaliseJulgamento()
			.getCodTipo());
	    }
	    hitoricoRequerimento = new HistoricoRequerimentoDAO().salvar(hitoricoRequerimento);
	    JSFUtil.getSessionMap().put(ConstantSinca.NOVO_HISTORICO_RA, hitoricoRequerimento);
	}

    }

    public void habilitaReparacaoEconomica() {
	if (this.bolReparacaoEconomica) {
	    this.bolReparacaoEconomica = true;
	} else {
	    this.bolReparacaoEconomica = false;
	}
    }

    public void habilitaReparacaoMoral() {
	if (this.bolReparacaoMoral) {
	    this.bolReparacaoMoral = true;
	} else {
	    this.bolReparacaoMoral = false;
	}
    }

    public void habilitaReparacaoDireito() {
	if (this.bolReparacaoDireito) {
	    this.bolReparacaoDireito = true;
	} else {
	    this.bolReparacaoDireito = false;
	}
    }

    public List<PessoaFisica> consultarPessoas() {

	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<PessoaFisica>();
	String nomePessoa = pessoaAnalista != null ? pessoaAnalista.getNomePessoa() : null;

	if (pessoaAnalista != null && pessoaAnalista.getIdPessoa() != null && pessoaAnalista.getIdPessoa() > 0) {
	    pessoaAnalista = new PessoaFisicaDAO().lerPorId(pessoaAnalista.getIdPessoa());
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
		pessoas = new PessoaFisicaDAO().listaPessoaPorNomeLk(nomePessoa);

	    } else {
		if (numCpf != null && numCpf.length() < 7) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o CPF da Pessoa com mas de 7 (quatro) caracteres!");
		    return pessoas;
		}

		pessoas = new PessoaFisicaDAO().listaPessoaPorNomeCpf(numCpf.replace(".", "").replace("-", "").trim(),
			nomePessoa);
		if (pessoas.isEmpty()) {
		    pessoas = new PessoaFisicaDAO().listaPessoaPorNomeCpf(numCpf.trim(), nomePessoa);
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

    public void addPerseguica() {
	this.perseguicaoAnalise = new PerseguicaoAnalise();
	this.perseguicaoAnalise.setTipoPerseguicao(new TipoPerseguicao());
	this.tipoPerseguicao = new TipoPerseguicao();
	JSFUtil.getRequestContext().execute("PF('dlg_perseguicao').show()");
    }

    public void incluirPerseguicaoLista() {
	List<PerseguicaoAnalise> lista = new ArrayList<PerseguicaoAnalise>();

	if (this.tipoPerseguicao != null && this.tipoPerseguicao.getCodTipo() != null
		&& this.tipoPerseguicao.getCodTipo() > 0) {
	    this.tipoPerseguicao = new TipoPerseguicaoDAO().lerPorId(tipoPerseguicao.getCodTipo());
	}
	perseguicaoAnalise.setTipoPerseguicao(tipoPerseguicao);

	if (perseguicaoAnalise.getTipoPerseguicao() == null
		|| perseguicaoAnalise.getTipoPerseguicao().getCodTipo() == null
		|| perseguicaoAnalise.getTipoPerseguicao().getCodTipo() == 0) {
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

    public void incluirProvaLista() {
	getListarProvasAnalise().add(provasAnalise);
	JSFUtil.getRequestContext().execute("PF('dlg_prova').hide()");
    }

    public void addProvas() {
	this.perseguicaoAnalise = new PerseguicaoAnalise();
	this.perseguicaoAnalise.setTipoPerseguicao(new TipoPerseguicao());
	this.tipoPerseguicao = new TipoPerseguicao();
	JSFUtil.getRequestContext().execute("PF('dlg_prova').show()");
    }

    public void editarProvas() {
	if (JSFUtil.getRequestMap().get("provasLista") != null
		&& (ProvasAnalise) JSFUtil.getRequestMap().get("provasLista") != null) {
	    this.provasAnalise = ((ProvasAnalise) JSFUtil.getRequestMap().get("provasLista"));
	    JSFUtil.getRequestContext().execute("PF('dlg_prova').show()");
	}
    }

    public void removerProvas() {
	if (JSFUtil.getRequestMap().get("provasLista") != null
		&& ((ProvasAnalise) JSFUtil.getRequestMap().get("provasLista")) != null) {
	    ProvasAnalise prova = ((ProvasAnalise) JSFUtil.getRequestMap().get("provasLista"));
	    List<ProvasAnalise> lista = new ArrayList<ProvasAnalise>();
	    for (ProvasAnalise provas : getListarProvasAnalise()) {
		if (!prova.equals(provas)) {
		    lista.add(provas);
		}
	    }
	    setListarProvasAnalise(lista);
	}
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
	    List<PerseguicaoAnalise> lista = new ArrayList<PerseguicaoAnalise>();
	    for (PerseguicaoAnalise perseg : getListarPerseguicaoAnalise()) {
		if (perc.getTipoPerseguicao().getCodTipo() != perseg.getTipoPerseguicao().getCodTipo()) {
		    lista.add(perseg);
		}
	    }
	    setListarPerseguicaoAnalise(lista);
	}
    }

    public List<PessoaFisica> listarPessoaPorNomeLike(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<PessoaFisica> pessoas = new PessoaFisicaDAO().listaPessoaPorNomeLk(nome);
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

    public boolean getHabilitaAnailiseIniciada() {
	return habilitaAnailiseIniciada;
    }

    public void setHabilitaAnailiseIniciada(boolean habilitaAnailiseIniciada) {
	this.habilitaAnailiseIniciada = habilitaAnailiseIniciada;
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

    public List<ProvasAnalise> getListarProvasAnalise() {
	return listarProvasAnalise;
    }

    public ProvasAnalise getProvasAnalise() {
	return provasAnalise;
    }

    public void setListarProvasAnalise(List<ProvasAnalise> listarProvasAnalise) {
	this.listarProvasAnalise = listarProvasAnalise;
    }

    public void setProvasAnalise(ProvasAnalise provasAnalise) {
	this.provasAnalise = provasAnalise;
    }

    public RecomendacaoAnalise getRecomendacaoAnalise() {
	return recomendacaoAnalise;
    }

    public void setRecomendacaoAnalise(RecomendacaoAnalise recomendacaoAnalise) {
	this.recomendacaoAnalise = recomendacaoAnalise;
    }

    public boolean getBolReparacaoEconomica() {
	return bolReparacaoEconomica;
    }

    public boolean getBolReparacaoMoral() {
	return bolReparacaoMoral;
    }

    public boolean getBolReparacaoDireito() {
	return bolReparacaoDireito;
    }

    public void setBolReparacaoEconomica(boolean bolReparacaoEconomica) {
	this.bolReparacaoEconomica = bolReparacaoEconomica;
    }

    public void setBolReparacaoMoral(boolean bolReparacaoMoral) {
	this.bolReparacaoMoral = bolReparacaoMoral;
    }

    public void setBolReparacaoDireito(boolean bolReparacaoDireito) {
	this.bolReparacaoDireito = bolReparacaoDireito;
    }

    public ReparacaoEconomicaAnalise getReparacaoEconomicaAnalise() {
	return reparacaoEconomicaAnalise;
    }

    public ReparacaoMoralAnalise getReparacaoMoralAnalise() {
	return reparacaoMoralAnalise;
    }

    public ReparacaoDireitoAnalise getReparacaoDireitoAnalise() {
	return reparacaoDireitoAnalise;
    }

    public void setReparacaoEconomicaAnalise(ReparacaoEconomicaAnalise reparacaoEconomicaAnalise) {
	this.reparacaoEconomicaAnalise = reparacaoEconomicaAnalise;
    }

    public void setReparacaoMoralAnalise(ReparacaoMoralAnalise reparacaoMoralAnalise) {
	this.reparacaoMoralAnalise = reparacaoMoralAnalise;
    }

    public void setReparacaoDireitoAnalise(ReparacaoDireitoAnalise reparacaoDireitoAnalise) {
	this.reparacaoDireitoAnalise = reparacaoDireitoAnalise;
    }

    public List<Diligencia> getListarDiligencia() {
	return listarDiligencia;
    }

    public void setListarDiligencia(List<Diligencia> listarDiligencia) {
	this.listarDiligencia = listarDiligencia;
    }

    public List<HitoricoRequerimento> getListarHitorico() {
	return listarHitorico;
    }

    public HitoricoRequerimento getHitoricoRequerimento() {
	return hitoricoRequerimento;
    }

    public void setListarHitorico(List<HitoricoRequerimento> listarHitorico) {
	this.listarHitorico = listarHitorico;
    }

    public void setHitoricoRequerimento(HitoricoRequerimento hitoricoRequerimento) {
	this.hitoricoRequerimento = hitoricoRequerimento;
    }

    public Integer getIdTipoAnaliseJulgamento() {
        return idTipoAnaliseJulgamento;
    }

    public void setIdTipoAnaliseJulgamento(Integer idTipoAnaliseJulgamento) {
        this.idTipoAnaliseJulgamento = idTipoAnaliseJulgamento;
    }

}
