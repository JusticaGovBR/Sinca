package br.gov.mj.sinca.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the pessoa_endereco database table.
 * 
 */
@Entity
@Table(name="pessoa_endereco")
@NamedQuery(name="PessoaEndereco.findAll", query="SELECT p FROM PessoaEndereco p")
public class PessoaEndereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA_ENDERECO", unique=true, nullable=false)
	private Integer idPessoaEndereco;

	//bi-directional many-to-one association to Endereco
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_ENDERECO")
	private Endereco endereco;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	public PessoaEndereco() {
	}

	public Integer getIdPessoaEndereco() {
	    return idPessoaEndereco;
	}

	public void setIdPessoaEndereco(Integer idPessoaEndereco) {
	    this.idPessoaEndereco = idPessoaEndereco;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}