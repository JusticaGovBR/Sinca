package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the localizacao database table.
 * 
 */
@Entity
@Table(name="localizacao")
@NamedQuery(name="Localizacao.findAll", query="SELECT l FROM Localizacao l")
public class Localizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LOCALIZACAO", unique=true, nullable=false)
	private Integer idLocalizacao;

	@Column(name="DESC_LOCALIZACAO", length=500)
	private String descLocalizacao;

	public Localizacao() {
	}

	public Integer getIdLocalizacao() {
		return this.idLocalizacao;
	}

	public void setIdLocalizacao(Integer idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
	}

	public String getDescLocalizacao() {
		return this.descLocalizacao;
	}

	public void setDescLocalizacao(String descLocalizacao) {
		this.descLocalizacao = descLocalizacao;
	}

}