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
 * The persistent class for the sub_grupo_social database table.
 * 
 */
@Entity
@Table(name="sub_grupo_social")
@NamedQuery(name="SubGrupoSocial.findAll", query="SELECT s FROM SubGrupoSocial s")
public class SubGrupoSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUB_GRUPO_SOCIAL")
	private Integer idSubGrupoSocial;

	@Column(name="DESC_SUB_GRUPO_SOCIAL")
	private String descSubGrupoSocial;

	public SubGrupoSocial() {
	}

	public Integer getIdSubGrupoSocial() {
		return this.idSubGrupoSocial;
	}

	public void setIdSubGrupoSocial(Integer idSubGrupoSocial) {
		this.idSubGrupoSocial = idSubGrupoSocial;
	}

	public String getDescSubGrupoSocial() {
		return this.descSubGrupoSocial;
	}

	public void setDescSubGrupoSocial(String descSubGrupoSocial) {
		this.descSubGrupoSocial = descSubGrupoSocial;
	}

	

}