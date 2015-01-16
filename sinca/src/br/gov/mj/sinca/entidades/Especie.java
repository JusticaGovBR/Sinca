package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especie database table.
 * 
 */
@Entity
@Table(name="especie")
@NamedQuery(name="Especie.findAll", query="SELECT e FROM Especie e")
public class Especie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESPECIE", unique=true, nullable=false)
	private Integer idEspecie;

	@Column(name="DESC_ESPECIE", length=500)
	private String descEspecie;

	//bi-directional many-to-one association to DadosAdicionais
	@OneToMany(mappedBy="especie")
	private List<DadosAdicionais> dadosAdicionais;

	public Especie() {
	}

	public Integer getIdEspecie() {
		return this.idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getDescEspecie() {
		return this.descEspecie;
	}

	public void setDescEspecie(String descEspecie) {
		this.descEspecie = descEspecie;
	}

	public List<DadosAdicionais> getDadosAdicionais() {
		return this.dadosAdicionais;
	}

	public void setDadosAdicionais(List<DadosAdicionais> dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public DadosAdicionais addDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().add(dadosAdicionai);
		dadosAdicionai.setEspecie(this);

		return dadosAdicionai;
	}

	public DadosAdicionais removeDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().remove(dadosAdicionai);
		dadosAdicionai.setEspecie(null);

		return dadosAdicionai;
	}

}