package br.gov.mj.sinca.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.DocumentoPessoaDAO;
import br.gov.mj.sinca.dao.EstadoCivilDAO;
import br.gov.mj.sinca.dao.EstadoUfDAO;
import br.gov.mj.sinca.dao.PessoaDAO;
import br.gov.mj.sinca.dao.PessoaEnderecoDAO;
import br.gov.mj.sinca.dao.TelefonePessoaDAO;
import br.gov.mj.sinca.dao.TipoDocumentoPessoaDAO;
import br.gov.mj.sinca.dao.TipoEnderecoDAO;
import br.gov.mj.sinca.dao.TipoTelefoneDAO;
import br.gov.mj.sinca.entidades.Cidade;
import br.gov.mj.sinca.entidades.DocumentoPessoa;
import br.gov.mj.sinca.entidades.Endereco;
import br.gov.mj.sinca.entidades.Estado;
import br.gov.mj.sinca.entidades.EstadoCivil;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaEndereco;
import br.gov.mj.sinca.entidades.TelefonePessoa;
import br.gov.mj.sinca.entidades.TipoDocumento;
import br.gov.mj.sinca.entidades.TipoEndereco;
import br.gov.mj.sinca.entidades.TipoTelefone;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterPessoaFisicaMB")
@RequestScoped
public class ManterPessoaFisicaMB implements Serializable {

    private static final String ANISTIADO = "ANISTIADO";

    /**
     * 
     */
    private static final long serialVersionUID = -4169640957785343590L;

    private boolean mesmoRequerente = false;

    private boolean comunicadoObito = false;

    private String numCpfAnistiado = "";

    private List<PessoaFisica> listarPessoaAnistiado = new ArrayList<PessoaFisica>();
    private List<EstadoCivil> listarEstadoCivil = new ArrayList<EstadoCivil>();

    private List<Estado> listarEstadouf = new ArrayList<Estado>();
    private List<Cidade> listarCidades = new ArrayList<Cidade>();

    private List<TipoEndereco> listaTipoEnderecoAnistiado = new ArrayList<TipoEndereco>();
    private List<Endereco> listarEnderecoAnistia = new ArrayList<Endereco>();

    private List<TipoDocumento> listarTipoDocumento = new ArrayList<TipoDocumento>();
    private List<TipoTelefone> listarTipoTelefone = new ArrayList<TipoTelefone>();

    private List<TelefonePessoa> listarTelefonePessoa = new ArrayList<TelefonePessoa>();
    private List<DocumentoPessoa> listarDocumentoPessoa = new ArrayList<DocumentoPessoa>();

    private PessoaFisica pessoa = new PessoaFisica();
    private PessoaFisica pessoaAnistiado = new PessoaFisica();
    private PessoaFisica pessoaAnistCadasto = new PessoaFisica();
    private Endereco endereco = new Endereco();
    private DocumentoPessoa documentoPessoa = new DocumentoPessoa();
    private DocumentoPessoa documentoPessDetalhar = new DocumentoPessoa();

    private TipoDocumento tipoDocumento = new TipoDocumento();

    private TipoEndereco tipoEndereco = new TipoEndereco();
    private TipoTelefone tipoTelefone = new TipoTelefone();	

    private TelefonePessoa telefonePessoa = new TelefonePessoa();

    private Cidade cidadeAnistiado = new Cidade();

    private Endereco enderecoAnistCadastro = new Endereco();
    
    private Integer codTipoPessoa;

    public ManterPessoaFisicaMB() {
	super();
    }


    public List<PessoaFisica> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<PessoaFisica> pessoas = new PessoaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public void addSessaoAnistiado() {
	System.out.println("Add Sessao Anistiado");
	if(JSFUtil.getSessionMap().get(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA)==null){
	   if(pessoaAnistCadasto!=null){
	       JSFUtil.getSessionMap().put(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA,pessoaAnistCadasto);
	   } 
	}
    }
    
