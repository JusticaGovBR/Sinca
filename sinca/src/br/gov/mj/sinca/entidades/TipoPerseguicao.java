package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String descricao;

	//bi-directional many-to-one association to PerseguicaoAnalise
	@OneToMany(mappedBy="tipoPerseguicao")
	private List<PerseguicaoAnalise> perseguicaoAnalises;

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

	public List<PerseguicaoAnalise> getPerseguicaoAnalises() {
		return this.perseguicaoAnalises;
	}

	public void setPerseguicaoAnalises(List<PerseguicaoAnalise> perseguicaoAnalises) {
		this.perseguicaoAnalises = perseguicaoAnalises;
	}

	public PerseguicaoAnalise addPerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().add(perseguicaoAnalis);
		perseguicaoAnalis.setTipoPerseguicao(this);

		return perseguicaoAnalis;
	}

	public PerseguicaoAnalise removePerseguicaoAnalis(PerseguicaoAnalise perseguicaoAnalis) {
		getPerseguicaoAnalises().remove(perseguicaoAnalis);
		perseguicaoAnalis.setTipoPerseguicao(null);

		return perseguicaoAnalis;
	}

}