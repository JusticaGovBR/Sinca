package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the hitorico_requerimento database table.
 * 
 */
@Entity
@Table(name="hitorico_requerimento")
@NamedQuery(name="HitoricoRequerimento.findAll", query="SELECT h FROM HitoricoRequerimento h")
public class HitoricoRequerimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HISTORICO")
	private Long idHistorico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CONCLUSAO")
	private Date dataConclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_RECEBIMENTO")
	private Date dataRecebimento;

	@Column(name="ID_TIPO_JULGAMENTO_PREPOSTO")
	private int idTipoJulgamentoPreposto;

	@Column(name="TOTAL_ANALISE")
	private String totalAnalise;


	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso1;


	@ManyToOne
	@JoinColumn(name="ID_ANALISE_COPIA")
	private AnaliseProcesso analiseProcesso2;

	@ManyToOne
	@JoinColumn(name="ID_JULGAMENTO_PROCESSO")
	private JulgamentoProcesso julgamentoProcesso;

	@ManyToOne
	@JoinColumn(name="ID_FINALIZACAO_PROCESSO")
	private FinalizacaoProcesso finalizacaoProcesso;

	@ManyToOne
	@JoinColumn(name="ID_USUARIO_ANALISTA")
	private Usuario usuarioAnalista;


	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

	public HitoricoRequerimento() {
	}

	public Long getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
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

	public Date getDataConclusao() {
		return this.dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public int getIdTipoJulgamentoPreposto() {
		return this.idTipoJulgamentoPreposto;
	}

	public void setIdTipoJulgamentoPreposto(int idTipoJulgamentoPreposto) {
		this.idTipoJulgamentoPreposto = idTipoJulgamentoPreposto;
	}

	public String getTotalAnalise() {
		return this.totalAnalise;
	}

	public void setTotalAnalise(String totalAnalise) {
		this.totalAnalise = totalAnalise;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public AnaliseProcesso getAnaliseProcesso1() {
		return this.analiseProcesso1;
	}

	public void setAnaliseProcesso1(AnaliseProcesso analiseProcesso1) {
		this.analiseProcesso1 = analiseProcesso1;
	}

	public AnaliseProcesso getAnaliseProcesso2() {
		return this.analiseProcesso2;
	}

	public void setAnaliseProcesso2(AnaliseProcesso analiseProcesso2) {
		this.analiseProcesso2 = analiseProcesso2;
	}

	public JulgamentoProcesso getJulgamentoProcesso() {
		return this.julgamentoProcesso;
	}

	public void setJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
		this.julgamentoProcesso = julgamentoProcesso;
	}

	public FinalizacaoProcesso getFinalizacaoProcesso() {
		return this.finalizacaoProcesso;
	}

	public void setFinalizacaoProcesso(FinalizacaoProcesso finalizacaoProcesso) {
		this.finalizacaoProcesso = finalizacaoProcesso;
	}

	public Usuario getUsuarioAnalista() {
	    return usuarioAnalista;
	}

	public Usuario getUsuario() {
	    return usuario;
	}

	public void setUsuarioAnalista(Usuario usuarioAnalista) {
	    this.usuarioAnalista = usuarioAnalista;
	}

	public void setUsuario(Usuario usuario) {
	    this.usuario = usuario;
	}

}