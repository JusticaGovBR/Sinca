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
	@Column(name="ID_TELEFONE_PESSOA", unique=true, nullable=false)
	private Integer idTelefonePessoa;

	@Column(name="NUM_TELEFONE", length=20)
	private String numTelefone;

	@ManyToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name="COD_TIPO")
	private TipoTelefone tipoTelefone;
	
	@Column(name="OBSERVACAO")
	private String observacao;

	
	public TelefonePessoa() {
	}

	public Integer getIdTelefonePessoa() {
		return this.idTelefonePessoa;
	}

	public void setIdTelefonePessoa(Integer idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
	}

	public TipoTelefone getTipoTelefone() {
	    return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
	    this.tipoTelefone = tipoTelefone;
	}

	public String getNumTelefone() {
		return this.numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getObservacao() {
	    return observacao;
	}

	public void setObservacao(String observacao) {
	    this.observacao = observacao;
	}

}