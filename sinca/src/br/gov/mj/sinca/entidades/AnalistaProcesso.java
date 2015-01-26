package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the analista_processo database table.
 * 
 */
@Entity
@Table(name="analista_processo")
@NamedQuery(name="AnalistaProcesso.findAll", query="SELECT a FROM AnalistaProcesso a")
public class AnalistaProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ANALISTA_PROCESSO")
	private Long idAnalistaProcesso;

	private BigInteger acoes;

	private String complemento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CONLUSAO")
	private Date dataConlusao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RECEBIMENTO")
	private Date dataRecebimento;

	@Column(name="ID_SUB_STATUS_PROCESSO")
	private int idSubStatusProcesso;

	//bi-directional many-to-one association to AnaliseProcesso
	@ManyToOne
	@JoinColumn(name="ID_ANALISE")
	private AnaliseProcesso analiseProcesso;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	public AnalistaProcesso() {
	}

	public Long getIdAnalistaProcesso() {
		return this.idAnalistaProcesso;
	}

	public void setIdAnalistaProcesso(Long idAnalistaProcesso) {
		this.idAnalistaProcesso = idAnalistaProcesso;
	}

	public BigInteger getAcoes() {
		return this.acoes;
	}

	public void setAcoes(BigInteger acoes) {
		this.acoes = acoes;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataConlusao() {
		return this.dataConlusao;
	}

	public void setDataConlusao(Date dataConlusao) {
		this.dataConlusao = dataConlusao;
	}

	public Date getDataRecebimento() {
		return this.dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public int getIdSubStatusProcesso() {
		return this.idSubStatusProcesso;
	}

	public void setIdSubStatusProcesso(int idSubStatusProcesso) {
		this.idSubStatusProcesso = idSubStatusProcesso;
	}

	public AnaliseProcesso getAnaliseProcesso() {
		return this.analiseProcesso;
	}

	public void setAnaliseProcesso(AnaliseProcesso analiseProcesso) {
		this.analiseProcesso = analiseProcesso;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}