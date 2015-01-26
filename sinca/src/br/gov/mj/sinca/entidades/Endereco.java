package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ENDERECO")
	private Integer idEndereco;

	private String bairro;

	private String cep;

	private String cidade;

	private String complemento;

	private String logradouro;

	private String numero;

	private String uf;

	//bi-directional many-to-one association to TipoEndereco
	@ManyToOne
	@JoinColumn(name="ID_TIPO_ENDERECO")
	private TipoEndereco tipoEndereco;

	//bi-directional many-to-one association to PessoaEndereco
	@OneToMany(mappedBy="endereco")
	private List<PessoaEndereco> pessoaEnderecos;

	public Endereco() {
	}

	public Integer getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public TipoEndereco getTipoEndereco() {
		return this.tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public List<PessoaEndereco> getPessoaEnderecos() {
		return this.pessoaEnderecos;
	}

	public void setPessoaEnderecos(List<PessoaEndereco> pessoaEnderecos) {
		this.pessoaEnderecos = pessoaEnderecos;
	}

	public PessoaEndereco addPessoaEndereco(PessoaEndereco pessoaEndereco) {
		getPessoaEnderecos().add(pessoaEndereco);
		pessoaEndereco.setEndereco(this);

		return pessoaEndereco;
	}

	public PessoaEndereco removePessoaEndereco(PessoaEndereco pessoaEndereco) {
		getPessoaEnderecos().remove(pessoaEndereco);
		pessoaEndereco.setEndereco(null);

		return pessoaEndereco;
	}

}