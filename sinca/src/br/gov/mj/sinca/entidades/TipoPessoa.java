package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_pessoa database table.
 * 
 */
@Entity
@Table(name="tipo_pessoa")
@NamedQuery(name="TipoPessoa.findAll", query="SELECT t FROM TipoPessoa t")
public class TipoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COD_TIPO")
	private Integer codTipo;

	private String descricao;

	//bi-directional many-to-one association to Pessoa
	@OneToMany(mappedBy="tipoPessoa")
	private List<Pessoa> pessoas;

	public TipoPessoa() {
	}

	public Integer getCodTipo() {
		return this.codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa addPessoa(Pessoa pessoa) {
		getPessoas().add(pessoa);
		pessoa.setTipoPessoa(this);

		return pessoa;
	}

	public Pessoa removePessoa(Pessoa pessoa) {
		getPessoas().remove(pessoa);
		pessoa.setTipoPessoa(null);

		return pessoa;
	}

}