package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tipo_telefone database table.
 * 
 */
@Entity
@Table(name="tipo_telefone")
@NamedQuery(name="TipoTelefone.findAll", query="SELECT t FROM TipoTelefone t")
public class TipoTelefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIPO", unique=true, nullable=false)
	private Integer codTipo;

	@Column(nullable=false, length=200)
	private String descricao;
	
	

	public TipoTelefone(Integer codTipo) {
	    super();
	    this.codTipo = codTipo;
	}

	public TipoTelefone() {
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