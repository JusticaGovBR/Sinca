package br.gov.mj.sinca.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


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
	private BigInteger idUsuario;

	private String observacao;

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

	public BigInteger getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}