package br.gov.mj.sinca.mb.interessados;

import java.io.Serializable;
import java.sql.Timestamp;
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

import org.apache.log4j.Logger;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.DocumentoPessoaDAO;
import br.gov.mj.sinca.dao.DoencaDAO;
import br.gov.mj.sinca.dao.EstadoCivilDAO;
import br.gov.mj.sinca.dao.EstadoUfDAO;
import br.gov.mj.sinca.dao.PessoaFisicaDAO;
import br.gov.mj.sinca.dao.PessoaEnderecoDAO;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.dao.TelefonePessoaDAO;
import br.gov.mj.sinca.dao.TipoDocumentoPessoaDAO;
import br.gov.mj.sinca.dao.TipoEnderecoDAO;
import br.gov.mj.sinca.dao.TipoPessoaProcessoDAO;
import br.gov.mj.sinca.dao.TipoTelefoneDAO;
import br.gov.mj.sinca.entidades.Cidade;
import br.gov.mj.sinca.entidades.DocumentoPessoa;
import br.gov.mj.sinca.entidades.Doenca;
import br.gov.mj.sinca.entidades.DoencaPessoa;
import br.gov.mj.sinca.entidades.Endereco;
import br.gov.mj.sinca.entidades.Estado;
import br.gov.mj.sinca.entidades.EstadoCivil;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaEndereco;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.TelefonePessoa;
import br.gov.mj.sinca.entidades.TipoDocumento;
import br.gov.mj.sinca.entidades.TipoEndereco;
import br.gov.mj.sinca.entidades.TipoPessoaProcesso;
import br.gov.mj.sinca.entidades.TipoTelefone;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.mb.processo.ManterProcessoMB;
import br.gov.mj.sinca.util.CpfCnpjUtil;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterInteressadosMB")
@ViewScoped
public class ManterInteressadosMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4258528337106321111L;

    private String numCpf;

    private Integer numProcessoCA;
    private String numProcessoMJ;

    private String mensagem;

    private Integer idTipo;


    private List<PessoaFisica> listarPessoa;
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

    private PessoaFisica pessoa;
    private PessoaFisica pessoaCadastro = new PessoaFisica();
    private Processo processo;
    private PessoaProcesso pessoaProcesso;

    private Cidade cidade = new Cidade();
    private Endereco endereco = new Endereco();
    private DocumentoPessoa documentoPessoa = new DocumentoPessoa();
    private TipoDocumento tipoDocumento = new TipoDocumento();
    private TipoEndereco tipoEndereco = new TipoEndereco();


    private Integer[] idTipoPessoaProcesso;
    private boolean habilitaEdicaoPessoa;
    private boolean habilitaTabePessoa;
    private boolean comunicadoObito = false;
    
    private Logger logger = null; 
    
    private boolean novo = true;
    
    private Usuario usuario; 
    
    
    @ManagedProperty(value="#{loginMB}") 
    private LoginMB loginMB;
    
    @ManagedProperty(value="#{manterProcessoMB}") 
    private ManterProcessoMB manterProcessoMB;
    
    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	JSFUtil.getRequestContext().execute("PF('carregarDadosInicioDG').show()");
	logger.info("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	instanciaAtributos();
	JSFUtil.getRequestContext().execute("PF('carregarDadosInicioDG').hide()");
	
    }

   
    public void habilitaNovo(){
	logger.info("Habilita NOVO: "+novo);
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


	listarPessoa = new ArrayList<PessoaFisica>();
	listarPessoaProcesso = new ArrayList<PessoaProcesso>();
	listarProcessos = new ArrayList<Processo>();
	listarTipoPessoaProc = new TipoPessoaProcessoDAO().lerTodos();
	listarDoenca = new DoencaDAO().lerTodos();
	doencaPessoa.setDoenca(new Doenca());
	
	usuario = loginMB.getUsuario();
	
	
	
	PessoaProcesso pessoaProcessoP = (PessoaProcesso) JSFUtil.getSessionMap().get("processoLista");
	if (pessoaProcessoP != null) {
	    // pessoa = pessoaProcessoP.getPessoa();
	    processo = pessoaProcessoP.getProcesso();
	    pessoaProcesso = pessoaProcessoP;
	    numProcessoMJ = pessoaProcessoP.getProcesso().getNumProtocoloMj();
	    listarPessoaProcesso = new PessoaProcessoDAO().listarProcesso(processo.getIdProcesso());
	} else {
	    pessoa = new PessoaFisica();
	    pessoaCadastro = new PessoaFisica();
	    pessoaProcesso = new PessoaProcesso();
	    processo = new Processo();
	    numProcessoMJ = "";
	    numProcessoCA = null;
	    numCpf = "";
	    this.mensagem = "Atualizando os Dados....";
	}
    }

  
    public List<PessoaFisica> listarPessoaPorNomeLike(String nome) {
	if (nome != null && nome.equals(""))
	    logger.info("Nome Pessoa PESQUISA " + nome);
	List<PessoaFisica> pessoas = new PessoaFisicaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public List<PessoaFisica> consultarPessoas() {

	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<PessoaFisica>();
	String nomePessoa = pessoa != null ? pessoa.getNomePessoa() : null;

	if (pessoa != null && pessoa.getIdPessoa() != null && pessoa.getIdPessoa() > 0) {
	    pessoa = new PessoaFisicaDAO().lerPorId(pessoa.getIdPessoa());
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
		pessoas = new PessoaFisicaDAO().listaPessoaPorNomeLk(nomePessoa);

	    } else {
		if (numCpf != null && numCpf.length() < 7) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o CPF da Pessoa com mas de 7 (quatro) caracteres!");
		    return pessoas;
		}

		pessoas = new PessoaFisicaDAO().listaPessoaPorNomeCpf(numCpf.replace(".", "").replace("-", "").trim(),
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
		logger.info("Detalhar pessoa ::: " + pessoa.getNomePessoa());
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
		pessoaCadastro = new PessoaFisicaDAO().lerPorId(pessoaCadastro.getIdPessoa());
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

    private void validaDadosPessoa(PessoaFisica pessoaCadastro) {
	String numCpf = pessoaCadastro.getNumCpf() == null ? "" : pessoaCadastro.getNumCpf();
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
	    
	    PessoaFisica pessoaSalva = new PessoaFisicaDAO().salvar(pessoaCadastro);

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

    private void carregarDocumentosPessoa(PessoaFisica pessoa) {
	listarDocumentoPessoa = new ArrayList<DocumentoPessoa>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (DocumentoPessoa doc : pessoaCadastro.getDocumentoPessoas()) {
		getListarDocumentoPessoa().add(doc);
	    }
	}
    }

    private void carregarEnderecosPessoa(PessoaFisica pessoa) {
	listarEnderecos = new ArrayList<Endereco>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (PessoaEndereco endereco : pessoaCadastro.getPessoaEnderecos()) {
		getListarEnderecos().add(endereco.getEndereco());
	    }
	}
    }

    private void carregarTelefonesPessoa(PessoaFisica pessoa) {
	listarTelefones = new ArrayList<TelefonePessoa>();
	if (pessoaCadastro.getIdPessoa() > 0) {
	    for (TelefonePessoa telefonePessoa : pessoaCadastro.getTelefonePessoas()) {
		getListarTelefones().add(telefonePessoa);
	    }
	}
    }

    private void carregarDoencaPessoa(PessoaFisica pessoa) {
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
	    logger.info("Detalhar documento ::: " + documentoPessoaPar.getNumeroDocumento());
	    this.documentoPessoa = documentoPessoaPar;
	    this.tipoDocumento = documentoPessoaPar.getTipoDocumento();
	    JSFUtil.getRequestContext().execute("PF('dlg_documentos_anistiado').show()");
	}
    }

    public void removerDocumento() {
	DocumentoPessoa documentoPessoaPar = (DocumentoPessoa) JSFUtil.getRequestMap().get("documentosAnisti");
	if (documentoPessoaPar != null && documentoPessoaPar.getNumeroDocumento() != null) {
	    logger.info("Remover documento ::: " + documentoPessoaPar.getNumeroDocumento());
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
	    logger.info("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    endereco = endPar;
	    tipoEndereco = endereco.getTipoEndereco();
	}
	if (endereco != null && endereco.getTipoEndereco() != null) {
	    logger.info("Remover endereco ::: " + endereco.getLogradouro());
	    this.tipoEndereco = endereco.getTipoEndereco();
	    getListarEnderecos().remove(endereco);

	}
    }

    public void detalharEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    logger.info("Detalhar Lougadoro ::: " + endPar.getLogradouro());
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
	    logger.info("Remover Telefone ::: " + telefonePessoa.getNumTelefone());
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
	    logger.info("Detalhar Telefone ::: " + telefonePessoa.getNumTelefone());
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

    public List<PessoaFisica> getListarPessoa() {
	return listarPessoa;
    }

    public void setListarPessoa(List<PessoaFisica> listarPessoa) {
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

    public PessoaFisica getPessoa() {
	return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
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

    public PessoaFisica getPessoaCadastro() {
	return pessoaCadastro;
    }

    public void setPessoaCadastro(PessoaFisica pessoaCadastro) {
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


    public Integer getIdTipo() {
        return idTipo;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public ManterProcessoMB getManterProcessoMB() {
        return manterProcessoMB;
    }


    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public void setManterProcessoMB(ManterProcessoMB manterProcessoMB) {
        this.manterProcessoMB = manterProcessoMB;
    }

   
    
    
}
