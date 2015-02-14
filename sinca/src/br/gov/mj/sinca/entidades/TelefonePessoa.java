package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefone_pessoa database table.
 * 
 */
@Entity
@Table(name="telefone_pessoa")
@NamedQuery(name="TelefonePessoa.findAll", query="SELECT t FROM TelefonePessoa t")
public class TelefonePessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TELEFONE_PESSOA")
	private Integer idTelefonePessoa;

	@Column(name="NUM_TELEFONE")
	private String numTelefone;

	private String observacao;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA_FISICA")
	private PessoaFisica pessoa;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="ID_PESSOA_JURIDICA")
	private PessoaJuridica pessoaJuridica;

	
	//bi-directional many-to-one association to TipoTelefone
	@ManyToOne
	@JoinColumn(name="COD_TIPO")
	private TipoTelefone tipoTelefone;

	public TelefonePessoa() {
	}

	public Integer getIdTelefonePessoa() {
		return this.idTelefonePessoa;
	}

	public void setIdTelefonePessoa(Integer idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
	}

	public String getNumTelefone() {
		return this.numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PessoaFisica getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public TipoTelefone getTipoTelefone() {
		return this.tipoTelefone;
	}

	public PessoaJuridica getPessoaJuridica() {
	    return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
	    this.pessoaJuridica = pessoaJuridica;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}