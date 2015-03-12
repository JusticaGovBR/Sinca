package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reparacao_direito_analise database table.
 * 
 */
@Entity
@Table(name="reparacao_direito_analise")
@NamedQuery(name="ReparacaoDireitoAnalise.findAll", query="SELECT r FROM ReparacaoDireitoAnalise r")
public class ReparacaoDireitoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_REPARACAO")
	private Long idReparacao;

	private String complemento;

	@Column(name="COMPLEMENTO_INSTITUICAO")
	private String complementoInstituicao;

	private String curso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	private String instituicao;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@ManyToOne
	@JoinColumn(name="ID_RECOMENDACAO")
	private RecomendacaoAnalise recomendacaoAnalise;

	//bi-directional many-to-one association to TipoRestituicaoDireito
	@ManyToOne
	@JoinColumn(name="COD_TIPO_RESTITUICAO")
	private TipoRestituicaoDireito tipoRestituicaoDireito;

	public ReparacaoDireitoAnalise() {
	}

	public Long getIdReparacao() {
		return this.idReparacao;
	}

	public void setIdReparacao(Long idReparacao) {
		this.idReparacao = idReparacao;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplementoInstituicao() {
		return this.complementoInstituicao;
	}

	public void setComplementoInstituicao(String complementoInstituicao) {
		this.complementoInstituicao = complementoInstituicao;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
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

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public RecomendacaoAnalise getRecomendacaoAnalise() {
		return this.recomendacaoAnalise;
	}

	public void setRecomendacaoAnalise(RecomendacaoAnalise recomendacaoAnalise) {
		this.recomendacaoAnalise = recomendacaoAnalise;
	}

	public TipoRestituicaoDireito getTipoRestituicaoDireito() {
		return this.tipoRestituicaoDireito;
	}

	public void setTipoRestituicaoDireito(TipoRestituicaoDireito tipoRestituicaoDireito) {
		this.tipoRestituicaoDireito = tipoRestituicaoDireito;
	}

}