package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reparacao_direito_julgamento database table.
 * 
 */
@Entity
@Table(name="reparacao_direito_julgamento")
@NamedQuery(name="ReparacaoDireitoJulgamento.findAll", query="SELECT r FROM ReparacaoDireitoJulgamento r")
public class ReparacaoDireitoJulgamento implements Serializable {
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
	private int idUsuario;

	private String instituicao;

	@ManyToOne
	@JoinColumn(name="ID_JULGAMENTO_PROCESSO")
	private JulgamentoProcesso julgamentoProcesso;

	@ManyToOne
	@JoinColumn(name="COD_TIPO_RESTITUICAO")
	private TipoRestituicaoDireito tipoRestituicaoDireito;

	public ReparacaoDireitoJulgamento() {
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

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public JulgamentoProcesso getJulgamentoProcesso() {
		return this.julgamentoProcesso;
	}

	public void setJulgamentoProcesso(JulgamentoProcesso julgamentoProcesso) {
		this.julgamentoProcesso = julgamentoProcesso;
	}

	public TipoRestituicaoDireito getTipoRestituicaoDireito() {
		return this.tipoRestituicaoDireito;
	}

	public void setTipoRestituicaoDireito(TipoRestituicaoDireito tipoRestituicaoDireito) {
		this.tipoRestituicaoDireito = tipoRestituicaoDireito;
	}

}