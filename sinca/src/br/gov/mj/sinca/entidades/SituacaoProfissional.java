package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the situacao_profissional database table.
 * 
 */
@Entity
@Table(name="situacao_profissional")
@NamedQuery(name="SituacaoProfissional.findAll", query="SELECT s FROM SituacaoProfissional s")
public class SituacaoProfissional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_SITUACAO_PROFISSIONAL")
	private Integer codSituacaoProfissional;

	@Column(name="DESC_SITUACAO")
	private String descSituacao;

	public SituacaoProfissional() {
	}

	public Integer getCodSituacaoProfissional() {
		return this.codSituacaoProfissional;
	}

	public void setCodSituacaoProfissional(Integer codSituacaoProfissional) {
		this.codSituacaoProfissional = codSituacaoProfissional;
	}

	public String getDescSituacao() {
		return this.descSituacao;
	}

	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}

}