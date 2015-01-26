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
 * The persistent class for the cargo_funcao database table.
 * 
 */
@Entity
@Table(name="cargo_funcao")
@NamedQuery(name="CargoFuncao.findAll", query="SELECT c FROM CargoFuncao c")
public class CargoFuncao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CARGO_FUNCAO")
	private Integer idCargoFuncao;

	@Column(name="DESC_CARGO_FUNCAO")
	private String descCargoFuncao;

	public CargoFuncao() {
	}

	
	public CargoFuncao(Integer idCargoFuncao) {
	    super();
	    this.idCargoFuncao = idCargoFuncao;
	}


	public Integer getIdCargoFuncao() {
		return this.idCargoFuncao;
	}

	public void setIdCargoFuncao(Integer idCargoFuncao) {
		this.idCargoFuncao = idCargoFuncao;
	}

	public String getDescCargoFuncao() {
		return this.descCargoFuncao;
	}

	public void setDescCargoFuncao(String descCargoFuncao) {
		this.descCargoFuncao = descCargoFuncao;
	}

}