package br.gov.mj.sinca.mb.processo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import br.gov.mj.sinca.dao.PessoaFisicaDAO;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.dao.ProcessoDAO;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.util.DateUtil;
import br.gov.mj.sinca.util.JSFUtil;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;

@ManagedBean(name = "consultarProcessoMB")
@ViewScoped
public class ConsultarProcessoMB implements Serializable {

    private static final String LISTA_SESSAO = "LISTA_SESSAO";

    private static final long serialVersionUID = 1L;

    private String numCpf;
    private String mensagem;

    private Integer numProcessoCA;
    private String numProcessoMJ;
    private Date dataProtocoloMJ;

    private List<PessoaFisica> listarPessoa;
    private List<Processo> listarProcessos;
    private List<PessoaProcesso> listarPessoaProcesso;
    private List<String> listaDataProtocoloCA;
    private List<String> listaDataProtocoloSEI;

    private PessoaFisica pessoa;
    private Processo processo;
    private PessoaProcesso pessoaProcesso;

    private String dataProtocoloCA;
    private String dataProtocoloSEI;

    private Logger logger = null;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct"+this.getClass().getName());
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	listarPessoa = new ArrayList<PessoaFisica>();
	listarPessoaProcesso = new ArrayList<PessoaProcesso>();
	JSFUtil.getSessionMap().put(LISTA_SESSAO, listarPessoaProcesso);
	JSFUtil.getSessionMap().put("processoLista", null);
	listarProcessos = new ArrayList<Processo>();
	listaDataProtocoloCA = new ProcessoDAO().listarDataProtocoloCa();
	listaDataProtocoloSEI = new ProcessoDAO().listarDataProtocoloSEI();
	pessoa = new PessoaFisica();
	pessoaProcesso = new PessoaProcesso();
	processo = new Processo();
	numProcessoMJ = null;
	numProcessoCA = null;
	this.mensagem = "Pesquisando....";
    }

    public String acaoNovoProcesso() throws IOException {
	return "/pages/processo/manterProcesso" + "?faces-redirect=true";
    }

    public String detalharProcesso() {
	JSFUtil.getSessionMap().put("processoLista", pessoaProcesso);
	return "/pages/processo/manterProcesso" + "?faces-redirect=true";
    }
    public void manterDiligencia() {
	JSFUtil.getSessionMap().put("processoLista", pessoaProcesso);
	JSFUtil.getRequestContext().execute("PF('dlg_diligencia').show()");
    }
    
    public List<PessoaFisica> consultarPessoas() {

	JSFUtil.retornarMensagemModal("", "Consultando pessoas!");
	List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();
	setListarPessoa(pessoas);
	listarPessoa = new ArrayList<PessoaFisica>();
	String nomePessoa = pessoa != null ? pessoa.getNomePessoa() : null;

	if (pessoa != null && pessoa.getIdPessoa() != null && pessoa.getIdPessoa() > 0) {
	    pessoa = new PessoaFisicaDAO().lerPorId(pessoa.getIdPessoa());
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
	    numProcessoCA = ((numProcessoCA == null || numProcessoCA == 0) ? null : numProcessoCA);
	    numProcessoMJ = (numProcessoMJ == null ? numProcessoMJ : (numProcessoMJ.equals("") ? null : numProcessoMJ));

	    if (pessoa != null && pessoa.getIdPessoa() > 0) {
		lista = new PessoaProcessoDAO().listarProcessoPorIdPessoa(pessoa.getIdPessoa());
		setListarPessoaProcesso(lista);
	    }

	    if (numProcessoCA != null || numProcessoMJ != null) {
		lista = new PessoaProcessoDAO().ListarProcesso(numProcessoCA, numProcessoMJ);
		setListarPessoaProcesso(lista);
	    }
	    if ((lista == null || lista.isEmpty()) && dataProtocoloCA != null || dataProtocoloMJ != null) {
		lista = new PessoaProcessoDAO().listarProcessoPorDataProtocolo(
			DateUtil.parseDate(dataProtocoloCA, DateUtil.DD_MM_YYYY), dataProtocoloMJ);
		processo = new Processo();
		setListarPessoaProcesso(lista);
		JSFUtil.getSessionMap().put(LISTA_SESSAO, getListarPessoaProcesso());
		JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
		return null;
	    }

	    if (lista == null || lista.isEmpty()) {
		JSFUtil.retornarMensagem(null, FacesMessage.SEVERITY_WARN,
			"Não foi localizando o processo com os parâmentros de pesquisa");
		processo = new Processo();
		setListarPessoaProcesso(new ArrayList<PessoaProcesso>());
		return null;
	    }
	    setListarPessoaProcesso(lista);
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	} catch (Exception e) {
	    JSFUtil.retornarMensagem(null, "Não foi possível consultar o processo na Comissão de Anistia");
	    processo = new Processo();
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem').hide()");
	    e.printStackTrace();
	}
	return null;
    }

    public List<PessoaFisica> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    logger.info("Nome Pessoa PESQUISA " + nome);
	List<PessoaFisica> pessoas = new PessoaFisicaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public List<String> listaDataProtocoCA(String data) {
	List<String> lista = new ArrayList<String>();
	if (listaDataProtocoloCA != null && !listaDataProtocoloCA.isEmpty()) {
	    for (String dataCA : listaDataProtocoloCA) {
		if (dataCA != null && dataCA.startsWith(data)) {
		    lista.add(dataCA);
		}
	    }
	}
	return lista;
    }

    public List<String> listaDataProtocoSEI(String data) {
	List<String> lista = new ArrayList<String>();
	if (listaDataProtocoloSEI != null && !listaDataProtocoloSEI.isEmpty()) {
	    for (String dataCA : listaDataProtocoloSEI) {
		if (dataCA != null && dataCA.startsWith(data)) {
		    lista.add(dataCA);
		}
	    }
	}
	return lista;
    }

    public void postProcessXLS(Object document) {
	HSSFWorkbook wb = (HSSFWorkbook) document;
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFRow header = sheet.getRow(0);

	HSSFCellStyle cellStyle = wb.createCellStyle();
	cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
	    HSSFCell cell = header.getCell(i);

	    cell.setCellStyle(cellStyle);
	}
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	Document pdf = (Document) document;
	pdf.open();

	BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
	Font font = new Font(helvetica, 12, Font.NORMAL);
	Chunk chunk = new Chunk("", font);
	pdf.add(chunk);

	// pdf.setPageSize(PageSize.CROWN_QUARTO);

	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
		.getContext();
	String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "themes"
		+ File.separator + "imagem" + File.separator + "cabecalho_3.png";

	pdf.add(Image.getInstance(logo));
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

    public List<String> getListaDataProtocoloCA() {
	return listaDataProtocoloCA;
    }

    public void setListaDataProtocoloCA(List<String> listaDataProtocoloCA) {
	this.listaDataProtocoloCA = listaDataProtocoloCA;
    }

    public String getDataProtocoloCA() {
	return dataProtocoloCA;
    }

    public String getDataProtocoloSEI() {
	return dataProtocoloSEI;
    }

    public void setDataProtocoloSEI(String dataProtocoloSEI) {
	this.dataProtocoloSEI = dataProtocoloSEI;
    }

    public List<String> getListaDataProtocoloSEI() {
	return listaDataProtocoloSEI;
    }

    public void setListaDataProtocoloSEI(List<String> listaDataProtocoloSEI) {
	this.listaDataProtocoloSEI = listaDataProtocoloSEI;
    }

    public void setDataProtocoloCA(String dataProtocoloCA) {
	this.dataProtocoloCA = dataProtocoloCA;
    }

}
