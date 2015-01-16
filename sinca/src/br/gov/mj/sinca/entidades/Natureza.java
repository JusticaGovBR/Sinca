package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the natureza database table.
 * 
 */
@Entity
@Table(name="natureza")
@NamedQuery(name="Natureza.findAll", query="SELECT n FROM Natureza n")
public class Natureza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_NATUREZA", unique=true, nullable=false)
	private Integer idNatureza;

	@Column(name="DESC_NATUREZA", length=500)
	private String descNatureza;

	//bi-directional many-to-one association to DadosAdicionais
	@OneToMany(mappedBy="natureza")
	private List<DadosAdicionais> dadosAdicionais;

	public Natureza() {
	}

	public Integer getIdNatureza() {
		return this.idNatureza;
	}

	public void setIdNatureza(Integer idNatureza) {
		this.idNatureza = idNatureza;
	}

	public String getDescNatureza() {
		return this.descNatureza;
	}

	public void setDescNatureza(String descNatureza) {
		this.descNatureza = descNatureza;
	}

	public List<DadosAdicionais> getDadosAdicionais() {
		return this.dadosAdicionais;
	}

	public void setDadosAdicionais(List<DadosAdicionais> dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public DadosAdicionais addDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().add(dadosAdicionai);
		dadosAdicionai.setNatureza(this);

		return dadosAdicionai;
	}

	public DadosAdicionais removeDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().remove(dadosAdicionai);
		dadosAdicionai.setNatureza(null);

		return dadosAdicionai;
	}

}