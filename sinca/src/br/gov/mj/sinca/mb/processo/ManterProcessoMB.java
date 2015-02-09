package br.gov.mj.sinca.mb.processo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.axis.AxisFault;
import org.primefaces.event.FlowEvent;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.CargoFuncaoDAO;
import br.gov.mj.sinca.dao.DocumentoPessoaDAO;
import br.gov.mj.sinca.dao.DoencaDAO;
import br.gov.mj.sinca.dao.EstadoCivilDAO;
import br.gov.mj.sinca.dao.EstadoUfDAO;
import br.gov.mj.sinca.dao.GrupoProcesssualDAO;
import br.gov.mj.sinca.dao.GrupoSocialDAO;
import br.gov.mj.sinca.dao.LocalizacaoDAO;
import br.gov.mj.sinca.dao.LoteProcessoDAO;
import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.dao.PessoaEnderecoDAO;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.dao.ProcessoDAO;
import br.gov.mj.sinca.dao.StatusProcessoDAO;
import br.gov.mj.sinca.dao.SubGrupoProcesssualDAO;
import br.gov.mj.sinca.dao.SubGrupoSocialDAO;
import br.gov.mj.sinca.dao.SubStatusProcessoDAO;
import br.gov.mj.sinca.dao.TelefonePessoaDAO;
import br.gov.mj.sinca.dao.TipoDocumentoPessoaDAO;
import br.gov.mj.sinca.dao.TipoEnderecoDAO;
import br.gov.mj.sinca.dao.TipoPessoaProcessoDAO;
import br.gov.mj.sinca.dao.TipoTelefoneDAO;
import br.gov.mj.sinca.entidades.CargoFuncao;
import br.gov.mj.sinca.entidades.Cidade;
import br.gov.mj.sinca.entidades.DocumentoPessoa;
import br.gov.mj.sinca.entidades.Doenca;
import br.gov.mj.sinca.entidades.DoencaPessoa;
import br.gov.mj.sinca.entidades.Endereco;
import br.gov.mj.sinca.entidades.Estado;
import br.gov.mj.sinca.entidades.EstadoCivil;
import br.gov.mj.sinca.entidades.GrupoProcessual;
import br.gov.mj.sinca.entidades.GrupoSocial;
import br.gov.mj.sinca.entidades.Localizacao;
import br.gov.mj.sinca.entidades.LoteProcesso;
import br.gov.mj.sinca.entidades.Pessoa;
import br.gov.mj.sinca.entidades.PessoaEndereco;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.StatusProcesso;
import br.gov.mj.sinca.entidades.SubGrupoProcessual;
import br.gov.mj.sinca.entidades.SubGrupoSocial;
import br.gov.mj.sinca.entidades.SubStatusProcesso;
import br.gov.mj.sinca.entidades.TelefonePessoa;
import br.gov.mj.sinca.entidades.TipoDocumento;
import br.gov.mj.sinca.entidades.TipoEndereco;
import br.gov.mj.sinca.entidades.TipoPessoaProcesso;
import br.gov.mj.sinca.entidades.TipoTelefone;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.mb.analise.ManterAnaliseMB;
import br.gov.mj.sinca.util.CpfCnpjUtil;
import br.gov.mj.sinca.util.JSFUtil;
import br.gov.mj.sinca.ws.sei.RetornoConsultaProcedimento;
import br.gov.mj.sinca.ws.sei.SeiServiceLocator;

