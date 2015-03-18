package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the reparacao_economica_analise database table.
 * 
 */
@Entity
@Table(name="reparacao_economica_analise")
@NamedQuery(name="ReparacaoEconomicaAnalise.findAll", query="SELECT r FROM ReparacaoEconomicaAnalise r")
public class ReparacaoEconomicaAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_REPARACAO")
	private Long idReparacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM_PU")
	private Date dataFimPu;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINALIZACAO")
	private Date dataFinalizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO_PU")
	private Date dataInicioPu;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PREVISTA_SESSAO")
	private Date dataPrevistaSessao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRIMEIRO_PROTOCOLO")
	private Date dataPrimeiroProtocolo;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RECEBIMENTO")
	private Date dataRecebimento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_REOTRAVIDADE")
	private Date dataReotravidade;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	@Column(name="ID_USUARIO_ANALISTA")
	private BigInteger idUsuarioAnalista;

	@Column(name="VALOR_PMPC")
	private String valorPmpc;

	@Column(name="VALOR_PU")
	private String valorPu;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@ManyToOne
	@JoinColumn(name="ID_RECOMENDACAO")
	private RecomendacaoAnalise recomendacaoAnalise;

	public ReparacaoEconomicaAnalise() {
	}

	public Long getIdReparacao() {
		return this.idReparacao;
	}

	public void setIdReparacao(Long idReparacao) {
		this.idReparacao = idReparacao;
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

	public Date getDataFimPu() {
		return this.dataFimPu;
	}

	public void setDataFimPu(Date dataFimPu) {
		this.dataFimPu = dataFimPu;
	}

	public Date getDataFinalizacao() {
		return this.dataFinalizacao;
	}

	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Date getDataInicioPu() {
		return this.dataInicioPu;
	}

	public void setDataInicioPu(Date dataInicioPu) {
		this.dataInicioPu = dataInicioPu;
	}

	public Date getDataPrevistaSessao() {
		return this.dataPrevistaSessao;
	}

	public void setDataPrevistaSessao(Date dataPrevistaSessao) {
		this.dataPrevistaSessao = dataPrevistaSessao;
	}

	public Date getDataPrimeiroProtocolo() {
		return this.dataPrimeiroProtocolo;
	}

	public void setDataPrimeiroProtocolo(Date dataPrimeiroProtocolo) {
		this.dataPrimeiroProtocolo = dataPrimeiroProtocolo;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataReotravidade() {
		return this.dataReotravidade;
	}

	public void setDataReotravidade(Date dataReotravidade) {
		this.dataReotravidade = dataReotravidade;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigInteger getIdUsuarioAnalista() {
		return this.idUsuarioAnalista;
	}

	public void setIdUsuarioAnalista(BigInteger idUsuarioAnalista) {
		this.idUsuarioAnalista = idUsuarioAnalista;
	}

	public String getValorPmpc() {
		return this.valorPmpc;
	}

	public void setValorPmpc(String valorPmpc) {
		this.valorPmpc = valorPmpc;
	}

	public String getValorPu() {
		return this.valorPu;
	}

	public void setValorPu(String valorPu) {
		this.valorPu = valorPu;
	}

	public RecomendacaoAnalise getRecomendacaoAnalise() {
		return this.recomendacaoAnalise;
	}

	public void setRecomendacaoAnalise(RecomendacaoAnalise recomendacaoAnalise) {
		this.recomendacaoAnalise = recomendacaoAnalise;
	}

}