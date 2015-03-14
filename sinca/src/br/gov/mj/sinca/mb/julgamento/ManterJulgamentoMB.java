package br.gov.mj.sinca.mb.julgamento;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.ConstantSinca;
import br.gov.mj.sinca.dao.HistoricoRequerimentoDAO;
import br.gov.mj.sinca.dao.JulgamentoProcessoDAO;
import br.gov.mj.sinca.dao.RecomendacaoAnaliseDAO;
import br.gov.mj.sinca.dao.ReparacaoDireitoJulgamentoDAO;
import br.gov.mj.sinca.dao.ReparacaoEconomicaJulgamentoDAO;
import br.gov.mj.sinca.dao.TipoDesicaoJulgamentoDAO;
import br.gov.mj.sinca.dao.TipoJulgamentoAnaliseDAO;
import br.gov.mj.sinca.dao.UsuarioDAO;
import br.gov.mj.sinca.entidades.AnaliseProcesso;
import br.gov.mj.sinca.entidades.HitoricoRequerimento;
import br.gov.mj.sinca.entidades.JulgamentoProcesso;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.RecomendacaoAnalise;
import br.gov.mj.sinca.entidades.ReparacaoDireitoAnalise;
import br.gov.mj.sinca.entidades.ReparacaoDireitoJulgamento;
import br.gov.mj.sinca.entidades.ReparacaoEconomicaAnalise;
import br.gov.mj.sinca.entidades.ReparacaoEconomicaJulgamento;
import br.gov.mj.sinca.entidades.ReparacaoMoralAnalise;
import br.gov.mj.sinca.entidades.ReparacaoMoralJulgamento;
import br.gov.mj.sinca.entidades.TipoDesicaoJulgamento;
import br.gov.mj.sinca.entidades.TipoJulgamentoAnalise;
import br.gov.mj.sinca.entidades.Usuario;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.JSFUtil;

/**
 * Maneger Beam que realiza as funcionalidades de manter Julgamento do processo
 * de anistia.
 * 
 * @author Sebastiao.Costa Contrato nº:XXXXX
 */
