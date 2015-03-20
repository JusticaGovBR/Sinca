package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the perseguicao_analise database table.
 * 
 */
@Entity
@Table(name="perseguicao_analise")
@NamedQuery(name="PerseguicaoAnalise.findAll", query="SELECT p FROM PerseguicaoAnalise p")
public class PerseguicaoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERSEGUICAO")
	private String idPerseguicao;

	private String complemento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;

	private String descricao;

	private String local;

	@Column(name="PAGINA_PROCESSO")
	private String paginaProcesso;

	//bi-directional many-to-one association to AnaliseProcesso
	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso;

	//bi-directional many-to-one association to TipoPerseguicao
	@ManyToOne
	@JoinColumn(name="COD_TIPO")
	private TipoPerseguicao tipoPerseguicao;

	public PerseguicaoAnalise() {
	}

	public String getIdPerseguicao() {
		return this.idPerseguicao;
	}

	public void setIdPerseguicao(String idPerseguicao) {
		this.idPerseguicao = idPerseguicao;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getPaginaProcesso() {
		return this.paginaProcesso;
	}

	public void setPaginaProcesso(String paginaProcesso) {
		this.paginaProcesso = paginaProcesso;
	}

	public AnaliseProcesso getAnaliseProcesso() {
		return this.analiseProcesso;
	}

	public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
		this.analiseProcesso = analiseProcesso;
	}

	public TipoPerseguicao getTipoPerseguicao() {
		return this.tipoPerseguicao;
	}

	public void setTipoPerseguicao(TipoPerseguicao tipoPerseguicao) {
		this.tipoPerseguicao = tipoPerseguicao;
	}

}