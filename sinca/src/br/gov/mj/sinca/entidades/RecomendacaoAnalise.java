package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the recomendacao_analise database table.
 * 
 */
@Entity
@Table(name="recomendacao_analise")
@NamedQuery(name="RecomendacaoAnalise.findAll", query="SELECT r FROM RecomendacaoAnalise r")
public class RecomendacaoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECOMENDACAO")
	private Long idRecomendacao;

	@Column(name="BOL_HOUVE_REPARACAO")
	private byte bolHouveReparacao;

	@Column(name="BOL_HOVE_DILIGENCIA")
	private BigInteger bolHoveDiligencia;

	@Column(name="BOL_REPARACAO_ECONOMICA")
	private BigInteger bolReparacaoEconomica;

	@Column(name="BOL_REPARACAO_MORAL")
	private BigInteger bolReparacaoMoral;

	@Column(name="BOL_RESTITUICAO_DIREITOS")
	private BigInteger bolRestituicaoDireitos;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_MINITUTA")
	private Date dataMinituta;

	@Column(name="DISPOSITIVO_RECOMENDACAO")
	private String dispositivoRecomendacao;

	//bi-directional many-to-one association to TipoAnaliseJulgamento
	@ManyToOne
	@JoinColumn(name="ID_TIPO_JULGAMENTO")
	private TipoAnaliseJulgamento tipoAnaliseJulgamento;

	@ManyToOne
	@JoinColumn(name="ID_ANALISE_PROCESSO")
	private AnaliseProcesso analiseProcesso;

	@OneToMany(mappedBy="recomendacaoAnalise")
	private List<RecomendacaoDiligencia> recomendacaoDiligencias;

	@OneToMany(mappedBy="recomendacaoAnalise")
	private List<ReparacaoDireitoAnalise> reparacaoDireitoAnalises;

	@OneToMany(mappedBy="recomendacaoAnalise")
	private List<ReparacaoEconomicaAnalise> reparacaoEconomicaAnalises;

	@OneToMany(mappedBy="recomendacaoAnalise")
	private List<ReparacaoMoralAnalise> reparacaoMoralAnalises;

	public RecomendacaoAnalise() {
	}

	public Long getIdRecomendacao() {
		return this.idRecomendacao;
	}

	public void setIdRecomendacao(Long idRecomendacao) {
		this.idRecomendacao = idRecomendacao;
	}

	public byte getBolHouveReparacao() {
		return this.bolHouveReparacao;
	}

	public void setBolHouveReparacao(byte bolHouveReparacao) {
		this.bolHouveReparacao = bolHouveReparacao;
	}

	public BigInteger getBolHoveDiligencia() {
		return this.bolHoveDiligencia;
	}

	public void setBolHoveDiligencia(BigInteger bolHoveDiligencia) {
		this.bolHoveDiligencia = bolHoveDiligencia;
	}

	public BigInteger getBolReparacaoEconomica() {
		return this.bolReparacaoEconomica;
	}

	public void setBolReparacaoEconomica(BigInteger bolReparacaoEconomica) {
		this.bolReparacaoEconomica = bolReparacaoEconomica;
	}

	public BigInteger getBolReparacaoMoral() {
		return this.bolReparacaoMoral;
	}

	public void setBolReparacaoMoral(BigInteger bolReparacaoMoral) {
		this.bolReparacaoMoral = bolReparacaoMoral;
	}

	public BigInteger getBolRestituicaoDireitos() {
		return this.bolRestituicaoDireitos;
	}

	public void setBolRestituicaoDireitos(BigInteger bolRestituicaoDireitos) {
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

	public List<ReparacaoDireitoAnalise> getReparacaoDireitoAnalises() {
		return this.reparacaoDireitoAnalises;
	}

	public void setReparacaoDireitoAnalises(List<ReparacaoDireitoAnalise> reparacaoDireitoAnalises) {
		this.reparacaoDireitoAnalises = reparacaoDireitoAnalises;
	}

	public ReparacaoDireitoAnalise addReparacaoDireitoAnalis(ReparacaoDireitoAnalise reparacaoDireitoAnalis) {
		getReparacaoDireitoAnalises().add(reparacaoDireitoAnalis);
		reparacaoDireitoAnalis.setRecomendacaoAnalise(this);

		return reparacaoDireitoAnalis;
	}

	public ReparacaoDireitoAnalise removeReparacaoDireitoAnalis(ReparacaoDireitoAnalise reparacaoDireitoAnalis) {
		getReparacaoDireitoAnalises().remove(reparacaoDireitoAnalis);
		reparacaoDireitoAnalis.setRecomendacaoAnalise(null);

		return reparacaoDireitoAnalis;
	}

	public List<ReparacaoEconomicaAnalise> getReparacaoEconomicaAnalises() {
		return this.reparacaoEconomicaAnalises;
	}

	public void setReparacaoEconomicaAnalises(List<ReparacaoEconomicaAnalise> reparacaoEconomicaAnalises) {
		this.reparacaoEconomicaAnalises = reparacaoEconomicaAnalises;
	}

	public ReparacaoEconomicaAnalise addReparacaoEconomicaAnalis(ReparacaoEconomicaAnalise reparacaoEconomicaAnalis) {
		getReparacaoEconomicaAnalises().add(reparacaoEconomicaAnalis);
		reparacaoEconomicaAnalis.setRecomendacaoAnalise(this);

		return reparacaoEconomicaAnalis;
	}

	public ReparacaoEconomicaAnalise removeReparacaoEconomicaAnalis(ReparacaoEconomicaAnalise reparacaoEconomicaAnalis) {
		getReparacaoEconomicaAnalises().remove(reparacaoEconomicaAnalis);
		reparacaoEconomicaAnalis.setRecomendacaoAnalise(null);

		return reparacaoEconomicaAnalis;
	}

	public List<ReparacaoMoralAnalise> getReparacaoMoralAnalises() {
		return this.reparacaoMoralAnalises;
	}

	public void setReparacaoMoralAnalises(List<ReparacaoMoralAnalise> reparacaoMoralAnalises) {
		this.reparacaoMoralAnalises = reparacaoMoralAnalises;
	}

	public ReparacaoMoralAnalise addReparacaoMoralAnalis(ReparacaoMoralAnalise reparacaoMoralAnalis) {
		getReparacaoMoralAnalises().add(reparacaoMoralAnalis);
		reparacaoMoralAnalis.setRecomendacaoAnalise(this);

		return reparacaoMoralAnalis;
	}

	public ReparacaoMoralAnalise removeReparacaoMoralAnalis(ReparacaoMoralAnalise reparacaoMoralAnalis) {
		getReparacaoMoralAnalises().remove(reparacaoMoralAnalis);
		reparacaoMoralAnalis.setRecomendacaoAnalise(null);

		return reparacaoMoralAnalis;
	}

}