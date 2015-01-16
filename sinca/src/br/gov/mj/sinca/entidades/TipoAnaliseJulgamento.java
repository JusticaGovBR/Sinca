package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tipo_endereco database table.
 * 
 */
@Entity
@Table(name="tipo_analise_julgamento")
@NamedQuery(name="TipoAnaliseJulgamento.findAll", query="SELECT t FROM TipoAnaliseJulgamento t")
public class TipoAnaliseJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id

	@Column(name="COD_TIPO", unique=true, nullable=false)
	private Integer codTipo;

	@Column(name="DECSCRICAO")
	private String descricao;

	public Integer getCodTipo() {
	    return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
	    this.codTipo = codTipo;
	}

	public String getDescricao() {
	    return descricao;
	}

	public void setDescricao(String descricao) {
	    this.descricao = descricao;
	}

	

}