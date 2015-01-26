package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_analise_julgamento database table.
 * 
 */
@Entity
@Table(name="tipo_analise_julgamento")
@NamedQuery(name="TipoAnaliseJulgamento.findAll", query="SELECT t FROM TipoAnaliseJulgamento t")
public class TipoAnaliseJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String decscricao;

	public TipoAnaliseJulgamento() {
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDecscricao() {
		return this.decscricao;
	}

	public void setDecscricao(String decscricao) {
		this.decscricao = decscricao;
	}

}