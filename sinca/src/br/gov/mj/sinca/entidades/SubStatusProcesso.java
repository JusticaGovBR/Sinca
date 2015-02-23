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
 * The persistent class for the sub_status_processo database table.
 * 
 */
@Entity
@Table(name="sub_status_processo")
@NamedQuery(name="SubStatusProcesso.findAll", query="SELECT s FROM SubStatusProcesso s")
public class SubStatusProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SUB_STATUS_PROCESSO")
	private Integer idSubStatusProcesso;

	@Column(name="DESC_SUB_STATUS_PROCESSO")
	private String descSubStatusProcesso;

	public SubStatusProcesso() {
	}

	public SubStatusProcesso(Integer idSubStatusProcesso2) {
	   this.idSubStatusProcesso = idSubStatusProcesso2;
	}

	public Integer getIdSubStatusProcesso() {
		return this.idSubStatusProcesso;
	}

	public void setIdSubStatusProcesso(Integer idSubStatusProcesso) {
		this.idSubStatusProcesso = idSubStatusProcesso;
	}

	public String getDescSubStatusProcesso() {
		return this.descSubStatusProcesso;
	}

	public void setDescSubStatusProcesso(String descSubStatusProcesso) {
		this.descSubStatusProcesso = descSubStatusProcesso;
	}
	
}