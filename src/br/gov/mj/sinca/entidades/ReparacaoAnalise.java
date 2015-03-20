package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reparacao_analise database table.
 * 
 */
@Entity
@Table(name="reparacao_analise")
@NamedQuery(name="ReparacaoAnalise.findAll", query="SELECT r FROM ReparacaoAnalise r")
public class ReparacaoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_REPARACAO")
	private int idReparacao;

	private String acao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;

	//bi-directional many-to-one association to AnaliseProcesso
	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso;

	public ReparacaoAnalise() {
	}

	public int getIdReparacao() {
		return this.idReparacao;
	}

	public void setIdReparacao(int idReparacao) {
		this.idReparacao = idReparacao;
	}

	public String getAcao() {
		return this.acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
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

	public AnaliseProcesso getAnaliseProcesso() {
		return this.analiseProcesso;
	}

	public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
		this.analiseProcesso = analiseProcesso;
	}

}