package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reparacao_moral_analise database table.
 * 
 */
@Entity
@Table(name="reparacao_moral_analise")
@NamedQuery(name="ReparacaoMoralAnalise.findAll", query="SELECT r FROM ReparacaoMoralAnalise r")
public class ReparacaoMoralAnalise implements Serializable {
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

	@Column(name="DECLARACAO_RETIFICACAO")
	private String declaracaoRetificacao;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	@Column(name="OUTRAS_REPARACOES")
	private String outrasReparacoes;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@ManyToOne
	@JoinColumn(name="ID_RECOMENDACAO")
	private RecomendacaoAnalise recomendacaoAnalise;

	public ReparacaoMoralAnalise() {
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

	public String getDeclaracaoRetificacao() {
		return this.declaracaoRetificacao;
	}

	public void setDeclaracaoRetificacao(String declaracaoRetificacao) {
		this.declaracaoRetificacao = declaracaoRetificacao;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getOutrasReparacoes() {
		return this.outrasReparacoes;
	}

	public void setOutrasReparacoes(String outrasReparacoes) {
		this.outrasReparacoes = outrasReparacoes;
	}

	public RecomendacaoAnalise getRecomendacaoAnalise() {
		return this.recomendacaoAnalise;
	}

	public void setRecomendacaoAnalise(RecomendacaoAnalise recomendacaoAnalise) {
		this.recomendacaoAnalise = recomendacaoAnalise;
	}

}