package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_processo database table.
 * 
 */
@Entity
@Table(name="tipo_processo")
@NamedQuery(name="TipoProcesso.findAll", query="SELECT t FROM TipoProcesso t")
public class TipoProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_PROCESSO", unique=true, nullable=false)
	private Integer idTipoProcesso;

	@Column(name="DESC_TIPO_PROCESSO", length=500)
	private String descTipoProcesso;

	public TipoProcesso() {
	}

	public Integer getIdTipoProcesso() {
		return this.idTipoProcesso;
	}

	public void setIdTipoProcesso(Integer idTipoProcesso) {
		this.idTipoProcesso = idTipoProcesso;
	}

	public String getDescTipoProcesso() {
		return this.descTipoProcesso;
	}

	public void setDescTipoProcesso(String descTipoProcesso) {
		this.descTipoProcesso = descTipoProcesso;
	}

}