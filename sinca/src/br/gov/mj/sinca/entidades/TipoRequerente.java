package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_requerente database table.
 * 
 */
@Entity
@Table(name="tipo_requerente")
@NamedQuery(name="TipoRequerente.findAll", query="SELECT t FROM TipoRequerente t")
public class TipoRequerente implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="COD_TIPO")
	private Integer codTipo;

	@Column(name="DESC_TIPO", length=100)
	private String descTipo;

	public TipoRequerente() {
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescTipo() {
		return this.descTipo;
	}

	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}

}