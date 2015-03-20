package br.gov.mj.sinca.mb.arquivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.ArquivoProcessoDAO;
import br.gov.mj.sinca.dao.TipoDescricaoArquivisticaDAO;
import br.gov.mj.sinca.entidades.ArquivoProcesso;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.TipoDescricaoArquivistica;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterArquivoMB")
@ViewScoped
public class ManterArquivoMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1344637621443972431L;

    private String mensagem;


    private Logger logger = null;

    private Processo processo;
    private ArquivoProcesso arquivoProcesso;
    private TipoDescricaoArquivistica descricaoArquivistica;
    private Integer idDescricaoArquivistica;

    private List<TipoDescricaoArquivistica> listaTipoDescricao = new ArrayList<TipoDescricaoArquivistica>();

    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;

    private Usuario usuario;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct" + this.getClass().getName());
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	usuario = loginMB.getUsuario();
	arquivoProcesso = new ArquivoProcesso();
	listaTipoDescricao = new TipoDescricaoArquivisticaDAO().lerTodos();
	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcesso");

	if (pessoaProcesso != null && pessoaProcesso.getProcesso().getIdProcesso() > 0) {
	    processo = pessoaProcesso.getProcesso();
	    arquivoProcesso.setProcesso(processo);
	    arquivoProcesso = new ArquivoProcessoDAO().buscarArquivoProcesso(processo.getIdProcesso());
	}

    }

    public void carregarDaddos() {
	instanciaAtributos();
	//JSFUtil.getRequestContext().execute("PF('dlg_diligencia').show()");
    }

    public String salvar() {
	try {
	    this.mensagem = "Salvando dados de Arquivo...";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_arquivo').show()");
	    if (processo == null) {
		    JSFUtil.retornarMensagemModal("Arquivo", "Não Existe Requerimento Salvo para vincular aos dados de Arquivo,"
		    	+ "Favor salvar o Requerimento!");
		return null;
	    }
	    ArquivoProcesso arquivo = new ArquivoProcessoDAO().buscarArquivoProcesso(processo.getIdProcesso());
	    arquivoProcesso.setIdArquivoProcesso(arquivo.getIdArquivoProcesso());
	    arquivoProcesso.setProcesso(processo);
	    arquivoProcesso.setIdUsuario(usuario.getIdUsuario());
	    
	    if(idDescricaoArquivistica!=null && idDescricaoArquivistica.intValue()>0){
		arquivoProcesso.setTipoDescricaoArquivistica(
			new TipoDescricaoArquivisticaDAO().lerPorId(idDescricaoArquivistica));
		if(arquivoProcesso.getIdArquivoProcesso().longValue()>0){
		    idDescricaoArquivistica = arquivoProcesso.getTipoDescricaoArquivistica().getIdDescricaoArquivistica();
		}
	    }
	    arquivoProcesso = new ArquivoProcessoDAO().salvar(arquivoProcesso);
	    JSFUtil.retornarMensagemModal("Arquivo", "Dados de Arquivo Salvo....");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_arquivo').hide()");

	} catch (Exception e) {
	    this.mensagem = "";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_arquivo').hide()");
	    e.printStackTrace();
	    return null;
	}
	return null;
    }

    public Processo getProcesso() {
	return processo;
    }

    public LoginMB getLoginMB() {
	return loginMB;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public void setLoginMB(LoginMB loginMB) {
	this.loginMB = loginMB;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public ArquivoProcesso getArquivoProcesso() {
	return arquivoProcesso;
    }

    public TipoDescricaoArquivistica getDescricaoArquivistica() {
	return descricaoArquivistica;
    }

    public List<TipoDescricaoArquivistica> getListaTipoDescricao() {
	return listaTipoDescricao;
    }

    public void setArquivoProcesso(ArquivoProcesso arquivoProcesso) {
	this.arquivoProcesso = arquivoProcesso;
    }

    public void setDescricaoArquivistica(TipoDescricaoArquivistica descricaoArquivistica) {
	this.descricaoArquivistica = descricaoArquivistica;
    }

    public void setListaTipoDescricao(List<TipoDescricaoArquivistica> listaTipoDescricao) {
	this.listaTipoDescricao = listaTipoDescricao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Integer getIdDescricaoArquivistica() {
        return idDescricaoArquivistica;
    }

    public void setIdDescricaoArquivistica(Integer idDescricaoArquivistica) {
        this.idDescricaoArquivistica = idDescricaoArquivistica;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
