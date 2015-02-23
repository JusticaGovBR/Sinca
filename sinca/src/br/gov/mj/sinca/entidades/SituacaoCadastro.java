package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the situacao_cadastro database table.
 * 
 */
@Entity
@Table(name="situacao_cadastro")
@NamedQuery(name="SituacaoCadastro.findAll", query="SELECT s FROM SituacaoCadastro s")
public class SituacaoCadastro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_SITUACAO_CADASTRO")
	private Integer codSituacaoCadastro;

	private String descricao;

	public SituacaoCadastro() {
	}

	public SituacaoCadastro(Integer codSituacao ) {
	    this.codSituacaoCadastro = codSituacao;
	}

	public Integer getCodSituacaoCadastro() {
		return this.codSituacaoCadastro;
	}

	public void setCodSituacaoCadastro(Integer codSituacaoCadastro) {
		this.codSituacaoCadastro = codSituacaoCadastro;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}