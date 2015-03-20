package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the localizacao database table.
 * 
 */
@Entity
@NamedQuery(name="Localizacao.findAll", query="SELECT l FROM Localizacao l")
public class Localizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_LOCALIZACAO")
	private Integer idLocalizacao;

	@Column(name="DESC_LOCALIZACAO")
	private String descLocalizacao;

	private String sigla;

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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}