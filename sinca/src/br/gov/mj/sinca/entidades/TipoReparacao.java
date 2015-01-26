package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_reparacao database table.
 * 
 */
@Entity
@Table(name="tipo_reparacao")
@NamedQuery(name="TipoReparacao.findAll", query="SELECT t FROM TipoReparacao t")
public class TipoReparacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String descricao;

	public TipoReparacao() {
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