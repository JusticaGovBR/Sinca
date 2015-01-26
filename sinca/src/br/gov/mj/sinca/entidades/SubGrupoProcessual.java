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
 * The persistent class for the sub_grupo_processual database table.
 * 
 */
@Entity
@Table(name = "sub_grupo_processual")
@NamedQuery(name = "SubGrupoProcessual.findAll", query = "SELECT s FROM SubGrupoProcessual s")
public class SubGrupoProcessual implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUB_GRUPO_PROCESSUAL")
    private Integer idSubGrupoProcessual;

    @Column(name = "DESC_SUB_GRUPO_PROCESSUAL")
    private String descSubGrupoProcessual;

    public SubGrupoProcessual() {
    }

    public SubGrupoProcessual(String descSubGrupoProcessual) {
	this.descSubGrupoProcessual = descSubGrupoProcessual;
    }

    public Integer getIdSubGrupoProcessual() {
	return this.idSubGrupoProcessual;
    }

    public void setIdSubGrupoProcessual(Integer idSubGrupoProcessual) {
	this.idSubGrupoProcessual = idSubGrupoProcessual;
    }

    public String getDescSubGrupoProcessual() {
	return this.descSubGrupoProcessual;
    }

    public void setDescSubGrupoProcessual(String descSubGrupoProcessual) {
	this.descSubGrupoProcessual = descSubGrupoProcessual;
    }

}