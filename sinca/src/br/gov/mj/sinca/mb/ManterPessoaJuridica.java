package br.gov.mj.sinca.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.PessoaJuridicaDAO;
import br.gov.mj.sinca.dao.TipoEnderecoDAO;
import br.gov.mj.sinca.dao.TipoTelefoneDAO;
import br.gov.mj.sinca.entidades.Cidade;
import br.gov.mj.sinca.entidades.DocumentoPessoa;
import br.gov.mj.sinca.entidades.Endereco;
import br.gov.mj.sinca.entidades.Estado;
import br.gov.mj.sinca.entidades.PessoaEndereco;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.TelefonePessoa;
import br.gov.mj.sinca.entidades.TipoDocumento;
import br.gov.mj.sinca.entidades.TipoEndereco;
import br.gov.mj.sinca.entidades.TipoTelefone;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterPessoaJuridicaMB")
@ViewScoped
public class ManterPessoaJuridica implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2241L;

    private Logger logger = null;

    private PessoaJuridica pessoaJuridicaCadastro;
    private PessoaJuridica pessoaJuridica;
    private String numCNPJ;
    private PessoaProcesso pessoaProcesso;
    private List<PessoaJuridica> listaPessoa;
    private List<Endereco> listarEndereco = new ArrayList<Endereco>();

    private List<Estado> listarEstadouf = new ArrayList<Estado>();
    private List<Cidade> listarCidades = new ArrayList<Cidade>();

    private List<TipoEndereco> listaTipoEndereco = new ArrayList<TipoEndereco>();
    private List<Endereco> listarEnderecoA = new ArrayList<Endereco>();

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

    private Cidade cidade = new Cidade();

    private Endereco enderecoCadastro = new Endereco();

    private Integer codTipoPessoa;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct" + this.getClass().getName());
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	pessoaJuridicaCadastro = new PessoaJuridica();

    }

    public void salvar() {

    }

    public List<PessoaJuridica> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    logger.info("Nome Pessoa PESQUISA " + nome);
	List<PessoaJuridica> pessoas = new PessoaJuridicaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    
    public void addEndereco() {
	if (tipoEndereco != null && tipoEndereco.getIdTipoEndereco() > 0) {
	    enderecoCadastro.setTipoEndereco(new TipoEnderecoDAO().lerPorId(tipoEndereco.getIdTipoEndereco()));
	}
	for (Endereco endereco : listarEndereco) {
	    if (endereco.getTipoEndereco() != null
		    && endereco.getTipoEndereco().getIdTipoEndereco() == tipoEndereco.getIdTipoEndereco()) {
		getListarEndereco().remove(endereco);
		break;
	    }
	}
	enderecoCadastro.setCidade(cidade.getNome());
	enderecoCadastro.setUf(endereco.getUf());
	getListarEndereco().add(enderecoCadastro);
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, getListarEndereco());
	JSFUtil.getRequestContext().execute("PF('dlg_endereco_anistiado').hide()");
    }
    
    public void addTelefone() {
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




    public void detalharEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    enderecoCadastro = endPar;
	    tipoEndereco = enderecoCadastro.getTipoEndereco();
	    JSFUtil.getRequestContext().execute("PF('dlg_endereco_anistiado').show()");
	}
    }

    public void removerEndereco() {
	Endereco endPar = (Endereco) JSFUtil.getRequestMap().get("enderecosAnistia");
	if (endPar != null) {
	    System.out.println("Detalhar Lougadoro ::: " + endPar.getLogradouro());
	    enderecoCadastro = endPar;
	    tipoEndereco = enderecoCadastro.getTipoEndereco();
	}
	if (enderecoCadastro != null && enderecoCadastro.getTipoEndereco() != null) {
	    System.out.println("Remover endereco ::: " + enderecoCadastro.getLogradouro());
	    this.endereco = enderecoCadastro;
	    this.tipoEndereco = enderecoCadastro.getTipoEndereco();
	    getListarEndereco().remove(enderecoCadastro);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_DOCIUMENTOS_ANISTIADO, getListarDocumentoPessoa());
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
	    JSFUtil.getRequestContext().execute("PF('dlg_telefone_anistiado').show()");
	}
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

    public void detalhar() {
	if (pessoa != null) {
	    System.out.println("Detalhar pessoa ::: " + pessoa.getNomePessoa());
	    pessoaAnistCadasto = pessoa;
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTAR_PESSOA_ANISTIADO, new ArrayList<PessoaFisica>());
	    JSFUtil.getRequestContext().execute("PF('dlg_pessoa_anistiado').hide()");
	    carregarEnderecosPessoa(pessoaAnistCadasto);
	    carregarTelefonePessoa(pessoaAnistCadasto);
	}
    }

    private void carregarEnderecosPessoa(PessoaFisica pessoa) {
	JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, null);
	listarEndereco = new ArrayList<Endereco>();
	if (pessoa.getIdPessoa() > 0) {
	    List<PessoaEndereco> listaPessoaEnd = pessoa.getPessoaEnderecos();
	    for (PessoaEndereco pessoaEndereco : listaPessoaEnd) {
		getListarEndereco().add(pessoaEndereco.getEndereco());
	    }
	    setPessoaAnistCadasto(pessoa);
	    JSFUtil.getSessionMap().put(ConstantSinca.LISTA_ENDERECO_ANISTIADO, getListarEndereco());
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

    
    public List<PessoaJuridica> consultarPessoasJuridica() {
	List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
	return lista;
    }

    public PessoaProcesso getPessoaProcesso() {
	return pessoaProcesso;
    }

    public void setPessoaProcesso(PessoaProcesso pessoaProcesso) {
	this.pessoaProcesso = pessoaProcesso;
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

    public List<Endereco> getListarEndereco() {
	return listarEndereco;
    }

    public void setListarEndereco(List<Endereco> listarEndereco) {
	this.listarEndereco = listarEndereco;
    }

    public Logger getLogger() {
	return logger;
    }

    public List<Estado> getListarEstadouf() {
	return listarEstadouf;
    }

    public List<Cidade> getListarCidades() {
	return listarCidades;
    }

    public List<TipoEndereco> getListaTipoEndereco() {
	return listaTipoEndereco;
    }

    public List<Endereco> getListarEnderecoA() {
	return listarEnderecoA;
    }

    public List<TipoDocumento> getListarTipoDocumento() {
	return listarTipoDocumento;
    }

    public List<TipoTelefone> getListarTipoTelefone() {
	return listarTipoTelefone;
    }

    public List<TelefonePessoa> getListarTelefonePessoa() {
	return listarTelefonePessoa;
    }

    public TipoTelefone getTipoTelefone() {
	return tipoTelefone;
    }

    public Integer getCodTipoPessoa() {
	return codTipoPessoa;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
	this.tipoTelefone = tipoTelefone;
    }

    public void setCodTipoPessoa(Integer codTipoPessoa) {
	this.codTipoPessoa = codTipoPessoa;
    }

    public List<DocumentoPessoa> getListarDocumentoPessoa() {
	return listarDocumentoPessoa;
    }

    public PessoaFisica getPessoa() {
	return pessoa;
    }

    public PessoaFisica getPessoaAnistiado() {
	return pessoaAnistiado;
    }

    public PessoaFisica getPessoaAnistCadasto() {
	return pessoaAnistCadasto;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public DocumentoPessoa getDocumentoPessoa() {
	return documentoPessoa;
    }

    public DocumentoPessoa getDocumentoPessDetalhar() {
	return documentoPessDetalhar;
    }

    public TipoDocumento getTipoDocumento() {
	return tipoDocumento;
    }

    public TipoEndereco getTipoEndereco() {
	return tipoEndereco;
    }

    public TelefonePessoa getTelefonePessoa() {
	return telefonePessoa;
    }

    public Endereco getEnderecoCadastro() {
	return enderecoCadastro;
    }

    public void setLogger(Logger logger) {
	this.logger = logger;
    }

    public void setListarEstadouf(List<Estado> listarEstadouf) {
	this.listarEstadouf = listarEstadouf;
    }

    public void setListarCidades(List<Cidade> listarCidades) {
	this.listarCidades = listarCidades;
    }

    public void setListaTipoEndereco(List<TipoEndereco> listaTipoEndereco) {
	this.listaTipoEndereco = listaTipoEndereco;
    }

    public void setListarEnderecoA(List<Endereco> listarEnderecoA) {
	this.listarEnderecoA = listarEnderecoA;
    }

    public void setListarTipoDocumento(List<TipoDocumento> listarTipoDocumento) {
	this.listarTipoDocumento = listarTipoDocumento;
    }

    public void setListarTipoTelefone(List<TipoTelefone> listarTipoTelefone) {
	this.listarTipoTelefone = listarTipoTelefone;
    }

    public void setListarTelefonePessoa(List<TelefonePessoa> listarTelefonePessoa) {
	this.listarTelefonePessoa = listarTelefonePessoa;
    }

    public void setListarDocumentoPessoa(List<DocumentoPessoa> listarDocumentoPessoa) {
	this.listarDocumentoPessoa = listarDocumentoPessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
	this.pessoa = pessoa;
    }

    public void setPessoaAnistiado(PessoaFisica pessoaAnistiado) {
	this.pessoaAnistiado = pessoaAnistiado;
    }

    public void setPessoaAnistCadasto(PessoaFisica pessoaAnistCadasto) {
	this.pessoaAnistCadasto = pessoaAnistCadasto;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public void setDocumentoPessoa(DocumentoPessoa documentoPessoa) {
	this.documentoPessoa = documentoPessoa;
    }

    public void setDocumentoPessDetalhar(DocumentoPessoa documentoPessDetalhar) {
	this.documentoPessDetalhar = documentoPessDetalhar;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
	this.tipoEndereco = tipoEndereco;
    }

    public void setTelefonePessoa(TelefonePessoa telefonePessoa) {
	this.telefonePessoa = telefonePessoa;
    }

    public void setEnderecoCadastro(Endereco enderecoCadastro) {
	this.enderecoCadastro = enderecoCadastro;
    }

    public List<PessoaJuridica> getListaPessoa() {
	return listaPessoa;
    }

    public void setListaPessoa(List<PessoaJuridica> listaPessoa) {
	this.listaPessoa = listaPessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

}
