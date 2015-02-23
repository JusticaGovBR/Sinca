package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_restituicao_direito database table.
 * 
 */
@Entity
@Table(name="tipo_restituicao_direito")
@NamedQuery(name="TipoRestituicaoDireito.findAll", query="SELECT t FROM TipoRestituicaoDireito t")
public class TipoRestituicaoDireito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO_RESTITUICAO")
	private int codTipoRestituicao;

	private String descricao;

	//bi-directional many-to-one association to ReparacaoDireitoAnalise
	@OneToMany(mappedBy="tipoRestituicaoDireito")
	private List<ReparacaoDireitoAnalise> reparacaoDireitoAnalises;

	//bi-directional many-to-one association to ReparacaoDireitoJulgamento
	@OneToMany(mappedBy="tipoRestituicaoDireito")
	private List<ReparacaoDireitoJulgamento> reparacaoDireitoJulgamentos;

	public TipoRestituicaoDireito() {
	}

	public int getCodTipoRestituicao() {
		return this.codTipoRestituicao;
	}

	public void setCodTipoRestituicao(int codTipoRestituicao) {
		this.codTipoRestituicao = codTipoRestituicao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ReparacaoDireitoAnalise> getReparacaoDireitoAnalises() {
		return this.reparacaoDireitoAnalises;
	}

	public void setReparacaoDireitoAnalises(List<ReparacaoDireitoAnalise> reparacaoDireitoAnalises) {
		this.reparacaoDireitoAnalises = reparacaoDireitoAnalises;
	}

	public ReparacaoDireitoAnalise addReparacaoDireitoAnalis(ReparacaoDireitoAnalise reparacaoDireitoAnalis) {
		getReparacaoDireitoAnalises().add(reparacaoDireitoAnalis);
		reparacaoDireitoAnalis.setTipoRestituicaoDireito(this);

		return reparacaoDireitoAnalis;
	}

	public ReparacaoDireitoAnalise removeReparacaoDireitoAnalis(ReparacaoDireitoAnalise reparacaoDireitoAnalis) {
		getReparacaoDireitoAnalises().remove(reparacaoDireitoAnalis);
		reparacaoDireitoAnalis.setTipoRestituicaoDireito(null);

		return reparacaoDireitoAnalis;
	}

	public List<ReparacaoDireitoJulgamento> getReparacaoDireitoJulgamentos() {
		return this.reparacaoDireitoJulgamentos;
	}

	public void setReparacaoDireitoJulgamentos(List<ReparacaoDireitoJulgamento> reparacaoDireitoJulgamentos) {
		this.reparacaoDireitoJulgamentos = reparacaoDireitoJulgamentos;
	}

	public ReparacaoDireitoJulgamento addReparacaoDireitoJulgamento(ReparacaoDireitoJulgamento reparacaoDireitoJulgamento) {
		getReparacaoDireitoJulgamentos().add(reparacaoDireitoJulgamento);
		reparacaoDireitoJulgamento.setTipoRestituicaoDireito(this);

		return reparacaoDireitoJulgamento;
	}

	public ReparacaoDireitoJulgamento removeReparacaoDireitoJulgamento(ReparacaoDireitoJulgamento reparacaoDireitoJulgamento) {
		getReparacaoDireitoJulgamentos().remove(reparacaoDireitoJulgamento);
		reparacaoDireitoJulgamento.setTipoRestituicaoDireito(null);

		return reparacaoDireitoJulgamento;
	}

}