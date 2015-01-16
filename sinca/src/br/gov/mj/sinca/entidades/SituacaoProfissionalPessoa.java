package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the situacao_profissional_pessoa database table.
 * 
 */
@Entity
@Table(name="situacao_profissional_pessoa")
@NamedQuery(name="SituacaoProfissionalPessoa.findAll", query="SELECT s FROM SituacaoProfissionalPessoa s")
public class SituacaoProfissionalPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SITUACAO_PESSOA")
	private Integer idSituacaoPessoa;

	@Column(name="VR_RENDA_MES")
	private BigDecimal vrRendaMes;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	public SituacaoProfissionalPessoa() {
	}

	public Integer getIdSituacaoPessoa() {
		return this.idSituacaoPessoa;
	}

	public void setIdSituacaoPessoa(Integer idSituacaoPessoa) {
		this.idSituacaoPessoa = idSituacaoPessoa;
	}

	public BigDecimal getVrRendaMes() {
		return this.vrRendaMes;
	}

	public void setVrRendaMes(BigDecimal vrRendaMes) {
		this.vrRendaMes = vrRendaMes;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}