@ManagedBean(name = "manterJulgamentoMB")
@ViewScoped
public class ManterJulgamentoMB implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1007696592419574224L;

    private List<TipoJulgamentoAnalise> listarTipoJulgamentoAnalise = new ArrayList<TipoJulgamentoAnalise>();
    private List<TipoDesicaoJulgamento> listarTipoDesicaoJulgamento = new ArrayList<TipoDesicaoJulgamento>();

    private String mensagem;
    private Long codTipoAnaliseJug;

    private ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamento;
    private ReparacaoMoralJulgamento reparacaoMoralJulgamento;
    private ReparacaoDireitoJulgamento reparacaoDireitoJulgamento;
    private Integer idDesicaoConselhero;

    private boolean bolReparacaoEconomica = false;
    private boolean bolReparacaoMoral = false;
    private boolean bolReparacaoDireito = false;

    private JulgamentoProcesso julgamentoProcesso;
    private AnaliseProcesso analiseProcesso;
    private HitoricoRequerimento hitoricoRequerimento;

    private Usuario usuarioRelator;
    private Usuario usuarioVistas;
    private Usuario usuarioCalculo;

    private Logger logger;

    private Usuario usuario;
    
    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;
    
    
    @PostConstruct
    public void Init() {
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	usuario = loginMB.getUsuario();
	
	listarTipoJulgamentoAnalise = new TipoJulgamentoAnaliseDAO().lerTodos();
	listarTipoDesicaoJulgamento = new TipoDesicaoJulgamentoDAO().lerTodos();
	
	julgamentoProcesso = new JulgamentoProcesso();
	reparacaoEconomicaJulgamento = new ReparacaoEconomicaJulgamento();
	reparacaoMoralJulgamento = new ReparacaoMoralJulgamento();
	reparacaoDireitoJulgamento = new ReparacaoDireitoJulgamento();

	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcesso");

	if (pessoaProcesso != null && pessoaProcesso.getProcesso().getIdProcesso() > 0) {
	    julgamentoProcesso.setProcesso(pessoaProcesso.getProcesso());
	}
	hitoricoRequerimento = (HitoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (hitoricoRequerimento != null && hitoricoRequerimento.getIdHistorico() != null
		&& hitoricoRequerimento.getIdHistorico().longValue() > 0) {
	    hitoricoRequerimento = new HistoricoRequerimentoDAO().lerPorId(hitoricoRequerimento.getIdHistorico());
	    this.analiseProcesso = hitoricoRequerimento.getAnaliseProcesso1();
	    this.julgamentoProcesso = hitoricoRequerimento.getJulgamentoProcesso();
	    if (this.julgamentoProcesso != null && this.julgamentoProcesso.getIdJulgamentoProcesso() != null
		    && this.julgamentoProcesso.getIdJulgamentoProcesso().intValue() > 0) {
		julgamentoProcesso = new JulgamentoProcessoDAO().lerPorId(julgamentoProcesso.getIdJulgamentoProcesso());
		julgamentoProcesso.getReparacaoDireitoJulgamentos().size();
		julgamentoProcesso.getReparacaoEconomicaJulgamentos().size();
		julgamentoProcesso.getReparacaoMoralJulgamentos().size();

		if (julgamentoProcesso.getReparacaoDireitoJulgamentos() != null) {
		    this.reparacaoDireitoJulgamento = julgamentoProcesso.getReparacaoDireitoJulgamentos().get(0);
		}
		if (julgamentoProcesso.getReparacaoEconomicaJulgamentos() != null) {
		    this.reparacaoEconomicaJulgamento = julgamentoProcesso.getReparacaoEconomicaJulgamentos().get(0);
		}
		if (julgamentoProcesso.getReparacaoMoralJulgamentos() != null) {
		    this.reparacaoMoralJulgamento = julgamentoProcesso.getReparacaoMoralJulgamentos().get(0);
		}
	    }
	    if (julgamentoProcesso == null){
		julgamentoProcesso = new JulgamentoProcesso();
	    }
	    
	    if (analiseProcesso != null && analiseProcesso.getIdAnalise() != null
		    && analiseProcesso.getIdAnalise().longValue() > 0) {
		RecomendacaoAnalise recomendacaoAnalise = new RecomendacaoAnaliseDAO()
			.buscarRecomendacaoAnalise(analiseProcesso.getIdAnalise());

		if (recomendacaoAnalise != null && recomendacaoAnalise.getIdRecomendacao().longValue() > 0) {
		    recomendacaoAnalise.getReparacaoEconomicaAnalises().size();
		    recomendacaoAnalise.getReparacaoMoralAnalises().size();
		    recomendacaoAnalise.getReparacaoDireitoAnalises().size();

		    carregarReparacaoEconomica(recomendacaoAnalise);

		    carregaReparacaoMoral(recomendacaoAnalise);

		    carregaReparacaoDireito(recomendacaoAnalise);

		}
	    }
	}

    }

    private void carregaReparacaoDireito(RecomendacaoAnalise recomendacaoAnalise) {
	if (!recomendacaoAnalise.getReparacaoDireitoAnalises().isEmpty()) {
	if (reparacaoDireitoJulgamento == null
		|| reparacaoDireitoJulgamento.getIdReparacao() == null
		|| reparacaoDireitoJulgamento.getIdReparacao().intValue() == 0) {
	    bolReparacaoMoral= true;
	    julgamentoProcesso.setBolReparacaoMoral(new BigInteger("1"));
	    ReparacaoDireitoAnalise direitoAnalise = recomendacaoAnalise.getReparacaoDireitoAnalises().get(0);
	    reparacaoDireitoJulgamento.setIdUsuario(usuario.getIdUsuario());
	    reparacaoDireitoJulgamento.setJulgamentoProcesso(julgamentoProcesso);
	    reparacaoDireitoJulgamento.setComplemento(direitoAnalise.getComplemento());
	    reparacaoDireitoJulgamento.setComplementoInstituicao(direitoAnalise.getComplementoInstituicao());
	    reparacaoDireitoJulgamento.setCurso(direitoAnalise.getCurso());
	    reparacaoDireitoJulgamento.setDataFim(direitoAnalise.getDataFim());
	    reparacaoDireitoJulgamento.setDataInicio(direitoAnalise.getDataInicio());
	    reparacaoDireitoJulgamento.setInstituicao(direitoAnalise.getInstituicao());
	    reparacaoDireitoJulgamento.setTipoRestituicaoDireito(direitoAnalise.getTipoRestituicaoDireito());
	}
	}
    }

    private void carregaReparacaoMoral(RecomendacaoAnalise recomendacaoAnalise) {
	if (!recomendacaoAnalise.getReparacaoMoralAnalises().isEmpty()) {
	if (reparacaoMoralJulgamento == null
		|| reparacaoMoralJulgamento.getIdReparacao() == null
		|| reparacaoMoralJulgamento.getIdReparacao().intValue() == 0) {
	    bolReparacaoMoral= true;
	    julgamentoProcesso.setBolReparacaoMoral(new BigInteger("1"));
	    ReparacaoMoralAnalise moralAnalise = recomendacaoAnalise.getReparacaoMoralAnalises().get(0);
	    reparacaoMoralJulgamento.setIdUsuario(usuario.getIdUsuario());
	    reparacaoMoralJulgamento.setJulgamentoProcesso(julgamentoProcesso);
	    reparacaoMoralJulgamento.setDeclaracaoRetificacao(moralAnalise.getDeclaracaoRetificacao());
	    reparacaoMoralJulgamento.setOutrasReparacoes(moralAnalise.getOutrasReparacoes());
	}
	}
    }

    private void carregarReparacaoEconomica(RecomendacaoAnalise recomendacaoAnalise) {
	if (!recomendacaoAnalise.getReparacaoEconomicaAnalises().isEmpty()) {
	if (reparacaoEconomicaJulgamento == null
		|| reparacaoEconomicaJulgamento.getIdReparacao() == null
		|| reparacaoEconomicaJulgamento.getIdReparacao().intValue() == 0) {
	    bolReparacaoEconomica = true;
	    julgamentoProcesso.setBolReparacaoEconomica(new BigInteger("1"));
	    ReparacaoEconomicaAnalise economicaAnalise = recomendacaoAnalise
		    .getReparacaoEconomicaAnalises().get(0);
	    reparacaoEconomicaJulgamento.setValorPu(economicaAnalise.getValorPu());
	    reparacaoEconomicaJulgamento.setValorPmpc(economicaAnalise.getValorPmpc());
	    reparacaoEconomicaJulgamento.setDataInicioPu(economicaAnalise.getDataInicioPu());
	    reparacaoEconomicaJulgamento.setDataPrevistaSessao(economicaAnalise.getDataPrevistaSessao());
	    reparacaoEconomicaJulgamento.setDataPrimeiroProtocolo(economicaAnalise.getDataPrimeiroProtocolo());
	    reparacaoEconomicaJulgamento.setDataRecebimento(economicaAnalise.getDataRecebimento());
	    reparacaoEconomicaJulgamento.setDataReotravidade(economicaAnalise.getDataReotravidade());
	    reparacaoEconomicaJulgamento.setIdUsuario(usuario.getIdUsuario());
	    reparacaoEconomicaJulgamento.setJulgamentoProcesso(julgamentoProcesso);
	}
	}
    }

    public String salvarJulgamento() {
	try {
	    this.mensagem = "Salvando Dados do Julgamento";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_julgamento').show()");

	    if(julgamentoProcesso.getIdJulgamentoProcesso()==null || julgamentoProcesso.getIdJulgamentoProcesso().longValue()==0){
		julgamentoProcesso.setDataCadastro(new Date());
	    }
	    julgamentoProcesso.setDataAtualizacao(new Date());
	    
	    if(codTipoAnaliseJug!=null && codTipoAnaliseJug.intValue()>0){
		julgamentoProcesso.setTipoJulgamentoAnalise(new TipoJulgamentoAnaliseDAO().lerPorId(codTipoAnaliseJug));
	    }
	    if(idDesicaoConselhero!=null && idDesicaoConselhero.intValue()>0){
		julgamentoProcesso.setTipoDesicaoJulgamento(new TipoDesicaoJulgamentoDAO().lerPorId(idDesicaoConselhero));
	    }
	    julgamentoProcesso.setIdUsuario(usuario.getIdUsuario());
	    
	    julgamentoProcesso = new JulgamentoProcessoDAO().salvar(julgamentoProcesso);
	    if(bolReparacaoDireito){
		reparacaoDireitoJulgamento = new ReparacaoDireitoJulgamentoDAO().salvar(reparacaoDireitoJulgamento);	
	    }
	    if(bolReparacaoEconomica){
		reparacaoEconomicaJulgamento = new ReparacaoEconomicaJulgamentoDAO().salvar(reparacaoEconomicaJulgamento);	
	    }
	    if(bolReparacaoDireito){
		reparacaoDireitoJulgamento = new ReparacaoDireitoJulgamentoDAO().salvar(reparacaoDireitoJulgamento);	
	    }
	    salvarHistoricoRequerimento(julgamentoProcesso);
	    JSFUtil.retornarMensagemModal("Julgamento", "Julgamento Salvo....");
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_julgamento').hide()");
	    this.mensagem = "";
	} catch (Exception e) {
	    this.mensagem = "";
	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_analise').hide()");
	    e.printStackTrace();
	}
	return null;
    }
    
    private void salvarHistoricoRequerimento(JulgamentoProcesso julgamentoProcesso) {
	hitoricoRequerimento = (HitoricoRequerimento) JSFUtil.getSessionMap().get(ConstantSinca.NOVO_HISTORICO_RA);
	if (hitoricoRequerimento != null && analiseProcesso != null && analiseProcesso.getIdAnalise().longValue() > 0) {
	    if (hitoricoRequerimento.getIdHistorico() == null || hitoricoRequerimento.getIdHistorico().intValue() == 0) {
		hitoricoRequerimento.setDataCadastro(new Date());
		hitoricoRequerimento.setUsuario(usuario);
	    }else if(hitoricoRequerimento.getIdHistorico() != null && hitoricoRequerimento.getIdHistorico().intValue() != 0){
		hitoricoRequerimento = new HistoricoRequerimentoDAO().lerPorId(hitoricoRequerimento.getIdHistorico());
	    }
	    hitoricoRequerimento.setDataAtualizacao(new Date());
	    hitoricoRequerimento.setJulgamentoProcesso(julgamentoProcesso);
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

    public List<Usuario> listarUsuarioPorNomeLike(String nome) {
	if (nome != null && nome.equals(""))
	    System.out.println("Nome Pessoa PESQUISA " + nome);
	List<Usuario> pessoas = new UsuarioDAO().listaUsuarioPorNomeLk(nome);
	return pessoas;
    }

    public Long getCodTipoAnaliseJug() {
	return codTipoAnaliseJug;
    }

    public void setCodTipoAnaliseJug(Long codTipoAnaliseJug) {
	this.codTipoAnaliseJug = codTipoAnaliseJug;
    }

    public ReparacaoEconomicaJulgamento getReparacaoEconomicaJulgamento() {
	return reparacaoEconomicaJulgamento;
    }

    public ReparacaoMoralJulgamento getReparacaoMoralJulgamento() {
	return reparacaoMoralJulgamento;
    }

    public ReparacaoDireitoJulgamento getReparacaoDireitoJulgamento() {
	return reparacaoDireitoJulgamento;
    }

    public void setReparacaoEconomicaJulgamento(ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamento) {
	this.reparacaoEconomicaJulgamento = reparacaoEconomicaJulgamento;
    }

    public void setReparacaoMoralJulgamento(ReparacaoMoralJulgamento reparacaoMoralJulgamento) {
	this.reparacaoMoralJulgamento = reparacaoMoralJulgamento;
    }

    public void setReparacaoDireitoJulgamento(ReparacaoDireitoJulgamento reparacaoDireitoJulgamento) {
	this.reparacaoDireitoJulgamento = reparacaoDireitoJulgamento;
    }

    public JulgamentoProcesso getJulgamentoProcesso() {
	return julgamentoProcesso;
    }

    public void setJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
	this.julgamentoProcesso = julgamentoProcesso;
    }

    public Integer getIdDesicaoConselhero() {
	return idDesicaoConselhero;
    }

    public void setIdDesicaoConselhero(Integer idDesicaoConselhero) {
	this.idDesicaoConselhero = idDesicaoConselhero;
    }

    public boolean isBolReparacaoEconomica() {
	return bolReparacaoEconomica;
    }

    public boolean isBolReparacaoMoral() {
	return bolReparacaoMoral;
    }

    public boolean isBolReparacaoDireito() {
	return bolReparacaoDireito;
    }

    public void setBolReparacaoEconomica(boolean bolReparacaoEconomica) {
	this.bolReparacaoEconomica = bolReparacaoEconomica;
    }

    public Usuario getUsuarioRelator() {
	return usuarioRelator;
    }

    public void setUsuarioRelator(Usuario usuarioRelator) {
	this.usuarioRelator = usuarioRelator;
    }

    public void setBolReparacaoMoral(boolean bolReparacaoMoral) {
	this.bolReparacaoMoral = bolReparacaoMoral;
    }

    public void setBolReparacaoDireito(boolean bolReparacaoDireito) {
	this.bolReparacaoDireito = bolReparacaoDireito;
    }

    public Usuario getUsuarioVistas() {
	return usuarioVistas;
    }

    public Usuario getUsuarioCalculo() {
	return usuarioCalculo;
    }

    public void setUsuarioVistas(Usuario usuarioVistas) {
	this.usuarioVistas = usuarioVistas;
    }

    public void setUsuarioCalculo(Usuario usuarioCalculo) {
	this.usuarioCalculo = usuarioCalculo;
    }

    public AnaliseProcesso getAnaliseProcesso() {
	return analiseProcesso;
    }

    public HitoricoRequerimento getHitoricoRequerimento() {
	return hitoricoRequerimento;
    }

    public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
	this.analiseProcesso = analiseProcesso;
    }

    public void setHitoricoRequerimento(HitoricoRequerimento hitoricoRequerimento) {
	this.hitoricoRequerimento = hitoricoRequerimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<TipoJulgamentoAnalise> getListarTipoJulgamentoAnalise() {
        return listarTipoJulgamentoAnalise;
    }

    public List<TipoDesicaoJulgamento> getListarTipoDesicaoJulgamento() {
        return listarTipoDesicaoJulgamento;
    }

    public void setListarTipoDesicaoJulgamento(List<TipoDesicaoJulgamento> listarTipoDesicaoJulgamento) {
        this.listarTipoDesicaoJulgamento = listarTipoDesicaoJulgamento;
    }

    public void setListarTipoJulgamentoAnalise(List<TipoJulgamentoAnalise> listarTipoJulgamentoAnalise) {
        this.listarTipoJulgamentoAnalise = listarTipoJulgamentoAnalise;
    }

}
