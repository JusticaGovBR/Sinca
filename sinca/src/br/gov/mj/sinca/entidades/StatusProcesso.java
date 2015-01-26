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
 * The persistent class for the status_processo database table.
 * 
 */
@Entity
@Table(name="status_processo")
@NamedQuery(name="StatusProcesso.findAll", query="SELECT s FROM StatusProcesso s")
public class StatusProcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_STATUS_PROCESSO")
	private Integer idStatusProcesso;

	@Column(name="DESC_STATUS_PROCESSO")
	private String descStatusProcesso;

        public StatusProcesso() {
	}

	public Integer getIdStatusProcesso() {
		return this.idStatusProcesso;
	}

	public void setIdStatusProcesso(Integer idStatusProcesso) {
		this.idStatusProcesso = idStatusProcesso;
	}

	public String getDescStatusProcesso() {
		return this.descStatusProcesso;
	}

	public void setDescStatusProcesso(String descStatusProcesso) {
		this.descStatusProcesso = descStatusProcesso;
	}

}