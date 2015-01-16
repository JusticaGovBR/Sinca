package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the assunto database table.
 * 
 */
@Entity
@Table(name="assunto")
@NamedQuery(name="Assunto.findAll", query="SELECT a FROM Assunto a")
public class Assunto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ASSUNTO")
	private Integer idAssunto;

	@Column(name="DESC_ASSUNTO")
	private String descAssunto;

	@OneToMany(mappedBy="assunto")
	private List<DadosAdicionais> dadosAdicionais;

	public Assunto() {
	}

	public Integer getIdAssunto() {
		return this.idAssunto;
	}

	public void setIdAssunto(Integer idAssunto) {
		this.idAssunto = idAssunto;
	}

	public String getDescAssunto() {
		return this.descAssunto;
	}

	public void setDescAssunto(String descAssunto) {
		this.descAssunto = descAssunto;
	}

	public List<DadosAdicionais> getDadosAdicionais() {
		return this.dadosAdicionais;
	}

	public void setDadosAdicionais(List<DadosAdicionais> dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	public DadosAdicionais addDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().add(dadosAdicionai);
		dadosAdicionai.setAssunto(this);

		return dadosAdicionai;
	}

	public DadosAdicionais removeDadosAdicionai(DadosAdicionais dadosAdicionai) {
		getDadosAdicionais().remove(dadosAdicionai);
		dadosAdicionai.setAssunto(null);

		return dadosAdicionai;
	}

}