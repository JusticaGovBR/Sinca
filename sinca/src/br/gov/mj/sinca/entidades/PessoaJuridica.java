package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the pessoa_juriduca database table.
 * 
 */
@Entity
@Table(name="pessoa_juridica")
@NamedQuery(name="PessoaJuridica.findAll", query="SELECT p FROM PessoaJuridica p")
public class PessoaJuridica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PESSOA_JURIDICA")
	private Long idPessoaJuridica;

	@Column(name="BOL_DILIGENCIA")
	private byte bolDiligencia;

	@Column(name="DATA_HORA_ATUALIZACAO")
	private Timestamp dataHoraAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_HORA_CADASTRO")
	private Date dataHoraCadastro;

	@Column(name="ID_USUARIO_CADASTRO")
	private int idUsuarioCadastro;

	@Column(name="NOME_FANTASIA")
	private String nomeFantasia;

	@Column(name="NOME_PESSOA")
	private String nomePessoa;

	@Column(name="NUM_CNPJ")
	private String numCnpj;

	@Column(name="NUM_INSC_ESTADUAL")
	private String numInscEstadual;

	private String observacao;

	public PessoaJuridica() {
	}

	public Long getIdPessoaJuridica() {
		return this.idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Long idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public byte getBolDiligencia() {
		return this.bolDiligencia;
	}

	public void setBolDiligencia(byte bolDiligencia) {
		this.bolDiligencia = bolDiligencia;
	}

	public Timestamp getDataHoraAtualizacao() {
		return this.dataHoraAtualizacao;
	}

	public void setDataHoraAtualizacao(Timestamp dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}

	public Date getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public int getIdUsuarioCadastro() {
		return this.idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(int idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomePessoa() {
		return this.nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNumCnpj() {
		return this.numCnpj;
	}

	public void setNumCnpj(String numCnpj) {
		this.numCnpj = numCnpj;
	}

	public String getNumInscEstadual() {
		return this.numInscEstadual;
	}

	public void setNumInscEstadual(String numInscEstadual) {
		this.numInscEstadual = numInscEstadual;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}