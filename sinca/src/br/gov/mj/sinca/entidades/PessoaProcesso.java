package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pessoa_processo database table.
 * 
 */
@Entity
@Table(name="pessoa_processo")
@NamedQuery(name="PessoaProcesso.findAll", query="SELECT p FROM PessoaProcesso p")
public class PessoaProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA_PROCESSO")
	private Long idPessoaProcesso;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private PessoaFisica pessoa;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;

	//bi-directional many-to-one association to TipoPessoaProcesso
	@ManyToOne
	@JoinColumn(name="ID_TIPO_PESSOA_PROCESSO")
	private TipoPessoaProcesso tipoPessoaProcesso;

	public PessoaProcesso() {
	}

	public Long getIdPessoaProcesso() {
		return this.idPessoaProcesso;
	}

	public void setIdPessoaProcesso(Long idPessoaProcesso) {
		this.idPessoaProcesso = idPessoaProcesso;
	}

	public PessoaFisica getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public TipoPessoaProcesso getTipoPessoaProcesso() {
		return this.tipoPessoaProcesso;
	}

	public void setTipoPessoaProcesso(TipoPessoaProcesso tipoPessoaProcesso) {
		this.tipoPessoaProcesso = tipoPessoaProcesso;
	}

}