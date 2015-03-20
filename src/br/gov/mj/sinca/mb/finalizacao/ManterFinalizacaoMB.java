package br.gov.mj.sinca.mb.finalizacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.FinalizacaoNotificacaoDAO;
import br.gov.mj.sinca.dao.FinalizacaoOrgaoDAO;
import br.gov.mj.sinca.dao.FinalizacaoProcessoDAO;
import br.gov.mj.sinca.dao.HistoricoRequerimentoDAO;
import br.gov.mj.sinca.dao.PessoaJuridicaDAO;
import br.gov.mj.sinca.entidades.FinalizacaoNotificacao;
import br.gov.mj.sinca.entidades.FinalizacaoOrgao;
import br.gov.mj.sinca.entidades.FinalizacaoProcesso;
import br.gov.mj.sinca.entidades.HistoricoRequerimento;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterFinalizacaoMB")
@ViewScoped
public class ManterFinalizacaoMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1344637621443972431L;

    private Logger logger = null;

    private Processo processo;
    private FinalizacaoProcesso finalizacaoProcesso;
    private FinalizacaoOrgao finalizacaoOrgao;
    private FinalizacaoNotificacao finalizacaoNotificacao;
    private Integer idFinalizacaoNotificacao;
    private PessoaJuridica pessoaJuridica;

    private List<FinalizacaoNotificacao> listaFinalizacaoNotificacaos = new ArrayList<FinalizacaoNotificacao>();
    private List<FinalizacaoOrgao> listaFinalizacaoOrgao = new ArrayList<FinalizacaoOrgao>();

    private HistoricoRequerimento historicoRequerimento;

    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;

    private Usuario usuario;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct" + this.getClass().getName());
	instanciaAtributos();
    }

    public void instanciaAtributos() {
	finalizacaoProcesso = new FinalizacaoProcesso();
	finalizacaoNotificacao = new FinalizacaoNotificacao();
	finalizacaoOrgao = new FinalizacaoOrgao();
	usuario = loginMB.getUsuario();

	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcesso");

	if (pessoaProcesso != null && pessoaProcesso.getProcesso().getIdProcesso() > 0) {
	    finalizacaoProcesso.setProcesso(pessoaProcesso.getProcesso());
	    processo = pessoaProcesso.getProcesso();
	}

	historicoRequerimento = (HistoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (historicoRequerimento != null && historicoRequerimento.getIdHistorico() != null
		&& historicoRequerimento.getIdHistorico().longValue() > 0) {
	    finalizacaoProcesso = historicoRequerimento.getFinalizacaoProcesso();
	    if (finalizacaoProcesso != null) {
		if (finalizacaoProcesso.getFinalizacaoNotificacaos() != null) {
		    listaFinalizacaoNotificacaos = finalizacaoProcesso.getFinalizacaoNotificacaos();
		}
		if (finalizacaoProcesso.getFinalizacaoOrgaos() != null) {
		    listaFinalizacaoOrgao = finalizacaoProcesso.getFinalizacaoOrgaos();
		}
	    } else {
		finalizacaoProcesso = new FinalizacaoProcesso();
		listaFinalizacaoNotificacaos = new ArrayList<FinalizacaoNotificacao>();
		listaFinalizacaoOrgao = new ArrayList<FinalizacaoOrgao>();
	    }
	}

    }

    public String salvar() {
	try {

	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_finalizacao').show()");
	    if (processo == null) {
		processo = (Processo) JSFUtil.getSessionMap().get("PROCESSO_EDITADO_SESSAO");
		if (processo == null) {
		    JSFUtil.retornarMensagemModal("Finalização",
			    "Não Existe Requerimento Salvo para vincular aos dados de Finalização,"
				    + "Favor salvar o Requerimento!");
		    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_finalizacao').hide()");
		    return null;
		}
	    }
	    finalizacaoProcesso.setProcesso(processo);
	    finalizacaoProcesso.setIdUsuario(usuario.getIdUsuario());

	    finalizacaoProcesso = new FinalizacaoProcessoDAO().salvar(finalizacaoProcesso);
	    if (finalizacaoProcesso.getFinalizacaoNotificacaos() != null) {
		for (FinalizacaoNotificacao notificacao : finalizacaoProcesso.getFinalizacaoNotificacaos()) {
		    new FinalizacaoNotificacaoDAO().excluir(notificacao);
		}
	    }
	    if (finalizacaoProcesso.getFinalizacaoOrgaos() != null) {
		for (FinalizacaoOrgao orgao : finalizacaoProcesso.getFinalizacaoOrgaos()) {
		    new FinalizacaoOrgaoDAO().excluir(orgao);
		}
	    }
	    for (FinalizacaoNotificacao notificacao : listaFinalizacaoNotificacaos) {
		notificacao.setFinalizacaoProcesso(finalizacaoProcesso);
		new FinalizacaoNotificacaoDAO().salvar(notificacao);
	    }
	    for (FinalizacaoOrgao orgao : listaFinalizacaoOrgao) {
		orgao.setFinalizacaoProcesso(finalizacaoProcesso);
		new FinalizacaoOrgaoDAO().salvar(orgao);
	    }
	    salvarHistoricoRequerimento(finalizacaoProcesso);

	    JSFUtil.retornarMensagemModal("Finalização", "Dados de Finalização Salvo....");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_finalizacao').hide()");

	} catch (Exception e) {
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_finalizacao').hide()");
	    JSFUtil.retornarMensagemModal("Finalização", "Erro ao salvar os dados de Finalização: " + e.getMessage());
	    e.printStackTrace();
	    return null;
	}
	return null;
    }

    private void salvarHistoricoRequerimento(FinalizacaoProcesso finalizacaoProcesso) {
	historicoRequerimento = (HistoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (historicoRequerimento != null && finalizacaoProcesso != null
		&& finalizacaoProcesso.getIdFinalizacaoProcesso().longValue() > 0) {
	    if (historicoRequerimento.getIdHistorico() == null
		    || historicoRequerimento.getIdHistorico().intValue() == 0) {
		historicoRequerimento.setDataCadastro(new Date());
		historicoRequerimento.setUsuario(usuario);
	    } else if (historicoRequerimento.getIdHistorico() != null
		    && historicoRequerimento.getIdHistorico().intValue() != 0) {
		historicoRequerimento = new HistoricoRequerimentoDAO().lerPorId(historicoRequerimento.getIdHistorico());
	    }
	    historicoRequerimento.setFinalizacaoProcesso(finalizacaoProcesso);
	    historicoRequerimento.setProcesso(finalizacaoProcesso.getProcesso());
	    historicoRequerimento.setDataAtualizacao(new Date());
	    historicoRequerimento = new HistoricoRequerimentoDAO().salvar(historicoRequerimento);
	    JSFUtil.getSessionMap().put(ConstantSinca.NOVO_HISTORICO_RA, historicoRequerimento);
	}

    }

    public void carregarDaddos() {
	instanciaAtributos();
	JSFUtil.getRequestContext().execute("PF('dlg_diligencia').show()");
    }

    public List<PessoaJuridica> listaPessoaPorNomeLk(String nome) {
	if (nome != null && nome.equals(""))
	    logger.info("Nome Pessoa PESQUISA " + nome);
	List<PessoaJuridica> pessoas = new PessoaJuridicaDAO().listaPessoaPorNomeLk(nome);
	return pessoas;
    }

    public void addNotificacao() {
	this.finalizacaoNotificacao = new FinalizacaoNotificacao();
	JSFUtil.getRequestContext().execute("PF('dlg_notificacao').show()");
    }

    public void incluirNotificacao() {
	List<FinalizacaoNotificacao> lista = new ArrayList<FinalizacaoNotificacao>();
	if (finalizacaoNotificacao != null) {
	    for (FinalizacaoNotificacao notificacao : getListaFinalizacaoNotificacaos()) {
		if (!notificacao.getTipo().equalsIgnoreCase(notificacao.getTipo())) {
		    lista.add(notificacao);
		}
	    }
	    lista.add(finalizacaoNotificacao);
	}
	listaFinalizacaoNotificacaos = lista;
	JSFUtil.getRequestContext().execute("PF('dlg_notificacao').hide()");
    }

    public void editarNotificacao() {
	FinalizacaoNotificacao notificacao = (FinalizacaoNotificacao) JSFUtil.getRequestMap().get("notificacao");
	finalizacaoNotificacao = notificacao;
	JSFUtil.getRequestContext().execute("PF('dlg_notificacao').show()");
    }

    public void removerNotificacao() {
	List<FinalizacaoNotificacao> lista = new ArrayList<FinalizacaoNotificacao>();
	FinalizacaoNotificacao notificacao = (FinalizacaoNotificacao) JSFUtil.getRequestMap().get("notificacao");
	if (notificacao != null) {
	    for (FinalizacaoNotificacao finalizacaoNotificacao : getListaFinalizacaoNotificacaos()) {
		if (!notificacao.getTipo().equalsIgnoreCase(finalizacaoNotificacao.getTipo())) {
		    lista.add(finalizacaoNotificacao);
		}
	    }
	}
	listaFinalizacaoNotificacaos = lista;
    }

    public void incluirOrgao() {
	List<FinalizacaoOrgao> lista = new ArrayList<FinalizacaoOrgao>();
	if (finalizacaoOrgao != null && pessoaJuridica != null) {
	    finalizacaoOrgao.setPessoaJuridica(pessoaJuridica);
	    for (FinalizacaoOrgao finOrgao : getListaFinalizacaoOrgao()) {
		if (finOrgao.getPessoaJuridica() != null
			&& !finOrgao.getPessoaJuridica().getNomePessoa()
				.equalsIgnoreCase(finOrgao.getPessoaJuridica().getNomePessoa())) {
		    lista.add(finOrgao);
		}
	    }
	    lista.add(finalizacaoOrgao);
	}
	listaFinalizacaoOrgao = lista;
    }

    public void editarOrgao() {
	FinalizacaoOrgao finOrgao = (FinalizacaoOrgao) JSFUtil.getRequestMap().get("finaOrgao");
	if (finOrgao != null && finOrgao.getPessoaJuridica() != null) {
	    finalizacaoOrgao = finOrgao;
	    this.pessoaJuridica = finOrgao.getPessoaJuridica();
	}
    }

    public void removerOrgao() {
	List<FinalizacaoOrgao> lista = new ArrayList<FinalizacaoOrgao>();
	FinalizacaoOrgao finOrgao = (FinalizacaoOrgao) JSFUtil.getRequestMap().get("finaOrgao");
	if (finOrgao != null) {
	    for (FinalizacaoOrgao finalizacaoOrgao : getListaFinalizacaoOrgao()) {
		if (finalizacaoOrgao.getPessoaJuridica() != null
			&& !finalizacaoOrgao.getPessoaJuridica().getNomePessoa()
				.equalsIgnoreCase(finalizacaoOrgao.getPessoaJuridica().getNomePessoa())) {
		    lista.add(finalizacaoOrgao);
		}
	    }
	}
	listaFinalizacaoOrgao = lista;
    }

    public Processo getProcesso() {
	return processo;
    }

    public LoginMB getLoginMB() {
	return loginMB;
    }

    public FinalizacaoProcesso getFinalizacaoProcesso() {
	return finalizacaoProcesso;
    }

    public void setFinalizacaoProcesso(FinalizacaoProcesso finalizacaoProcesso) {
	this.finalizacaoProcesso = finalizacaoProcesso;
    }

    public FinalizacaoNotificacao getFinalizacaoNotificacao() {
	return finalizacaoNotificacao;
    }

    public Integer getIdFinalizacaoNotificacao() {
	return idFinalizacaoNotificacao;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public void setFinalizacaoNotificacao(FinalizacaoNotificacao finalizacaoNotificacao) {
	this.finalizacaoNotificacao = finalizacaoNotificacao;
    }

    public void setIdFinalizacaoNotificacao(Integer idFinalizacaoNotificacao) {
	this.idFinalizacaoNotificacao = idFinalizacaoNotificacao;
    }

    public void setLoginMB(LoginMB loginMB) {
	this.loginMB = loginMB;
    }

    public List<FinalizacaoNotificacao> getListaFinalizacaoNotificacaos() {
	return listaFinalizacaoNotificacaos;
    }

    public void setListaFinalizacaoNotificacaos(List<FinalizacaoNotificacao> listaFinalizacaoNotificacaos) {
	this.listaFinalizacaoNotificacaos = listaFinalizacaoNotificacaos;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public HistoricoRequerimento getHistoricoRequerimento() {
	return historicoRequerimento;
    }

    public void setHistoricoRequerimento(HistoricoRequerimento historicoRequerimento) {
	this.historicoRequerimento = historicoRequerimento;
    }

    public FinalizacaoOrgao getFinalizacaoOrgao() {
	return finalizacaoOrgao;
    }

    public List<FinalizacaoOrgao> getListaFinalizacaoOrgao() {
	return listaFinalizacaoOrgao;
    }

    public void setFinalizacaoOrgao(FinalizacaoOrgao finalizacaoOrgao) {
	this.finalizacaoOrgao = finalizacaoOrgao;
    }

    public void setListaFinalizacaoOrgao(List<FinalizacaoOrgao> listaFinalizacaoOrgao) {
	this.listaFinalizacaoOrgao = listaFinalizacaoOrgao;
    }

    public PessoaJuridica getPessoaJuridica() {
	return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
	this.pessoaJuridica = pessoaJuridica;
    }

}
