package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO")
	private int codTipo;

	private String descricao;

	//bi-directional many-to-one association to RecomendacaoAnalise
	@OneToMany(mappedBy="tipoAnaliseJulgamento")
	private List<RecomendacaoAnalise> recomendacaoAnalises;

	public TipoAnaliseJulgamento() {
	}

	public int getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<RecomendacaoAnalise> getRecomendacaoAnalises() {
		return this.recomendacaoAnalises;
	}

	public void setRecomendacaoAnalises(List<RecomendacaoAnalise> recomendacaoAnalises) {
		this.recomendacaoAnalises = recomendacaoAnalises;
	}

	public RecomendacaoAnalise addRecomendacaoAnalis(RecomendacaoAnalise recomendacaoAnalis) {
		getRecomendacaoAnalises().add(recomendacaoAnalis);
		recomendacaoAnalis.setTipoAnaliseJulgamento(this);

		return recomendacaoAnalis;
	}

	public RecomendacaoAnalise removeRecomendacaoAnalis(RecomendacaoAnalise recomendacaoAnalis) {
		getRecomendacaoAnalises().remove(recomendacaoAnalis);
		recomendacaoAnalis.setTipoAnaliseJulgamento(null);

		return recomendacaoAnalis;
	}

}