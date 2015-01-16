package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the tipo_procurador database table.
 * 
 */
@Entity
@Table(name="tipo_procurador")
@NamedQuery(name="TipoProcurador.findAll", query="SELECT t FROM TipoProcurador t")
public class TipoProcurador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIPO")
	private Integer codTipo;

	@Column(name="DESC_TIPO", length=100)
	private String descTipo;

	public TipoProcurador() {
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescTipo() {
		return this.descTipo;
	}

	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}

}