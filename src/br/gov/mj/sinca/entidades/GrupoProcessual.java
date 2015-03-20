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
 * The persistent class for the grupo_processual database table.
 * 
 */
@Entity
@Table(name = "grupo_processual")
@NamedQuery(name = "GrupoProcessual.findAll", query = "SELECT g FROM GrupoProcessual g")
public class GrupoProcessual implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO_PROCESSUAL")
    private Integer idGrupoProcessual;

    @Column(name = "DESC_GRUPO_PROCESSUAL")
    private String descGrupoProcessual;

    public GrupoProcessual() {
    }

    public GrupoProcessual(Integer idGrupoProcessual) {
	super();
	this.idGrupoProcessual = idGrupoProcessual;
    }

    public GrupoProcessual(String descGrupoProcessual) {
	super();
	this.descGrupoProcessual = descGrupoProcessual;
    }

    public Integer getIdGrupoProcessual() {
	return this.idGrupoProcessual;
    }

    public void setIdGrupoProcessual(Integer idGrupoProcessual) {
	this.idGrupoProcessual = idGrupoProcessual;
    }

    public String getDescGrupoProcessual() {
	return this.descGrupoProcessual;
    }

    public void setDescGrupoProcessual(String descGrupoProcessual) {
	this.descGrupoProcessual = descGrupoProcessual;
    }

}