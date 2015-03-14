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
 * The persistent class for the tipo_desicao_julgamento database table.
 * 
 */
@Entity
@Table(name="tipo_desicao_julgamento")
@NamedQuery(name="TipoDesicaoJulgamento.findAll", query="SELECT t FROM TipoDesicaoJulgamento t")
public class TipoDesicaoJulgamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO_DESICAO")
	private Integer codTipoDesicao;

	private String decricao;

	public TipoDesicaoJulgamento() {
	}

	public Integer getCodTipoDesicao() {
		return this.codTipoDesicao;
	}

	public void setCodTipoDesicao(Integer codTipoDesicao) {
		this.codTipoDesicao = codTipoDesicao;
	}

	public String getDecricao() {
		return this.decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

}