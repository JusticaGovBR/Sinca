package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the historico_tabela database table.
 * 
 */
@Entity
@Table(name="historico_tabela")
@NamedQuery(name="HistoricoTabela.findAll", query="SELECT h FROM HistoricoTabela h")
public class HistoricoTabela implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HISTORICO")
	private Long idHistorico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Column(name="ID_CHAVE_TABELA")
	private BigInteger idChaveTabela;

	@Column(name="ID_USUARIO")
	private BigInteger idUsuario;

	@Column(name="NOME_COLUNA")
	private String nomeColuna;

	@Column(name="VALOR_ANTERIOR")
	private String valorAnterior;

	@Column(name="VALOR_ATUAL")
	private String valorAtual;

	//bi-directional many-to-one association to TabelaHistorico
	@ManyToOne
	@JoinColumn(name="ID_TABELA_HIST")
	private TabelaHistorico tabelaHistorico;

	public HistoricoTabela() {
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

	public BigInteger getIdChaveTabela() {
		return this.idChaveTabela;
	}

	public void setIdChaveTabela(BigInteger idChaveTabela) {
		this.idChaveTabela = idChaveTabela;
	}

	public BigInteger getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeColuna() {
		return this.nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}

	public String getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorAtual() {
		return this.valorAtual;
	}

	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}

	public TabelaHistorico getTabelaHistorico() {
		return this.tabelaHistorico;
	}

	public void setTabelaHistorico(TabelaHistorico tabelaHistorico) {
		this.tabelaHistorico = tabelaHistorico;
	}

}