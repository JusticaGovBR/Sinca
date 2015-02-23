package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_julgamento_analise database table.
 * 
 */
@Entity
@Table(name="tipo_julgamento_analise")
@NamedQuery(name="TipoJulgamentoAnalise.findAll", query="SELECT t FROM TipoJulgamentoAnalise t")
public class TipoJulgamentoAnalise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_JULGAMENTO")
	private String idTipoJulgamento;

	private String decricao;

	public TipoJulgamentoAnalise() {
	}

	public String getIdTipoJulgamento() {
		return this.idTipoJulgamento;
	}

	public void setIdTipoJulgamento(String idTipoJulgamento) {
		this.idTipoJulgamento = idTipoJulgamento;
	}

	public String getDecricao() {
		return this.decricao;
	}

	public void setDecricao(String decricao) {
		this.decricao = decricao;
	}

}