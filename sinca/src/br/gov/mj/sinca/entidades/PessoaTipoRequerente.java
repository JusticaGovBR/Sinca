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
 * The persistent class for the pessoa_tipo_requerente database table.
 * 
 */
@Entity
@Table(name="pessoa_tipo_requerente")
@NamedQuery(name="PessoaTipoRequerente.findAll", query="SELECT p FROM PessoaTipoRequerente p")
public class PessoaTipoRequerente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA_TIPO_REQUENTE")
	private Long idPessoaTipoRequente;

	@Column(name="ID_PESSOA")
	private Pessoa pessoa;

	@Column(name="ID_TIPO_REQUERENTE")
	private Integer idTipoRequerente;

	public PessoaTipoRequerente() {
	}

	public Long getIdPessoaTipoRequente() {
	    return idPessoaTipoRequente;
	}

	public void setIdPessoaTipoRequente(Long idPessoaTipoRequente) {
	    this.idPessoaTipoRequente = idPessoaTipoRequente;
	}

	public Pessoa getPessoa() {
	    return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
	    this.pessoa = pessoa;
	}

	public Integer getIdTipoRequerente() {
	    return idTipoRequerente;
	}

	public void setIdTipoRequerente(Integer idTipoRequerente) {
	    this.idTipoRequerente = idTipoRequerente;
	}

	
	

	
}