    public void salvarPessoaAnistia() {
	PessoaDAO pessoaDao = new PessoaDAO();
	PessoaEnderecoDAO pessoaEndeDAO = new PessoaEnderecoDAO();
	TelefonePessoaDAO telefonePessoaDAO = new TelefonePessoaDAO();
	DocumentoPessoaDAO documentoPessoaDAO = new DocumentoPessoaDAO();
	try {
	    JSFUtil.getSessionMap().put(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA,null);
	    if(pessoaAnistCadasto.getIdEstadoCivil()==null || pessoaAnistCadasto.getIdEstadoCivil()==0){
		JSFUtil.retornarMensagem(null,FacesMessage.SEVERITY_ERROR, "Favor preencher o Estado Civil do Anistiado!");
	        return;
	    }
	    JSFUtil.retornarMensagemModal("Atualizando Anistiado", "Realizando a Atualização dos Dados!");
	    PessoaFisica pessoaSalva = pessoaDao.salvar(pessoaAnistCadasto);

	    if (pessoaSalva.getIdPessoa() != null) {
		List<PessoaEndereco> pssEndList = pessoaEndeDAO.listarPorIdPessoa(pessoaSalva.getIdPessoa());

		Map<Integer, Endereco> mapEndercos = getMapEndercoSessao();
		Map<Integer, TelefonePessoa> mapTelefones = getMapTelefonesSessao();
		Map<Integer, DocumentoPessoa> mapDocumentos = getMapDocumentoPessoaSessao();

		for (PessoaEndereco pessoaEndereco : pssEndList) {
		    if (!mapEndercos.containsKey(pessoaEndereco.getEndereco())) {
			pessoaEndeDAO.excluir(pessoaEndereco);
		    }
		}
		for (Endereco endereco : getListarEnderecoAnistia()) {
		    endereco.setIdEndereco(null);
		    PessoaEndereco pessEnd = new PessoaEndereco();
		    pessEnd.setEndereco(endereco);
		    pessEnd.setPessoa(pessoaSalva);
		    pessoaEndeDAO.salvar(pessEnd);
		}
		
		List<TelefonePessoa> listaTelefone = telefonePessoaDAO.listarTelefonePorIdPessoa(pessoaSalva.getIdPessoa());
		for (TelefonePessoa telefonePessoa : listaTelefone) {
		     if(!mapTelefones.containsKey(telefonePessoa.getIdTelefonePessoa())){
			 telefonePessoaDAO.excluir(telefonePessoa);
		     }    
		}
		
 		for (TelefonePessoa telefonePessoa : getListarTelefonePessoa()) {
		    telefonePessoa.setPessoa(pessoaSalva);
		    telefonePessoaDAO.salvar(telefonePessoa);
		}

		List<DocumentoPessoa>  listaDocumento = documentoPessoaDAO.listarDocumentoPorIdPessoa(pessoaSalva.getIdPessoa());
		for (DocumentoPessoa documentoPessoa : listaDocumento) {
		     if(!mapDocumentos.containsKey(documentoPessoa.getIdDocumento())){
			 documentoPessoaDAO.excluir(documentoPessoa);
		     }    
		}

		for (DocumentoPessoa documentoPessoa : getListarDocumentoPessoa()) {
		    documentoPessoa.setPessoa(pessoaSalva);
		    documentoPessoaDAO.salvar(documentoPessoa);
		}

	    }
	    if(JSFUtil.getSessionMap().get(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA)==null || 
		    ((PessoaFisica) JSFUtil.getSessionMap().get(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA))==null){
		JSFUtil.getSessionMap().put(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA,pessoaSalva);
	    }
	    
	    JSFUtil.retornarMensagemModal("Atualizando Anistiado", "Dados atualizados com Sucesso!");
	} catch (Exception e) {
	    JSFUtil.retornarMensagem(null,FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao salvar o processo!");
	    JSFUtil.retornarMensagemModal("Atualizando Anistiado", "Ocorreu um erro ao salvar o processo!");
	    e.printStackTrace();
	    pessoaDao.desfazerTransacao();
	    pessoaEndeDAO.desfazerTransacao();
	    telefonePessoaDAO.desfazerTransacao();
	    documentoPessoaDAO.desfazerTransacao();
	}
	
    }

    public void detalharAnistiado() {
	if (pessoa != null) {
	    System.out.println("Detalhar pessoa ::: " + pessoa.getNomePessoa());
	    pessoaAnistCadasto = pessoa;
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, new ArrayList<PessoaFisica>());
	    JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').hide()");
	    carregarEnderecosPessoa(pessoaAnistCadasto);
	    carregarTelefonePessoa(pessoaAnistCadasto);
	    carregarDocumentosPessoa(pessoaAnistCadasto);
	}
    }
    
    
    public List<PessoaFisica> consultarPessoasJuridica() {

	JSFUtil.limparObjetosSessao(ANISTIADO);
	
	
	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoaAnistiado(pessoas);
	listarPessoaAnistiado = new ArrayList<PessoaFisica>();
	JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, pessoas);
	String nomePessoa = pessoaAnistiado != null ? pessoaAnistiado.getNomePessoa() : null;