@ManagedBean(name = "manterProcessoMB")
@ViewScoped
public class ManterProcessoMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4258528337106321111L;

    private String numCpf;

    private Integer numProcessoCA;
    private String numProcessoMJ;

    private String mensagem;

    private Long idLocalizacao;
    private Integer idStatusProcesso;
    private Integer idSubStatusProcesso;
    private Integer idGrupoProcessual;
    private Integer idSubGrupoProcessual;
    private Integer idCargoFuncao;
    private Integer idGrupoSocial;
    private Integer idSubGrupoSocial;
    private Integer idTipo;
    private Integer idProtocoloExterno;
    private Integer idAssunto;
    private Integer idEspecie;
    private Integer idNatureza;
    private Integer idProcedencia;
    private Integer idAcompanhamentoExterno;
    private Integer idTipoProtocolo;
    private Integer idLoteProcesso;

    private List<Localizacao> listarLocalizacao = new ArrayList<Localizacao>();
    private List<StatusProcesso> listarStatusProcesso = new ArrayList<StatusProcesso>();
    private List<SubStatusProcesso> listarSubStatusProcesso = new ArrayList<SubStatusProcesso>();
    private List<GrupoProcessual> listarGrupoProcessual = new ArrayList<GrupoProcessual>();
    private List<SubGrupoProcessual> listarSubGrupoProcessual = new ArrayList<SubGrupoProcessual>();
    private List<CargoFuncao> listarCargoFuncao = new ArrayList<CargoFuncao>();
    private List<GrupoSocial> listarGrupoSocial = new ArrayList<GrupoSocial>();
    private List<SubGrupoSocial> listarSubGrupoSocial = new ArrayList<SubGrupoSocial>();

    private List<Pessoa> listarPessoa;
    private List<Processo> listarProcessos;
    private List<PessoaProcesso> listarPessoaProcesso;
    private List<TipoPessoaProcesso> listarTipoPessoaProc;
    private List<EstadoCivil> listarEstadoCivil = new ArrayList<EstadoCivil>();
    private List<DocumentoPessoa> listarDocumentoPessoa = new ArrayList<DocumentoPessoa>();
    private List<TipoDocumento> listarTipoDocumento = new ArrayList<TipoDocumento>();
    private List<TelefonePessoa> listarTelefones = new ArrayList<TelefonePessoa>();
    private List<TipoTelefone> listarTipoTelefone = new ArrayList<TipoTelefone>();

    private List<Endereco> listarEnderecos = new ArrayList<Endereco>();
    private List<TipoEndereco> listaTipoEnderecos = new ArrayList<TipoEndereco>();

    private List<Estado> listarUF = new ArrayList<Estado>();
    private List<Cidade> listarCidades = new ArrayList<Cidade>();

    private List<DoencaPessoa> listarDoencaPessoa = new ArrayList<DoencaPessoa>();
    private List<Doenca> listarDoenca = new ArrayList<Doenca>();

    private DoencaPessoa doencaPessoa = new DoencaPessoa();
    private DoencaPessoa doencaPessoaCadastro = new DoencaPessoa();

    private TipoTelefone tipoTelefone = new TipoTelefone();
    private TelefonePessoa telefonePessoa = new TelefonePessoa();

    private Pessoa pessoa;
    private Pessoa pessoaCadastro = new Pessoa();
    private Processo processo;
    private PessoaProcesso pessoaProcesso;

    private Cidade cidade = new Cidade();
    private Endereco endereco = new Endereco();
    private DocumentoPessoa documentoPessoa = new DocumentoPessoa();
    private TipoDocumento tipoDocumento = new TipoDocumento();
    private TipoEndereco tipoEndereco = new TipoEndereco();

    private LoteProcesso loteProcesso = new LoteProcesso();
    private List<LoteProcesso> listaLoteProcesso = new ArrayList<LoteProcesso>();

    private Integer[] idTipoPessoaProcesso;
    private boolean habilitaEdicaoPessoa;
    private boolean habilitaTabePessoa;
    private boolean comunicadoObito = false;
    
    private boolean novo = true;
    
    
    private String linkDocSel;
    
    private Usuario usuario; 
    
    private boolean proximo;

    public boolean isProximo() {
	return proximo;
    }

    public void setProximo(boolean proximo) {
	this.proximo = proximo;
    }

    public String onFlowProcess(FlowEvent event) {
	if (proximo) {
	    proximo = false;
	    return "confirm";
	} else {
	    return event.getNewStep();
	}
    }
    
    @ManagedProperty(value="#{loginMB}") 
    private LoginMB loginMB;
    
    @ManagedProperty(value="#{manterAnaliseMB}") 
    private ManterAnaliseMB manterAnaliseMB;
    
    @PostConstruct
    public void Init() {
	JSFUtil.getRequestContext().execute("PF('carregarDadosInicioDG').show()");
	System.out.println("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	instanciaAtributos();
	JSFUtil.getRequestContext().execute("PF('carregarDadosInicioDG').hide()");
	
    }

    public String salvarProcesso() {
	try {
	    if (validarProcesso()) {
		JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "Validando...");
		JSFUtil.getRequestContext().execute("PF('dlg_mensagem_processo').hide()");
		return null;
	    }

	    if (idCargoFuncao > 0) {
		processo.setCargoFuncao(new CargoFuncao(idCargoFuncao));
	    }
	   

	    if (processo.getIdProcesso() == null || processo.getIdProcesso() == 0) {
		processo.setDataCadastro(new Date());
		processo.setDataHoraInclusao(new Timestamp(new Date().getTime()));
		processo.setNumProtocoloCa(new ProcessoDAO().bustaUltimoNumeroProcessoCA() + 1);
		processo.setIdProcesso(null);
	    } else {
		processo.setDataHoraAtualizacao(new Timestamp(new Date().getTime()));
	    }

	    Processo processoSalvo = new ProcessoDAO().salvar(processo);

	    List<PessoaProcesso> listaPessoaProc = getListarPessoaProcesso();
	    for (PessoaProcesso pessoaProc : listaPessoaProc) {
		pessoaProc.setProcesso(processoSalvo);
		new PessoaProcessoDAO().salvar(pessoaProc);
	    }
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_processo').hide()");
	    JSFUtil.retornarMensagemModal("Processo", "Processo Salvo....");
	    // limpDados();
	} catch (Exception e) {
	    e.printStackTrace();
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_processo').hide()");
	    return null;
	}
	return null;
    }

    public void habilitaNovo(){
	System.out.println("Habilita NOVO: "+novo);
	if(novo){
	  novo = false;   
	}else{
	  novo = true;  
	}
    }
    
    private boolean validarProcesso() {
	return true;
    }

    private void instanciaAtributos() {

	linkDocSel = "http://seipreprod.mj.gov.br/sei/processo_acesso_externo_consulta.php?id_acesso_externo=12&infra_hash=ca477862c7589a4dab02c7bb0d936f92";
	// linkDocSel ="http://www.globo.com";

	listarPessoa = new ArrayList<Pessoa>();
	listarPessoaProcesso = new ArrayList<PessoaProcesso>();
	listarProcessos = new ArrayList<Processo>();
	listarTipoPessoaProc = new TipoPessoaProcessoDAO().lerTodos();
	listarDoenca = new DoencaDAO().lerTodos();
	doencaPessoa.setDoenca(new Doenca());
	
	usuario = loginMB.getUsuario();
	
	listaLoteProcesso = new LoteProcessoDAO().lerTodosAtivos();
	
	System.out.println("manterAnaliseMB::::"+manterAnaliseMB.getListarTipoAnaliseJulgamento().size());
	
	PessoaProcesso pessoaProcessoP = (PessoaProcesso) JSFUtil.getSessionMap().get("processoLista");
	if (pessoaProcessoP != null) {
	    // pessoa = pessoaProcessoP.getPessoa();
	    processo = pessoaProcessoP.getProcesso();
	    this.idLocalizacao = processo.getIdLocalizacao();
	    this.idStatusProcesso = (processo.getStatusProcesso() != null ? processo.getStatusProcesso()
		    .getIdStatusProcesso() : null);
	    this.idSubStatusProcesso = (processo.getSubStatusProcesso() != null ? processo.getSubStatusProcesso()
		    .getIdSubStatusProcesso() : null);
	    this.idGrupoProcessual = (processo.getGrupoProcessual() != null ? processo.getGrupoProcessual()
		    .getIdGrupoProcessual() : null);
	    this.idGrupoSocial = (processo.getGrupoSocial() != null ? processo.getGrupoSocial().getIdGrupoSocial()
		    : null);
	    this.idSubGrupoProcessual = (processo.getSubGrupoProcessual() != null ? processo.getSubGrupoProcessual()
		    .getIdSubGrupoProcessual() : null);

	    pessoaProcesso = pessoaProcessoP;
	    numProcessoMJ = pessoaProcessoP.getProcesso().getNumProtocoloMj();
	    listarPessoaProcesso = new PessoaProcessoDAO().listarProcesso(processo.getIdProcesso());

	    JSFUtil.getSessionMap().put("processoLista", null);
	} else {
	    pessoa = new Pessoa();
	    pessoaCadastro = new Pessoa();
	    pessoaProcesso = new PessoaProcesso();
	    processo = new Processo();
	    numProcessoMJ = "";
	    numProcessoCA = null;
	    numCpf = "";
	    this.mensagem = "Atualizando os Dados....";
	}
    }

    public String consultarProcesso() {
	try {
	    // 08000.000002/2014-14
	    // JSFUtil.retornarMensagem(null,
	    // "Consultando WebServices do SEI, processo:" +
	    // processo.getNumProtocoloMj());
	    this.mensagem = "Consultando Serviço do SEI...";
	    SeiServiceLocator locator = new SeiServiceLocator();
	    RetornoConsultaProcedimento retorno = locator.getSeiPortService().consultarProcedimento("SEI", "lu",
		    "110000834", processo.getNumProtocoloMj(), null, null, null, null, null, null, null, null, null);
	    if (retorno != null) {
		System.out.println("retorno " + retorno.getLinkAcesso() + " " + retorno.getDataAutuacao());
		processo.setDataProtocoloMJ(new SimpleDateFormat("dd/MM/yyyy").parse(retorno.getDataAutuacao()));
		this.linkDocSel = retorno.getLinkAcesso();
	    }
	    
	} catch (AxisFault eAxis) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_FATAL,
		    "Error na Consultando WebServices do SEI: " + eAxis.getFaultString());
	    eAxis.printStackTrace();
	} catch (Exception e) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_FATAL,
		    "Error na Consultando WebServices do SEI: " + e.getMessage());
	    e.printStackTrace();
	}
	JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	return null;
    }

    public List<Pessoa> listarPessoaPorNomeLike(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<Pessoa> pessoas = new PessoaDAO().listaPessoaPorNomeLk(1, nome);
	return pessoas;
    }

    public List<Pessoa> consultarPessoas() {

	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<Pessoa>();
	String nomePessoa = pessoa != null ? pessoa.getNomePessoa() : null;

	if (pessoa != null && pessoa.getIdPessoa() != null && pessoa.getIdPessoa() > 0) {
	    pessoa = new PessoaDAO().lerPorId(pessoa.getIdPessoa());
	    pessoas.add(pessoa);
	    setListarPessoa(pessoas);
	    habilitaTabePessoa = true;
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
		pessoas = new PessoaDAO().listaPessoaPorNomeLk(1, nomePessoa);

	    } else {
		if (numCpf != null && numCpf.length() < 7) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o CPF da Pessoa com mas de 7 (quatro) caracteres!");
		    return pessoas;
		}

		pessoas = new PessoaDAO().listaPessoaPorNomeCpfCnpj(1, numCpf.replace(".", "").replace("-", "").trim(),
			nomePessoa);
	    }
	    setListarPessoa(pessoas);
	}

	habilitaTabePessoa = true;

	return pessoas;
    }

    public void incluirNoProcesso() {
	if (pessoa != null) {
	    if (idTipoPessoaProcesso == null || idTipoPessoaProcesso.length == 0) {
		JSFUtil.retornarMensagemModal("", "Tipo da Pessoa no Processo não Selecionado!");
	    } else {
		System.out.println("Detalhar pessoa ::: " + pessoa.getNomePessoa());
		for (Integer idTipo : idTipoPessoaProcesso) {
		    PessoaProcesso pessoaProcesso = new PessoaProcesso();
		    pessoaProcesso.setProcesso(processo);
		    pessoaProcesso.setPessoa(pessoa);
		    pessoaProcesso.setTipoPessoaProcesso(new TipoPessoaProcessoDAO().lerPorId(idTipo));
		    List<PessoaProcesso> lista = getListarPessoaProcesso();
		    boolean adiciona = true;
		    for (PessoaProcesso pessoaProc : lista) {
			if (pessoaProc.getPessoa() != null && pessoa != null
				&& pessoaProc.getTipoPessoaProcesso() != null) {
			    if ((pessoaProc.getPessoa().getIdPessoa().longValue() == pessoa.getIdPessoa().longValue())
				    && pessoaProc.getTipoPessoaProcesso().getIdTipoPessoaProcesso().intValue() == idTipo
					    .intValue()) {
				adiciona = false;
			    }
			}
		    }
		    if (adiciona) {
			getListarPessoaProcesso().add(pessoaProcesso);
		    }
		}
		idTipoPessoaProcesso = null;
		habilitaTabePessoa = false;
	    }
	}
    }

    public void editarPessoaProcesso() {
	if (JSFUtil.getRequestMap().get("processoLista") != null
		&& ((PessoaProcesso) JSFUtil.getRequestMap().get("processoLista")).getPessoa() != null) {
	    pessoaCadastro = ((PessoaProcesso) JSFUtil.getRequestMap().get("processoLista")).getPessoa();
	    setListarDocumentoPessoa(new ArrayList<DocumentoPessoa>());
	    if (pessoaCadastro.getIdPessoa() > 0) {
		pessoaCadastro = new PessoaDAO().lerPorId(pessoaCadastro.getIdPessoa());
		pessoaCadastro.getDocumentoPessoas().size();
		pessoaCadastro.getPessoaEnderecos().size();
		pessoaCadastro.getTelefonePessoas().size();
		pessoaCadastro.getDoencaPessoas().size();

		carregarDocumentosPessoa(pessoaCadastro);
		carregarEnderecosPessoa(pessoaCadastro);
		carregarTelefonesPessoa(pessoaCadastro);
		carregarTelefonesPessoa(pessoaCadastro);
		carregarDoencaPessoa(pessoaCadastro);

		getMapDocumentoPessoa();
		getMapEndereco();
		getMapTelefones();
		getMapDoencaPessoa();
	    }
	    validaDadosPessoa(pessoaCadastro);
	}

	habilitaEdicaoPessoa = true;

    }

    private void validaDadosPessoa(Pessoa pessoaCadastro) {
	String numCpf = pessoaCadastro.getNumCpfCnpj() == null ? "" : pessoaCadastro.getNumCpfCnpj();
	numCpf = numCpf.replace(".", "").replace("-", "");
	if (!CpfCnpjUtil.ValidateCPF(CpfCnpjUtil.FormatCPF(numCpf)) || numCpf.equals("")
		|| numCpf.equals("99999999999")) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "CPF :" + numCpf + ", Inválido!");
	}
	if (pessoaCadastro.getIdEstadoCivil() == null || pessoaCadastro.getIdEstadoCivil().intValue() == 0) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "Estado Civil não informado!");
	}
	if (pessoaCadastro.getNacionalidade() == null || pessoaCadastro.getNacionalidade().equals("")) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "Nacionalidade não informado!");
	}
	if (pessoaCadastro.getEmailPessoa() == null || pessoaCadastro.getEmailPessoa().equals("")) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "E-mail não informado!");
	}

    }

    public void removerPessoaProcesso() {
	if (JSFUtil.getRequestMap().get("processoLista") != null
		&& ((PessoaProcesso) JSFUtil.getRequestMap().get("processoLista")).getPessoa() != null) {
	    pessoaCadastro = ((PessoaProcesso) JSFUtil.getRequestMap().get("processoLista")).getPessoa();
	}
	List<PessoaProcesso> lista = getListarPessoaProcesso();
	for (PessoaProcesso pessoaProcesso : lista) {
	    if (pessoaProcesso.getPessoa().getIdPessoa() == pessoaCadastro.getIdPessoa()) {
		lista.remove(pessoaProcesso);
		return;
	    }
	}
    }

    public void salvarInteressado() {
	try {
	    if (pessoaCadastro.getIdEstadoCivil() == null || pessoaCadastro.getIdEstadoCivil() == 0) {
		JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR,
			"Favor preencher o Estado Civil do Anistiado!");
		return;
	    }
	    pessoaCadastro.setDataHoraAtualizacao(new Timestamp(new Date().getTime()));
	    pessoaCadastro.setPessoaEnderecos(null);
	    pessoaCadastro.setDocumentoPessoas(null);
	    pessoaCadastro.setTelefonePessoas(null);
	    
	    if(pessoaCadastro.getDataHoraCadastro()==null){
		pessoaCadastro.setDataHoraCadastro(new Date());
	    }
	    
	    Pessoa pessoaSalva = new PessoaDAO().salvar(pessoaCadastro);

	    if (pessoaSalva.getIdPessoa() != null) {
		List<PessoaEndereco> pssEndList = new PessoaEnderecoDAO().listarPorIdPessoa(pessoaSalva.getIdPessoa());

		Map<Integer, Endereco> mapEndercos = getMapEndereco();
		Map<Integer, TelefonePessoa> mapTelefones = getMapTelefones();
		Map<Integer, DocumentoPessoa> mapDocumentos = getMapDocumentoPessoa();

		for (PessoaEndereco pessoaEndereco : pssEndList) {
		    if (!mapEndercos.containsKey(pessoaEndereco.getEndereco())) {
			new PessoaEnderecoDAO().excluir(pessoaEndereco);
		    }
		}
		for (Endereco endereco : getListarEnderecos()) {
		    endereco.setIdEndereco(null);
		    PessoaEndereco pessEnd = new PessoaEndereco();
		    pessEnd.setEndereco(endereco);
		    pessEnd.setPessoa(pessoaSalva);
		    new PessoaEnderecoDAO().salvar(pessEnd);
		}

		List<TelefonePessoa> listaTelefone = new TelefonePessoaDAO().listarTelefonePorIdPessoa(pessoaSalva
			.getIdPessoa());
		for (TelefonePessoa telefonePessoa : listaTelefone) {
		    if (!mapTelefones.containsKey(telefonePessoa.getIdTelefonePessoa())) {
			new TelefonePessoaDAO().excluir(telefonePessoa);
		    }
		}

		for (TelefonePessoa telefonePessoa : getListarTelefones()) {
		    telefonePessoa.setPessoa(pessoaSalva);
		    new TelefonePessoaDAO().salvar(telefonePessoa);
		}

		List<DocumentoPessoa> listaDocumento = new DocumentoPessoaDAO().listarDocumentoPorIdPessoa(pessoaSalva
			.getIdPessoa());
		for (DocumentoPessoa documentoPessoa : listaDocumento) {
		    if (!mapDocumentos.containsKey(documentoPessoa.getIdDocumento())) {
			new DocumentoPessoaDAO().excluir(documentoPessoa);
		    }
		}

		for (DocumentoPessoa documentoPessoa : getListarDocumentoPessoa()) {
		    documentoPessoa.setPessoa(pessoaSalva);
		    new DocumentoPessoaDAO().salvar(documentoPessoa);
		}

	    }
	    if (processo != null && processo.getIdProcesso() != null && processo.getIdProcesso() > 0) {
		setListarPessoaProcesso(new PessoaProcessoDAO().listarProcesso(processo.getIdProcesso()));
	    }
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	    JSFUtil.retornarMensagemModal("Atualizando Anistiado", "Dados atualizados com Sucesso!");
	} catch (Exception e) {
	    JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao salvar o processo!");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	    e.printStackTrace();
	}

    }

    public void fecharPainelPessoa() {
	habilitaEdicaoPessoa = false;
    }

    private Map<Integer, Endereco> getMapEndereco() {
	Map<Integer, Endereco> map = new HashMap<Integer, Endereco>();
	if (getListarEnderecos() != null && !getListarEnderecos().isEmpty()) {
	    for (Endereco enderecoLista : getListarEnderecos()) {
		if (enderecoLista != null
			&& (enderecoLista.getIdEndereco() != null && enderecoLista.getIdEndereco() > 0))
		    map.put(endereco.getIdEndereco(), enderecoLista);
	    }
	}
	return map;
    }

    private Map<Integer, TelefonePessoa> getMapTelefones() {
	Map<Integer, TelefonePessoa> map = new HashMap<Integer, TelefonePessoa>();
	if (getListarTelefones() != null && !getListarTelefones().isEmpty()) {
	    for (TelefonePessoa telefoneLista : getListarTelefones()) {
		if (telefoneLista != null
			&& (telefoneLista.getIdTelefonePessoa() != null && telefoneLista.getIdTelefonePessoa() > 0))
		    map.put(telefoneLista.getIdTelefonePessoa(), telefoneLista);
	    }
	}
	return map;
    }

    private Map<Integer, DocumentoPessoa> getMapDocumentoPessoa() {
	Map<Integer, DocumentoPessoa> map = new HashMap<Integer, DocumentoPessoa>();
	if (getListarDocumentoPessoa() != null && !getListarDocumentoPessoa().isEmpty()) {
	    for (DocumentoPessoa documentoLista : getListarDocumentoPessoa()) {
		if (documentoLista != null
			&& (documentoLista.getIdDocumento() != null && documentoLista.getIdDocumento() > 0))
		    map.put(documentoLista.getIdDocumento(), documentoLista);
	    }
	}
	return map;
    }

    private Map<Long, DoencaPessoa> getMapDoencaPessoa() {
	Map<Long, DoencaPessoa> map = new HashMap<Long, DoencaPessoa>();
	if (getListarDoencaPessoa() != null && !getListarDoencaPessoa().isEmpty()) {
	    for (DoencaPessoa doenca : getListarDoencaPessoa()) {
		if (doenca != null && (doenca.getIdDoencaPessoa() != null && doenca.getIdDoencaPessoa() > 0))
		    map.put(doenca.getIdDoencaPessoa(), doenca);
	    }
	}
	return map;
    }

    private void carregarDocumentosPessoa(Pessoa pessoa) {
	listarDocumentoPessoa = new ArrayList<DocumentoPessoa>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (DocumentoPessoa doc : pessoaCadastro.getDocumentoPessoas()) {
		getListarDocumentoPessoa().add(doc);
	    }
	}
    }

    private void carregarEnderecosPessoa(Pessoa pessoa) {
	listarEnderecos = new ArrayList<Endereco>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (PessoaEndereco endereco : pessoaCadastro.getPessoaEnderecos()) {
		getListarEnderecos().add(endereco.getEndereco());
	    }
	}
    }

    private void carregarTelefonesPessoa(Pessoa pessoa) {
	listarTelefones = new ArrayList<TelefonePessoa>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (TelefonePessoa telefonePessoa : pessoaCadastro.getTelefonePessoas()) {
		getListarTelefones().add(telefonePessoa);
	    }
	}
    }

    private void carregarDoencaPessoa(Pessoa pessoa) {
	listarDoencaPessoa = new ArrayList<DoencaPessoa>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (DoencaPessoa doencaPessoa : pessoaCadastro.getDoencaPessoas()) {
		getListarDoencaPessoa().add(doencaPessoa);
	    }
	}
    }

    public void detalharDocumento() {
	DocumentoPessoa documentoPessoaPar = (DocumentoPessoa) JSFUtil.getRequestMap().get("documentosAnisti");
	if (documentoPessoaPar != null && documentoPessoaPar.getNumeroDocumento() != null) {
	    System.out.println("Detalhar documento ::: " + documentoPessoaPar.getNumeroDocumento());
	    this.documentoPessoa = documentoPessoaPar;
	    this.tipoDocumento = documentoPessoaPar.getTipoDocumento();
	    JSFUtil.getRequestContext().execute("PF('dlg_documentos_anistiado').show()");
	}
    }

    public void removerDocumento() {
	DocumentoPessoa documentoPessoaPar = (DocumentoPessoa) JSFUtil.getRequestMap().get("documentosAnisti");
	if (documentoPessoaPar != null && documentoPessoaPar.getNumeroDocumento() != null) {
	    System.out.println("Remover documento ::: " + documentoPessoaPar.getNumeroDocumento());
	    this.documentoPessoa = documentoPessoaPar;
	    this.tipoDocumento = documentoPessoaPar.getTipoDocumento();
	    getListarDocumentoPessoa().remove(documentoPessoa);
	    // JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO,
	    // getListarDocumentoPessoa());
	}
    }

    public void addDocumento() {

	if (tipoDocumento == null || tipoDocumento.getIdTipoDocumento() == 0) {
	    JSFUtil.retornarMensagemModal("", "Tipo Documento Obrigatório!");
	    return;
	}

	for (DocumentoPessoa doc : listarDocumentoPessoa) {
	    if (doc.getTipoDocumento() != null
		    && doc.getTipoDocumento().getIdTipoDocumento() == tipoDocumento.getIdTipoDocumento()) {
		getListarDocumentoPessoa().remove(doc);
		break;
	    }
	}
	if (tipoDocumento != null && tipoDocumento.getIdTipoDocumento() > 0) {
	    tipoDocumento = new TipoDocumentoPessoaDAO().lerPorId(tipoDocumento.getIdTipoDocumento());
	}
	documentoPessoa.setTipoDocumento(tipoDocumento);
	getListarDocumentoPessoa().add(documentoPessoa);
	JSFUtil.getRequestContext().execute("PF('dlg_documentos').hide()");
    }

    public void addEndereco() {
	for (Endereco endereco : listarEnderecos) {
	    if (endereco.getTipoEndereco() != null
		    && endereco.getTipoEndereco().getIdTipoEndereco() == tipoEndereco.getIdTipoEndereco()) {
		getListarEnderecos().remove(endereco);
		break;
	    }
	}
	if (tipoEndereco != null && tipoEndereco.getIdTipoEndereco() > 0) {
	    endereco.setTipoEndereco(new TipoEnderecoDAO().lerPorId(tipoEndereco.getIdTipoEndereco()));
	}
	endereco.setCidade(cidade.getNome());
	endereco.setUf(endereco.getUf());
	getListarEnderecos().add(endereco);
	JSFUtil.getRequestContext().execute("PF('dlg_endereco').hide()");
    }

    public void removerEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    endereco = endPar;
	    tipoEndereco = endereco.getTipoEndereco();
	}
	if (endereco != null && endereco.getTipoEndereco() != null) {
	    System.out.println("Remover endereco ::: " + endereco.getLogradouro());
	    this.tipoEndereco = endereco.getTipoEndereco();
	    getListarEnderecos().remove(endereco);

	}
    }

    public void detalharEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    endereco = endPar;
	    tipoEndereco = endereco.getTipoEndereco();
	    JSFUtil.getRequestContext().execute("PF('dlg_endereco').show()");
	}
    }

    public void addTelefone() {
	for (TelefonePessoa tell : listarTelefones) {
	    if (tell.getNumTelefone().trim().equals(telefonePessoa.getNumTelefone().trim())
		    && tell.getTipoTelefone().getCodTipo() == tipoTelefone.getCodTipo()) {
		getListarTelefones().remove(tell);
		break;
	    }
	}
	if (tipoTelefone != null && tipoTelefone.getCodTipo() > 0) {
	    telefonePessoa.setTipoTelefone(new TipoTelefoneDAO().lerPorId(tipoTelefone.getCodTipo()));
	}
	getListarTelefones().add(telefonePessoa);
	JSFUtil.getRequestContext().execute("PF('dlg_telefone').hide()");
    }

    public void removerTelefone() {
	TelefonePessoa telefonePessoaPar = (TelefonePessoa) JSFUtil.getRequestMap().get("telefoneAnistia");
	if (telefonePessoa != null) {
	    telefonePessoa = telefonePessoaPar;
	    tipoTelefone = telefonePessoaPar.getTipoTelefone();
	}
	if (telefonePessoa != null && telefonePessoa.getNumTelefone() != null) {
	    System.out.println("Remover Telefone ::: " + telefonePessoa.getNumTelefone());
	    getListarTelefones().remove(telefonePessoa);
	}

    }

    public void detalharTelefone() {
	TelefonePessoa telefonePessoaPar = (TelefonePessoa) JSFUtil.getRequestMap().get("telefoneAnistia");
	if (telefonePessoa != null) {
	    telefonePessoa = telefonePessoaPar;
	    tipoTelefone = telefonePessoaPar.getTipoTelefone();
	}
	if (telefonePessoa != null) {
	    System.out.println("Detalhar Telefone ::: " + telefonePessoa.getNumTelefone());
	    JSFUtil.getRequestContext().execute("PF('dlg_telefone').show()");
	}
    }

    public void addDoenca() {

	if (doencaPessoa.getDoenca() == null || doencaPessoa.getDoenca().getCodCid() == null
		|| doencaPessoa.getDoenca().getCodCid().equals("") || doencaPessoa.getDoenca().getDescDoenca() == null
		|| doencaPessoa.getDoenca().getDescDoenca().equals("")) {
	    JSFUtil.retornarMensagemModal("", "Código CID  e Descrição da Doença são Obrigatórios!");
	    return;
	}
	List<DoencaPessoa> lista = new ArrayList<DoencaPessoa>();
	for (DoencaPessoa doencaP : getListarDoencaPessoa()) {
	    if (!doencaPessoa.getDoenca().getCodCid().equalsIgnoreCase(doencaP.getDoenca().getCodCid())
		    && !doencaPessoa.getDoenca().getDescDoenca().equalsIgnoreCase(doencaP.getDoenca().getDescDoenca())) {
		lista.add(doencaP);
	    }
	}
	setListarDoencaPessoa(lista);

	getListarDoencaPessoa().add(doencaPessoa);
	JSFUtil.getRequestContext().execute("PF('dlg_doenca').hide()");
    }

    public void removerDoenca() {
	DoencaPessoa doencaRm = (DoencaPessoa) JSFUtil.getRequestMap().get("doencaPessao");
	List<DoencaPessoa> lista = new ArrayList<DoencaPessoa>();
	for (DoencaPessoa doencaP : getListarDoencaPessoa()) {
	    if (!doencaRm.getDoenca().getCodCid().equalsIgnoreCase(doencaP.getDoenca().getCodCid())
		    && !doencaRm.getDoenca().getDescDoenca().equalsIgnoreCase(doencaP.getDoenca().getDescDoenca())) {
		lista.add(doencaPessoa);
	    }
	}
	setListarDoencaPessoa(lista);
	JSFUtil.getRequestContext().execute("PF('dlg_doenca').hide()");
    }

    public void editarDoenca() {
	this.doencaPessoa = (DoencaPessoa) JSFUtil.getRequestMap().get("doencaPessao");
	JSFUtil.getRequestContext().execute("PF('dlg_doenca').show()");
    }

    public void onEstadoChange() {
	if (endereco.getUf() != null && !endereco.getUf().equals("")) {
	    for (Estado estado : getListarUF()) {
		if (estado.getSigla().equals(endereco.getUf())) {
		    listarCidades = estado.getCidades();
		    return;
		}
	    }
	}
    }

    public void habilitarPainelObito() {
	if (comunicadoObito) {
	    comunicadoObito = true;
	} else {
	    comunicadoObito = false;
	}
    }

    public void cidDoencaChanged(ValueChangeEvent e) {
	// assign new value to localeCode
	String codCid = e.getNewValue().toString();
	for (Doenca doenca : listarDoenca) {
	    if (doenca.getCodCid().trim().equalsIgnoreCase(codCid)) {
		doencaPessoa.setDoenca(doenca);
		return;
	    }
	}
    }

    public void addLoteProcesso(){
	loteProcesso = new LoteProcesso();
	JSFUtil.getRequestContext().execute("PF('dlg_loteProcesso').show()");
    }

    public void salvarLoteProcesso(){
	LoteProcesso loteAtualizar = (LoteProcesso) JSFUtil.getRequestMap().get("loteEdicao");
	if(loteAtualizar!=null && loteAtualizar.getIdLote()>0){
	    	  loteAtualizar.setIdUsuario(usuario.getIdUsuario());
		  new LoteProcessoDAO().salvar(loteAtualizar);  
		  JSFUtil.retornarMensagemModal("Lote do Requerimento", "Lote do Requerimento Atualizado....");
		  listaLoteProcesso = new LoteProcessoDAO().lerTodosAtivos();
		  return;
	}
	if(loteProcesso.getDescricao()!=null && loteProcesso.getDescricao().length()>0){
	  if(loteProcesso.getIdLote()==null){
	     loteProcesso.setDataCriacao(new Date());
	  }else{
	     loteProcesso.setDataAtualizacao(new Date()); 
	  }
	  
	  loteProcesso.setIdUsuario(usuario.getIdUsuario());
	  new LoteProcessoDAO().salvar(loteProcesso);  
	  JSFUtil.retornarMensagemModal("Lote do Requerimento", "Lote do Requerimento Salvo....");
	  listaLoteProcesso = new LoteProcessoDAO().lerTodosAtivos();
	}

    }
    
    public void excluirLoteProcesso(){
	LoteProcesso loteAtualizar = (LoteProcesso) JSFUtil.getRequestMap().get("loteEdicao");
	if(loteAtualizar!=null && loteAtualizar.getIdLote()>0 && loteAtualizar.getDescricao().length()>0){
	    loteAtualizar.setDataAtualizacao(new Date()); 
	    loteAtualizar.setAtivo((byte) 0);
	    loteAtualizar.setIdUsuario(usuario.getIdUsuario());
	    new LoteProcessoDAO().salvar(loteAtualizar);  
	    JSFUtil.retornarMensagemModal("Lote do Requerimento", "Lote do Requerimento Exluido....");
	    listaLoteProcesso = new LoteProcessoDAO().lerTodosAtivos();
	}
    }
    
    public void selecionarLoteProcesso(){
	LoteProcesso loteAtualizar = (LoteProcesso) JSFUtil.getRequestMap().get("loteEdicao");
	if(loteAtualizar!=null && loteAtualizar.getIdLote()>0){
	    loteProcesso = loteAtualizar;
	}
	  listaLoteProcesso = new LoteProcessoDAO().lerTodosAtivos();
	  JSFUtil.getRequestContext().execute("PF('dlg_loteProcesso').hide()");
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

    public List<Processo> getListarProcessos() {
	return listarProcessos;
    }

    public void setListarProcessos(List<Processo> listarProcessos) {
	this.listarProcessos = listarProcessos;
    }

    public List<PessoaProcesso> getListarPessoaProcesso() {
	return listarPessoaProcesso;
    }

    public void setListarPessoaProcesso(List<PessoaProcesso> listarPessoaProcesso) {
	this.listarPessoaProcesso = listarPessoaProcesso;
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

    public Integer[] getIdTipoPessoaProcesso() {
	return idTipoPessoaProcesso;
    }

    public void setIdTipoPessoaProcesso(Integer[] idTipoPessoaProcesso) {
	this.idTipoPessoaProcesso = idTipoPessoaProcesso;
    }

    public List<TipoPessoaProcesso> getListarTipoPessoaProc() {
	return listarTipoPessoaProc;
    }

    public void setListarTipoPessoaProc(List<TipoPessoaProcesso> listarTipoPessoaProc) {
	this.listarTipoPessoaProc = listarTipoPessoaProc;
    }

    public Pessoa getPessoaCadastro() {
	return pessoaCadastro;
    }

    public void setPessoaCadastro(Pessoa pessoaCadastro) {
	this.pessoaCadastro = pessoaCadastro;
    }

    public boolean isHabilitaEdicaoPessoa() {
	return habilitaEdicaoPessoa;
    }

    public void setHabilitaEdicaoPessoa(boolean habilitaEdicaoPessoa) {
	this.habilitaEdicaoPessoa = habilitaEdicaoPessoa;
    }

    @SuppressWarnings("unchecked")
    public List<EstadoCivil> getListarEstadoCivil() {
	if (listarEstadoCivil.isEmpty()
		&& JSFUtil.getServletContext().getAttribute(ConstantSinca.LISTA_ESTADO_CIVIL) != null) {
	    listarEstadoCivil = (List<EstadoCivil>) JSFUtil.getServletContext().getAttribute(
		    ConstantSinca.LISTA_ESTADO_CIVIL);
	} else {
	    listarEstadoCivil = new EstadoCivilDAO().lerTodos();
	}
	return listarEstadoCivil;
    }

    public void setListarEstadoCivil(List<EstadoCivil> listarEstadoCivil) {
	this.listarEstadoCivil = listarEstadoCivil;
    }

    public boolean isComunicadoObito() {
	return comunicadoObito;
    }

    public void setComunicadoObito(boolean comunicadoObito) {
	this.comunicadoObito = comunicadoObito;
    }

    public List<DocumentoPessoa> getListarDocumentoPessoa() {
	return listarDocumentoPessoa;
    }

    public void setListarDocumentoPessoa(List<DocumentoPessoa> listarDocumentoPessoa) {
	this.listarDocumentoPessoa = listarDocumentoPessoa;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public DocumentoPessoa getDocumentoPessoa() {
	return documentoPessoa;
    }

    public void setDocumentoPessoa(DocumentoPessoa documentoPessoa) {
	this.documentoPessoa = documentoPessoa;
    }

    public TipoDocumento getTipoDocumento() {
	return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumento> getListarTipoDocumento() {
	if (listarTipoDocumento.isEmpty()) {
	    listarTipoDocumento = new TipoDocumentoPessoaDAO().lerTodos();
	}
	return listarTipoDocumento;
    }

    public void setListarTipoDocumento(List<TipoDocumento> listarTipoDocumento) {
	this.listarTipoDocumento = listarTipoDocumento;
    }

    public List<Endereco> getListarEnderecos() {
	return listarEnderecos;
    }

    public void setListarEnderecos(List<Endereco> listarEnderecos) {
	this.listarEnderecos = listarEnderecos;
    }

    public List<TipoEndereco> getListaTipoEnderecos() {
	if (listaTipoEnderecos.isEmpty()) {
	    listaTipoEnderecos = new TipoEnderecoDAO().lerTodos();
	}
	return listaTipoEnderecos;
    }

    public void setListaTipoEnderecos(List<TipoEndereco> listaTipoEnderecos) {
	this.listaTipoEnderecos = listaTipoEnderecos;
    }

    public TipoEndereco getTipoEndereco() {
	return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
	this.tipoEndereco = tipoEndereco;
    }

    @SuppressWarnings("unchecked")
    public List<Estado> getListarUF() {
	if (listarUF.isEmpty() && JSFUtil.getServletContext().getAttribute(ConstantSinca.LISTA_UF) != null) {
	    listarUF = (List<Estado>) JSFUtil.getServletContext().getAttribute(ConstantSinca.LISTA_UF);
	} else {
	    listarUF = new EstadoUfDAO().lerTodos();
	}
	return listarUF;
    }

    public void setListarUF(List<Estado> listarUF) {
	this.listarUF = listarUF;
    }

    public List<Cidade> getListarCidades() {
	return listarCidades;
    }

    public void setListarCidades(List<Cidade> listarCidades) {
	this.listarCidades = listarCidades;
    }

    public Cidade getCidade() {
	return cidade;
    }

    public void setCidade(Cidade cidade) {
	this.cidade = cidade;
    }

    public List<TelefonePessoa> getListarTelefones() {
	return listarTelefones;
    }

    public void setListarTelefones(List<TelefonePessoa> listarTelefones) {
	this.listarTelefones = listarTelefones;
    }

    public List<TipoTelefone> getListarTipoTelefone() {
	if (listarTipoTelefone.isEmpty()) {
	    listarTipoTelefone = new TipoTelefoneDAO().lerTodos();
	}
	return listarTipoTelefone;
    }

    public void setListarTipoTelefone(List<TipoTelefone> listarTipoTelefone) {
	this.listarTipoTelefone = listarTipoTelefone;
    }

    public TipoTelefone getTipoTelefone() {
	return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
	this.tipoTelefone = tipoTelefone;
    }

    public TelefonePessoa getTelefonePessoa() {
	return telefonePessoa;
    }

    public void setTelefonePessoa(TelefonePessoa telefonePessoa) {
	this.telefonePessoa = telefonePessoa;
    }

    public Long getIdLocalizacao() {
	return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
	this.idLocalizacao = idLocalizacao;
    }

    public Integer getIdStatusProcesso() {
	return idStatusProcesso;
    }

    public void setIdStatusProcesso(Integer idStatusProcesso) {
	this.idStatusProcesso = idStatusProcesso;
    }

    public Integer getIdSubStatusProcesso() {
	return idSubStatusProcesso;
    }

    public void setIdSubStatusProcesso(Integer idSubStatusProcesso) {
	this.idSubStatusProcesso = idSubStatusProcesso;
    }

    public Integer getIdGrupoProcessual() {
	return idGrupoProcessual;
    }

    public void setIdGrupoProcessual(Integer idGrupoProcessual) {
	this.idGrupoProcessual = idGrupoProcessual;
    }

    public Integer getIdSubGrupoProcessual() {
	return idSubGrupoProcessual;
    }

    public void setIdSubGrupoProcessual(Integer idSubGrupoProcessual) {
	this.idSubGrupoProcessual = idSubGrupoProcessual;
    }

    public Integer getIdCargoFuncao() {
	return idCargoFuncao;
    }

    public void setIdCargoFuncao(Integer idCargoFuncao) {
	this.idCargoFuncao = idCargoFuncao;
    }

    public Integer getIdGrupoSocial() {
	return idGrupoSocial;
    }

    public void setIdGrupoSocial(Integer idGrupoSocial) {
	this.idGrupoSocial = idGrupoSocial;
    }

    public Integer getIdSubGrupoSocial() {
	return idSubGrupoSocial;
    }

    public void setIdSubGrupoSocial(Integer idSubGrupoSocial) {
	this.idSubGrupoSocial = idSubGrupoSocial;
    }

    public Integer getIdTipo() {
	return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
	this.idTipo = idTipo;
    }

    public Integer getIdProtocoloExterno() {
	return idProtocoloExterno;
    }

    public void setIdProtocoloExterno(Integer idProtocoloExterno) {
	this.idProtocoloExterno = idProtocoloExterno;
    }

    public Integer getIdAssunto() {
	return idAssunto;
    }

    public void setIdAssunto(Integer idAssunto) {
	this.idAssunto = idAssunto;
    }

    public Integer getIdEspecie() {
	return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
	this.idEspecie = idEspecie;
    }

    public Integer getIdNatureza() {
	return idNatureza;
    }

    public void setIdNatureza(Integer idNatureza) {
	this.idNatureza = idNatureza;
    }

    public Integer getIdProcedencia() {
	return idProcedencia;
    }

    public void setIdProcedencia(Integer idProcedencia) {
	this.idProcedencia = idProcedencia;
    }

    public Integer getIdAcompanhamentoExterno() {
	return idAcompanhamentoExterno;
    }

    public void setIdAcompanhamentoExterno(Integer idAcompanhamentoExterno) {
	this.idAcompanhamentoExterno = idAcompanhamentoExterno;
    }

    public Integer getIdTipoProtocolo() {
	return idTipoProtocolo;
    }

    public void setIdTipoProtocolo(Integer idTipoProtocolo) {
	this.idTipoProtocolo = idTipoProtocolo;
    }

    public List<Localizacao> getListarLocalizacao() {
	if (listarLocalizacao.isEmpty()) {
	    listarLocalizacao = new LocalizacaoDAO().lerTodos();
	}
	return listarLocalizacao;
    }

    public void setListarLocalizacao(List<Localizacao> listarLocalizacao) {
	this.listarLocalizacao = listarLocalizacao;
    }

    public List<StatusProcesso> getListarStatusProcesso() {
	if (listarStatusProcesso.isEmpty()) {
	    listarStatusProcesso = new StatusProcessoDAO().lerTodos();
	}
	return listarStatusProcesso;
    }

    public void setListarStatusProcesso(List<StatusProcesso> listarStatusProcesso) {
	this.listarStatusProcesso = listarStatusProcesso;
    }

    public List<SubStatusProcesso> getListarSubStatusProcesso() {
	if (listarSubStatusProcesso.isEmpty()) {
	    listarSubStatusProcesso = new SubStatusProcessoDAO().lerTodos();
	}
	return listarSubStatusProcesso;
    }

    public void setListarSubStatusProcesso(List<SubStatusProcesso> listarSubStatusProcesso) {
	this.listarSubStatusProcesso = listarSubStatusProcesso;
    }

    public List<GrupoProcessual> getListarGrupoProcessual() {
	if (listarGrupoProcessual.isEmpty()) {
	    listarGrupoProcessual = new GrupoProcesssualDAO().lerTodos();
	}
	return listarGrupoProcessual;
    }

    public void setListarGrupoProcessual(List<GrupoProcessual> listarGrupoProcessual) {
	this.listarGrupoProcessual = listarGrupoProcessual;
    }

    public List<SubGrupoProcessual> getListarSubGrupoProcessual() {
	if (listarSubGrupoProcessual.isEmpty()) {
	    listarSubGrupoProcessual = new SubGrupoProcesssualDAO().lerTodos();
	}
	return listarSubGrupoProcessual;
    }

    public void setListarSubGrupoProcessual(List<SubGrupoProcessual> listarSubGrupoProcessual) {
	this.listarSubGrupoProcessual = listarSubGrupoProcessual;
    }

    public List<CargoFuncao> getListarCargoFuncao() {
	if (listarCargoFuncao.isEmpty()) {
	    listarCargoFuncao = new CargoFuncaoDAO().lerTodos();
	}
	return listarCargoFuncao;
    }

    public void setListarCargoFuncao(List<CargoFuncao> listarCargoFuncao) {
	this.listarCargoFuncao = listarCargoFuncao;
    }

    public List<GrupoSocial> getListarGrupoSocial() {
	if (listarGrupoSocial.isEmpty()) {
	    listarGrupoSocial = new GrupoSocialDAO().lerTodos();
	}
	return listarGrupoSocial;
    }

    public void setListarGrupoSocial(List<GrupoSocial> listarGrupoSocial) {
	this.listarGrupoSocial = listarGrupoSocial;
    }

    public List<SubGrupoSocial> getListarSubGrupoSocial() {
	if (listarSubGrupoSocial.isEmpty()) {
	    listarSubGrupoSocial = new SubGrupoSocialDAO().lerTodos();
	}
	return listarSubGrupoSocial;
    }

    public void setListarSubGrupoSocial(List<SubGrupoSocial> listarSubGrupoSocial) {
	this.listarSubGrupoSocial = listarSubGrupoSocial;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }

    public boolean isHabilitaTabePessoa() {
	return habilitaTabePessoa;
    }

    public void setHabilitaTabePessoa(boolean habilitaTabePessoa) {
	this.habilitaTabePessoa = habilitaTabePessoa;
    }

    public List<DoencaPessoa> getListarDoencaPessoa() {
	return listarDoencaPessoa;
    }

    public void setListarDoencaPessoa(List<DoencaPessoa> listarDoencaPessoa) {
	this.listarDoencaPessoa = listarDoencaPessoa;
    }

    public DoencaPessoa getDoencaPessoa() {
	return doencaPessoa;
    }

    public void setDoencaPessoa(DoencaPessoa doencaPessoa) {
	this.doencaPessoa = doencaPessoa;
    }

    public DoencaPessoa getDoencaPessoaCadastro() {
	return doencaPessoaCadastro;
    }

    public List<Doenca> getListarDoenca() {
	return listarDoenca;
    }

    public void setListarDoenca(List<Doenca> listarDoenca) {
	this.listarDoenca = listarDoenca;
    }

    public void setDoencaPessoaCadastro(DoencaPessoa doencaPessoaCadastro) {
	this.doencaPessoaCadastro = doencaPessoaCadastro;
    }

    public String getLinkDocSel() {
	return linkDocSel;
    }

    public void setLinkDocSel(String linkDocSel) {
	this.linkDocSel = linkDocSel;
    }

    public Integer getIdLoteProcesso() {
        return idLoteProcesso;
    }

    public void setIdLoteProcesso(Integer idLoteProcesso) {
        this.idLoteProcesso = idLoteProcesso;
    }

    public LoteProcesso getLoteProcesso() {
        return loteProcesso;
    }

    public List<LoteProcesso> getListaLoteProcesso() {
        return listaLoteProcesso;
    }

    public void setLoteProcesso(LoteProcesso loteProcesso) {
        this.loteProcesso = loteProcesso;
    }

    public void setListaLoteProcesso(List<LoteProcesso> listaLoteProcesso) {
        this.listaLoteProcesso = listaLoteProcesso;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public boolean getNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public ManterAnaliseMB getManterAnaliseMB() {
        return manterAnaliseMB;
    }

    public void setManterAnaliseMB(ManterAnaliseMB manterAnaliseMB) {
        this.manterAnaliseMB = manterAnaliseMB;
    }

    
    
}
