package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the analise_processo database table.
 * 
 */
@Entity
@Table(name="analise_processo")
@NamedQuery(name="AnaliseProcesso.findAll", query="SELECT a FROM AnaliseProcesso a")
public class AnaliseProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANALISE")
	private Long idAnalise;

	@Column(name="ALTERACAO_DILIGENCIA")
	private byte alteracaoDiligencia;

	@Column(name="ALTERACAO_PEDITO")
	private byte alteracaoPedito;

	@Column(name="ALTERACAO_PROBATORIA")
	private byte alteracaoProbatoria;

	@Column(name="ALTERACAO_PROCESSOS_RELACIONADOS")
	private byte alteracaoProcessosRelacionados;

	private String complemento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ALTERACAO")
	private Date dataAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRIMEIRO_PROTOCOLO")
	private Date dataPrimeiroProtocolo;

	@Column(name="DESC_COMPLEMENTO_PEDIDO")
	private String descComplementoPedido;

	@Column(name="DESC_TIPO_BENEF_ANT")
	private String descTipoBenefAnt;

	@Column(name="ID_PROCESSO")
	private Processo processo;

	private String observacoes;

	@Column(name="OUTROS_PEDIDO")
	private byte outrosPedido;

	@Column(name="REQ_ANISTIADO")
	private byte reqAnistiado;

	@Column(name="TIPO_ANISTIA")
	private byte tipoAnistia;

	@Column(name="TIPO_ANISTIANDO")
	private byte tipoAnistiando;
	
	@Column(name="DESC_RELATO_REQUERENTE")
	private String descRelatoRequerente;

	@Column(name="DESC_TESTEMUNHAS")
	private String descTestemunhas;

	@Column(name="DESC_LOCAIS_PERSEGUICOES")
	private String descLocaisPerseguicoes;

	@Column(name="DESC_OUTRAS_FONTES")
	private String descOutrasFontes;
	
	@Column(name="DESC_LITERATURA_ASSUNTO")
	private String descLiteraturaAssunto;

	@Column(name="DESC_PESSOAS_ENVOLVIDAS")
	private String descPessoasEnvolvidas;

	@OneToMany(mappedBy="analiseProcesso")
	private List<PerseguicaoAnalise> perseguicaoAnalises;

	@OneToMany(mappedBy="analiseProcesso")
	private List<ReparacaoAnalise> reparacaoAnalises;

	public AnaliseProcesso() {
	}

	public Long getIdAnalise() {
		return this.idAnalise;
	}

	public void setIdAnalise(Long idAnalise) {
		this.idAnalise = idAnalise;
	}

	public byte getAlteracaoDiligencia() {
		return this.alteracaoDiligencia;
	}

	public void setAlteracaoDiligencia(byte alteracaoDiligencia) {
		this.alteracaoDiligencia = alteracaoDiligencia;
	}

	public byte getAlteracaoPedito() {
		return this.alteracaoPedito;
	}

	public void setAlteracaoPedito(byte alteracaoPedito) {
		this.alteracaoPedito = alteracaoPedito;
	}

	public byte getAlteracaoProbatoria() {
		return this.alteracaoProbatoria;
	}

	public void setAlteracaoProbatoria(byte alteracaoProbatoria) {
		this.alteracaoProbatoria = alteracaoProbatoria;
	}

	public byte getAlteracaoProcessosRelacionados() {
		return this.alteracaoProcessosRelacionados;
	}

	public void setAlteracaoProcessosRelacionados(byte alteracaoProcessosRelacionados) {
		this.alteracaoProcessosRelacionados = alteracaoProcessosRelacionados;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataAlteracao() {
		return this.dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	public Date getDataPrimeiroProtocolo() {
		return this.dataPrimeiroProtocolo;
	}

	public void setDataPrimeiroProtocolo(Date dataPrimeiroProtocolo) {
		this.dataPrimeiroProtocolo = dataPrimeiroProtocolo;
	}

	public String getDescComplementoPedido() {
		return this.descComplementoPedido;
	}

	public void setDescComplementoPedido(String descComplementoPedido) {
		this.descComplementoPedido = descComplementoPedido;
	}

	public String getDescTipoBenefAnt() {
		return this.descTipoBenefAnt;
	}

	public void setDescTipoBenefAnt(String descTipoBenefAnt) {
		this.descTipoBenefAnt = descTipoBenefAnt;
	}

	public Processo getProcesso() {
	    return processo;
	}

	public void setProcesso(Processo processo) {
	    this.processo = processo;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public byte getOutrosPedido() {
		return this.outrosPedido;
	}

	public void setOutrosPedido(byte outrosPedido) {
		this.outrosPedido = outrosPedido;
	}

	public byte getReqAnistiado() {
		return this.reqAnistiado;
	}

	public void setReqAnistiado(byte reqAnistiado) {
		this.reqAnistiado = reqAnistiado;
	}

	public byte getTipoAnistia() {
		return this.tipoAnistia;
	}

	public void setTipoAnistia(byte tipoAnistia) {
		this.tipoAnistia = tipoAnistia;
	}

	public byte getTipoAnistiando() {
		return this.tipoAnistiando;
	}

	public void setTipoAnistiando(byte tipoAnistiando) {
		this.tipoAnistiando = tipoAnistiando;
	}

	public List<PerseguicaoAnalise> getPerseguicaoAnalises() {
		return this.perseguicaoAnalises;
	}

	public void setPerseguicaoAnalises(List<PerseguicaoAnalise> perseguicaoAnalises) {
		this.perseguicaoAnalises = perseguicaoAnalises;
	}

	public PerseguicaoAnalise addPerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().add(perseguicaoAnalis);
		perseguicaoAnalis.setAnaliseProcesso(this);

		return perseguicaoAnalis;
	}

	public PerseguicaoAnalise removePerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().remove(perseguicaoAnalis);
		perseguicaoAnalis.setAnaliseProcesso(null);

		return perseguicaoAnalis;
	}

	public List<ReparacaoAnalise> getReparacaoAnalises() {
		return this.reparacaoAnalises;
	}

	public void setReparacaoAnalises(List<ReparacaoAnalise> reparacaoAnalises) {
		this.reparacaoAnalises = reparacaoAnalises;
	}

	public ReparacaoAnalise addReparacaoAnalis(ReparacaoAnalise reparacaoAnalis) {
		getReparacaoAnalises().add(reparacaoAnalis);
		reparacaoAnalis.setAnaliseProcesso(this);

		return reparacaoAnalis;
	}

	public ReparacaoAnalise removeReparacaoAnalis(ReparacaoAnalise reparacaoAnalis) {
		getReparacaoAnalises().remove(reparacaoAnalis);
		reparacaoAnalis.setAnaliseProcesso(null);

		return reparacaoAnalis;
	}

	public String getDescRelatoRequerente() {
	    return descRelatoRequerente;
	}

	public String getDescTestemunhas() {
	    return descTestemunhas;
	}

	public String getDescLocaisPerseguicoes() {
	    return descLocaisPerseguicoes;
	}

	public String getDescOutrasFontes() {
	    return descOutrasFontes;
	}

	public String getDescLiteraturaAssunto() {
	    return descLiteraturaAssunto;
	}

	public String getDescPessoasEnvolvidas() {
	    return descPessoasEnvolvidas;
	}

	public void setDescRelatoRequerente(String descRelatoRequerente) {
	    this.descRelatoRequerente = descRelatoRequerente;
	}

	public void setDescTestemunhas(String descTestemunhas) {
	    this.descTestemunhas = descTestemunhas;
	}

	public void setDescLocaisPerseguicoes(String descLocaisPerseguicoes) {
	    this.descLocaisPerseguicoes = descLocaisPerseguicoes;
	}

	public void setDescOutrasFontes(String descOutrasFontes) {
	    this.descOutrasFontes = descOutrasFontes;
	}

	public void setDescLiteraturaAssunto(String descLiteraturaAssunto) {
	    this.descLiteraturaAssunto = descLiteraturaAssunto;
	}

	public void setDescPessoasEnvolvidas(String descPessoasEnvolvidas) {
	    this.descPessoasEnvolvidas = descPessoasEnvolvidas;
	}

}