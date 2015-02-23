package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the provas_analise database table.
 * 
 */
@Entity
@Table(name="provas_analise")
@NamedQuery(name="ProvasAnalise.findAll", query="SELECT p FROM ProvasAnalise p")
public class ProvasAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PROVAS_ANALISE")
	private String idProvasAnalise;

	private String conteudo;

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

	@Column(name="FOLHA_PROCESSO")
	private String folhaProcesso;

	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String prova;

	private String tipo;

	//bi-directional many-to-one association to AnaliseProcesso
	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso;

	public ProvasAnalise() {
	}

	public String getIdProvasAnalise() {
		return this.idProvasAnalise;
	}

	public void setIdProvasAnalise(String idProvasAnalise) {
		this.idProvasAnalise = idProvasAnalise;
	}

	public String getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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

	public String getFolhaProcesso() {
		return this.folhaProcesso;
	}

	public void setFolhaProcesso(String folhaProcesso) {
		this.folhaProcesso = folhaProcesso;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getProva() {
		return this.prova;
	}

	public void setProva(String prova) {
		this.prova = prova;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public AnaliseProcesso getAnaliseProcesso() {
		return this.analiseProcesso;
	}

	public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
		this.analiseProcesso = analiseProcesso;
	}

}