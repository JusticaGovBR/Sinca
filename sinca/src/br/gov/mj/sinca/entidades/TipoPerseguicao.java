package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tipo_perseguicao database table.
 * 
 */
@Entity
@Table(name="tipo_perseguicao")
@NamedQuery(name="TipoPerseguicao.findAll", query="SELECT t FROM TipoPerseguicao t")
public class TipoPerseguicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String descricao;

	public TipoPerseguicao() {
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}