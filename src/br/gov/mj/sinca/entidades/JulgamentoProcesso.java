package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the julgamento_processo database table.
 * 
 */
@Entity
@Table(name = "julgamento_processo")
@NamedQuery(name = "JulgamentoProcesso.findAll", query = "SELECT j FROM JulgamentoProcesso j")
public class JulgamentoProcesso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JULGAMENTO_PROCESSO")
    private Long idJulgamentoProcesso;

    @Column(name = "BOL_HOUVE_ALTERACAO_PROPOSTA")
    private Byte bolHouveAlteracaoProposta;

    @Column(name = "BOL_HOUVE_NOVAS_DILIGENCIAS")
    private Byte bolHouveNovasDiligencias;

    @Column(name = "BOL_HOUVE_PEDIDO_VISTA")
    private Byte bolHouvePedidoVista;

    @Column(name = "BOL_HOUVE_REVISAO_MINUTA")
    private Byte bolHouveRevisaoMinuta;

    @Column(name = "BOL_JULGAMENTO_ADIADO")
    private Byte bolJulgamentoAdiado;

    @Column(name = "BOL_REPARACAO_ECONOMICA")
    private Byte bolReparacaoEconomica;

    @Column(name = "BOL_REPARACAO_MORAL")
    private Byte bolReparacaoMoral;

    @Column(name = "BOL_RESTITUICAO_DIREITOS")
    private Byte bolRestituicaoDireitos;

    @Column(name = "COMPLEMENTO_ALTERACAO_PROPOSTA")
    private String complementoAlteracaoProposta;

    @Column(name = "COMPLEMENTO_DILIGENCIA")
    private String complementoDiligencia;

    @Column(name = "COMPLEMENTO_JULGAMENTO")
    private String complementoJulgamento;

    @Column(name = "COMPLEMENTO_REPARACAO")
    private String complementoReparacao;

    @Column(name = "COMPLEMENTO_REVISAO")
    private String complementoRevisao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_ATUALIZACAO")
    private Date dataAtualizacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_CADASTRO")
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_DISTRIBUICAO")
    private Date dataDistribuicao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_JULGAMENTO")
    private Date dataJulgamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PREVISAO_PAUTA")
    private Date dataPrevisaoPauta;

    @Column(name = "ID_USUARIO")
    private int idUsuario;

    @Column(name = "ID_USUARIO_RELATOR")
    private Integer idUsuarioRelator;

    @Column(name = "ID_USUARIO_VISTAS")
    private Integer idUsuarioVistas;

    // bi-directional many-to-one association to ConselheirosJulgamento
    @OneToMany(mappedBy = "julgamentoProcesso")
    private List<ConselheirosJulgamento> conselheirosJulgamentos;

    // bi-directional many-to-one association to ConselheirosJulgamento
    @OneToMany(mappedBy = "julgamentoProcesso")
    private List<PresentesJulgamento> presentesJulgamentos;
   
    @ManyToOne
    @JoinColumn(name = "COD_TIPO_DESICAO")
    private TipoDesicaoJulgamento tipoDesicaoJulgamento;

    @ManyToOne
    @JoinColumn(name = "CD_TIPO_JULGAMENTO")
    private TipoJulgamentoAnalise tipoJulgamentoAnalise;

    @ManyToOne
    @JoinColumn(name = "ID_PROCESSO")
    private Processo processo;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_DIREITO")
    private ReparacaoDireitoJulgamento reparacaoDireitoJulgamentos;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_ECONOMICA")
    private ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamentos;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_MORAL")
    private ReparacaoMoralJulgamento reparacaoMoralJulgamentos;

    public JulgamentoProcesso() {
    }

    public Long getIdJulgamentoProcesso() {
	return this.idJulgamentoProcesso;
    }

    public void setIdJulgamentoProcesso(Long idJulgamentoProcesso) {
	this.idJulgamentoProcesso = idJulgamentoProcesso;
    }

    public Byte getBolHouveAlteracaoProposta() {
	return this.bolHouveAlteracaoProposta;
    }

    public void setBolHouveAlteracaoProposta(Byte bolHouveAlteracaoProposta) {
	this.bolHouveAlteracaoProposta = bolHouveAlteracaoProposta;
    }

    public Byte getBolHouveNovasDiligencias() {
	return this.bolHouveNovasDiligencias;
    }

    public void setBolHouveNovasDiligencias(Byte bolHouveNovasDiligencias) {
	this.bolHouveNovasDiligencias = bolHouveNovasDiligencias;
    }

    public Byte getBolHouvePedidoVista() {
	return this.bolHouvePedidoVista;
    }

    public void setBolHouvePedidoVista(Byte bolHouvePedidoVista) {
	this.bolHouvePedidoVista = bolHouvePedidoVista;
    }

    public Byte getBolHouveRevisaoMinuta() {
	return this.bolHouveRevisaoMinuta;
    }

    public void setBolHouveRevisaoMinuta(Byte bolHouveRevisaoMinuta) {
	this.bolHouveRevisaoMinuta = bolHouveRevisaoMinuta;
    }

    public Byte getBolJulgamentoAdiado() {
	return this.bolJulgamentoAdiado;
    }

    public void setBolJulgamentoAdiado(Byte bolJulgamentoAdiado) {
	this.bolJulgamentoAdiado = bolJulgamentoAdiado;
    }

    public Byte getBolReparacaoEconomica() {
	return this.bolReparacaoEconomica;
    }

    public void setBolReparacaoEconomica(Byte bolReparacaoEconomica) {
	this.bolReparacaoEconomica = bolReparacaoEconomica;
    }

    public Byte getBolReparacaoMoral() {
	return this.bolReparacaoMoral;
    }

    public void setBolReparacaoMoral(Byte bolReparacaoMoral) {
	this.bolReparacaoMoral = bolReparacaoMoral;
    }

    public Byte getBolRestituicaoDireitos() {
	return this.bolRestituicaoDireitos;
    }

    public void setBolRestituicaoDireitos(Byte bolRestituicaoDireitos) {
	this.bolRestituicaoDireitos = bolRestituicaoDireitos;
    }

    public String getComplementoAlteracaoProposta() {
	return this.complementoAlteracaoProposta;
    }

    public void setComplementoAlteracaoProposta(String complementoAlteracaoProposta) {
	this.complementoAlteracaoProposta = complementoAlteracaoProposta;
    }

    public String getComplementoDiligencia() {
	return this.complementoDiligencia;
    }

    public void setComplementoDiligencia(String complementoDiligencia) {
	this.complementoDiligencia = complementoDiligencia;
    }

    public String getComplementoJulgamento() {
	return this.complementoJulgamento;
    }

    public void setComplementoJulgamento(String complementoJulgamento) {
	this.complementoJulgamento = complementoJulgamento;
    }

    public String getComplementoReparacao() {
	return this.complementoReparacao;
    }

    public void setComplementoReparacao(String complementoReparacao) {
	this.complementoReparacao = complementoReparacao;
    }

    public String getComplementoRevisao() {
	return this.complementoRevisao;
    }

    public void setComplementoRevisao(String complementoRevisao) {
	this.complementoRevisao = complementoRevisao;
    }

    public Date getDataAtualizacao() {
	return this.dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
	this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataCadastro() {
	return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public Date getDataDistribuicao() {
	return this.dataDistribuicao;
    }

    public void setDataDistribuicao(Date dataDistribuicao) {
	this.dataDistribuicao = dataDistribuicao;
    }

    public Date getDataJulgamento() {
	return this.dataJulgamento;
    }

    public void setDataJulgamento(Date dataJulgamento) {
	this.dataJulgamento = dataJulgamento;
    }

    public Date getDataPrevisaoPauta() {
	return this.dataPrevisaoPauta;
    }

    public void setDataPrevisaoPauta(Date dataPrevisaoPauta) {
	this.dataPrevisaoPauta = dataPrevisaoPauta;
    }

    public int getIdUsuario() {
	return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
    }

    public Integer getIdUsuarioRelator() {
	return this.idUsuarioRelator;
    }

    public void setIdUsuarioRelator(Integer idUsuarioRelator) {
	this.idUsuarioRelator = idUsuarioRelator;
    }

    public int getIdUsuarioVistas() {
	return this.idUsuarioVistas;
    }

    public void setIdUsuarioVistas(int idUsuarioVistas) {
	this.idUsuarioVistas = idUsuarioVistas;
    }

    public List<ConselheirosJulgamento> getConselheirosJulgamentos() {
	return this.conselheirosJulgamentos;
    }

    public void setConselheirosJulgamentos(List<ConselheirosJulgamento> conselheirosJulgamentos) {
	this.conselheirosJulgamentos = conselheirosJulgamentos;
    }

    public ConselheirosJulgamento addConselheirosJulgamento(ConselheirosJulgamento conselheirosJulgamento) {
	getConselheirosJulgamentos().add(conselheirosJulgamento);
	conselheirosJulgamento.setJulgamentoProcesso(this);

	return conselheirosJulgamento;
    }

    public ConselheirosJulgamento removeConselheirosJulgamento(ConselheirosJulgamento conselheirosJulgamento) {
	getConselheirosJulgamentos().remove(conselheirosJulgamento);
	conselheirosJulgamento.setJulgamentoProcesso(null);

	return conselheirosJulgamento;
    }

    public TipoDesicaoJulgamento getTipoDesicaoJulgamento() {
	return this.tipoDesicaoJulgamento;
    }

    public void setTipoDesicaoJulgamento(TipoDesicaoJulgamento tipoDesicaoJulgamento) {
	this.tipoDesicaoJulgamento = tipoDesicaoJulgamento;
    }

    public Processo getProcesso() {
	return this.processo;
    }

    public void setProcesso(Processo processo) {
	this.processo = processo;
    }

    public TipoJulgamentoAnalise getTipoJulgamentoAnalise() {
	return tipoJulgamentoAnalise;
    }

    public void setTipoJulgamentoAnalise(TipoJulgamentoAnalise tipoJulgamentoAnalise) {
	this.tipoJulgamentoAnalise = tipoJulgamentoAnalise;
    }

    public ReparacaoDireitoJulgamento getReparacaoDireitoJulgamentos() {
        return reparacaoDireitoJulgamentos;
    }

    public ReparacaoEconomicaJulgamento getReparacaoEconomicaJulgamentos() {
        return reparacaoEconomicaJulgamentos;
    }

    public ReparacaoMoralJulgamento getReparacaoMoralJulgamentos() {
        return reparacaoMoralJulgamentos;
    }

    public void setReparacaoDireitoJulgamentos(ReparacaoDireitoJulgamento reparacaoDireitoJulgamentos) {
        this.reparacaoDireitoJulgamentos = reparacaoDireitoJulgamentos;
    }

    public void setReparacaoEconomicaJulgamentos(ReparacaoEconomicaJulgamento reparacaoEconomicaJulgamentos) {
        this.reparacaoEconomicaJulgamentos = reparacaoEconomicaJulgamentos;
    }

    public void setReparacaoMoralJulgamentos(ReparacaoMoralJulgamento reparacaoMoralJulgamentos) {
        this.reparacaoMoralJulgamentos = reparacaoMoralJulgamentos;
    }

}