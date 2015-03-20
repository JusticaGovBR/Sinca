package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the diligencia database table.
 * 
 */
@Entity
@NamedQuery(name="Diligencia.findAll", query="SELECT d FROM Diligencia d")
public class Diligencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DILIGENCIA")
	private Long idDiligencia;

	@ManyToOne
	@JoinColumn(name="ID_PESSOA_JURIDICA")
	private PessoaJuridica pessoaJuridica;
	
	@ManyToOne
	@JoinColumn(name="ID_PROCESSO")
	private Processo processo;
	
	private byte bolresultado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO")
	private Date dataEnvio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RETORNO")
	private Date dataRetorno;

	@Column(name="ID_USUARIO")
	private Integer idUsuario;

	private String observacao;
	
	@OneToMany(mappedBy="diligencia",cascade = CascadeType.ALL)
	private List<DiligenciaPessoa> listaDiligenciaPessoa;

	public Diligencia() {
	}

	public Long getIdDiligencia() {
		return this.idDiligencia;
	}

	public void setIdDiligencia(Long idDiligencia) {
		this.idDiligencia = idDiligencia;
	}

	public byte getBolresultado() {
		return this.bolresultado;
	}

	public void setBolresultado(byte bolresultado) {
		this.bolresultado = bolresultado;
	}

	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataRetorno() {
		return this.dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PessoaJuridica getPessoaJuridica() {
	    return pessoaJuridica;
	}

	public Processo getProcesso() {
	    return processo;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
	    this.pessoaJuridica = pessoaJuridica;
	}

	public void setProcesso(Processo processo) {
	    this.processo = processo;
	}

	public List<DiligenciaPessoa> getListaDiligenciaPessoa() {
	    return listaDiligenciaPessoa;
	}

	public void setListaDiligenciaPessoa(List<DiligenciaPessoa> listaDiligenciaPessoa) {
	    this.listaDiligenciaPessoa = listaDiligenciaPessoa;
	}

	@Override
	public String toString() {
	    return "Diligencia [idDiligencia=" + idDiligencia + ", pessoaJuridica=" + pessoaJuridica + ", processo="
		    + processo + ", bolresultado=" + bolresultado + ", dataAtualizacao=" + dataAtualizacao
		    + ", dataCadastro=" + dataCadastro + ", dataEnvio=" + dataEnvio + ", dataRetorno=" + dataRetorno
		    + ", idUsuario=" + idUsuario + ", observacao=" + observacao + "]";
	}



}