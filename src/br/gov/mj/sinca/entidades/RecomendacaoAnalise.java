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
 * The persistent class for the recomendacao_analise database table.
 * 
 */
@Entity
@Table(name = "recomendacao_analise")
@NamedQuery(name = "RecomendacaoAnalise.findAll", query = "SELECT r FROM RecomendacaoAnalise r")
public class RecomendacaoAnalise implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECOMENDACAO")
    private Long idRecomendacao;

    @Column(name = "BOL_HOUVE_REPARACAO")
    private Byte bolHouveReparacao;

    @Column(name = "BOL_HOVE_DILIGENCIA")
    private Byte bolHoveDiligencia;

    @Column(name = "BOL_REPARACAO_ECONOMICA")
    private Byte bolReparacaoEconomica;

    @Column(name = "BOL_REPARACAO_MORAL")
    private Byte bolReparacaoMoral;

    @Column(name = "BOL_RESTITUICAO_DIREITOS")
    private Byte bolRestituicaoDireitos;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_MINITUTA")
    private Date dataMinituta;

    @Column(name = "DISPOSITIVO_RECOMENDACAO")
    private String dispositivoRecomendacao;

    // bi-directional many-to-one association to TipoAnaliseJulgamento
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_JULGAMENTO")
    private TipoAnaliseJulgamento tipoAnaliseJulgamento;

    @ManyToOne
    @JoinColumn(name = "ID_ANALISE_PROCESSO")
    private AnaliseProcesso analiseProcesso;

    @OneToMany(mappedBy = "recomendacaoAnalise")
    private List<RecomendacaoDiligencia> recomendacaoDiligencias;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_ECONOMICA")
    private ReparacaoEconomicaAnalise reparacaoEconomicaAnalise;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_MORAL")
    private ReparacaoMoralAnalise reparacaoMoralAnalise;

    @OneToOne
    @JoinColumn(name = "ID_REPARACAO_DIREITO")
    private ReparacaoDireitoAnalise reparacaoDireitoAnalise;

    public RecomendacaoAnalise() {
    }

    public Long getIdRecomendacao() {
	return this.idRecomendacao;
    }

    public void setIdRecomendacao(Long idRecomendacao) {
	this.idRecomendacao = idRecomendacao;
    }

    public Byte getBolHouveReparacao() {
	return this.bolHouveReparacao;
    }

    public void setBolHouveReparacao(Byte bolHouveReparacao) {
	this.bolHouveReparacao = bolHouveReparacao;
    }

    public Byte getBolHoveDiligencia() {
	return this.bolHoveDiligencia;
    }

    public void setBolHoveDiligencia(Byte bolHoveDiligencia) {
	this.bolHoveDiligencia = bolHoveDiligencia;
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

    public Date getDataMinituta() {
	return this.dataMinituta;
    }

    public void setDataMinituta(Date dataMinituta) {
	this.dataMinituta = dataMinituta;
    }

    public String getDispositivoRecomendacao() {
	return this.dispositivoRecomendacao;
    }

    public void setDispositivoRecomendacao(String dispositivoRecomendacao) {
	this.dispositivoRecomendacao = dispositivoRecomendacao;
    }

    public TipoAnaliseJulgamento getTipoAnaliseJulgamento() {
	return this.tipoAnaliseJulgamento;
    }

    public void setTipoAnaliseJulgamento(TipoAnaliseJulgamento tipoAnaliseJulgamento) {
	this.tipoAnaliseJulgamento = tipoAnaliseJulgamento;
    }

    public AnaliseProcesso getAnaliseProcesso() {
	return this.analiseProcesso;
    }

    public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
	this.analiseProcesso = analiseProcesso;
    }

    public List<RecomendacaoDiligencia> getRecomendacaoDiligencias() {
	return this.recomendacaoDiligencias;
    }

    public void setRecomendacaoDiligencias(List<RecomendacaoDiligencia> recomendacaoDiligencias) {
	this.recomendacaoDiligencias = recomendacaoDiligencias;
    }

    public RecomendacaoDiligencia addRecomendacaoDiligencia(RecomendacaoDiligencia recomendacaoDiligencia) {
	getRecomendacaoDiligencias().add(recomendacaoDiligencia);
	recomendacaoDiligencia.setRecomendacaoAnalise(this);

	return recomendacaoDiligencia;
    }

    public RecomendacaoDiligencia removeRecomendacaoDiligencia(RecomendacaoDiligencia recomendacaoDiligencia) {
	getRecomendacaoDiligencias().remove(recomendacaoDiligencia);
	recomendacaoDiligencia.setRecomendacaoAnalise(null);

	return recomendacaoDiligencia;
    }

    public ReparacaoEconomicaAnalise getReparacaoEconomicaAnalise() {
        return reparacaoEconomicaAnalise;
    }

    public ReparacaoMoralAnalise getReparacaoMoralAnalise() {
        return reparacaoMoralAnalise;
    }

    public ReparacaoDireitoAnalise getReparacaoDireitoAnalise() {
        return reparacaoDireitoAnalise;
    }

    public void setReparacaoEconomicaAnalise(ReparacaoEconomicaAnalise reparacaoEconomicaAnalise) {
        this.reparacaoEconomicaAnalise = reparacaoEconomicaAnalise;
    }

    public void setReparacaoMoralAnalise(ReparacaoMoralAnalise reparacaoMoralAnalise) {
        this.reparacaoMoralAnalise = reparacaoMoralAnalise;
    }

    public void setReparacaoDireitoAnalise(ReparacaoDireitoAnalise reparacaoDireitoAnalise) {
        this.reparacaoDireitoAnalise = reparacaoDireitoAnalise;
    }

        
}