package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the finalizacao_processo database table.
 * 
 */
@Entity
@Table(name="finalizacao_processo")
@NamedQuery(name="FinalizacaoProcesso.findAll", query="SELECT f FROM FinalizacaoProcesso f")
public class FinalizacaoProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FINALIZACAO_PROCESSO")
	private Long idFinalizacaoProcesso;

	private String complemento;

	@Column(name="COMPLEMENTO_COMUNICACAO")
	private String complementoComunicacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_AUTUACAO_RECURSO")
	private Date dataAutuacaoRecurso;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CERTIDAO")
	private Date dataCertidao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CIENCIA")
	private Date dataCiencia;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ELABORACAO_PORTARIA")
	private Date dataElaboracaoPortaria;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO")
	private Date dataEnvio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO_ARQUIVO")
	private Date dataEnvioArquivo;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO_AVISO")
	private Date dataEnvioAviso;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO_MINUTA_PORTARIA")
	private Date dataEnvioMinutaPortaria;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO_PUBLICAO")
	private Date dataEnvioPublicao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NOTA_TECNICA")
	private Date dataNotaTecnica;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PUBLICAO_DOU")
	private Date dataPublicaoDou;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RECURSO")
	private Date dataRecurso;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RETORNO_PORTARIA")
	private Date dataRetornoPortaria;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	@Column(name="NUMER_PORTARIA_DOU")
	private String numerPortariaDou;

	@Column(name="NUMERO_AR_EDITAL")
	private String numeroArEdital;

	@Column(name="NUMERO_AVISO")
	private String numeroAviso;

	//bi-directional many-to-one association to FinalizacaoNotificacao
	@OneToMany(mappedBy="finalizacaoProcesso")
	private List<FinalizacaoNotificacao> finalizacaoNotificacaos;

	//bi-directional many-to-one association to FinalizacaoOrgao
	@OneToMany(mappedBy="finalizacaoProcesso")
	private List<FinalizacaoOrgao> finalizacaoOrgaos;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	public FinalizacaoProcesso() {
	}

	public Long getIdFinalizacaoProcesso() {
		return this.idFinalizacaoProcesso;
	}

	public void setIdFinalizacaoProcesso(Long idFinalizacaoProcesso) {
		this.idFinalizacaoProcesso = idFinalizacaoProcesso;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplementoComunicacao() {
		return this.complementoComunicacao;
	}

	public void setComplementoComunicacao(String complementoComunicacao) {
		this.complementoComunicacao = complementoComunicacao;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataAutuacaoRecurso() {
		return this.dataAutuacaoRecurso;
	}

	public void setDataAutuacaoRecurso(Date dataAutuacaoRecurso) {
		this.dataAutuacaoRecurso = dataAutuacaoRecurso;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataCertidao() {
		return this.dataCertidao;
	}

	public void setDataCertidao(Date dataCertidao) {
		this.dataCertidao = dataCertidao;
	}

	public Date getDataCiencia() {
		return this.dataCiencia;
	}

	public void setDataCiencia(Date dataCiencia) {
		this.dataCiencia = dataCiencia;
	}

	public Date getDataElaboracaoPortaria() {
		return this.dataElaboracaoPortaria;
	}

	public void setDataElaboracaoPortaria(Date dataElaboracaoPortaria) {
		this.dataElaboracaoPortaria = dataElaboracaoPortaria;
	}

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataEnvioArquivo() {
		return this.dataEnvioArquivo;
	}

	public void setDataEnvioArquivo(Date dataEnvioArquivo) {
		this.dataEnvioArquivo = dataEnvioArquivo;
	}

	public Date getDataEnvioAviso() {
		return this.dataEnvioAviso;
	}

	public void setDataEnvioAviso(Date dataEnvioAviso) {
		this.dataEnvioAviso = dataEnvioAviso;
	}

	public Date getDataEnvioMinutaPortaria() {
		return this.dataEnvioMinutaPortaria;
	}

	public void setDataEnvioMinutaPortaria(Date dataEnvioMinutaPortaria) {
		this.dataEnvioMinutaPortaria = dataEnvioMinutaPortaria;
	}

	public Date getDataEnvioPublicao() {
		return this.dataEnvioPublicao;
	}

	public void setDataEnvioPublicao(Date dataEnvioPublicao) {
		this.dataEnvioPublicao = dataEnvioPublicao;
	}

	public Date getDataNotaTecnica() {
		return this.dataNotaTecnica;
	}

	public void setDataNotaTecnica(Date dataNotaTecnica) {
		this.dataNotaTecnica = dataNotaTecnica;
	}

	public Date getDataPublicaoDou() {
		return this.dataPublicaoDou;
	}

	public void setDataPublicaoDou(Date dataPublicaoDou) {
		this.dataPublicaoDou = dataPublicaoDou;
	}

	public Date getDataRecurso() {
		return this.dataRecurso;
	}

	public void setDataRecurso(Date dataRecurso) {
		this.dataRecurso = dataRecurso;
	}

	public Date getDataRetornoPortaria() {
		return this.dataRetornoPortaria;
	}

	public void setDataRetornoPortaria(Date dataRetornoPortaria) {
		this.dataRetornoPortaria = dataRetornoPortaria;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNumerPortariaDou() {
		return this.numerPortariaDou;
	}

	public void setNumerPortariaDou(String numerPortariaDou) {
		this.numerPortariaDou = numerPortariaDou;
	}

	public String getNumeroArEdital() {
		return this.numeroArEdital;
	}

	public void setNumeroArEdital(String numeroArEdital) {
		this.numeroArEdital = numeroArEdital;
	}

	public String getNumeroAviso() {
		return this.numeroAviso;
	}

	public void setNumeroAviso(String numeroAviso) {
		this.numeroAviso = numeroAviso;
	}

	public List<FinalizacaoNotificacao> getFinalizacaoNotificacaos() {
		return this.finalizacaoNotificacaos;
	}

	public void setFinalizacaoNotificacaos(List<FinalizacaoNotificacao> finalizacaoNotificacaos) {
		this.finalizacaoNotificacaos = finalizacaoNotificacaos;
	}

	public FinalizacaoNotificacao addFinalizacaoNotificacao(FinalizacaoNotificacao finalizacaoNotificacao) {
		getFinalizacaoNotificacaos().add(finalizacaoNotificacao);
		finalizacaoNotificacao.setFinalizacaoProcesso(this);

		return finalizacaoNotificacao;
	}

	public FinalizacaoNotificacao removeFinalizacaoNotificacao(FinalizacaoNotificacao finalizacaoNotificacao) {
		getFinalizacaoNotificacaos().remove(finalizacaoNotificacao);
		finalizacaoNotificacao.setFinalizacaoProcesso(null);

		return finalizacaoNotificacao;
	}

	public List<FinalizacaoOrgao> getFinalizacaoOrgaos() {
		return this.finalizacaoOrgaos;
	}

	public void setFinalizacaoOrgaos(List<FinalizacaoOrgao> finalizacaoOrgaos) {
		this.finalizacaoOrgaos = finalizacaoOrgaos;
	}

	public FinalizacaoOrgao addFinalizacaoOrgao(FinalizacaoOrgao finalizacaoOrgao) {
		getFinalizacaoOrgaos().add(finalizacaoOrgao);
		finalizacaoOrgao.setFinalizacaoProcesso(this);

		return finalizacaoOrgao;
	}

	public FinalizacaoOrgao removeFinalizacaoOrgao(FinalizacaoOrgao finalizacaoOrgao) {
		getFinalizacaoOrgaos().remove(finalizacaoOrgao);
		finalizacaoOrgao.setFinalizacaoProcesso(null);

		return finalizacaoOrgao;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

}