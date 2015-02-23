package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the atos_excecao_analise database table.
 * 
 */
@Entity
@Table(name="atos_excecao_analise")
@NamedQuery(name="AtosExcecaoAnalise.findAll", query="SELECT a FROM AtosExcecaoAnalise a")
public class AtosExcecaoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ATOS_EXCECAO")
	private String idAtosExcecao;

	private String complemento;

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
	private int idUsuario;

	@Column(name="LOCAL_PERSEGUICAO")
	private String localPerseguicao;

	private String paginas;

	//bi-directional many-to-one association to AnaliseProcesso
	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso;

	//bi-directional many-to-one association to TipoPerseguicao
	@ManyToOne
	@JoinColumn(name="COD_TIPO_PERSEGUICAO")
	private TipoPerseguicao tipoPerseguicao;

	public AtosExcecaoAnalise() {
	}

	public String getIdAtosExcecao() {
		return this.idAtosExcecao;
	}

	public void setIdAtosExcecao(String idAtosExcecao) {
		this.idAtosExcecao = idAtosExcecao;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLocalPerseguicao() {
		return this.localPerseguicao;
	}

	public void setLocalPerseguicao(String localPerseguicao) {
		this.localPerseguicao = localPerseguicao;
	}

	public String getPaginas() {
		return this.paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
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