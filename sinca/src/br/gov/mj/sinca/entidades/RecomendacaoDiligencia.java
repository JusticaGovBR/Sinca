package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the recomendacao_diligencia database table.
 * 
 */
@Entity
@Table(name="recomendacao_diligencia")
@NamedQuery(name="RecomendacaoDiligencia.findAll", query="SELECT r FROM RecomendacaoDiligencia r")
public class RecomendacaoDiligencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECOMENDACAO_DILIGENCIA")
	private Long idRecomendacaoDiligencia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	//bi-directional many-to-one association to Diligencia
	@ManyToOne
	@JoinColumn(name="ID_DILIGENCIA")
	private Diligencia diligencia;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@ManyToOne
	@JoinColumn(name="ID_RECOMENDACAO")
	private RecomendacaoAnalise recomendacaoAnalise;

	public RecomendacaoDiligencia() {
	}

	public Long getIdRecomendacaoDiligencia() {
		return this.idRecomendacaoDiligencia;
	}

	public void setIdRecomendacaoDiligencia(Long idRecomendacaoDiligencia) {
		this.idRecomendacaoDiligencia = idRecomendacaoDiligencia;
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

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Diligencia getDiligencia() {
		return this.diligencia;
	}

	public void setDiligencia(Diligencia diligencia) {
		this.diligencia = diligencia;
	}

	public RecomendacaoAnalise getRecomendacaoAnalise() {
		return this.recomendacaoAnalise;
	}

	public void setRecomendacaoAnalise(RecomendacaoAnalise recomendacaoAnalise) {
		this.recomendacaoAnalise = recomendacaoAnalise;
	}

}