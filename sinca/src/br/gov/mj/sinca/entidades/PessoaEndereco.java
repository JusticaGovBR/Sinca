package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


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
	@Column(name="ID_PESSOA_ENDERECO")
	private Long idPessoaEndereco;

	//bi-directional many-to-one association to Endereco
	@ManyToOne
	@JoinColumn(name="ID_ENDERECO")
	private Endereco endereco;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	public PessoaEndereco() {
	}

	public Long getIdPessoaEndereco() {
		return this.idPessoaEndereco;
	}

	public void setIdPessoaEndereco(Long idPessoaEndereco) {
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