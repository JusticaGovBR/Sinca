package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(name="endereco")
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	
         private static final long serialVersionUID = 145454545454564564L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ENDERECO", unique=true, nullable=false)
	private Integer idEndereco;

	@Column(length=200)
	private String bairro;

	@Column(length=30)
	private String cep;

	@Column(length=200)
	private String cidade;

	@Column(name="COMPLEMENTO")
	private String complemento;

	@Column(name="LOGRADOURO")
	private String logradouro;

	@Column(length=20)
	private String numero;

	@Column(length=50)
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
	    return logradouro;
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