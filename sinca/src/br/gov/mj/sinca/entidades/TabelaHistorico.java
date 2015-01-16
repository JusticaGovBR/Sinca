package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tabela_historico database table.
 * 
 */
@Entity
@Table(name="tabela_historico")
@NamedQuery(name="TabelaHistorico.findAll", query="SELECT t FROM TabelaHistorico t")
public class TabelaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TABELA_HIST")
	private Integer idTabelaHist;

	@Column(name="MENSAGEM_ALTERACAO")
	private String mensagemAlteracao;

	@Column(name="NOME_COLUNA")
	private String nomeColuna;

	@Column(name="NOME_FORMULARIO")
	private String nomeFormulario;

	@Column(name="NOME_TABELA")
	private String nomeTabela;

	//bi-directional many-to-one association to HistoricoTabela
	@OneToMany(mappedBy="tabelaHistorico")
	private List<HistoricoTabela> historicoTabelas;

	public TabelaHistorico() {
	}

	public Integer getIdTabelaHist() {
		return this.idTabelaHist;
	}

	public void setIdTabelaHist(Integer idTabelaHist) {
		this.idTabelaHist = idTabelaHist;
	}

	public String getMensagemAlteracao() {
		return this.mensagemAlteracao;
	}

	public void setMensagemAlteracao(String mensagemAlteracao) {
		this.mensagemAlteracao = mensagemAlteracao;
	}

	public String getNomeColuna() {
		return this.nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}

	public String getNomeFormulario() {
		return this.nomeFormulario;
	}

	public void setNomeFormulario(String nomeFormulario) {
		this.nomeFormulario = nomeFormulario;
	}

	public String getNomeTabela() {
		return this.nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public List<HistoricoTabela> getHistoricoTabelas() {
		return this.historicoTabelas;
	}

	public void setHistoricoTabelas(List<HistoricoTabela> historicoTabelas) {
		this.historicoTabelas = historicoTabelas;
	}

	public HistoricoTabela addHistoricoTabela(HistoricoTabela historicoTabela) {
		getHistoricoTabelas().add(historicoTabela);
		historicoTabela.setTabelaHistorico(this);

		return historicoTabela;
	}

	public HistoricoTabela removeHistoricoTabela(HistoricoTabela historicoTabela) {
		getHistoricoTabelas().remove(historicoTabela);
		historicoTabela.setTabelaHistorico(null);

		return historicoTabela;
	}

}