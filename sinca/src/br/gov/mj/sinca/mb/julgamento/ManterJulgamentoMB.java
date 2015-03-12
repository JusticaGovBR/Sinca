package br.gov.mj.sinca.mb.julgamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.JulgamentoProcessoDAO;
import br.gov.mj.sinca.dao.TipoAnaliseJulgamentoDAO;
import br.gov.mj.sinca.dao.UsuarioDAO;
import br.gov.mj.sinca.entidades.JulgamentoProcesso;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.ReparacaoDireitoJulgamento;
import br.gov.mj.sinca.entidades.ReparacaoEconomicaJulgamento;
import br.gov.mj.sinca.entidades.ReparacaoMoralJulgamento;
import br.gov.mj.sinca.entidades.TipoAnaliseJulgamento;
import br.gov.mj.sinca.entidades.Usuario;
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

    private List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento = new ArrayList<TipoAnaliseJulgamento>();

    private Integer codTipoAnaliseJug;

    private ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamento;
    private ReparacaoMoralJulgamento reparacaoMoralJulgamento;
    private ReparacaoDireitoJulgamento reparacaoDireitoJulgamento;
    private Integer idDesicaoConselhero;
    
    private boolean bolReparacaoEconomica = false;
    private boolean bolReparacaoMoral = false;
    private boolean bolReparacaoDireito = false;    

    private JulgamentoProcesso julgamentoProcesso;
    
    private Usuario usuarioRelator;
    private Usuario usuarioVistas;
    private Usuario usuarioCalculo;

    private Logger logger;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada :" + this.getClass().getName() + " Init <>  PosConstruct");
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	listarTipoAnaliseJulgamento = new TipoAnaliseJulgamentoDAO().lerTodos();
	julgamentoProcesso = new JulgamentoProcesso();
	reparacaoEconomicaJulgamento = new ReparacaoEconomicaJulgamento();
	reparacaoMoralJulgamento = new ReparacaoMoralJulgamento();
	reparacaoDireitoJulgamento = new ReparacaoDireitoJulgamento();

	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcesso");

	if (pessoaProcesso != null && pessoaProcesso.getProcesso().getIdProcesso() > 0) {
	    julgamentoProcesso.setProcesso(pessoaProcesso.getProcesso());
	}

    }

    public String salvarJulgamento() {
	try {
	    julgamentoProcesso = new JulgamentoProcessoDAO().salvar(julgamentoProcesso);

	    JSFUtil.getRequestContext().execute("PF('dlg_mensagem_analise').show()");
	} catch (Exception e) {

	}
	return null;
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
    
    public List<TipoAnaliseJulgamento> getListarTipoAnaliseJulgamento() {
	return listarTipoAnaliseJulgamento;
    }

    public void setListarTipoAnaliseJulgamento(List<TipoAnaliseJulgamento> listarTipoAnaliseJulgamento) {
	this.listarTipoAnaliseJulgamento = listarTipoAnaliseJulgamento;
    }

    public Integer getCodTipoAnaliseJug() {
	return codTipoAnaliseJug;
    }

    public void setCodTipoAnaliseJug(Integer codTipoAnaliseJug) {
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


}
