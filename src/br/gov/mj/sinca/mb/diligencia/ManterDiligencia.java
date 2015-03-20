package br.gov.mj.sinca.mb.diligencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import br.gov.mj.sinca.dao.DiligenciaDAO;
import br.gov.mj.sinca.dao.DiligenciaPessoaDAO;
import br.gov.mj.sinca.dao.JPAUtil;
import br.gov.mj.sinca.dao.PessoaProcessoDAO;
import br.gov.mj.sinca.entidades.Diligencia;
import br.gov.mj.sinca.entidades.DiligenciaPessoa;
import br.gov.mj.sinca.entidades.PessoaFisica;
import br.gov.mj.sinca.entidades.PessoaJuridica;
import br.gov.mj.sinca.entidades.PessoaProcesso;
import br.gov.mj.sinca.entidades.Processo;
import br.gov.mj.sinca.mb.LoginMB;
import br.gov.mj.sinca.util.JSFUtil;

@ManagedBean(name = "manterDiligenciaMB")
@ViewScoped
public class ManterDiligencia implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1344637621443972431L;

    private Logger logger = null;

    private Diligencia diligencia;
    private DiligenciaPessoa diligenciaProcesso;
    private DiligenciaPessoa diligenciaProcessoEdicao;
    private Processo processo;
    private PessoaJuridica pessoaJuridica;
    private PessoaFisica pessoaFisicaEdicao;
    private PessoaProcesso pessoaProcesso;
    private Long idPessoaSelecionada;

    private List<PessoaFisica> listaPessoaFisicas;
    private List<PessoaFisica> listaPessoaFisicasProcesso;
    private List<Diligencia> listaDiligencias;

    @ManagedProperty(value = "#{loginMB}")
    private LoginMB loginMB;

    @PostConstruct
    public void Init() {
	logger = Logger.getLogger(this.getClass());
	logger.info("Chamada Init <>  PosConstruct" + this.getClass().getName());
	instanciaAtributos();
    }

    private void instanciaAtributos() {
	diligencia = new Diligencia();
	diligenciaProcesso = new DiligenciaPessoa();
	listaPessoaFisicas = new ArrayList<PessoaFisica>();
	pessoaJuridica = new PessoaJuridica();
	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcessoDiligenica");
	if (pessoaProcesso != null && pessoaProcesso.getIdPessoaProcesso() > 0) {
	    this.listaPessoaFisicasProcesso = new PessoaProcessoDAO().listarPessoasProcesso(pessoaProcesso
		    .getProcesso().getIdProcesso());
	    this.processo = pessoaProcesso.getProcesso();
	    this.listaDiligencias = new DiligenciaDAO().listarDiligenciaPorProcesso(pessoaProcesso.getProcesso()
		    .getIdProcesso());
	}

    }

    public void carregarDaddos() {
	instanciaAtributos();
	JSFUtil.getRequestContext().execute("PF('dlg_diligencia').show()");
    }

    public List<PessoaFisica> getListaPessoaFisicasProcesso() {
	PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcessoDiligenica");
	if (pessoaProcesso != null && pessoaProcesso.getIdPessoaProcesso() > 0) {
	    this.listaPessoaFisicasProcesso = new PessoaProcessoDAO().listarPessoasProcesso(pessoaProcesso
		    .getProcesso().getIdProcesso());
	    this.processo = pessoaProcesso.getProcesso();
	}
	return listaPessoaFisicasProcesso;
    }

    public void salvarDiligencia() {
	try {
	    PessoaProcesso pessoaProcesso = (PessoaProcesso) JSFUtil.getSessionMap().get("pessoaProcessoDiligenica");
	    if (pessoaProcesso != null && pessoaProcesso.getIdPessoaProcesso() > 0) {
		this.processo = pessoaProcesso.getProcesso();
	    }
	    if (pessoaJuridica == null || pessoaJuridica.getIdPessoaJuridica() == null
		    || pessoaJuridica.getIdPessoaJuridica() == 0) {
		JSFUtil.retornarMensagemModal("", "Orgão não Selecionado!");
		return;
	    }
	    diligencia.setDataCadastro(new Date());
	    diligencia.setIdUsuario(getLoginMB().getUsuario().getIdUsuario());
	    EntityManager em = JPAUtil.getEntityManager();
	    em.getTransaction().begin();
	    diligencia.setProcesso(processo);

	    pessoaJuridica.setBolDiligencia((byte) 1);
	    pessoaJuridica = em.merge(pessoaJuridica);

	    diligencia.setPessoaJuridica(pessoaJuridica);
	    Diligencia diligenciaSalva = em.merge(diligencia);
	    em.flush();
	    new DiligenciaPessoaDAO().excluirPorIdDiligencia(diligenciaSalva.getIdDiligencia());

	    for (PessoaFisica pessoa : listaPessoaFisicas) {
		DiligenciaPessoa diligenciaProcesso = new DiligenciaPessoa();
		diligenciaProcesso.setDataCadastro(diligenciaSalva.getDataCadastro());
		diligenciaProcesso.setDiligencia(diligenciaSalva);
		diligenciaProcesso.setPessoaFisica(pessoa);
		diligenciaProcesso.setIdUsuario(diligenciaSalva.getIdUsuario());
		em.merge(diligenciaProcesso);
		em.flush();
	    }
	    em.getTransaction().commit();
	    this.listaDiligencias = new DiligenciaDAO().listarDiligenciaPorProcesso(processo.getIdProcesso());

	    JSFUtil.retornarMensagemModal("", "Dados Salvos com Sucesso!");

	} catch (Exception e) {
	    JSFUtil.retornarMensagemModal("", "Erro ao salvar a Diligência");
	    e.printStackTrace();
	    logger.error("Erro ao salvar a Diligência", e);
	}
    }

    public void incluirDiligencia() {
	for (PessoaFisica pessoa : getListaPessoaFisicasProcesso()) {
	    if (pessoa.getIdPessoa().longValue() == idPessoaSelecionada.longValue()) {
		if (!getListaPessoaFisicas().contains(pessoa)) {
		    getListaPessoaFisicas().add(pessoa);
		}
	    }
	}
    }

    public List<PessoaFisica> getListaPessoaFisicas() {
	return listaPessoaFisicas;
    }

    public void editarDiligencia() {
	diligencia = (Diligencia) JSFUtil.getRequestMap().get("diligenciasP");
	if (diligencia != null) {
	    this.pessoaJuridica = diligencia.getPessoaJuridica();
	    this.listaPessoaFisicas = new DiligenciaPessoaDAO().listarDiligenciados(diligencia.getIdDiligencia());
	}
    }

    public void removerDiligencia() {
	try {
	    DiligenciaDAO dao = new DiligenciaDAO();
	    diligencia = (Diligencia) JSFUtil.getRequestMap().get("diligenciasP");
	    diligencia = dao.lerPorId(diligencia.getIdDiligencia());
	    dao.excluir(diligencia);
	    instanciaAtributos();
	    JSFUtil.getRequestContext().execute("PF('dlg_diligencia').hide()");
	    JSFUtil.retornarMensagemModal("", "Dados Excluidos com Sucesso!");
	} catch (Exception e) {
	    JSFUtil.retornarMensagemModal("", "Erro ao remover a diligência! :" + e.getMessage());
	    e.printStackTrace();
	    logger.error("Erro ao remover a diligência! :", e);
	}
    }

    public void removerDiligenciado() {
	pessoaFisicaEdicao = (PessoaFisica) JSFUtil.getRequestMap().get("pessoas");
	List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
	for (PessoaFisica pessoaFisica : listaPessoaFisicas) {
	    if (pessoaFisica.getIdPessoa().longValue() != pessoaFisicaEdicao.getIdPessoa().longValue()) {
		lista.add(pessoaFisica);
	    }
	}
	setListaPessoaFisicas(lista);
    }

    public DiligenciaPessoa getDiligenciaProcesso() {
	return diligenciaProcesso;
    }

    public Processo getProcesso() {
	return processo;
    }

    public void setDiligenciaProcesso(DiligenciaPessoa diligenciaProcesso) {
	this.diligenciaProcesso = diligenciaProcesso;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public Diligencia getDiligencia() {
	return diligencia;
    }

    public void setDiligencia(Diligencia diligencia) {
	this.diligencia = diligencia;
    }

    public PessoaProcesso getPessoaProcesso() {
	return pessoaProcesso;
    }

    public void setPessoaProcesso(PessoaProcesso pessoaProcesso) {
	this.pessoaProcesso = pessoaProcesso;
    }

    public PessoaJuridica getPessoaJuridica() {
	return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
	this.pessoaJuridica = pessoaJuridica;
    }

    public void setListaPessoaFisicas(List<PessoaFisica> listaPessoaFisicas) {
	this.listaPessoaFisicas = listaPessoaFisicas;
    }

    public LoginMB getLoginMB() {
	return loginMB;
    }

    public DiligenciaPessoa getDiligenciaProcessoEdicao() {
	return diligenciaProcessoEdicao;
    }

    public void setDiligenciaProcessoEdicao(DiligenciaPessoa diligenciaProcessoEdicao) {
	this.diligenciaProcessoEdicao = diligenciaProcessoEdicao;
    }

    public void setLoginMB(LoginMB loginMB) {
	this.loginMB = loginMB;
    }

    public PessoaFisica getPessoaFisicaEdicao() {
	return pessoaFisicaEdicao;
    }

    public void setPessoaFisicaEdicao(PessoaFisica pessoaFisicaEdicao) {
	this.pessoaFisicaEdicao = pessoaFisicaEdicao;
    }

    public void setListaPessoaFisicasProcesso(List<PessoaFisica> listaPessoaFisicasProcesso) {
	this.listaPessoaFisicasProcesso = listaPessoaFisicasProcesso;
    }

    public Long getIdPessoaSelecionada() {
	return idPessoaSelecionada;
    }

    public void setIdPessoaSelecionada(Long idPessoaSelecionada) {
	this.idPessoaSelecionada = idPessoaSelecionada;
    }

    public List<Diligencia> getListaDiligencias() {
	return listaDiligencias;
    }

    public void setListaDiligencias(List<Diligencia> listaDiligencias) {
	this.listaDiligencias = listaDiligencias;
    }

}