	if (pessoaAnistiado != null && pessoaAnistiado.getIdPessoa() != null && pessoaAnistiado.getIdPessoa() > 0) {
	    JSFUtil.getSessionMap().put(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA,null);
	    pessoaAnistCadasto = new PessoaDAO().lerPorId(pessoaAnistiado.getIdPessoa());
	    pessoaAnistCadasto.getPessoaEnderecos().size();
	    pessoaAnistCadasto.getTelefonePessoas();
	    pessoas.add(pessoaAnistCadasto);
	    setListarPessoaAnistiado(pessoas);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, getListarPessoaAnistiado());
	    JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    // carrgarEnderecosPessoa(pessoaAnistCadasto);
	    return pessoas;

	}
	if (nomePessoa == null && (numCpfAnistiado == null || numCpfAnistiado.equals(""))) {
	    JSFUtil.retornarMensagem(null,FacesMessage.SEVERITY_ERROR, "Favor preencher o nome do Anistiado ou o CPF!");
	    return pessoas;
	}


	if (numCpfAnistiado != null || nomePessoa != null) {
	    if ((numCpfAnistiado == null || numCpfAnistiado.equals("")) && nomePessoa != null) {
		if (nomePessoa.length() < 4) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o Nome da Pessoa com mas de 3 (quatro) caracteres!");
		    return pessoas;
		}
		pessoas = new PessoaDAO().listaPessoaPorNomeLk(nomePessoa);
		JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    } else {
		pessoas = new PessoaDAO().listaPessoaPorNomeCpf(numCpfAnistiado.replaceAll(".", "").replaceAll("-", "").trim(), nomePessoa);
		JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    }
	    setListarPessoaAnistiado(pessoas);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, getListarPessoaAnistiado());
	}
	return pessoas;
    }


    public List<PessoaFisica> consultarPessoasAnistiada() {

	JSFUtil.limparObjetosSessao(ANISTIADO);
	
	
	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoaAnistiado(pessoas);
	listarPessoaAnistiado = new ArrayList<PessoaFisica>();
	JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, pessoas);
	String nomePessoa = pessoaAnistiado != null ? pessoaAnistiado.getNomePessoa() : null;

	if (pessoaAnistiado != null && pessoaAnistiado.getIdPessoa() != null && pessoaAnistiado.getIdPessoa() > 0) {
	    JSFUtil.getSessionMap().put(ConstantSinca.PESSOA_SALVA_ANISTIADO_PROPOSTA,null);
	    pessoaAnistCadasto = new PessoaDAO().lerPorId(pessoaAnistiado.getIdPessoa());
	    pessoaAnistCadasto.getPessoaEnderecos().size();
	    pessoaAnistCadasto.getTelefonePessoas();
	    pessoas.add(pessoaAnistCadasto);
	    setListarPessoaAnistiado(pessoas);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, getListarPessoaAnistiado());
	    JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    // carrgarEnderecosPessoa(pessoaAnistCadasto);
	    return pessoas;

	}
	if (nomePessoa == null && (numCpfAnistiado == null || numCpfAnistiado.equals(""))) {
	    JSFUtil.retornarMensagem(null,FacesMessage.SEVERITY_ERROR, "Favor preencher o nome do Anistiado ou o CPF!");
	    return pessoas;
	}


	if (numCpfAnistiado != null || nomePessoa != null) {
	    if ((numCpfAnistiado == null || numCpfAnistiado.equals("")) && nomePessoa != null) {
		if (nomePessoa.length() < 4) {
		    JSFUtil.retornarMensagem(null,
			    "Para Consulta a Pessoa Favor Informar o Nome da Pessoa com mas de 3 (quatro) caracteres!");
		    return pessoas;
		}
		pessoas = new PessoaDAO().listaPessoaPorNomeLk(nomePessoa);
		JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    } else {
		pessoas = new PessoaDAO().listaPessoaPorNomeCpf(
			numCpfAnistiado.replaceAll(".", "").replaceAll("-", "").trim(), nomePessoa);
		JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').show()");
	    }
	    setListarPessoaAnistiado(pessoas);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, getListarPessoaAnistiado());
	}
	return pessoas;
    }

    private void carregarEnderecosPessoa(PessoaFisica pessoa) {
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, null);
	listarEnderecoAnistia = new ArrayList<Endereco>();
	if (pessoa.getIdPessoa() > 0) {
	    List<PessoaEndereco> listaPessoaEnd = pessoa.getPessoaEnderecos();
	    for (PessoaEndereco pessoaEndereco : listaPessoaEnd) {
		getListarEnderecoAnistia().add(pessoaEndereco.getEndereco());
	    }
	    setPessoaAnistCadasto(pessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, getListarEnderecoAnistia());
	}
    }

    private void carregarTelefonePessoa(PessoaFisica pessoa) {
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TELEFONES_ANISTIADO, null);
	listarTelefonePessoa = new ArrayList<TelefonePessoa>();
	if (pessoa.getIdPessoa() > 0) {
	    for (TelefonePessoa tel : pessoa.getTelefonePessoas()) {
		getListarTelefonePessoa().add(tel);
	    }
	    setPessoaAnistCadasto(pessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TELEFONES_ANISTIADO, getListarTelefonePessoa());
	}
    }

    private void carregarDocumentosPessoa(PessoaFisica pessoa) {
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, null);
	listarDocumentoPessoa = new ArrayList<DocumentoPessoa>();
	if (pessoa.getIdPessoa() > 0) {
	    for (DocumentoPessoa doc : pessoa.getDocumentoPessoas()) {
		getListarDocumentoPessoa().add(doc);
	    }
	    setPessoaAnistCadasto(pessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, getListarDocumentoPessoa());
	}
    }

    @SuppressWarnings("unchecked")
    public List<Estado> getListarEstadouf() {
	if (listarEstadouf.isEmpty() && JSFUtil.getServletContext().getAttribute(ConstantSinca.LISTA_UF) != null) {
	    listarEstadouf = (List<Estado>) JSFUtil.getServletContext().getAttribute(ConstantSinca.LISTA_UF);
	} else {
	    listarEstadouf = new EstadoUfDAO().lerTodos();
	}
	return listarEstadouf;
    }

    public void addEnderecoAnistia() {
	if (tipoEndereco != null && tipoEndereco.getIdTipoEndereco() > 0) {
	    enderecoAnistCadastro.setTipoEndereco(new TipoEnderecoDAO().lerPorId(tipoEndereco.getIdTipoEndereco()));
	}
	for (Endereco endereco : listarEnderecoAnistia) {
	    if (endereco.getTipoEndereco() != null
		    && endereco.getTipoEndereco().getIdTipoEndereco() == tipoEndereco.getIdTipoEndereco()) {
		getListarEnderecoAnistia().remove(endereco);
		break;
	    }
	}
	enderecoAnistCadastro.setCidade(cidadeAnistiado.getNome());
	enderecoAnistCadastro.setUf(endereco.getUf());
	getListarEnderecoAnistia().add(enderecoAnistCadastro);
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, getListarEnderecoAnistia());
	JSFUtil.getRequestContext().execute("PF('dlg_endereco_anistiado').hide()");
    }

    public void removerEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    enderecoAnistCadastro = endPar;
	    tipoEndereco = enderecoAnistCadastro.getTipoEndereco();
	}
	if (enderecoAnistCadastro != null && enderecoAnistCadastro.getTipoEndereco() != null) {
	    System.out.println("Remover endereco ::: " + enderecoAnistCadastro.getLogradouro());
	    this.endereco = enderecoAnistCadastro;
	    this.tipoEndereco = enderecoAnistCadastro.getTipoEndereco();
	    getListarEnderecoAnistia().remove(enderecoAnistCadastro);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, getListarDocumentoPessoa());
	}
    }

    public void detalharEnderecoAnistia() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    enderecoAnistCadastro = endPar;
	    tipoEndereco = enderecoAnistCadastro.getTipoEndereco();
	    JSFUtil.getRequestContext().execute("PF('dlg_endereco_anistiado').show()");
	}
    }

    public void addDocumentoAnistia() {
	if (tipoDocumento != null && tipoDocumento.getIdTipoDocumento() > 0) {
	    tipoDocumento = new TipoDocumentoPessoaDAO().lerPorId(tipoDocumento.getIdTipoDocumento());
	}
	documentoPessoa.setTipoDocumento(tipoDocumento);
	for (DocumentoPessoa doc : listarDocumentoPessoa) {
	    if (doc.getTipoDocumento() != null
		    && doc.getTipoDocumento().getIdTipoDocumento() == tipoDocumento.getIdTipoDocumento()) {
		getListarDocumentoPessoa().remove(doc);
		break;
	    }
	}
	getListarDocumentoPessoa().add(documentoPessoa);
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, getListarDocumentoPessoa());
	JSFUtil.getRequestContext().execute("PF('dlg_documentos_anistiado').hide()");
    }

    public void detalharDocumentoAnistia() {
	DocumentoPessoa documentoPessoaPar = (DocumentoPessoa) JSFUtil.getRequestMap().get("documentosAnisti");
	if (documentoPessoaPar != null) {
	    documentoPessDetalhar = documentoPessoaPar;
	}

	if (documentoPessDetalhar != null && documentoPessDetalhar.getNumeroDocumento() != null) {
	    System.out.println("Detalhar documento ::: " + documentoPessDetalhar.getNumeroDocumento());
	    this.documentoPessoa = documentoPessDetalhar;
	    this.tipoDocumento = documentoPessDetalhar.getTipoDocumento();
	    JSFUtil.getRequestContext().execute("PF('dlg_documentos_anistiado').show()");
	}
    }

    public void removerDocumento() {
	DocumentoPessoa documentoPessoaPar = (DocumentoPessoa) JSFUtil.getRequestMap().get("documentosAnisti");
	if (documentoPessoaPar != null) {
	    documentoPessDetalhar = documentoPessoaPar;
	}
	if (documentoPessDetalhar != null && documentoPessDetalhar.getNumeroDocumento() != null) {
	    System.out.println("Remover documento ::: " + documentoPessDetalhar.getNumeroDocumento());
	    this.documentoPessoa = documentoPessDetalhar;
	    this.tipoDocumento = documentoPessDetalhar.getTipoDocumento();
	    getListarDocumentoPessoa().remove(documentoPessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, getListarDocumentoPessoa());
	}
    }

    public void addTelefoneAnistia() {
	if (tipoTelefone != null && tipoTelefone.getCodTipo() > 0) {
	    telefonePessoa.setTipoTelefone(new TipoTelefoneDAO().lerPorId(tipoTelefone.getCodTipo()));
	}
	for (TelefonePessoa tell : listarTelefonePessoa) {
	    if (tell.getNumTelefone().trim().equals(telefonePessoa.getNumTelefone().trim())
		    && tell.getTipoTelefone().getCodTipo() == telefonePessoa.getTipoTelefone().getCodTipo()) {
		getListarTelefonePessoa().remove(tell);
		break;
	    }
	}
	getListarTelefonePessoa().add(telefonePessoa);
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TELEFONES_ANISTIADO, getListarTelefonePessoa());
	JSFUtil.getRequestContext().execute("PF('dlg_telefone_anistiado').hide()");
    }

    public void removerTelefone() {
	TelefonePessoa telefonePessoaPar = (TelefonePessoa) JSFUtil.getRequestMap().get("telefoneAnistia");
	if (telefonePessoa != null) {
	    telefonePessoa = telefonePessoaPar;
	    tipoTelefone = telefonePessoaPar.getTipoTelefone();
	}
	if (telefonePessoa != null && telefonePessoa.getNumTelefone() != null) {
	    System.out.println("Remover Telefone ::: " + telefonePessoa.getNumTelefone());
	    getListarTelefonePessoa().remove(telefonePessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TELEFONES_ANISTIADO, getListarTelefonePessoa());
	}

    }

    public void detalharTelefoneAnistia() {
	TelefonePessoa telefonePessoaPar = (TelefonePessoa) JSFUtil.getRequestMap().get("telefoneAnistia");
	if (telefonePessoa != null) {
	    telefonePessoa = telefonePessoaPar;
	    tipoTelefone = telefonePessoaPar.getTipoTelefone();
	}
	if (telefonePessoa != null) {
	    System.out.println("Detalhar Telefone ::: " + telefonePessoa.getNumTelefone());
	    JSFUtil.getRequestContext().execute("PF('dlg_telefone_anistiado').show()");
	}
    }

    public void onEstadoChange() {

	if (endereco.getUf() != null && !endereco.getUf().equals("")) {
	    for (Estado estado : getListarEstadouf()) {
		if (estado.getSigla().equals(endereco.getUf())) {
		    listarCidades = estado.getCidades();
		    return;
		}
	    }
	}
    }

    private Map<Integer, Endereco> getMapEndercoSessao() {
	Map<Integer, Endereco> map = new HashMap<Integer, Endereco>();
	if (getListarEnderecoAnistia() != null && !getListarEnderecoAnistia().isEmpty()) {
	    for (Endereco enderecoLista : getListarEnderecoAnistia()) {
		if (enderecoLista != null && (enderecoLista.getIdEndereco()!=null 
			&& enderecoLista.getIdEndereco()>0))
		    map.put(endereco.getIdEndereco(), enderecoLista);
	    }
	}
	return map;
    }

    private Map<Integer, TelefonePessoa> getMapTelefonesSessao() {
	Map<Integer, TelefonePessoa> map = new HashMap<Integer, TelefonePessoa>();
	if (getListarTelefonePessoa() != null && !getListarTelefonePessoa().isEmpty()) {
	    for (TelefonePessoa telefoneLista : getListarTelefonePessoa()) {
		if (telefoneLista != null && (telefoneLista.getIdTelefonePessoa()!=null 
			 	&&telefoneLista.getIdTelefonePessoa() > 0))
		    map.put(telefoneLista.getIdTelefonePessoa(), telefoneLista);
	    }
	}
	return map;
    }

    private Map<Integer, DocumentoPessoa> getMapDocumentoPessoaSessao() {
	Map<Integer, DocumentoPessoa> map = new HashMap<Integer, DocumentoPessoa>();
	if (getListarDocumentoPessoa() != null && !getListarDocumentoPessoa().isEmpty()) {
	    for (DocumentoPessoa documentoLista : getListarDocumentoPessoa()) {
		if (documentoLista  != null && (documentoLista.getIdDocumento()!=null && documentoLista.getIdDocumento()> 0))
		    map.put(documentoLista.getIdDocumento(), documentoLista);
	    }
	}
	return map;
    }

    
    public void setListarEstadouf(List<Estado> listarEstadouf) {
	this.listarEstadouf = listarEstadouf;
    }

    public PessoaFisica getPessoa() {
	return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
	this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public PessoaFisica getPessoaAnistiado() {
	return pessoaAnistiado;
    }

    public void setPessoaAnistiado(PessoaFisica pessoaAnistiado) {
	this.pessoaAnistiado = pessoaAnistiado;
    }

    @SuppressWarnings("unchecked")
    public List<PessoaFisica> getListarPessoaAnistiado() {
	if (listarPessoaAnistiado.isEmpty()
		&& JSFUtil.getSessionMap().get(ConstantSinca.LISTAR_PESSOA_ANISTIADO) != null) {
	    listarPessoaAnistiado = (List<PessoaFisica>) JSFUtil.getSessionMap().get(ConstantSinca.LISTAR_PESSOA_ANISTIADO);
	}
	return listarPessoaAnistiado;
    }

    public void setListarPessoaAnistiado(List<PessoaFisica> listarPessoaAnistiado) {
	this.listarPessoaAnistiado = listarPessoaAnistiado;
    }

    public boolean getMesmoRequerente() {
	return mesmoRequerente;
    }

    public void setMesmoRequerente(boolean mesmoRequerente) {
	this.mesmoRequerente = mesmoRequerente;
    }

    public PessoaFisica getPessoaAnistCadasto() {
	return pessoaAnistCadasto;
    }

    public void setPessoaAnistCadasto(PessoaFisica pessoaAnistCadasto) {
	this.pessoaAnistCadasto = pessoaAnistCadasto;
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

    public void habilitarPainelObito() {
	if (comunicadoObito) {
	    comunicadoObito = true;
	} else {
	    comunicadoObito = false;
	}
    }

    public void setListarEstadoCivil(List<EstadoCivil> listarEstadoCivil) {
	this.listarEstadoCivil = listarEstadoCivil;
    }

    @SuppressWarnings("unchecked")
    public List<Endereco> getListarEnderecoAnistia() {
	List<Endereco> listaSessao = (List<Endereco>) JSFUtil.getSessionMap().get(
		ConstantSinca.LISTA_ENDERECO_ANISTIADO);
	if (listarEnderecoAnistia.isEmpty() && listaSessao != null) {
	    listarEnderecoAnistia = listaSessao;
	}
	return listarEnderecoAnistia;
    }

    public void setListarEnderecoAnistia(List<Endereco> listarEnderecoAnistia) {
	this.listarEnderecoAnistia = listarEnderecoAnistia;
    }

    @SuppressWarnings("unchecked")
    public List<TipoEndereco> getListaTipoEnderecoAnistiado() {
	if (listaTipoEnderecoAnistiado.isEmpty()
		&& JSFUtil.getSessionMap().get(ConstantSinca.LISTA_TIPO_ENDERECO_ANISTIADO) != null) {
	    listaTipoEnderecoAnistiado = (List<TipoEndereco>) JSFUtil.getSessionMap().get(
		    ConstantSinca.LISTA_TIPO_ENDERECO_ANISTIADO);
	} else {
	    listaTipoEnderecoAnistiado = new TipoEnderecoDAO().lerTodos();
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TIPO_ENDERECO_ANISTIADO, listaTipoEnderecoAnistiado);

	}
	return listaTipoEnderecoAnistiado;
    }

    public void setListaTipoEnderecoAnistiado(List<TipoEndereco> listaTipoEnderecoAnistiado) {
	this.listaTipoEnderecoAnistiado = listaTipoEnderecoAnistiado;
    }

    @SuppressWarnings("unchecked")
    public List<TipoDocumento> getListarTipoDocumento() {
	if (listarTipoDocumento.isEmpty()
		&& JSFUtil.getSessionMap().get(ConstantSinca.LISTA_TIPO_DOCUMENTO_ANISTIADO) != null) {
	    listarTipoDocumento = (List<TipoDocumento>) JSFUtil.getSessionMap().get(
		    ConstantSinca.LISTA_TIPO_DOCUMENTO_ANISTIADO);
	} else {
	    listarTipoDocumento = new TipoDocumentoPessoaDAO().lerTodos();
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TIPO_DOCUMENTO_ANISTIADO, listarTipoDocumento);

	}

	return listarTipoDocumento;
    }

    public void setListarTipoDocumento(List<TipoDocumento> listarTipoDocumento) {
	this.listarTipoDocumento = listarTipoDocumento;
    }

    @SuppressWarnings("unchecked")
    public List<TipoTelefone> getListarTipoTelefone() {
	if (listarTipoTelefone.isEmpty()
		&& JSFUtil.getSessionMap().get(ConstantSinca.LISTA_TIPO_TELEFONE_ANISTIADO) != null) {
	    listarTipoTelefone = (List<TipoTelefone>) JSFUtil.getSessionMap().get(
		    ConstantSinca.LISTA_TIPO_TELEFONE_ANISTIADO);
	} else {
	    listarTipoTelefone = new TipoTelefoneDAO().lerTodos();
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_TIPO_TELEFONE_ANISTIADO, listarTipoTelefone);

	}
	return listarTipoTelefone;
    }

    public void setListarTipoTelefone(List<TipoTelefone> listarTipoTelefone) {
	this.listarTipoTelefone = listarTipoTelefone;
    }

    public DocumentoPessoa getDocumentoPessoa() {
	return documentoPessoa;
    }

    public void setDocumentoPessoa(DocumentoPessoa documentoPessoa) {
	this.documentoPessoa = documentoPessoa;
    }

    @SuppressWarnings("unchecked")
    public List<DocumentoPessoa> getListarDocumentoPessoa() {
	List<DocumentoPessoa> listaSessao = (List<DocumentoPessoa>) JSFUtil.getSessionMap().get(
		ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO);
	if (listarDocumentoPessoa.isEmpty() && listaSessao != null) {
	    listarDocumentoPessoa = listaSessao;

	}
	return listarDocumentoPessoa;
    }

    public void setListarDocumentoPessoa(List<DocumentoPessoa> listarDocumentoPessoa) {
	this.listarDocumentoPessoa = listarDocumentoPessoa;
    }

    public List<TelefonePessoa> getListarTelefonePessoa() {
	@SuppressWarnings("unchecked")
	List<TelefonePessoa> listaSessao = (List<TelefonePessoa>) JSFUtil.getSessionMap().get(
		ConstantSinca.LISTA_TELEFONES_ANISTIADO);
	if (listarTelefonePessoa.isEmpty() && listaSessao != null) {
	    listarTelefonePessoa = listaSessao;
	}
	return listarTelefonePessoa;
    }

    public void setListarTelefonePessoa(List<TelefonePessoa> listarTelefonePessoa) {
	this.listarTelefonePessoa = listarTelefonePessoa;
    }

    public TelefonePessoa getTelefonePessoa() {
	return telefonePessoa;
    }

    public void setTelefonePessoa(TelefonePessoa telefonePessoa) {
	this.telefonePessoa = telefonePessoa;
    }

    public TipoDocumento getTipoDocumento() {
	return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
    }

    public TipoTelefone getTipoTelefone() {
	return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
	this.tipoTelefone = tipoTelefone;
    }

    public DocumentoPessoa getDocumentoPessDetalhar() {
	return documentoPessDetalhar;
    }

    public void setDocumentoPessDetalhar(DocumentoPessoa documentoPessDetalhar) {
	this.documentoPessDetalhar = documentoPessDetalhar;
    }

    public TipoEndereco getTipoEndereco() {
	return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
	this.tipoEndereco = tipoEndereco;
    }

    public Cidade getCidadeAnistiado() {
	return cidadeAnistiado;
    }

    public void setCidadeAnistiado(Cidade cidadeAnistiado) {
	this.cidadeAnistiado = cidadeAnistiado;
    }

    public List<Cidade> getListarCidades() {
	return listarCidades;
    }

    public void setListarCidades(List<Cidade> listarCidades) {
	this.listarCidades = listarCidades;
    }

    public String getNumCpfAnistiado() {
	return numCpfAnistiado;
    }

    public void setNumCpfAnistiado(String numCpfAnistiado) {
	this.numCpfAnistiado = numCpfAnistiado;
    }

    public boolean isComunicadoObito() {
	return comunicadoObito;
    }

    public void setComunicadoObito(boolean comunicadoObito) {
	this.comunicadoObito = comunicadoObito;
    }

    public Endereco getEnderecoAnistCadastro() {
	return enderecoAnistCadastro;
    }

    public void setEnderecoAnistCadastro(Endereco enderecoAnistCadastro) {
	this.enderecoAnistCadastro = enderecoAnistCadastro;
    }

    public Integer getCodTipoPessoa() {
        return codTipoPessoa;
    }

    public void setCodTipoPessoa(Integer codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }


    
}
